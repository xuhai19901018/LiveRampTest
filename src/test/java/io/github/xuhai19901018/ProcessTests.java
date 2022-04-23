package io.github.xuhai19901018;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.xuhai19901018.abs.BaseProcess;
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

		ParallelProcess<TaskLeaf> pp = new ParallelProcess<TaskLeaf>(tasks, 2);
		
		pp.doing();
		
		System.out.println(pp.getId()+"----"+pp.getStatus());
		
	}
	
	@Test
	void testSequenceProcess() throws Exception {
		
		List<TaskLeaf> tasks= new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			tasks.add( new TaskLeaf());
		}

		SequenceProcess<TaskLeaf> sp = new SequenceProcess<TaskLeaf>(tasks);
		
		sp.doing();
		
		System.out.println(sp.getId()+"----"+sp.getStatus());
		
	}

	@Test
	void testMixProcess() throws Exception {
		
		List<BaseProcess> stasks= new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			
			List<TaskLeaf> ptasks= new ArrayList<>();
			
			for (int j = 0; j < 10; j++) {
				ptasks.add( new TaskLeaf());
			}
			ParallelProcess<BaseProcess> pp = new ParallelProcess(ptasks, 4);
			stasks.add(pp);
		}
		
		SequenceProcess<BaseProcess> sp = new SequenceProcess<>(stasks);
		
		sp.doing();
		
		System.out.println(sp.getId()+"----"+sp.getStatus());
		
	}
	
}
