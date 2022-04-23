package io.github.xuhai19901018.abs;

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
