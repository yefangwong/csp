package org.occ.csp.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.occ.csp.domain.ChurchMember;
import org.occ.csp.domain.Fellowship;
import org.occ.csp.domain.Footprint;
import org.occ.csp.domain.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("cspDao")
@Transactional
public class JpaCspDao implements CspDao {
    private Logger log = LoggerFactory.getLogger(JpaCspDao.class);
    
	private static final String ALL_REGIONS = "SELECT r FROM Region r";
	private static final String ALL_FELLOWSHIPS = "SELECT f FROM Fellowship f";
	private static final String ALL_CHURCHMEMBERS = "SELECT c FROM ChurchMember c";
	private static final String FELLOWSHIPS_BY_REGIONID = 
		"SELECT f FROM Fellowship f WHERE f.region.regionId = :regionId";
	private static final String CHURCHMEMBERS_BY_FELLOWSHIPID = 
		"SELECT c FROM ChurchMember c WHERE c.resideIn.fellowshipId = :fellowshipId";
	private static final String FOOTPRINTS_BY_MEMBERID = 
		"SELECT f FROM Footprint f WHERE f.memberSid = :memberId";
	private static final String INSERT_CHURCHMEMBER = 
			"INSERT INTO tbl_churchmem_mst (cmem_act, cmem_pwd, cmem_name, fshp_sid, cmem_login_dte, cmem_crt_uid, cmem_crt_dte) " +
			" VALUES(?,?,?,?,?,?,?)";
//	private static final String MAX_FOOTPRINT = 
//		"SELECT f FROM Footprint f WHERE f.footprintId = (SELECT MAX(f.footprintId) FROM f)";
	private static final String MAX_FOOTPRINT = 
		"SELECT f FROM Footprint f ORDER BY f.footprintId desc";
	
	private static final String INSERT_FOOTPRINT = 
		"INSERT INTO tbl_footprint (fpt_sid, fpt_crt_dte, fpt_crt_uid, cmem_login_dte, cmem_sid) " +
		" VALUES(?,?,?,?,?)";
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Region> findAllRegions() {
		return em.createQuery(ALL_REGIONS).getResultList();
	}

	@Override
	public List<Fellowship> findFellowshipsByRegion(String regionId) {
		return (List<Fellowship>)em.createQuery(FELLOWSHIPS_BY_REGIONID).setParameter("regionId", regionId).
		getResultList();
	}

	@Override
	public List<ChurchMember> findChurchMembersByFellowship(String fellowshipId) {
		//TODO fix bug here
		//return (List<ChurchMember>)em.createQuery(CHURCHMEMBERS_BY_FELLOWSHIPID).setParameter("fellowshipId", fellowshipId).
		//getResultList();
		List<ChurchMember> churchMemberList = new ArrayList<ChurchMember>();
		ChurchMember cm = new ChurchMember();
		cm.setMemberName("周Q任");
		cm.setMemberSid("1");
		churchMemberList.add(cm);

		cm = new ChurchMember();
		cm.setMemberName("翁Q芳");
		cm.setMemberSid("2");
		churchMemberList.add(cm);

		cm = new ChurchMember();
		cm.setMemberName("華Q元");
		cm.setMemberSid("3");
		churchMemberList.add(cm);
		
		cm = new ChurchMember();
		cm.setMemberName("陳Q邦");
		cm.setMemberSid("4");
		churchMemberList.add(cm);

		cm = new ChurchMember();
		cm.setMemberName("黃Q茜");
		cm.setMemberSid("5");
		churchMemberList.add(cm);
	
		cm = new ChurchMember();
		cm.setMemberName("黃Q玲");
		cm.setMemberSid("6");
		churchMemberList.add(cm);

		cm = new ChurchMember();
		cm.setMemberName("謝Q力");
		cm.setMemberSid("7");
		churchMemberList.add(cm);

		cm = new ChurchMember();
		cm.setMemberName("廖Q惠");
		cm.setMemberSid("8");
		churchMemberList.add(cm);

		return churchMemberList;
	}
	
	@Override
	public List<ChurchMember> findAllChurchMembers(){
		return em.createQuery(ALL_CHURCHMEMBERS).getResultList();
	}

	@Override
	public List<Footprint> findFootprintsByMemberId(String memberId) {
		return (List<Footprint>)em.createQuery(FOOTPRINTS_BY_MEMBERID).setParameter("memberId", memberId).
		getResultList();
	}
	
	@Override
	public Fellowship findFellowshipById(String fellowshipId) {
		return em.find(Fellowship.class, fellowshipId);
	}
	
	@Override
	public int findMaxFootprintId() {
		Query query = em.createQuery(MAX_FOOTPRINT);
		List fList = query.getResultList();
		Footprint _footprint;
		int maxId;
		if (fList.size() > 0) {
			_footprint = (Footprint)fList.get(0);
			maxId = _footprint.getFootprintId();
		}
		else maxId = 0;
		return maxId + 1;
	}
	
	@Override
	public void saveChurchMember(ChurchMember member) {
		Query query = em.createNativeQuery(INSERT_CHURCHMEMBER);
		
		log.info(member.toString());
		
		query.setParameter(1, member.getMemberAccount()); 			   //account
		query.setParameter(2, member.getMemberPassword()); 			   //pwd
		query.setParameter(3, member.getMemberName()); 				   //name
		query.setParameter(4, member.getResideIn().getFellowshipId()); //fellowship id
		query.setParameter(5, member.getMemberLoginDate());           //login date
		query.setParameter(6, member.getCreateUid());
		query.setParameter(7, member.getCreateDate());
		
		query.executeUpdate();
	}
	
	@Override
	public void saveFootprint(Footprint footprint) {
		Query query = em.createNativeQuery(INSERT_FOOTPRINT);
		
		query.setParameter(1, footprint.getFootprintId());
		query.setParameter(2, footprint.getCreateDate());
		query.setParameter(3, footprint.getCreateUid());
		query.setParameter(4, footprint.getLoginDate());
		query.setParameter(5, footprint.getMemberSid());
	
		query.executeUpdate();
	}
	
	@Override
	public List<Fellowship> findAllFellowships() {
		return em.createQuery(ALL_FELLOWSHIPS).getResultList();
	}
	
	@Override
	public ChurchMember findChurchMemberByEmail(String email) {
		log.info("find church member=" + email);
		String CHURCHMEMBER_BY_EMAIL = 
				"SELECT * FROM tbl_churchmem_mst WHERE cmem_acct = '" + email + "'";
		//TODO bug
		//fix the it need 2 parameters...
		List<ChurchMember> churchMemberList = em.createNativeQuery(CHURCHMEMBER_BY_EMAIL).getResultList();
		for (ChurchMember m : churchMemberList) {
			if (m.getMemberAccount().equals(email)) {
				return m;
			}
		}
		return null;
	}
	
	@Override
	public ChurchMember findChurchMemberById(String memberId) {
		return em.find(ChurchMember.class, memberId);
	}

}
