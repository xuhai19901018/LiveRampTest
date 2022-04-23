package io.github.xuhai19901018;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    	
       	taskService.startTaskChain(new int[]{1,2,3,4,5,6,7,8,9});
    	
    }
    
}
