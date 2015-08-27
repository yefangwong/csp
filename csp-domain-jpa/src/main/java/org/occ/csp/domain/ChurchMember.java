package org.occ.csp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tbl_churchmem_mst")
@XmlRootElement
public class ChurchMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6664148145554719728L;

	private String memberSid;
	private String memberAccount;
	private String memberPassword;
	private String memberName;
	private String memberStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date memberLoginDate;
	
	private Fellowship resideIn;

	private ChurchMemberDtl dtl;
	
	private String createUid;
	private String updateUid;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cmem_sid")
	public String getMemberSid() {
		return memberSid;
	}

	@Column(name="cmem_acct")
	@NotNull
	public String getMemberAccount() {
		return memberAccount;
	}

	@Column(name="cmem_pwd")
	@NotNull
	public String getMemberPassword() {
		return memberPassword;
	}

	@Column(name="cmem_name")
	@NotNull
	public String getMemberName() {
		return memberName;
	}

	@Column(name="cmem_sts")
	@NotNull
	public String getMemberStatus() {
		return memberStatus;
	}
	
	@Column(name="cmem_login_dte")
	@NotNull
	public Date getMemberLoginDate() {
		return memberLoginDate;
	}

	public void setMemberLoginDate(Date memberLoginDate) {
		this.memberLoginDate = memberLoginDate;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fshp_sid")
	public Fellowship getResideIn() {
		return this.resideIn;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cmem_sid")
	public ChurchMemberDtl getDtl() {
		return this.dtl;
	}
	

	@Column(name="cmem_crt_uid")
	@NotNull
	public String getCreateUid() {
		return createUid;
	}

	@Column(name="cmem_upd_uid")
	public String getUpdateUid() {
		return updateUid;
	}

	@Column(name="cmem_crt_dte")
	@NotNull
	public Date getCreateDate() {
		return createDate;
	}

	@Column(name="cmem_upd_dte")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setMemberSid(String memberSid) {
		this.memberSid = memberSid;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	
	public void setResideIn(Fellowship resideIn) {
		this.resideIn = resideIn;
	}
	
	public void setDtl(ChurchMemberDtl dtl) {
		this.dtl = dtl;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public void setUpdateUid(String updateUid) {
		this.updateUid = updateUid;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
