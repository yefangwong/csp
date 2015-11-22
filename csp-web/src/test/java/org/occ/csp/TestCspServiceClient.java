package org.occ.csp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.occ.csp.domain.Fellowship;
import org.occ.csp.service.CspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:httpinvoker-client-madaga-csp.xml"})
public class TestCspServiceClient {
	
	@Autowired
	@Qualifier("cspService")
	private CspService cspService;
	
	@Test
	public void testGetAllFellowships() {
		List<Fellowship> fellowshipList;
		try {
			fellowshipList = cspService.getAllFellowships();
			System.out.println("size:" + fellowshipList.size());			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
