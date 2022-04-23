package io.github.xuhai19901018.abs;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/***
 * @author xuhai
 *
 */
public abstract class BaseProcess implements Process {

	protected String processId= UUID.randomUUID().toString();
	
	protected String processName= "";

	@Override
	public String getName() {
		return processName;
	}
	
	public void setName(String processName) {
		this.processName = processName;
	}

	protected ProcessStatus status= ProcessStatus.NotStarted;
	
	@Override
	public ProcessStatus getStatus() {
		return status;
	}

	@Override
	public String getId() {
		return processId;
	}
//
//	@Override
//	public synchronized boolean doing() throws Exception {
//		if(getStatus() == ProcessStatus.Handling) {
//			throw new Exception("任务当前正在运行中，请勿重复运行！");
//		}
//	}

	@Override
	public synchronized boolean retry() throws Exception {
		
		if(getStatus() == ProcessStatus.Failed) {
			return this.doing();
		}
		else {
			throw new Exception("此任务当前状态："+getStatus()+"，无需重试");
		}		
	}
	
	@Override
	public Map<String, Object> getCurrentProgress() {
		Map<String, Object> current = new HashMap<>();
		current.put("ID", getId());
		current.put("name", getName());
		current.put("status", getStatus());
		return current;
	}

}
