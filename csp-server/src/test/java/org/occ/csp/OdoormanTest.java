package org.occ.csp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.occ.csp.domain.ActivatedCardInfo;
import org.occ.csp.domain.ChurchMember;
import org.occ.csp.domain.Fellowship;
import org.occ.csp.domain.Footprint;
import org.occ.csp.domain.Region;
import org.occ.csp.service.CspService;
import org.slf4j.Logger;
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

import base.util.BaseLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:service-context.xml", "classpath:persistence-context.xml",
		"classpath:datasource-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class, 
	TransactionalTestExecutionListener.class})
	
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class OdoormanTest {
	Logger logger = new BaseLogger(getClass());
	@Autowired
	@Qualifier("cspService")
	private CspService cspService;
	
	@Test
	public void testGetAllRegions() {
		logger.debug("==================================testGetAllRegions=============================================");

		List<Region> regions;
		try {
			regions = cspService.getAllRegions();
			Assert.notEmpty(regions);
			//--------------------------
			logger.debug("region 0 name :" + regions.get(0).getRegionName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetFellowshipsByRegion() {
		logger.debug("==================================testGetFellowshipsByRegion=============================================");

		List<Fellowship> fellowships;
		try {
			fellowships = cspService.getFellowshipsByRegion("4");
			Assert.notEmpty(fellowships);
			//--------------------------
			for (int index=0; index<fellowships.size(); index++) {
				logger.debug("fellowship "+index+" id :" + fellowships.get(index).getFellowshipId());
				logger.debug("fellowship "+index+" name :" + fellowships.get(index).getFellowshipName());
				logger.debug("fellowship region id :" + fellowships.get(index).getRegion().getRegionId());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				logger.debug("fellowship "+index+" date :" + sdf.format(fellowships.get(index).getCreateDate()));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void testGetChurchMembersByFellowship() {
		logger.debug("==================================testGetChurchMembersByFellowship=============================================");

		String fellowshipId = "2";
		List<ChurchMember> churchMembers;
		try {
			churchMembers = cspService.getChurchMembersByFellowship(fellowshipId);
			Assert.notEmpty(churchMembers);

			//--------------------------
			for (int index=0; index<churchMembers.size(); index++) {
				logger.debug("church member "+index+" id :" + churchMembers.get(index).getMemberSid());
				logger.debug("church member "+index+" name :" + churchMembers.get(index).getMemberName());
				//String fellowshipId = churchMembers.get(index).getResideIn().getFellowshipId();
				Fellowship fellowship = cspService.getFellowshipById(fellowshipId);
				logger.debug("church member fellowship name :" + fellowship.getFellowshipName());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				logger.debug("church member "+index+" date :" + sdf.format(churchMembers.get(index).getCreateDate()));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveFootprint() {
		logger.debug("==================================testSaveFootprint=============================================");

		Footprint f = new Footprint();
		f.setMemberSid("1");
		f.setLoginDate(new Date());
		f.setCreateUid("9999");
		f.setCreateDate(new Date());
		try {
			cspService.saveFootprint(f);
			
			//--------------------------
			List<Footprint> fList = cspService.getFootprintsByMemberId("1");
			logger.debug("footprint count of member 1:" + fList.size());
//			//All footprint of somebody
			logger.debug("footprint of member 1 ");

			for (int index=0; index<fList.size(); index++) {
				logger.debug("footprint "+index+" member :" + fList.get(index).getMemberSid());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				logger.debug("footprint "+index+" date :" + sdf.format(fList.get(index).getCreateDate()));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCardNumExists() {
		String cardNum = "123333";
		boolean exists;
		try {
			exists = cspService.cardNumExists(cardNum);
			Assert.isTrue(!exists);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testActivateCard() throws Exception {
		logger.debug("==================================testActivateCard=============================================");
		String name = "翁義芳3";
		String cardNum = "12344";
		if (!cspService.cardNumExists(cardNum)) 
			cspService.activateCard(name, cardNum);
		else
			logger.debug("============================card exists============================");
	}
	
	@Test
	public void testGetOccId() {
		logger.debug("=================================GetOccId======================================================");
		String cardNum = "12344";
		String occId;
		try {
			occId = cspService.getOccId(cardNum);
			logger.debug("occId=" + occId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteCard() {
		logger.debug("=================================testDeleteCard======================================================");
		String cardNumber = "12344";
		try {
			cspService.deleteCard(cardNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean b;
		try {
			b = cspService.cardNumExists(cardNumber);
			Assert.isTrue(!b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetCardInfoByName() {
		logger.debug("=================================testGetCardInfoByName======================================================");
		String name = "翁義芳3";
		String cardNum = "12345";
		try {
			if (!cspService.cardNumExists(cardNum))
				try {
					cspService.activateCard(name, cardNum);
				} catch (Exception e) {
					e.printStackTrace();
				}
			else
				logger.debug("============================card exists============================");
			List<ActivatedCardInfo> resultList = new ArrayList<ActivatedCardInfo>();
			resultList = cspService.getCardInfoByName(name);
			//Assert.isTrue(resultList.size() == 1);
			Assert.notNull(resultList.get(0).getPk().getOccId());
			String memSid = resultList.get(0).getChurchMember().getMemberSid();
			ChurchMember cm = cspService.getChurchMemberById(memSid);
			Assert.isTrue(cm.getMemberName().equals(name));
			Assert.isTrue(!resultList.get(0).getPk().getCardNumber().equals(resultList.get(1).getPk().getCardNumber()));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testActiveCardWithTheSameName() {
		logger.debug("=================================testActiveCardWithTheSameName======================================================");
		String OCCId = "319A7FBA-EEC6-44C8-AD77-5F20912A2E92";
		String name = "翁義芳3";
		String cardNumber = getRandomNumber(5);
		try {
			if (!cspService.cardNumExists(cardNumber)) {
				try {
					cspService.activeCardWithTheSameName(OCCId, name, cardNumber);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else
				logger.debug("============================card exists============================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetActivatedCardList() {
		logger.debug("=================================testGetActivatedCardList======================================================");
		List<ActivatedCardInfo> aciList;
		try {
			aciList = cspService.getAllActivatedCardList();
			//assert size
			logger.debug("get activated card list size : {}", aciList.size());
			Assert.notNull(aciList);
			//assert element value
			if (aciList.size() > 0) {
				ActivatedCardInfo ac = (ActivatedCardInfo)aciList.get(0);
				Assert.notNull(ac.getPk().getOccId());
				Assert.notNull(ac.getChurchMember().getMemberName());
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Random rnd = new Random();
	
	private static String getRandomNumber(int digCount) {
	    StringBuilder sb = new StringBuilder(digCount);
	    for(int i=0; i < digCount; i++)
	        sb.append((char)('0' + rnd.nextInt(10)));
	    return sb.toString();
	}

}
