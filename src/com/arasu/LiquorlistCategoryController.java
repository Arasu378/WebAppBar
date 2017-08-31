package com.arasu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.LiquorData;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Utils;


@ManagedBean
@ViewScoped
public class LiquorlistCategoryController implements Serializable {
	/**
	 * 
	 */
	private List<LiquorData> liquorDataList=new ArrayList<LiquorData>();
    @ManagedProperty("#{liquorService}")
    private LiquorData liquorData;
	private static final long serialVersionUID = 1L;
	private String categoryName;
public LiquorlistCategoryController(){
	 FacesContext context = FacesContext.getCurrentInstance();
		Object	userDetails=context.getExternalContext().getSessionMap().get("CategoryBottle");
		categoryName=(String)userDetails;
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
@PostConstruct
public void getCategorylist(){
	if(categoryName!=null){
	String output=	getCategoryliquorlist(categoryName);
	if(output!=null){
		JSONObject obj =new JSONObject(output);
		String Message=obj.getString("Message");
		boolean issuccess=obj.getBoolean("IsSuccess");
		if(issuccess){
		
		}else{
			String msg=Message;
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("maddbottles.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}else{
		String msg="something went wrong!";
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("maddbottles.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}else{
		String msg="category name is empty!";
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("maddbottles.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
private String getCategoryliquorlist(String categoryName2) {
	String output;
	String finaloutput=null;
	try{
		String geturl=Utils.END_URL+"/getLiquorListCategory/"+categoryName2;
		System.out.println("URL : "+geturl);
		
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
					finaloutput=output;
					try{
						JSONObject obj =new JSONObject(output);
						boolean issuccess=obj.getBoolean("IsSuccess");
						if(issuccess){
							  JSONArray array=obj.getJSONArray("UserList");
		                        for (int i=0;i<array.length();i++){
		                            JSONObject first=array.getJSONObject(i);
		                            String name=first.getString("name");
		                            int quantity=first.getInt("capacity_mL");
		                            String type=first.getString("alcohol_type");
		                            String pic=first.getString("picture_url");
//		                            String pic=first.getString("small_picture_url");
		                            String subtype=first.getString("alcohol_subtype");
		                            double max_height=first.getDouble("max_height");
		                            double min_height=first.getDouble("min_height");
		                            liquorData=new LiquorData();		                            
		                            liquorData.setLiquorName(name);
		                            liquorData.setLiquorCapacity(String.valueOf(quantity));
		                            liquorData.setSubCategory(subtype);
		                            liquorData.setCategory(type);
		                            liquorData.setPictureURL(pic);
		                            liquorData.setMinValue(String.valueOf(min_height));
		                            liquorData.setMaxValue(String.valueOf(max_height));
		                            liquorDataList.add(liquorData);
		                        }

						}
					}catch(Exception e){
						e.printStackTrace();
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
public String nextPage(LiquorData liData){
	 String name=liData.getLiquorName();
     String quantity=liData.getLiquorCapacity();
     String category=liData.getCategory();
     String pic=liData.getPictureURL();
     String subcategory=liData.getSubCategory();
     String max_height=liData.getMaxValue();
     String min_height=liData.getMinValue();
	 FacesContext context = FacesContext.getCurrentInstance();
	 
	context.getExternalContext().getSessionMap().put("LiquorNameInsert",name);
		context.getExternalContext().getSessionMap().put("LiquorQuantityInsert",quantity);
		context.getExternalContext().getSessionMap().put("CategoryInsert",category);
	context.getExternalContext().getSessionMap().put("LiquorPictureURLInsert",pic);
		context.getExternalContext().getSessionMap().put("SubCategoryInsert",subcategory);
		context.getExternalContext().getSessionMap().put("MaxValueInsert",max_height);
		context.getExternalContext().getSessionMap().put("MinValueInsert",min_height);

	return "mliquorcategoryinsert.jsf?faces-redirect=true";
}

}
