package com.arasu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.NamedEvent;

import model.Bar;

import org.json.JSONArray;
import org.json.JSONObject;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import utils.Utils;

@ManagedBean
@RequestScoped
@SessionScoped
@ViewScoped
@NamedEvent
public class LandingController implements Serializable{

	private static final long serialVersionUID = 1L;
	public  Object userDetails=null;
	public  String userString;
	public String venue;
	private String val;
	private String addBar;

	public String UserProfileId;
 private List<Bar> barlist=new ArrayList<Bar>();
 @ManagedProperty("#{barService}")
 private Bar service;
	
public Bar getService() {
	return service;
}

public void setService(Bar service) {
	this.service = service;
}
public String getVal() {
    return val;
}

public void setVal(String val) {
    this.val = val;
}
public void closeDialog() {
    //pass back to level 2
    RequestContext.getCurrentInstance().closeDialog(val);
}
public LandingController(){
	 FacesContext context = FacesContext.getCurrentInstance();
		userDetails=context.getExternalContext().getSessionMap().get("userDetails");
		userString=(String) userDetails;
		try{
			JSONObject obj=new JSONObject(userString);
                   JSONArray array=obj.getJSONArray("UserList");
                   for (int i=0;i<array.length();i++){
                       JSONObject first=array.getJSONObject(i);
                       int userprofile=first.getInt("UserProfileId");
                       UserProfileId=String.valueOf(userprofile);
                       String fname=first.getString("UserFirstName");
                       String lname=first.getString("UserLastName");
                       String number=first.getString("UserMobileNumber");
                       String email=first.getString("UserEmail");
                        venue=first.getString("UserVenueName");
               		context.getExternalContext().getSessionMap().put("uservenue",venue);
               		context.getExternalContext().getSessionMap().put("UserProfileId", userprofile);
                       String country=first.getString("UserCountry");
                       String inventory=first.getString("UserOftenInventory");
                       int inventorytime=first.getInt("UserInventoryTime");
                       String ParentUserProfileId=first.getString("ParentUserProfileId");
                       String UserRole=first.getString("UserRole");
                       boolean active=first.getBoolean("IsActive");
                       String create=first.getString("CreatedOn");
                       String modify=first.getString("ModifiedOn");
                       int id=first.getInt("Id");
                       String password=first.getString("Password");

                   }
                   

               

		}catch(Exception e){
			e.printStackTrace();
		}
}
public  String getUserString() {
	return userString;
}


public String getVenue() {
	return venue;
}
public void setVenue(String venue) {
	this.venue = venue;
}
public  void setUserString(String userString) {
	this.userString = userString;
}
@PostConstruct
public void getBarlistM(){
	  System.out.println("UserProfileId : "+UserProfileId);
		if(UserProfileId!=null){
			getBarListItems(UserProfileId);
		}else{
	        try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
}
private void getBarListItems(String UserProfileId){
    String stringurl =Utils.END_URL+"/getBarbyUserProfileId/"+UserProfileId;
    System.out.println("End Url: "+stringurl);
    try{
        URL url = new URL(stringurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

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
						String BarName=first.getString("BarName");
						String CreatedOn=first.getString("CreatedOn");
						service=new Bar();
						service.setBarId(BarId);
						service.setUserProfileId(UserProfileId1);
						service.setBarName(BarName);
						service.setCreatedOn(CreatedOn);
						barlist.add(service);
					}
				}
			
			}
			
				

    }catch(Exception e){
    	e.printStackTrace();
    }
    

}
public void setBarlist(List<Bar> barlist) {
	this.barlist = barlist;
}

public List<Bar> getBarlists() {
	return barlist;
}
public String BarItemClick(Bar id){	   
	int BarId=id.getBarId();
	 FacesContext context = FacesContext.getCurrentInstance();
	context.getExternalContext().getSessionMap().put("BarId",BarId);
     System.out.println("List Item Id : "+id.getBarId());
		return "msection.jsf?faces-redirect=true";

}
public String logout(ActionEvent actionEvent){

	System.out.println("Logout!");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "mlogin.jsf?faces-redirect=true";

}
public void openLevel1() {
    Map<String,Object> options = new HashMap<String, Object>();
    System.out.println("button clicked : ");
    options.put("modal", true);
    options.put("draggable", false);  
    options.put("resizable", false);  
    options.put("contentHeight", 320);
    try{
    	 
        RequestContext.getCurrentInstance().openDialog("addbar", options, null);
    }catch(Exception e){
    	e.printStackTrace();
    }
}
 
public void onReturnFromLevel1(SelectEvent event) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data Returned", event.getObject().toString()));
    System.out.println("Bar Name given : "+event.getObject().toString());
}

public String getAddBar() {
	return addBar;
}

public void setAddBar(String addBar) {
	this.addBar = addBar;
}

public String addnewbar(){
	System.out.println("New Bar Name : "+addBar);
	RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('wdialog').hide();");
	return "";
}
public String showdialog(){
	System.out.println("New Bar Name : ");

	RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF('wdialog').show();");
	return "";
}
}
