package io.github.xuhai19901018.controller;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.xuhai19901018.service.TaskService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("task")
@Slf4j
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@RequestMapping("startTaskChain")
	@ResponseBody
	public Object startTaskChain(@RequestBody int[][] vector) {
		
		try {
			taskService.startTaskChain(vector);
		} catch (Exception e) {
			log.error("启动任务链失败！",e);
			return e.getMessage();
		}
		return taskService.taskChain.getId();
	}
	
	@RequestMapping("retryTaskChain")
	@ResponseBody
	public Object retryTaskChain() {
		
		try {
			taskService.retryTaskChain();
		} catch (Exception e) {
			log.error("重启任务链失败！",e);
			return e.getMessage();
		}
		return taskService.taskChain.getId();
	}
	
}
