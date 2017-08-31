package com.arasu;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.SlideEndEvent;

import model.SliderValue;
@ViewScoped
@ManagedBean

public class BottlesDetailsController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pictureURL;
	private String liquorName;
	private int sliderNumber;
	private int liquorId;
	private int userProfileId;
	private int barId;
	private int sectionId;
	private String liquorCapacity;
	private String shots;
	private String category;
	private String subCategory;
	private String parLevel;
	private String distributorName;
	private String price;
	private String binNumber;
	private String createdOn;
	private String modifiedOn;
	private String minValue;
	private String maxValue;
	private String totalBottles;
	private String type;
	private String fullWeight;
	private String emptyWeight;
	private String productCode;
	private SliderValue sliderValue;
	private String outid;
	private float realValue = 0;
	private float finalrealValue=0;

    public float getValueAsInt() {
    	System.out.println("getValueasint : "+this.realValue);
        return this.realValue;
    }

    public float getValueAsNumber() {
    	System.out.println("getValueasnumber : "+this.realValue);

        return realValue;
    }

    public float getFinalrealValue() {
		return finalrealValue;
	}

	public void setFinalrealValue(float finalrealValue) {
		this.finalrealValue = finalrealValue;
	}

	public void setValueAsInt(float value) {
    	System.out.println("setValueasint : "+value);

        this.realValue = value;
    }

    public float getRealValue() {
		return realValue;
	}

	public void setRealValue(float realValue) {
		this.realValue = realValue;
	}

	public void setValueAsNumber(float value) {
    	System.out.println("setValueasnumber : "+value);

        this.realValue = value;
    }
    

    public void onSlideEnd(SlideEndEvent event) {
    	Object iobject=event.getSource();
    	float iobjectfloat=(Float)iobject;
    	System.out.println("onslideend : "+iobject.toString());

    	System.out.println("onslideend : "+event.getValue());
		this.finalrealValue=(float)event.getValue()/10;
    	System.out.println("finalrealValue : "+finalrealValue);

        this.realValue = event.getValue();
    }
    public void onInputChanged(ValueChangeEvent event) {
    	float value=(Float) event.getNewValue();
    	System.out.println("Input value changed: "+value);
    }
	public BottlesDetailsController(){
		this.sliderValue=new SliderValue();
		 FacesContext context = FacesContext.getCurrentInstance();
			Object userDetails=context.getExternalContext().getSessionMap().get("PictureURL");
			pictureURL=(String)userDetails;
			liquorName=(String)context.getExternalContext().getSessionMap().get("LiquorName");
			System.out.println("Slide Number context : "+sliderNumber);
			
		liquorId=(Integer)context.getExternalContext().getSessionMap().get("LiquorId");
		   	userProfileId=(Integer)	context.getExternalContext().getSessionMap().get("UserProfileId" );
		   	barId=(Integer)	context.getExternalContext().getSessionMap().get("BarId" );

		   	sectionId=(Integer)	context.getExternalContext().getSessionMap().get("SectionId" );

			
			  liquorCapacity=(String)	context.getExternalContext().getSessionMap().get("LiquorCapacity" );

			  shots=(String)	context.getExternalContext().getSessionMap().get("Shots" );

			  category=(String)context.getExternalContext().getSessionMap().get("Category" );

			 subCategory=(String)context.getExternalContext().getSessionMap().get("SubCategory" );

			  parLevel=(String)	context.getExternalContext().getSessionMap().get("ParLevel" );

			  distributorName=(String)context.getExternalContext().getSessionMap().get("DistributorName" );

			  price=(String)	context.getExternalContext().getSessionMap().get("Price" );

			  binNumber=(String)context.getExternalContext().getSessionMap().get("BinNumber" );

			  productCode=(String)context.getExternalContext().getSessionMap().get("ProductCode" );

			  createdOn=(String)context.getExternalContext().getSessionMap().get("CreatedOn" );

			  modifiedOn=(String)context.getExternalContext().getSessionMap().get("ModifiedOn" );

			  minValue=(String)context.getExternalContext().getSessionMap().get("MinValue" );

			  maxValue=(String)context.getExternalContext().getSessionMap().get("MaxValue" );

			
			  totalBottles=(String)context.getExternalContext().getSessionMap().get("TotalBottles" );

			  type=(String)context.getExternalContext().getSessionMap().get("Type" );

			  fullWeight=(String)context.getExternalContext().getSessionMap().get("FullWeight" );

			  emptyWeight=(String)context.getExternalContext().getSessionMap().get("EmptyWeight" );


	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	public String getLiquorName() {
		return liquorName;
	}
	public void setLiquorName(String liquorName) {
		this.liquorName = liquorName;
	}
	public int getSliderNumber() {
		//sliderNumber=(float)sliderNumber/100;
		System.out.println("Slide Number : "+sliderNumber);
		return sliderNumber;
	}
	public void setSliderNumber(int sliderNumber) {
		this.sliderNumber = sliderNumber;
	}
	
	
public int getLiquorId() {
		return liquorId;
	}
	public void setLiquorId(int liquorId) {
		this.liquorId = liquorId;
	}
	public int getUserProfileId() {
		return userProfileId;
	}
	public void setUserProfileId(int userProfileId) {
		this.userProfileId = userProfileId;
	}
	public int getBarId() {
		return barId;
	}
	public void setBarId(int barId) {
		this.barId = barId;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getLiquorCapacity() {
		return liquorCapacity;
	}
	public void setLiquorCapacity(String liquorCapacity) {
		this.liquorCapacity = liquorCapacity;
	}
	public String getShots() {
		return shots;
	}
	public void setShots(String shots) {
		this.shots = shots;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getParLevel() {
		return parLevel;
	}
	public void setParLevel(String parLevel) {
		this.parLevel = parLevel;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBinNumber() {
		return binNumber;
	}
	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public String getTotalBottles() {
		return totalBottles;
	}
	public void setTotalBottles(String totalBottles) {
		this.totalBottles = totalBottles;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFullWeight() {
		return fullWeight;
	}
	public void setFullWeight(String fullWeight) {
		this.fullWeight = fullWeight;
	}
	public String getEmptyWeight() {
		return emptyWeight;
	}
	public void setEmptyWeight(String emptyWeight) {
		this.emptyWeight = emptyWeight;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
public String getBottledetailUpdate(){
	String finaloutput=null;
	 Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
     String answerID = String.valueOf(params.get("#output_slidervalue.outid"));
     String value=String.valueOf(getSliderNumber());
     String valueslider=this.sliderValue.getSliderValue();
     String ovalue=getOutid();

	//  FacesContext context = FacesContext.getCurrentInstance();
	  //  Integer answerID = context.getApplication().evaluateExpressionGet(context, "#{bottlesDetailsController.sliderNumber}", Integer.class);
		System.out.println("Slide Number context button: "+finalrealValue);

	return finaloutput;
}
public SliderValue getSliderValue() {
	System.out.println("Slider value: get :" +sliderValue.getSliderValue());
	return sliderValue;
}
public void setSliderValue(SliderValue sliderValue) {
	System.out.println("Slider value: set :" +sliderValue.getSliderValue());

	this.sliderValue = sliderValue;
}
public String getOutid() {
	return outid;
}
public void setOutid(String outid) {
	this.outid = outid;
}


}
