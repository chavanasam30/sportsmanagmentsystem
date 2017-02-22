/**
 * 
 */
package com.experian.finance.work;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author C02809A
 *
 */
public class Input {
	
	private String STR_BLANK="";
	private String legalEntity;
	private String businessUnit;
	private int clientId;
	private String clientLocationNo;
	private String clientName;
	private String nickName;
	private String transactionType;
	private long charges;
	private String productId;
	private long quantity;
	private String accountSetupFlag;
	private Date processDate;
	private String processResult;
	
	
	public Input() {
		super();
	}
	
	public Input(String [] argument) {
		
		setLegalEntity(argument[0]);
		setBusinessUnit(argument[1]);
		setClientId((null != argument[2] && !argument[2].equalsIgnoreCase(STR_BLANK))?Integer.parseInt(argument[2]):0);
		setClientLocationNo(argument[3]);
		setClientName(argument[4]);
		setNickName(argument[5]);
		setTransactionType(argument[6]);
		setCharges((null != argument[7] && !argument[7].equalsIgnoreCase(STR_BLANK))?Long.parseLong(argument[7]):0);
		setProductId(argument[8]);
		setQuantity((null != argument[9] && !argument[9].equalsIgnoreCase(STR_BLANK))?Long.parseLong(argument[9]):0);
		setAccountSetupFlag(argument[10]);
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yy");
		SimpleDateFormat dateFormat1=new SimpleDateFormat("dd-MMM-yy");
		Date inputProcessDate = null;
		try {
			inputProcessDate = dateFormat.parse(argument[11]);
		} catch (ParseException e) {
			try {
				inputProcessDate = dateFormat1.parse(argument[11]);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		setProcessDate(inputProcessDate);
		
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
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	public long getCharges() {
		return charges;
	}
	public void setCharges(long charges) {
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
	public String getAccountSetupFlag() {
		return accountSetupFlag;
	}
	public void setAccountSetupFlag(String accountSetupFlag) {
		this.accountSetupFlag = accountSetupFlag;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	public String getProcessResult() {
		return processResult;
	}
	public void setProcessResult(String processStatus) {
		this.processResult = processStatus;
	}

	public Input getInputObject(String [] argument) 
	{
		Input inputObj=new Input();
		inputObj.setLegalEntity(argument[0]);
		inputObj.setBusinessUnit(argument[1]);
		inputObj.setClientId((null != argument[2] && !argument[2].equalsIgnoreCase(STR_BLANK))?Integer.parseInt(argument[2]):0);
		inputObj.setClientLocationNo(argument[3]);
		inputObj.setClientName(argument[4]);
		inputObj.setNickName(argument[5]);
		inputObj.setTransactionType(argument[6]);
		inputObj.setCharges((null != argument[7] && !argument[7].equalsIgnoreCase(STR_BLANK))?Long.parseLong(argument[7]):0);
		inputObj.setProductId(argument[8]);
		inputObj.setQuantity((null != argument[9] && !argument[9].equalsIgnoreCase(STR_BLANK))?Long.parseLong(argument[9]):0);
		inputObj.setAccountSetupFlag(argument[10]);
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yy");
		Date inputProcessDate = null;
		try {
			inputProcessDate = dateFormat.parse(argument[11]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		inputObj.setProcessDate(inputProcessDate);
		return inputObj;
	}
	
}
