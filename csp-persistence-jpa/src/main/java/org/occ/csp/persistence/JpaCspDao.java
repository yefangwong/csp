package org.occ.csp.persistence;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.occ.csp.domain.ActivatedCardInfo;
import org.occ.csp.domain.ChurchMember;
import org.occ.csp.domain.Fellowship;
import org.occ.csp.domain.Footprint;
import org.occ.csp.domain.Region;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import base.util.BaseLogger;

//@Repository("cspDao")
@Component
@Transactional
public class JpaCspDao implements CspDao {
    private Logger log = new BaseLogger(getClass());
    
	private static final String ALL_REGIONS = "SELECT r FROM Region r";
	private static final String ALL_FELLOWSHIPS = "SELECT f FROM Fellowship f";
	private static final String ALL_CHURCHMEMBERS = "SELECT c FROM ChurchMember c";
	private static final String ALL_ACTIVATED_CARDS = "SELECT a FROM ActivatedCardInfo a";
	private static final String FELLOWSHIPS_BY_REGIONID = 
		"SELECT f FROM Fellowship f WHERE f.region.regionId = :regionId";
	private static final String CHURCHMEMBERS_BY_FELLOWSHIPID = 
		"SELECT c FROM ChurchMember c WHERE c.resideIn.fellowshipId = :fellowshipId";
	private static final String MEMBERSID_BY_MEMBERNAME = 
		"SELECT c FROM ChurchMember c WHERE c.memberName = :memberName";
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
		"INSERT INTO tbl_footprint (fpt_crt_dte, fpt_crt_uid, cmem_login_dte, cmem_sid) VALUES(?,?,?,?)";
	
	//------- activated card info
	private static final String INSERT_ACTIVATED_CARDINFO = 
			"INSERT INTO tbl_activated_cardinfo (occ_id, cmem_sid, card_num, create_user, create_date) " +
					" VALUES(uuid(),?,?,?,?)";
	private static final String INSERT_ACTIVATED_CARDINFO_WITH_OCCID =
			"INSERT INTO tbl_activated_cardinfo (occ_id, cmem_sid, card_num, create_user, create_date) " +
					" VALUES(?,?,?,?,?)";
	private static final String OCCID_BY_CARDNUM = 
			"SELECT a FROM ActivatedCardInfo a WHERE a.pk.cardNumber = :cardNumber";
	private static final String ACTIVATED_CARD_BY_MEMBER_SID =
			"SELECT a FROM ActivatedCardInfo a WHERE a.churchMember.memberSid = :memberSid";

	private static final String DELETE_ACTIVATED_CARD_INFO_BY_CARDNUM = 
			"DELETE a FROM tbl_activated_cardinfo a WHERE a.card_num = ?";
	
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
		return (List<ChurchMember>)em.createQuery(CHURCHMEMBERS_BY_FELLOWSHIPID).setParameter("fellowshipId", fellowshipId).
		getResultList();
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
		query.setParameter(4, member.getResideIn() == null? null : member.getResideIn().getFellowshipId()); //fellowship id
		query.setParameter(5, member.getMemberLoginDate());            //login date
		query.setParameter(6, member.getCreateUid());
		query.setParameter(7, member.getCreateDate());
		
		query.executeUpdate();
	}
	
	@Override
	public void saveFootprint(Footprint footprint) {
		Query query = em.createNativeQuery(INSERT_FOOTPRINT);
		
		query.setParameter(1, footprint.getCreateDate());
		query.setParameter(2, footprint.getCreateUid());
		query.setParameter(3, footprint.getLoginDate());
		query.setParameter(4, footprint.getMemberSid());
	
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
				"SELECT * FROM tbl_churchmem_mst WHERE cmem_act = '" + email + "'";
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

	@Override
	public String findChurchMemberSidByName(String name) {
		List<ChurchMember> churchMemberList = (List<ChurchMember>)em.createQuery(MEMBERSID_BY_MEMBERNAME).setParameter("memberName", name).
		getResultList();
		if (churchMemberList.isEmpty()) return null;
		return churchMemberList.get(0).getMemberSid();
	}

	@Override
	public void insertActivatedCardInfo(String memberSid, String cardNum) {
		Query query = em.createNativeQuery(INSERT_ACTIVATED_CARDINFO);
		
		query.setParameter(1, memberSid); 			   //account
		query.setParameter(2, cardNum); 			   //pwd
		query.setParameter(3, "admin");
		query.setParameter(4, Calendar.getInstance().getTime());
		
		query.executeUpdate();
	}

	@Override
	public String findOccIdFromCardNum(String cardNum) {
		List<ActivatedCardInfo> aciList = em.createQuery(OCCID_BY_CARDNUM).setParameter("cardNumber", cardNum).getResultList();
		if (aciList.size() > 0) {
			return aciList.get(0).getPk().getOccId();
		}
		return null;
		
	}

	@Override
	public void deleteCard(String cardNumber) {
		Query query = em.createNativeQuery(DELETE_ACTIVATED_CARD_INFO_BY_CARDNUM);
		query.setParameter(1, cardNumber); 			 
		query.executeUpdate();
	}

	@Override
	public List<ActivatedCardInfo> findActicatedCardInfoByMemberSid(String memberSid) {
		return (List<ActivatedCardInfo>)em.createQuery(ACTIVATED_CARD_BY_MEMBER_SID).setParameter("memberSid", memberSid).getResultList();
	}

	@Override
	public void insertActivatedCardInfo(String OCCId, String memberSid,
			String cardNumber) {
		Query query = em.createNativeQuery(INSERT_ACTIVATED_CARDINFO_WITH_OCCID);
		
		log.debug("insert activated cardinfo with the same name {},{},{}", 
				new Object[]{OCCId, memberSid, cardNumber});
		
		query.setParameter(1, OCCId); 			   //OCC Id
		query.setParameter(2, memberSid); 		   //account
		query.setParameter(3, cardNumber); 		   //pwd
		query.setParameter(4, "admin");
		query.setParameter(5, Calendar.getInstance().getTime());
		
		query.executeUpdate();
		
	}

	@Override
	public List<ActivatedCardInfo> findAllActivatedCardList() {
		return em.createQuery(ALL_ACTIVATED_CARDS).getResultList();
	}

}
