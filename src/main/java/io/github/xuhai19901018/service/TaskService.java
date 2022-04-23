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
	
	public volatile BaseProcess instanceProcess;
	
	private boolean running = false;
	/***
	 * service初始化
	 */
	@PostConstruct
	private void postConstruct() {

		logger.info("TaskService启动！");

	}

	public void startTaskChain(int[][] vector) throws Exception {
	
		if(running) {
			
			throw new Exception("任务链正在运行，无法启动新任务链！");
		}
		else {
			running=true;
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					List<BaseProcess> stasks= new ArrayList<>();
					for (int i = 0; i < vector.length; i++) {
						List<TaskLeaf> ptasks= new ArrayList<>();
						for (int j = 0; j < vector[i][0]; j++) {
							TaskLeaf task = new TaskLeaf();
							task.setName("节点"+(i+1)+"并行"+(j+1));
							ptasks.add( task);
						}
						ParallelProcess<BaseProcess> taskNode = new ParallelProcess(ptasks, vector[i][1]);
						taskNode.setName("节点"+(i+1));
						stasks.add(taskNode);
					}
					instanceProcess = new SequenceProcess<>(stasks);
					
					try {
						instanceProcess.doing();
					} catch (Exception e) {
						log.error("任务执行异常",e);
					}finally {
						running=false;
					}
				}
			});
			
		}
//		return instanceProcess;
	}
	
	
	public Object getCurrentProgress() throws Exception{
		if(null==instanceProcess) {
			throw new Exception("任务尚未初始化！");
		}
		return instanceProcess.getCurrentProgress();
	}
	
	public Object getStatus() throws Exception{
		if(null==instanceProcess) {
			throw new Exception("任务尚未初始化！");
		}
		return instanceProcess.getStatus();
	}

	@PreDestroy
	private void preDestroy() {
		logger.info("TaskService结束！");
	}

}
