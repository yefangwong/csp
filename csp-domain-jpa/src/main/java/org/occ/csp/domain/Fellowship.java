package org.occ.csp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tbl_fellowship")
public class Fellowship implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7220439449960567521L;

	private String fellowshipId;
	private String fellowshipName;
	
	private Region belongTo;
	
	private List<ChurchMember> churchMembers;
	
	private String createUid;
	private String updateUid;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fshp_sid")
	public String getFellowshipId() {
		return fellowshipId;
	}

	@Column(name="fshp_name")
	@NotNull
	public String getFellowshipName() {
		return fellowshipName;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="regn_sid")
	public Region getRegion() {
		return this.belongTo;
	}
	
	@OneToMany(mappedBy="resideIn")
	public List<ChurchMember> getChurchMembers() {
		return this.churchMembers;
	}

	@Column(name="fshp_crt_uid")
	@NotNull
	public String getCreateUid() {
		return createUid;
	}

	@Column(name="fshp_upd_uid")
	public String getUpdateUid() {
		return updateUid;
	}

	@Column(name="fshp_crt_dte")
	@NotNull
	public Date getCreateDate() {
		return createDate;
	}

	@Column(name="fshp_upd_dte")
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setFellowshipId(String fellowshipId) {
		this.fellowshipId = fellowshipId;
	}

	public void setFellowshipName(String fellowshipName) {
		this.fellowshipName = fellowshipName;
	}

	public void setRegion(Region region) {
		this.belongTo = region;
	}

	public void setChurchMembers(List<ChurchMember> churchMembers) {
		this.churchMembers = churchMembers;
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
