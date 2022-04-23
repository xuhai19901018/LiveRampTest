package io.github.xuhai19901018;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.xuhai19901018.abs.ParallelProcess;
import io.github.xuhai19901018.abs.SequenceProcess;
import io.github.xuhai19901018.model.TaskLeaf;

class ProcessTests {

	@Test
	void testParallelProcess() throws Exception {
		
		List<TaskLeaf> tasks= new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			tasks.add( new TaskLeaf());
		}

		ParallelProcess<TaskLeaf> p = new ParallelProcess<TaskLeaf>(tasks, 2);
		
		p.doing();
		
		System.out.println(p.getId()+"----"+p.getStatus());
		
	}
	
	@Test
	void testSequenceProcess() throws Exception {
		
		List<TaskLeaf> tasks= new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			tasks.add( new TaskLeaf());
		}

		SequenceProcess<TaskLeaf> p = new SequenceProcess<TaskLeaf>(tasks);
		
		p.doing();
		
		System.out.println(p.getId()+"----"+p.getStatus());
		
	}

}
