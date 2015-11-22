package org.occ.csp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class ActivatedCardInfoPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8215385610824270024L;
	@Column
	private String occId;
	private String cardNumber;

	@Column(name="occ_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String getOccId() {
		return occId;
	}
	public void setOccId(String occId) {
		this.occId = occId;
	}
	@Column(name="card_num")
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
