package model;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
@ManagedBean(name = "liquorService")
@ApplicationScoped
public class LiquorData implements Serializable {

	private static final long serialVersionUID = 1L;
	private int liquorId;
	private int userProfileId;
	private int barId;
	private int sectionId;
	private String liquorName;
	private String liquorCapacity;
	private String shots;
	private String category;
	private String subCategory;
	private String parLevel;
	private String distributorName;
	private String price;
	private String binNumber;
	private String productCode;
	private String createdOn;
	private String modifiedOn;
	private String minValue;
	private String maxValue;
	private String pictureURL;
	private String totalBottles;
	private String type;
	private String fullWeight;
	private String emptyWeight;
	public LiquorData(){
		
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
	public String getLiquorName() {
		return liquorName;
	}
	public void setLiquorName(String liquorName) {
		this.liquorName = liquorName;
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
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
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
	
	

}
