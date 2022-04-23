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
	public boolean doing() throws Exception {

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


}
