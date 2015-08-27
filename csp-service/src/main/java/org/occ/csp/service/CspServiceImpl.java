package org.occ.csp.service;

import java.util.ArrayList;
import java.util.List;

import org.occ.csp.domain.ActivatedCardInfo;
import org.occ.csp.domain.ChurchMember;
import org.occ.csp.domain.Fellowship;
import org.occ.csp.domain.Footprint;
import org.occ.csp.domain.Region;
import org.occ.csp.persistence.CspDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("cspService")
@Transactional(propagation = Propagation.REQUIRED)
public class CspServiceImpl implements CspService {
   public String hello() {
	   return "";
   }
	
	@Autowired
	CspDao cspDao;
	
	@Override
	public List<Region> getAllRegions() {
		return cspDao.findAllRegions();
	}

	@Override
	public List<Fellowship> getFellowshipsByRegion(String regionId) {
		return cspDao.findFellowshipsByRegion(regionId);
	}

	@Override
	public List<ChurchMember> getChurchMembersByFellowship(String fellowshipId) {
		return cspDao.findChurchMembersByFellowship(fellowshipId);
	}

	@Override
	public synchronized void saveFootprint(Footprint footprint) {
		System.out.println("saveFootprint :" + footprint.getMemberSid());
		cspDao.saveFootprint(footprint);
	}

	@Override
	public List<Footprint> getFootprintsByMemberId(String memberId) {
		return cspDao.findFootprintsByMemberId(memberId);

	}
	
	@Override 
	public int getMaxFootprintId() {
		return cspDao.findMaxFootprintId();
	}
	
	@Override
	public Fellowship getFellowshipById(String fellowshipId) {
		return cspDao.findFellowshipById(fellowshipId);
	}
	
	@Override
	public List<Fellowship> getAllFellowships() {
		return cspDao.findAllFellowships();
	}
	
	@Override
	public ChurchMember getChurchMemberById(String churchMemberId) {
		return cspDao.findChurchMemberById(churchMemberId);
	}
	
	@Override
	public ChurchMember getChurchMemberByEmail(String email) {
		return cspDao.findChurchMemberByEmail(email);
	}

	@Override
	public void saveChurchMember(ChurchMember member) throws Exception {
		ChurchMember cm = getChurchMemberByEmail(member.getMemberAccount());
		if (cm == null) {
			cspDao.saveChurchMember(member);
		} else {
			throw new Exception("account :" + member.getMemberAccount() + " already exists...");
		}
	}

	@Override
	public boolean cardNumExists(String cardNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String activateCard(String name, String cardNum) throws Exception {
		// TODO Auto-generated method stub
		//throw new Exception("add card fail");
		return "6091b930-fd63-11e4-b939-0800200c9a66";
	}

	@Override
	public void deleteCard(String cardNumber) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActivatedCardInfo> getCardInfoByName(String name) {
		List<ActivatedCardInfo> resultList = new ArrayList<ActivatedCardInfo>();
		ActivatedCardInfo aci = new ActivatedCardInfo();
		aci.setCardNumber("12344");
		aci.setName("Mark");
		aci.setOccId("6091b930-fd63-11e4-b939-0800200c9a61");
		resultList.add(aci);
		return resultList;
	}

	@Override
	public void activeCardWithTheSameName(String OCCId, String name, String cardNumber)
			throws Exception {
		//throw new Exception("add card fail");
	}
}
