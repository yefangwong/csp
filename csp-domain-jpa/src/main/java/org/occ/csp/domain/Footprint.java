package org.occ.csp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="tbl_footprint")
public class Footprint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3043099806548332696L;
	
	private int footprintId;
	
	private String memberSid;
	private Date loginDate;
	
	private String createUid;
	private String updateUid;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fpt_sid")
	public int getFootprintId() {
		return footprintId;
	}
	
	@Column(name="cmem_sid")
	@NotNull
	public String getMemberSid() {
		return memberSid;
	}

	@Column(name="cmem_login_dte")
	@NotNull
	public Date getLoginDate() {
		return loginDate;
	}

	@Column(name="fpt_crt_uid")
	@NotNull
	public String getCreateUid() {
		return createUid;
	}

	@Column(name="fpt_upd_uid")
	public String getUpdateUid() {
		return updateUid;
	}

	@Column(name="fpt_crt_dte")
	@NotNull
	public Date getCreateDate() {
		return createDate;
	}

	@Column(name="fpt_upd_dte")
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setFootprintId(int footprintId) {
		this.footprintId = footprintId;
	}

	public void setMemberSid(String memberSid) {
		this.memberSid = memberSid;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
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
