package io.github.xuhai19901018.abs;

/***
 * @author xuhai
 *
 */
public abstract class BaseProcess implements Process {


	protected ProcessStatus status= ProcessStatus.NotStarted;
	
	public ProcessStatus getStatus() {
		return status;
	}

}
