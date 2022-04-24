package io.github.xuhai19901018.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/***
 * 并行任务
 * 
 * @author xuhai
 *
 */
public class ParallelProcess<P extends BaseProcess> extends BatchProcess {

	private int passedThreshold = 0;

	private ExecutorService pool;
	
	private int total;
	private int succeedCount = 0;
	private int failedCount = 0;

	public ParallelProcess(List<P> processList, int passedThreshold) {

		this.passedThreshold = passedThreshold;

		this.processList = (List<Process>) processList;

		total = processList.size();

		pool = Executors.newFixedThreadPool(total);

	}

	@Override
	public synchronized boolean doing() throws Exception {
		
		if(getStatus() == ProcessStatus.Handling) {
			throw new Exception("任务当前正在运行中，请勿重复运行！");
		}
		
		status = ProcessStatus.Handling;

		List<Future<Boolean>> tasks = new ArrayList<>();

		for (Process process : processList) {

			Future<Boolean> future = pool.submit(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return process.doing();
				}

			});
			tasks.add(future);
		}

		// 等待任务全部完成
		for (Future<Boolean> future : tasks) {

			boolean result = (boolean) future.get();

			if (result) {
				succeedCount += 1;
			} else {
				failedCount += 1;
			}
		}

		if (succeedCount >= passedThreshold) {
			status = ProcessStatus.Succeed;
			return true;
		} else {
			status = ProcessStatus.Failed;
			return false;
		}
	}

	@Override
	public synchronized boolean redoing() throws Exception {
		if (getStatus() == ProcessStatus.Failed) {
			status = ProcessStatus.Handling;

			List<Future<Boolean>> tasks = new ArrayList<>();

			for (Process process : processList) {
				// 仅对失败的任务进行重试
				if (process.getStatus() == ProcessStatus.Failed) {
					Future<Boolean> future = pool.submit(new Callable<Boolean>() {

						@Override
						public Boolean call() throws Exception {
							return process.doing();
						}

					});
					tasks.add(future);
				}
			}

			// 等待任务全部完成
			for (Future<Boolean> future : tasks) {

				boolean result = (boolean) future.get();

				if (result) {
					succeedCount += 1;
					failedCount -= 1;
				}
			}

			if (succeedCount >= passedThreshold) {
				status = ProcessStatus.Succeed;
				return true;
			} else {
				status = ProcessStatus.Failed;
				return false;
			}
		} else {
			throw new Exception("此任务当前状态：" + getStatus() + "，无需重试");
		}
	}
}
