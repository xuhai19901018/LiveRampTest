package io.github.xuhai19901018;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.xuhai19901018.abs.BaseProcess;
import io.github.xuhai19901018.abs.ProcessStatus;
import io.github.xuhai19901018.service.TaskService;

@SpringBootTest
class LiveRampTestApplicationTests {

	
	@Autowired
	private TaskService taskService;
    @Test
    void contextLoads() {
    }
    @Test
    void testTaskService() throws Exception {
    	
    	taskService.startTaskChain(new int[][]{{1,1},{2,1},{3,1},{4,1},{5,1}});
    	
//       	while(null==taskService.taskChain) {
//       		Thread.sleep(10);
//       	}
       	
       	while (taskService.getStatus()==ProcessStatus.NotStarted || taskService.getStatus()==ProcessStatus.Handling) {
//       		System.out.println(taskService.getCurrentProgress().toString());
       		Thread.sleep(10);
		}
//       	System.out.println(taskService.getCurrentProgress().toString());
    }
    
}
