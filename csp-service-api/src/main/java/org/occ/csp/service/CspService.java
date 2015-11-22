package org.occ.csp.service;

import java.util.List;

import org.occ.csp.domain.ActivatedCardInfo;
import org.occ.csp.domain.ChurchMember;
import org.occ.csp.domain.Fellowship;
import org.occ.csp.domain.Footprint;
import org.occ.csp.domain.Region;

/**
 * 
 * @author Mark
 *
 */
public interface CspService {
	public String hello();
	List<Region> getAllRegions() throws Exception;
	List<Fellowship> getFellowshipsByRegion(String regionId) throws Exception;
	List<ChurchMember> getChurchMembersByFellowship(String fellowshipId) throws Exception;
	public void saveFootprint(Footprint footprint) throws Exception;
	public int getMaxFootprintId() throws Exception;
	List<Footprint> getFootprintsByMemberId(String memberId) throws Exception;
	public Fellowship getFellowshipById(String fellowshipId) throws Exception;
	public List<Fellowship> getAllFellowships() throws Exception;
	public ChurchMember getChurchMemberById(String memberId) throws Exception;
	public ChurchMember getChurchMemberByEmail(String email) throws Exception;
	//add by Mark for calling by ocheckin application 2014-09-01
	public void saveChurchMember(ChurchMember churchMember) throws Exception;
	
	/**
	 * 檢查會友卡片是否存在
	 * @param cardNum 卡號
	 * @return true 表示卡片已經存在
	 */
	public boolean cardNumExists(String cardNum) throws Exception;
	
	/**
	 * 開卡
	 * @param name 姓名
	 * @param cardNum 卡號
	 * @return OCCId
	 */
	public void activateCard(String name, String cardNum) throws Exception;
	
	/**
	 * 刪除卡片
	 * @param cardNumber 卡號
	 */
	public void deleteCard(String cardNumber) throws Exception;
	
	/**
	 * 根據姓名取得Tag資訊
	 * @param name 姓名
	 * @return List<ActivatedCardInfo> 會友靠卡資訊
	 */
	public List<ActivatedCardInfo> getCardInfoByName(String name) throws Exception;
	
	/**
	 * 同名開卡
	 * @param OCCId OCC ID
	 * @param name 姓名
	 * @param cardNumber 卡號
	 * @throws Exception
	 */
	public void activeCardWithTheSameName(String OCCId, String name, String cardNumber) throws Exception;
	
	/**
	 * 根據卡號取得 OCC Id
	 * @param cardNum
	 * @return OCC Id
	 * @throws Exception
	 */
	public String getOccId(String cardNum) throws Exception;
	
	/**
	 * 取得所有已經靠卡設定的卡清單
	 * @return
	 */
	public List<ActivatedCardInfo> getAllActivatedCardList() throws Exception;
	
}
