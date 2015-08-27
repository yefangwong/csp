package org.occ.csp.persistence;

import java.util.List;

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
}
