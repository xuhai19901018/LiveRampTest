package io.github.xuhai19901018.model;

import java.util.HashMap;
import java.util.Map;

import io.github.xuhai19901018.abs.BaseProcess;
import io.github.xuhai19901018.abs.Process;
import io.github.xuhai19901018.abs.ProcessStatus;

public class TaskLeaf extends BaseProcess{

	/***
	 * 模拟任务运行
	 */
	@Override
	public boolean doing() throws Exception {
		status= ProcessStatus.Handling;
		//模拟任务运行时间
		Thread.sleep(100);
		//假设任务有90%的成功率
		if(Math.random()>0.90) {
			status= ProcessStatus.Failed;
			return false;
		}
		
		else {
			status= ProcessStatus.Succeed;
			return true;
		}
		
	}



}
