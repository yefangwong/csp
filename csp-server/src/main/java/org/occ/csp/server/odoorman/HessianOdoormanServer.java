package org.occ.csp.server.odoorman;

import java.util.List;

import org.occ.csp.domain.ActivatedCardInfo;
import org.occ.csp.domain.ChurchMember;
import org.occ.csp.domain.Fellowship;
import org.occ.csp.domain.Footprint;
import org.occ.csp.domain.Region;
import org.occ.csp.service.CspService;

import com.caucho.hessian.server.HessianServlet;

public class HessianOdoormanServer extends HessianServlet implements CspService {
	private String greeting = "Hello, this is greeting from csp12!";
	public String hello() {
		return greeting;
	}
	@Override
	public List<Fellowship> getAllFellowships() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Region> getAllRegions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChurchMember getChurchMemberById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChurchMember> getChurchMembersByFellowship(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fellowship getFellowshipById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fellowship> getFellowshipsByRegion(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Footprint> getFootprintsByMemberId(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxFootprintId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveFootprint(Footprint footprint) {
		System.out.println("saveFootprint :" + footprint.getMemberSid());
	}
	@Override
	public void saveChurchMember(ChurchMember churchMember) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ChurchMember getChurchMemberByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean cardNumExists(String cardNum) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String activateCard(String name, String cardNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteCard(String cardNumber) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<ActivatedCardInfo> getCardInfoByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void activeCardWithTheSameName(String OCCId, String name,
			String cardNumber) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
