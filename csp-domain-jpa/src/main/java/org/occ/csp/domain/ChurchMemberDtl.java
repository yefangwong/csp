package org.occ.csp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tbl_churchmem_dtl")
@XmlRootElement
public class ChurchMemberDtl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4596608227436057731L;

	private String memberSid;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	
	private String email;
	private String tel;
	private String address;
	private String photoUrl;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="dtl")
	private ChurchMember owner;
	
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

	@Column(name="birthday")
	public Date getBirthday() {
		return birthday;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	@Column(name="tel")
	public String getTel() {
		return tel;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}

	@Column(name="photo_url")
	public String getPhotoUrl() {
		return photoUrl;
	}

	@Column(name="crt_uid")
	@NotNull
	public String getCreateUid() {
		return createUid;
	}

	@Column(name="upd_uid")
	public String getUpdateUid() {
		return updateUid;
	}

	@Column(name="crt_dte")
	@NotNull
	public Date getCreateDate() {
		return createDate;
	}

	@Column(name="upd_dte")
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public ChurchMember getOwner() {
		return owner;
	}

	public void setOwner(ChurchMember owner) {
		this.owner = owner;
	}

	public void setMemberSid(String memberSid) {
		this.memberSid = memberSid;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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
	
}
