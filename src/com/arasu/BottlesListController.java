package com.arasu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Utils;
import model.LiquorData;
@ManagedBean
@ViewScoped
public class BottlesListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<LiquorData> liquorDataList=new ArrayList<LiquorData>();
    @ManagedProperty("#{liquorService}")
    private LiquorData liquorData;
    private int UserProfileId;
    private int SectionID;
    @PostConstruct
    public void getLiquourData() {
   	 FacesContext context = FacesContext.getCurrentInstance();
		Object userDetails=context.getExternalContext().getSessionMap().get("UserProfileId");
		Object secId=context.getExternalContext().getSessionMap().get("SectionId");
		int Proid=(Integer)userDetails;
		int sectionid=(Integer)secId;
		SectionID=sectionid;
		if(Proid!=0 && sectionid!=0){
			GetBottlesListApi(Proid,sectionid);
		}
                   
   }
	private void GetBottlesListApi(int proid, int sectionid) {
		String geturl=Utils.END_URL+"/getUserliquorlist/"+proid+"/"+sectionid;
		String output;
		try{
			 URL url = new URL(geturl);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

					while ((output = br.readLine()) != null) {
						System.out.println(output);
						JSONObject obj=new JSONObject(output);
						boolean IsSuccess=obj.getBoolean("IsSuccess");
						String Message=obj.getString("Message");
						if(IsSuccess){
							JSONArray array=obj.getJSONArray("Model");
							for(int i=0;i<array.length();i++){
								JSONObject first=array.getJSONObject(i);
								int UserProfileId=first.getInt("UserProfileId");
								int LiquorId=first.getInt("Id");
								int BarId=first.getInt("BarId");
								int SectionId=first.getInt("SectionId");
								String LiquorName=first.getString("LiquorName");
								String LiquorCapacity=first.getString("LiquorCapacity");
								String Shots=first.getString("Shots");
								String Category=first.getString("Category");
								String subcategory=first.getString("SubCategory");
		                        String parlevel=first.getString("ParLevel");
		                        String distributorname=first.getString("DistributorName");
		                        String price=first.getString("Price");
		                        String binnumber=first.getString("BinNumber");
		                        String productcode=first.getString("ProductCode");
		                        String CreatedOn=first.getString("CreatedOn");
		                        String ModifiedOn=first.getString("ModifiedOn");
		                        String minvalue=first.getString("MinValue");
	                            String maxvalue=first.getString("MaxValue");
	                            String pictureurl=first.getString("PictureURL");
	                            String totalbottles=first.getString("TotalBottles");
	                            String type=first.getString("Type");
	                            String fullweight=first.getString("FullWeight");
	                            String emptyweight=first.getString("EmptyWeight");
	                            liquorData=new LiquorData();
	                            liquorData.setUserProfileId(UserProfileId);
	                            liquorData.setLiquorId(LiquorId);
	                            liquorData.setBarId(BarId);
	                            liquorData.setSectionId(SectionId);
	                            liquorData.setLiquorName(LiquorName);
	                            liquorData.setLiquorCapacity(LiquorCapacity);
	                            liquorData.setShots(Shots);
	                            liquorData.setCategory(Category);
	                            liquorData.setSubCategory(subcategory);
	                            liquorData.setParLevel(parlevel);
	                            liquorData.setDistributorName(distributorname);
	                            liquorData.setPrice(price);
	                            liquorData.setBinNumber(binnumber);
	                            liquorData.setProductCode(productcode);
	                            liquorData.setCreatedOn(CreatedOn);
	                            liquorData.setModifiedOn(ModifiedOn);
	                            liquorData.setMinValue(minvalue);
	                            liquorData.setMaxValue(maxvalue);
	                            liquorData.setPictureURL(pictureurl);
	                            liquorData.setTotalBottles(totalbottles);
	                            liquorData.setType(type);
	                            liquorData.setFullWeight(fullweight);
	                            liquorData.setEmptyWeight(emptyweight);
	                            liquorDataList.add(liquorData);
							}
							
						}
						
						}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public List<LiquorData> getLiquorDataList() {
		return liquorDataList;
	}
	public void setLiquorDataList(List<LiquorData> liquorDataList) {
		this.liquorDataList = liquorDataList;
	}
	public LiquorData getLiquorData() {
		return liquorData;
	}
	public void setLiquorData(LiquorData liquorData) {
		this.liquorData = liquorData;
	}
	public int getUserProfileId() {
		return UserProfileId;
	}
	public void setUserProfileId(int userProfileId) {
		UserProfileId = userProfileId;
	}
	public int getSectionID() {
		return SectionID;
	}
	public void setSectionID(int sectionID) {
		SectionID = sectionID;
	}
	public String getBottleDetails(LiquorData model){
		 FacesContext context = FacesContext.getCurrentInstance();

		 int liquorId =model.getLiquorId();
   		context.getExternalContext().getSessionMap().put("LiquorId",liquorId );
		 int userProfileId=model.getUserProfileId();
	   		context.getExternalContext().getSessionMap().put("UserProfileId",userProfileId );
		 int barId=model.getBarId();
	   		context.getExternalContext().getSessionMap().put("BarId",barId );

		 int sectionId=model.getSectionId();
	   		context.getExternalContext().getSessionMap().put("SectionId",sectionId );

		 String liquorName=model.getLiquorName();
	   		context.getExternalContext().getSessionMap().put("LiquorName",liquorName );

		 String liquorCapacity=model.getLiquorCapacity();
	   		context.getExternalContext().getSessionMap().put("LiquorCapacity",liquorCapacity );

		 String shots=model.getShots();
	   		context.getExternalContext().getSessionMap().put("Shots",shots );

		 String category=model.getCategory();
	   		context.getExternalContext().getSessionMap().put("Category",category );

		 String subCategory=model.getSubCategory();
	   		context.getExternalContext().getSessionMap().put("SubCategory",subCategory );

		 String parLevel=model.getParLevel();
	   		context.getExternalContext().getSessionMap().put("ParLevel",parLevel );

		 String distributorName=model.getDistributorName();
	   		context.getExternalContext().getSessionMap().put("DistributorName",distributorName );

		 String price=model.getPrice();
	   		context.getExternalContext().getSessionMap().put("Price",price );

		 String binNumber=model.getBinNumber();
	   		context.getExternalContext().getSessionMap().put("BinNumber",binNumber );

		 String productCode=model.getProductCode();
	   		context.getExternalContext().getSessionMap().put("ProductCode",productCode );

		 String createdOn=model.getCreatedOn();
	   		context.getExternalContext().getSessionMap().put("CreatedOn",createdOn );

		 String modifiedOn=model.getModifiedOn();
	   		context.getExternalContext().getSessionMap().put("ModifiedOn",modifiedOn );

		 String minValue=model.getMinValue();
	   		context.getExternalContext().getSessionMap().put("MinValue",minValue );

		 String maxValue=model.getMaxValue();
	   		context.getExternalContext().getSessionMap().put("MaxValue",maxValue );

		 String pictureURL=model.getPictureURL();
	   		context.getExternalContext().getSessionMap().put("PictureURL",pictureURL );

		 String totalBottles=model.getTotalBottles();
	   		context.getExternalContext().getSessionMap().put("TotalBottles",totalBottles );

		 String type=model.getType();
	   		context.getExternalContext().getSessionMap().put("Type",type );

		 String fullWeight=model.getFullWeight();
	   		context.getExternalContext().getSessionMap().put("FullWeight",fullWeight );

		 String emptyWeight=model.getEmptyWeight();
	   		context.getExternalContext().getSessionMap().put("EmptyWeight",emptyWeight );

		
		return "mbottledetails.jsf?faces-redirect=true";
	}
	
	
}
