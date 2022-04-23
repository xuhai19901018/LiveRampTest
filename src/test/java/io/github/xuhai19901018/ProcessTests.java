package io.github.xuhai19901018;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.xuhai19901018.abs.ParallelProcess;
import io.github.xuhai19901018.model.TaskLeaf;

class ProcessTests {

	@Test
	void testParallelProcess() throws Exception {
		
		List<TaskLeaf> tasks= new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			tasks.add( new TaskLeaf());
		}

		ParallelProcess<TaskLeaf> pp = new ParallelProcess<TaskLeaf>(tasks, 2);
		
		pp.doing();
		
		System.out.println(pp.getStatus());
		
	}

}
