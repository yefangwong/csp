package org.occ.csp.persistence;

import java.util.List;

import org.occ.csp.domain.ActivatedCardInfo;
import org.occ.csp.domain.ChurchMember;
import org.occ.csp.domain.Fellowship;
import org.occ.csp.domain.Footprint;
import org.occ.csp.domain.Region;

public interface CspDao {
	public List<Region> findAllRegions();
	public List<Fellowship> findFellowshipsByRegion(String regionId);
	public Fellowship findFellowshipById(String fellowshipId);
	public List<Fellowship> findAllFellowships();
	public List<ChurchMember> findAllChurchMembers();
	public List<ChurchMember> findChurchMembersByFellowship(String fellowshipId);
	public ChurchMember findChurchMemberById(String memberId);
	public ChurchMember findChurchMemberByEmail(String email);
	public void saveChurchMember(ChurchMember member);
	public void saveFootprint(Footprint footprint);
	public int findMaxFootprintId();
	public List<Footprint> findFootprintsByMemberId(String memberId);
	public String findChurchMemberSidByName(String name);
	public void insertActivatedCardInfo(String memberSid, String cardNum);
	public void insertActivatedCardInfo(String OCCId, String memberSid, String cardNumber);
	public String findOccIdFromCardNum(String cardNum);
	public void deleteCard(String cardNumber);
	public List<ActivatedCardInfo> findActicatedCardInfoByMemberSid(String memberSid);
	public List<ActivatedCardInfo> findAllActivatedCardList();
}
