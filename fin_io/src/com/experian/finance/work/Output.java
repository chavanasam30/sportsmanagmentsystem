/**
 * 
 */
package com.experian.finance.work;

import java.util.Date;

/**
 * @author C02809A
 *
 */
public class Output {
	
	private int srNumber;
	private String legalEntity;
	private String businessUnit;
	private String clientName;
	private int clientId;
	private String clientLocationNo;
	private String nickName;
	private String transactionType;
	private String charges;
	private String productId;
	private long quantity;
	private float rate;
	private float amount;
	private String defaultText;
	private String referenceNo;
	private String line;
	private Date processDate;
	
	
	
	public int getSrNumber() {
		return srNumber;
	}
	public void setSrNumber(int srNumber) {
		this.srNumber = srNumber;
	}
	public String getLegalEntity() {
		return legalEntity;
	}
	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientLocationNo() {
		return clientLocationNo;
	}
	public void setClientLocationNo(String clientLocationNo) {
		this.clientLocationNo = clientLocationNo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getDefaultText() {
		return defaultText;
	}
	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	
	@Override
	public String toString() {
		return  srNumber+","+businessUnit +","+clientName+","+clientId+","+clientLocationNo+","+nickName
				+","+transactionType+","+charges+","+productId+","+quantity+","+rate+","+amount+","+defaultText+
				","+referenceNo+","+line+","+processDate;
	}

	

}
