package org.occ.csp;

import java.util.List;

import org.occ.csp.domain.ActivatedCardInfo;
import org.occ.csp.service.CspService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CspServiceTestClient {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("remoting-services-invoker.xml");
		CspService cspService = (CspService)context.getBean("cspService");
		List<ActivatedCardInfo> aciList;
		try {
			aciList = cspService.getCardInfoByName("翁藝芳3");
			System.out.println("size:" + aciList.size());			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
