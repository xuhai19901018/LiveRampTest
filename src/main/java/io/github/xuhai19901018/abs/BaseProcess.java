package io.github.xuhai19901018.abs;

import java.util.UUID;

/***
 * @author xuhai
 *
 */
public abstract class BaseProcess implements Process {

	protected String processId= UUID.randomUUID().toString();

	protected ProcessStatus status= ProcessStatus.NotStarted;
	
	@Override
	public ProcessStatus getStatus() {
		return status;
	}

	@Override
	public String getId() {
		return processId;
	}
	
	

}
