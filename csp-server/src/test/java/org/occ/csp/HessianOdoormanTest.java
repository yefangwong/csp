package org.occ.csp;

import java.net.MalformedURLException;
import java.util.Date;

import org.occ.csp.domain.Footprint;
import org.occ.csp.service.CspService;

import com.caucho.hessian.client.HessianProxyFactory;

public class HessianOdoormanTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// String url = "http://localhost:8080/csp-server/csp.do";
		String url = "http://54.251.104.102:8080/csp-server/csp.do";
	        HessianProxyFactory factory = new HessianProxyFactory();
	        try {
	            factory.setDebug(true);
	            factory.setReadTimeout(5000);
	            System.out.println("start 调用");
	            CspService basic = (CspService)factory.create(CspService.class, url);
	            System.out.println("调用结果:"+basic.hello());
	            
	            Footprint fp = new Footprint();
	            fp.setMemberSid("11111");
	            fp.setLoginDate(new Date());
	            basic.saveFootprint(fp);
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }
		System.out.println("test...");

	}

}
