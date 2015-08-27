package org.occ.csp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.occ.csp.domain.ChurchMember;
import org.occ.csp.domain.Fellowship;
import org.occ.csp.domain.Footprint;
import org.occ.csp.domain.Region;
import org.occ.csp.service.CspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:service-context.xml", "classpath:persistence-context.xml",
		"classpath:datasource-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class, 
	TransactionalTestExecutionListener.class})
	
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class OdoormanTest {
	@Autowired
	@Qualifier("cspService")
	private CspService cspService;
	
	@Test
	public void testGetAllRegions() {
		System.out.println("==================================testGetAllRegions=============================================");

		List<Region> regions = cspService.getAllRegions();
		Assert.notEmpty(regions);
		//--------------------------
		System.out.println("region 0 name :" + regions.get(0).getRegionName());

	}
	
	@Test
	public void testGetFellowshipsByRegion() {
		System.out.println("==================================testGetFellowshipsByRegion=============================================");

		List<Fellowship> fellowships = cspService.getFellowshipsByRegion("1");
		Assert.notEmpty(fellowships);
		//--------------------------
		for (int index=0; index<fellowships.size(); index++) {
			System.out.println("fellowship "+index+" id :" + fellowships.get(index).getFellowshipId());
			System.out.println("fellowship "+index+" name :" + fellowships.get(index).getFellowshipName());
			System.out.println("fellowship region id :" + fellowships.get(index).getRegion().getRegionId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("fellowship "+index+" date :" + sdf.format(fellowships.get(index).getCreateDate()));
		}
	}

	@Test
	public void testGetChurchMembersByFellowship() {
		System.out.println("==================================testGetChurchMembersByFellowship=============================================");

		List<ChurchMember> churchMembers = cspService.getChurchMembersByFellowship("2");
		Assert.notEmpty(churchMembers);

		//--------------------------
		for (int index=0; index<churchMembers.size(); index++) {
			System.out.println("church member "+index+" id :" + churchMembers.get(index).getMemberSid());
			System.out.println("church member "+index+" name :" + churchMembers.get(index).getMemberName());
			String fellowshipId = churchMembers.get(index).getResideIn().getFellowshipId();
			Fellowship fellowship = cspService.getFellowshipById(fellowshipId);
			System.out.println("church member fellowship name :" + fellowship.getFellowshipName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("church member "+index+" date :" + sdf.format(churchMembers.get(index).getCreateDate()));
		}
	}
	
	@Test
	public void testSaveFootprint() {
		System.out.println("==================================testSaveFootprint=============================================");

		Footprint f = new Footprint();
		//f.setFootprintId(2);
		f.setMemberSid("1");
		f.setLoginDate(new Date());
		f.setCreateUid("9999");
		f.setCreateDate(new Date());
		cspService.saveFootprint(f);
		
		//--------------------------
		List<Footprint> fList = cspService.getFootprintsByMemberId("1");
		System.out.println("footprint count of member 1:" + fList.size());
//		//All footprint of somebody
		System.out.println("footprint of member 1 ");

		for (int index=0; index<fList.size(); index++) {
			System.out.println("footprint "+index+" member :" + fList.get(index).getMemberSid());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("footprint "+index+" date :" + sdf.format(fList.get(index).getCreateDate()));
		}
	}
}
