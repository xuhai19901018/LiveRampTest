package io.github.xuhai19901018.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.github.xuhai19901018.abs.BaseProcess;
import io.github.xuhai19901018.abs.ParallelProcess;
import io.github.xuhai19901018.abs.ProcessStatus;
import io.github.xuhai19901018.abs.SequenceProcess;
import io.github.xuhai19901018.model.TaskLeaf;
import lombok.extern.slf4j.Slf4j;

/***
 * 
 * @author xuhai
 *
 */
@Service
@Slf4j
public class TaskService {

	private static Logger logger = LoggerFactory.getLogger(TaskService.class);

	private ExecutorService pool = Executors.newSingleThreadExecutor();

	public volatile BaseProcess taskChain;

//	private boolean running = false;

	/***
	 * service初始化
	 */
	@PostConstruct
	private void postConstruct() {

		logger.info("TaskService启动！");

		new Thread(new Runnable() {

			@Override
			public void run() {

				watching();
			}
		}).start();

	}

	public synchronized void startTaskChain(int[][] vector) throws Exception {

		if (null != taskChain && taskChain.getStatus() != ProcessStatus.Failed && taskChain.getStatus() != ProcessStatus.Succeed) {

			throw new Exception("任务链正在运行，无法启动新任务链！");
		} else {
			List<BaseProcess> stasks = new ArrayList<>();
			for (int i = 0; i < vector.length; i++) {
				List<TaskLeaf> ptasks = new ArrayList<>();
				for (int j = 0; j < vector[i][0]; j++) {
					TaskLeaf task = new TaskLeaf();
					task.setName("节点" + (i + 1) + "并行" + (j + 1));
					ptasks.add(task);
				}
				ParallelProcess<BaseProcess> taskNode = new ParallelProcess(ptasks, vector[i][1]);
				taskNode.setName("节点" + (i + 1));
				stasks.add(taskNode);
				taskChain = new SequenceProcess<>(stasks);
			}
			pool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						taskChain.doing();
					} catch (Exception e) {
						log.error("任务执行异常", e);
					} finally {
						log.info("任务链执行完成，执行结果："+taskChain.getCurrentProgress().toString());
					}
				}
			});
		}
	}

	public synchronized void retryTaskChain() throws Exception {
		if (null == taskChain) {
			throw new Exception("任务尚未初始化！");
		} else if (taskChain.getStatus() == ProcessStatus.Failed) {

			pool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						taskChain.redoing();

					} catch (Exception e) {
						log.error("任务执行异常", e);
					} finally {
						log.info("任务链执行完成，执行结果："+taskChain.getCurrentProgress().toString());
					}
				}
			});

		} else {
			throw new Exception("此任务当前状态：" + taskChain.getStatus() + "，无需重试");
		}
	}

	public Object getCurrentProgress() throws Exception {
		if (null == taskChain) {
			throw new Exception("任务尚未初始化！");
		}
		return taskChain.getCurrentProgress();
	}

	public Object getStatus() throws Exception {
		if (null == taskChain) {
			throw new Exception("任务尚未初始化！");
		}
		return taskChain.getStatus();
	}

//	让你无运行监控日志
	private void watching() {
		while (true) {
			try {
				while (null != taskChain && taskChain.getStatus() != ProcessStatus.Failed && taskChain.getStatus() != ProcessStatus.Succeed) {
					log.debug(taskChain.getCurrentProgress().toString());
					Thread.sleep(100);
				}
				Thread.sleep(100);

			} catch (InterruptedException e) {
				log.error("监控异常", e);
			}

		}
	}

	@PreDestroy
	private void preDestroy() {
		pool.shutdown();

		while (!pool.isTerminated()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}
		logger.info("TaskService结束！");
		
	}

}
