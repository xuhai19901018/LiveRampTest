package io.github.xuhai19901018.abs;

import org.springframework.boot.devtools.autoconfigure.DevToolsProperties.Restart;

/***
 * 
 * @author xuhai
 *
 */
public interface Process {
	
	/***
	 * 获取任务ID
	 * @return
	 */
	String getId();
	
	/***
	 * 获取任务名称
	 * @return
	 */
	String getName();
	
	/**
	 * 查询任务状态
	 * @return
	 */
	ProcessStatus getStatus();
		
	/***
	 * 任务处理过程
	 * @return
	 * @throws Exception
	 */
	boolean doing() throws Exception;
	
	/***
	 * 任务重试
	 * @return 
	 * @throws Exception 
	 */
	boolean retry() throws Exception;
	
	/***
	 * 获取当前任务状态
	 * @return
	 */
	Object getCurrentProgress();
}
