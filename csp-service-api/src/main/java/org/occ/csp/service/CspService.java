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
	List<Region> getAllRegions();
	List<Fellowship> getFellowshipsByRegion(String regionId);
	List<ChurchMember> getChurchMembersByFellowship(String fellowshipId);
	public void saveFootprint(Footprint footprint);
	public int getMaxFootprintId();
	List<Footprint> getFootprintsByMemberId(String memberId);
	public Fellowship getFellowshipById(String fellowshipId);
	public List<Fellowship> getAllFellowships();
	public ChurchMember getChurchMemberById(String memberId);
	public ChurchMember getChurchMemberByEmail(String email);
	//add by Mark for calling by ocheckin application 2014-09-01
	public void saveChurchMember(ChurchMember churchMember) throws Exception;
	
	/**
	 * 檢查會友卡片是否存在
	 * @param cardNum 卡號
	 * @return true 表示卡片已經存在
	 */
	public boolean cardNumExists(String cardNum);
	
	/**
	 * 開卡
	 * @param name 姓名
	 * @param cardNum 卡號
	 * @return OCCId
	 */
	public String activateCard(String name, String cardNum) throws Exception;
	
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
	public List<ActivatedCardInfo> getCardInfoByName(String name);
	
	/**
	 * 同名開卡
	 * @param OCCId OCC ID
	 * @param name 姓名
	 * @param cardNumber 卡號
	 * @throws Exception
	 */
	public void activeCardWithTheSameName(String OCCId, String name, String cardNumber) throws Exception;
}
