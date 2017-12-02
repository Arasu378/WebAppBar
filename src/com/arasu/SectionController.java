package com.arasu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Utils;
import model.Bar;
import model.Section;

@ManagedBean
@RequestScoped
@SessionScoped
@ViewScoped
public class SectionController {
	private int sectionId;
	private int UserProfileId;
	private String authorizationKey=null;

	 private List<Section> sectionlist=new ArrayList<Section>();
	 @ManagedProperty("#{sectionService}")
	 private Section service;
	public SectionController(){
		 FacesContext context = FacesContext.getCurrentInstance();
		Object secid=context.getExternalContext().getSessionMap().get("BarId");
		Object proid=context.getExternalContext().getSessionMap().get("UserProfileId");
		sectionId=(Integer) secid;
		UserProfileId=(Integer)proid;
		Object	authKey=context.getExternalContext().getSessionMap().get("AuthorizationKey");
		authorizationKey=(String)authKey;
		System.out.println("Section Controller class : "+sectionId+" / "+UserProfileId);
		GetSectionList(sectionId,UserProfileId);
	}
	private void GetSectionList(int sectionId2, int userProfileId2) {
		String geturl=Utils.END_URL+"/getSectionByUserProfileID/"+sectionId2;
		System.out.println("URL : "+geturl);
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

					String output;
					while ((output = br.readLine()) != null) {
						System.out.println(output);
						JSONObject obj=new JSONObject(output);
						boolean IsSuccess=obj.getBoolean("IsSuccess");
						String Message=obj.getString("Message");
						if(IsSuccess){
							JSONArray array=obj.getJSONArray("Model");
							for(int i=0;i<array.length();i++){
								JSONObject first=array.getJSONObject(i);
								int UserProfileId1=first.getInt("UserProfileId");
								int BarId=first.getInt("BarId");
								int SectionId=first.getInt("SectionId");
								String SectionName=first.getString("SectionName");
								String CreatedOn=first.getString("CreatedOn");
								service=new Section();
								service.setBarId(BarId);
								service.setUserProfileId(UserProfileId1);
								service.setSectionName(SectionName);
								service.setCreatedOn(CreatedOn);
								service.setSectionId(SectionId);
								sectionlist.add(service);
							}
						}
					
					}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getUserProfileId() {
		return UserProfileId;
	}
	public void setUserProfileId(int userProfileId) {
		UserProfileId = userProfileId;
	}
	public List<Section> getSectionlist() {
		return sectionlist;
	}
	public void setSectionlist(List<Section> sectionlist) {
		this.sectionlist = sectionlist;
	}
	public Section getService() {
		return service;
	}
	public void setService(Section service) {
		this.service = service;
	}
	
	public String SectionItemClick(Section id){	   
		int SectionId=id.getSectionId();
		 FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("SectionId",SectionId);
	     System.out.println("List Item Id : "+id.getSectionId());
			return "mbottleslist.jsf?faces-redirect=true";

	}
}
