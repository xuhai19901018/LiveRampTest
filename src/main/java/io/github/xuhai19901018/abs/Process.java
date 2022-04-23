package io.github.xuhai19901018.abs;

import java.util.concurrent.ExecutionException;

/***
 * 
 * @author xuhai
 *
 */
public interface Process {
	
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
	
	
}
