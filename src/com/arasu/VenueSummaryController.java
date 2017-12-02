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
public class VenueSummaryController implements Serializable{
	 /**
	 * 00
	 */
	private static final long serialVersionUID = 1L;
	private List<LiquorData> liquorDataList=new ArrayList<LiquorData>();
	    @ManagedProperty("#{liquorService}")
	    private LiquorData liquorData;
	    private String authorizationKey=null;

	    private String userString;
	    private Object userDetails;
	    private String UserProfileId;
	    @PostConstruct
	    public void getLiquourData() {
	    	 FacesContext context = FacesContext.getCurrentInstance();
	    	 Object	authKey=context.getExternalContext().getSessionMap().get("AuthorizationKey");
	    		authorizationKey=(String)authKey;
	 		userDetails=context.getExternalContext().getSessionMap().get("userDetails");
	 		userString=(String) userDetails;
	 		try{
	 			JSONObject obj=new JSONObject(userString);
	                    JSONArray array=obj.getJSONArray("UserList");
	                    for (int i=0;i<array.length();i++){
	                        JSONObject first=array.getJSONObject(i);
	                        int userprofile=first.getInt("UserProfileId");
	                        UserProfileId=String.valueOf(userprofile);
	                    }
	 		}catch(Exception e){
	 			e.printStackTrace();
	 		}
	 		if(UserProfileId!=null){
	 			GetVenueSummaryAPI(UserProfileId);
	 		}
	                    
	    }
		private String GetVenueSummaryAPI(String userProfileId2) {
			String geturl=Utils.END_URL+"/GetVenueSummary/"+userProfileId2;
			String output;
			String finaloutput=null;
			try{
				 URL url = new URL(geturl);
			        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
			   		conn.setRequestProperty("Authorization", "Kyros "+authorizationKey);

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
			return finaloutput;
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
	 
	     
}
