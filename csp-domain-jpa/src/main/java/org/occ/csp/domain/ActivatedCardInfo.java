package org.occ.csp.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_activated_cardinfo")
public class ActivatedCardInfo implements Serializable {

	private static final long serialVersionUID = -139819430760236537L;

	private ActivatedCardInfoPk pk;
	
	private ChurchMember belongTo;

	@EmbeddedId
	public ActivatedCardInfoPk getPk() {
		return pk;
	}
	public void setPk(ActivatedCardInfoPk pk) {
		this.pk = pk;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cmem_sid")
	public ChurchMember getChurchMember() {
		return this.belongTo;
	}
	
	public void setChurchMember(ChurchMember churchMember) {
		this.belongTo = churchMember;
	}
	
}
