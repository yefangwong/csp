package org.occ.csp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tbl_region")
@XmlRootElement
public class Region implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2038101205092150383L;

	private String regionId;
	private String regionName;

	private List<Fellowship> fellowships;
	
	private String createUid;
	private String updateUid;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="regn_sid")
	public String getRegionId() {
		return regionId;
	}

	@Column(name="regn_name")
	@NotNull
	public String getRegionName() {
		return regionName;
	}
	
	@OneToMany(mappedBy="region")
	public List<Fellowship> getFellowships() {
		return this.fellowships;
	}

	@Column(name="regn_crt_uid")
	@NotNull
	public String getCreateUid() {
		return createUid;
	}

	@Column(name="regn_upd_uid")
	public String getUpdateUid() {
		return updateUid;
	}

	@Column(name="regn_crt_dte")
	@NotNull
	public Date getCreateDate() {
		return createDate;
	}

	@Column(name="regn_upd_dte")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public void setFellowships(List<Fellowship> fellowships) {
		this.fellowships = fellowships;
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
