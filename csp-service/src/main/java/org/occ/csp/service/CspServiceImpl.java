package org.occ.csp.service;

import java.util.Calendar;
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
	CspDao cspDaoImpl;
	
	@Override
	public List<Region> getAllRegions() {
		return cspDaoImpl.findAllRegions();
	}

	@Override
	public List<Fellowship> getFellowshipsByRegion(String regionId) {
		return cspDaoImpl.findFellowshipsByRegion(regionId);
	}

	@Override
	public List<ChurchMember> getChurchMembersByFellowship(String fellowshipId) {
		return cspDaoImpl.findChurchMembersByFellowship(fellowshipId);
	}

	@Override
	public synchronized void saveFootprint(Footprint footprint) {
		System.out.println("saveFootprint :" + footprint.getMemberSid());
		cspDaoImpl.saveFootprint(footprint);
	}

	@Override
	public List<Footprint> getFootprintsByMemberId(String memberId) {
		return cspDaoImpl.findFootprintsByMemberId(memberId);

	}
	
	@Override 
	public int getMaxFootprintId() {
		return cspDaoImpl.findMaxFootprintId();
	}
	
	@Override
	public Fellowship getFellowshipById(String fellowshipId) {
		return cspDaoImpl.findFellowshipById(fellowshipId);
	}
	
	@Override
	public List<Fellowship> getAllFellowships() {
		return cspDaoImpl.findAllFellowships();
	}
	
	@Override
	public ChurchMember getChurchMemberById(String churchMemberId) {
		return cspDaoImpl.findChurchMemberById(churchMemberId);
	}
	
	@Override
	public ChurchMember getChurchMemberByEmail(String email) {
		return cspDaoImpl.findChurchMemberByEmail(email);
	}

	@Override
	public void saveChurchMember(ChurchMember member) throws Exception {
		ChurchMember cm = getChurchMemberByEmail(member.getMemberAccount());
		if (cm == null) {
			cspDaoImpl.saveChurchMember(member);
		} else {
			throw new Exception("account :" + member.getMemberAccount() + " already exists...");
		}
	}

	@Override
	public boolean cardNumExists(String cardNum) {
		String occId = cspDaoImpl.findOccIdFromCardNum(cardNum);
		if (occId == null) 
			return false;
		else
			return true;
	}

	@Override
	public void activateCard(String name, String cardNum) throws Exception {
		// get member sid by name
		try {
			String memberSid = cspDaoImpl.findChurchMemberSidByName(name);
			if (memberSid == null) { // insert into church member data
				ChurchMember member = new ChurchMember();
				member.setMemberName(name);
				member.setCreateUid("admin");
				member.setCreateDate(Calendar.getInstance().getTime());
				cspDaoImpl.saveChurchMember(member);
				memberSid = cspDaoImpl.findChurchMemberSidByName(name);
			}
			// insert activate card info and get occ id
			cspDaoImpl.insertActivatedCardInfo(memberSid, cardNum);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	@Override
	public String getOccId(String cardNum) throws Exception {
		return cspDaoImpl.findOccIdFromCardNum(cardNum);
	}
	
	@Override
	public void deleteCard(String cardNumber) throws Exception {
		cspDaoImpl.deleteCard(cardNumber);
	}

	@Override
	public List<ActivatedCardInfo> getCardInfoByName(String name) throws Exception {
		String memberSid = cspDaoImpl.findChurchMemberSidByName(name);
		return cspDaoImpl.findActicatedCardInfoByMemberSid(memberSid);
	}

	@Override
	public void activeCardWithTheSameName(String OCCId, String name, String cardNumber)
			throws Exception {
		try {
			String memberSid = cspDaoImpl.findChurchMemberSidByName(name);
			// insert activate card info and get occ id
			cspDaoImpl.insertActivatedCardInfo(OCCId, memberSid, cardNumber);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	@Override
	public List<ActivatedCardInfo> getAllActivatedCardList() throws Exception {
		return cspDaoImpl.findAllActivatedCardList();
	}
}
