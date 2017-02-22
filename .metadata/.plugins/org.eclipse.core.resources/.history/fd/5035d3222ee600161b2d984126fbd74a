/**
 * 
 */
package com.experian.finance.master;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author C02809A
 *
 */
public class Slab {
	
	private String STR_BLANK="";
	private String legalEntity;
	private String businessUnit;
	private Integer clientId;
	private String clientLocationNo;
	private String clientName;
	private String productId;
	private String productName;
	private Integer slabNo;
	private String slabCategory;
	private String slabType;
	private Long minSlabVolume;
	private Long maxSlabVolume;
	private Float charges;
	private Integer active;
	private Date slabEndDate;
	
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
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getClientLocationNo() {
		return clientLocationNo;
	}
	public void setClientLocationNo(String clientLocationNo) {
		this.clientLocationNo = clientLocationNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getClientName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getSlabNo() {
		return slabNo;
	}
	public void setSlabNo(Integer slabNo) {
		this.slabNo = slabNo;
	}
	public String getSlabCategory() {
		return slabCategory;
	}
	public void setSlabCategory(String slabCategory) {
		this.slabCategory = slabCategory;
	}
	public String getSlabType() {
		return slabType;
	}
	public void setSlabType(String slabType) {
		this.slabType = slabType;
	}
	public Long getMinSlabVolume() {
		return minSlabVolume;
	}
	public void setMinSlabVolume(Long minSlabVolume) {
		this.minSlabVolume = minSlabVolume;
	}
	public Long getMaxSlabVolume() {
		return maxSlabVolume;
	}
	public void setMaxSlabVolume(Long maxSlabVolume) {
		this.maxSlabVolume = maxSlabVolume;
	}
	public Float getCharges() {
		return charges;
	}
	public void setCharges(Float charges) {
		this.charges = charges;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public Date getSlabEndDate() {
		return slabEndDate;
	}
	public void setSlabEndDate(Date slabEndDate) {
		this.slabEndDate = slabEndDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Slab() {
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	
	public Slab(String[] ar) {
		try {
			legalEntity = ar[0];
			businessUnit = ar[1];
			clientId = ar[2].length()==0?null:Integer.parseInt(ar[2]);
			clientLocationNo = ar[3];
			productId = ar[4];
			productName = ar[5];
			slabCategory = ar[6];
			slabType = ar[7];
			minSlabVolume = ar[8].length()==0?null:Long.parseLong(ar[8]);
			maxSlabVolume = ar[9].length()==0?null:Long.parseLong(ar[9]);
			charges = ar[10].length()==0?null:Float.parseFloat(ar[10]);
			active = ar[11].length()==0?null:Integer.parseInt(ar[11]);
			slabEndDate = ar[12].length() == 0 ? null : sdf.parse(ar[12]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void nullifyEmptyFields(){
		try {
			Class<Slab> aClass = Slab.class;
			Method[] methodList = aClass.getDeclaredMethods();
			
			for(Method fi : methodList){
				if(StringUtils.startsWith(fi.getName(),"set"))
					continue;
				if (fi.getReturnType().equals(String.class)){
					String ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob=(String) fi.invoke(this, new Object[] {});
					
					if (ob==null)
						continue;
					if (ob.length() == 0){
                        Method setter = aClass.getMethod("set" + callingMethodName,
                                new Class<?>[] { String.class });
                        if (setter != null)
                            // Setter to trim and set the trimmed String value
                            setter.invoke(this, new Object[] {null});

					}
						
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public String toString() {
		String  slabItem= "legalEntity=" + legalEntity + ", businessUnit="
				+ businessUnit + ", clientId=" + clientId
				+ ", clientLocationNo=" + clientLocationNo + ", productId="
				+ productId + ", productName=" + productName + ", slabNo="
				+ slabNo + ", slabCategory=" + slabCategory + ", slabType="
				+ slabType + ", minSlabVolume=" + minSlabVolume
				+ ", maxSlabVolume=" + maxSlabVolume + ", charges=" + charges
				+ ", active=" + active + ", slabEndDate=" + slabEndDate;
		
		return slabItem;
	}

	
	public void populateNullFields() {
		try {
			Class<Slab> aClass = Slab.class;
			Method[] methodList = aClass.getDeclaredMethods();

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date defaultDate = sdf.parse("01-02-0001");

			for (Method fi : methodList) {
				if (StringUtils.startsWith(fi.getName(), "set"))
					continue;
				if (fi.getReturnType().equals(String.class)) {
					String ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (String) fi.invoke(this, new Object[] {});

					if (ob == null)
						continue;
					if (ob.length() == 0) {
						Method setter = aClass.getMethod("set"
								+ callingMethodName,
								new Class<?>[] { String.class });
						if (setter != null)
							// Setter to trim and set the trimmed String value
							setter.invoke(this, new Object[] { null });

					}

				}
				if (fi.getReturnType().equals(Date.class)) {
					Date ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (Date) fi.invoke(this, new Object[] {});

					if (ob != null)
						continue;
					Method setter = aClass.getMethod("set" + callingMethodName,
							new Class<?>[] { Date.class });
					if (setter != null)
						// Setter to trim and set the trimmed String value
						setter.invoke(this, new Object[] { defaultDate });

				}
				if (fi.getReturnType().equals(Long.class)) {
					Long ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (Long) fi.invoke(this, new Object[] {});

					if (ob != null)
						continue;
					Method setter = aClass.getMethod("set" + callingMethodName,
							new Class<?>[] { Long.class });
					if (setter != null)
						// Setter to trim and set the trimmed String value
						setter.invoke(this, new Object[] { new Long(-1) });

				}
				if (fi.getReturnType().equals(Integer.class)) {
					Integer ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (Integer) fi.invoke(this, new Object[] {});

					if (ob != null)
						continue;
					Method setter = aClass.getMethod("set" + callingMethodName,
							new Class<?>[] { Integer.class });
					if (setter != null)
						// Setter to trim and set the trimmed String value
						setter.invoke(this, new Object[] { new Integer(-1) });

				}
				if (fi.getReturnType().equals(Float.class)) {
					Float ob = null;
					String callingMethodName = fi.getName().substring(3);
					ob = (Float) fi.invoke(this, new Object[] {});

					if (ob != null)
						continue;
					Method setter = aClass.getMethod("set" + callingMethodName,
							new Class<?>[] { Float.class });
					if (setter != null)
						// Setter to trim and set the trimmed String value
						setter.invoke(this, new Object[] { new Float(-1) });

				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
}
