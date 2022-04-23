package io.github.xuhai19901018.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/***
 * 并行任务
 * 
 * @author xuhai
 *
 */
public class ParallelProcess<P extends Process> extends BatchProcess {


	private int passedThreshold = 0;

	private ExecutorService pool;

	public ParallelProcess(List<P> processList, int passedThreshold) {

		this.passedThreshold = passedThreshold;

		this.processList = (List<Process>) processList;

		total = processList.size();

		pool = Executors.newFixedThreadPool(total);

	}

	public boolean doing() throws Exception {

		status= ProcessStatus.Handling;
		
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

		if (succeedCount > passedThreshold) {
			status= ProcessStatus.Succeed;
			return true;
		}
		else {
			status= ProcessStatus.Failed;
			return false;
		}
	}

}
