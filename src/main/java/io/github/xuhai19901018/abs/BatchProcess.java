package io.github.xuhai19901018.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/***
 * @author xuhai
 *
 */
public abstract class BatchProcess extends BaseProcess {
	
	protected List<Process> processList;

	protected int total;
	protected int succeedCount = 0;
	protected int failedCount = 0;

	public ProcessStatus getStatus() {
		return status;
	}

}
