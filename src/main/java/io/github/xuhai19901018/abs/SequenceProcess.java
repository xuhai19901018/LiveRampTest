package io.github.xuhai19901018.abs;

import java.util.ArrayList;
import java.util.List;

/***
 * 顺序任务
 * 
 * @author xuhai
 *
 */
public class SequenceProcess<P extends BaseProcess> extends BatchProcess {

	public SequenceProcess(List<P> processList) {

		this.processList = (List<Process>) processList;

		total = processList.size();

	}

	@Override
	public synchronized boolean doing() throws Exception {
		if(getStatus() == ProcessStatus.Handling) {
			throw new Exception("任务当前正在运行中，请勿重复运行！");
		}
		status= ProcessStatus.Handling;
		
		for (Process process : processList) {

			if(!process.doing()) {
				status= ProcessStatus.Failed;
				return false;
			}
		}
		status= ProcessStatus.Succeed;
		return true;
	}

	@Override
	public synchronized boolean redoing() throws Exception {
		
		if(getStatus() == ProcessStatus.Failed) {
			status= ProcessStatus.Handling;
			
			for (Process process : processList) {
				// 从失败的节点开始
				if (process.getStatus()!= ProcessStatus.Succeed) {
					if(!process.doing()) {
						status= ProcessStatus.Failed;
						return false;
					}
				}
			}
			status= ProcessStatus.Succeed;
			return true;
		}
		else {
			throw new Exception("此任务当前状态："+getStatus()+"，无需重试");
		}	
	}
}
