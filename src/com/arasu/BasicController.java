package com.arasu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Utils;
import model.Bar;

@ManagedBean
@RequestScoped
@ViewScoped
@NamedEvent
public class BasicController implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String bar_Name ;
private int UserProfileId;
private String bar_NameUpdate;

public BasicController(){
	 FacesContext context = FacesContext.getCurrentInstance();
	Object	userDetails=context.getExternalContext().getSessionMap().get("UserProfileId");
	UserProfileId=(Integer) userDetails;
}
public String getBar_Name() {
	return bar_Name;
}
public void setBar_Name(String bar_Name) {
	this.bar_Name = bar_Name;
}

public String getBar_NameUpdate() {
	return bar_NameUpdate;
}
public void setBar_NameUpdate(String bar_NameUpdate) {
	this.bar_NameUpdate = bar_NameUpdate;
}
public String clicked(String inputvalue){
	
	if(inputvalue!=null &&!inputvalue.isEmpty()&&UserProfileId!=0){
		String output=CreateBarAPI(inputvalue,UserProfileId);
		if(output!=null){
			JSONObject obj=new JSONObject(output);
			boolean issuccess=obj.getBoolean("IsSuccess");
			String Message=obj.getString("Message");
			if(issuccess){
				String msg="Successfully added!";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return "mlanding.jsf?faces-redirect=true";
			}else{
				String msg="Bar Not added!";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return "mlanding.jsf?faces-redirect=true";

			}
		}
	}
	String msg="Please enter Bar Name!";
	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	System.out.println("Cloicked : "+inputvalue+" / UserProfileId : "+UserProfileId);
		return "mlanding.jsf?faces-redirect=true";

	 
}
private String CreateBarAPI(String inputvalue, int userProfileId2) {
	String posturl=Utils.END_URL+"/insertBar";
	String finaloutput=null;
	try{
		JSONObject inputLogin=new JSONObject();
		String output;
        try{
            inputLogin.put("UserProfileId",userProfileId2);
            inputLogin.put("BarName",inputvalue);
           }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("inputJsonuser : "+ inputLogin.toString());
        String input=String.valueOf(inputLogin);
        URL url = new URL(posturl);
   		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
   		conn.setDoOutput(true);
   		conn.setRequestMethod("POST");
   		conn.setRequestProperty("Content-Type", "application/json");
   		java.io.OutputStream os = conn.getOutputStream();
   		os.write(input.getBytes());
   		os.flush();

   		BufferedReader br = new BufferedReader(new InputStreamReader(
   				(conn.getInputStream())));
   		System.out.println("Output from Server .... \n");
 		while ((output = br.readLine()) != null) {
			System.out.println(output);
			finaloutput=output;
		}

		conn.disconnect();
	 
	}catch(Exception e){
		e.printStackTrace();
	}
	 	return finaloutput;
	
}
public int getUserProfileId() {
	return UserProfileId;
}
public void setUserProfileId(int userProfileId) {
	UserProfileId = userProfileId;
}
public String delete(){
	 FacesContext context = FacesContext.getCurrentInstance();
	 int  barId=(Integer)	context.getExternalContext().getSessionMap().get("BarIdDelete");
	 int proid=(Integer)context.getExternalContext().getSessionMap().get("UserProfileIdDelete");
	
	if(barId!=0&&proid!=0){
		String output=DeleteBarAPI(barId,proid);
		if(output!=null){
			JSONObject obj=new JSONObject(output);
			boolean issuccess=obj.getBoolean("IsSuccess");
			String Message =obj.getString("Message");
			if(issuccess){
				String msg="Successfully deleted!";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					return "mlanding.jsf?faces-redirect=true";
			}else{
				String msg=Message;
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					return "mlanding.jsf?faces-redirect=true";
			}
		}else{
			String msg="some error occured!";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return "mlanding.jsf?faces-redirect=true";
		}
	}else{
		String msg="bar id empty!";
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "mlanding.jsf?faces-redirect=true";
	}
	
	
}
public void updatebarid(Bar bar){
	int barid=bar.getBarId();
	System.out.println("bar id update : "+barid);
	 FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("BarIdUpdate",barid);
	
}
public void deletebarid(Bar bar){
	int barId=bar.getBarId();
	int proid=bar.getUserProfileId();
	System.out.println("Delete Id are : bar ID :  "+barId+" / proid : "+proid);
	 FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("BarIdDelete",barId);
		context.getExternalContext().getSessionMap().put("UserProfileIdDelete",proid);

}
private String DeleteBarAPI(int barId,int proid) {
	String geturl=Utils.END_URL+"/DeleteBar/"+proid+"/"+barId;
	String finaloutput=null;
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

				String output;
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					finaloutput=output;
				
				
				}
	}catch(Exception e){
		e.printStackTrace();
	}
	return finaloutput;
	
}
public String update(String inputvalue){
	 FacesContext context = FacesContext.getCurrentInstance();
		Object	userDetails=context.getExternalContext().getSessionMap().get("BarIdUpdate");
		int barid=(Integer) userDetails;
		int proid=(Integer)context.getExternalContext().getSessionMap().get("UserProfileId");
	if(inputvalue!=null&&!inputvalue.isEmpty()&&barid!=0){
		String output=updateBarAPI(inputvalue,barid,proid);
		if(output!=null){
			JSONObject obj=new JSONObject(output);
			boolean issuccess=obj.getBoolean("IsSuccess");
			String Message=obj.getString("Message");
			if(issuccess){
				String msg="Successfully updated!";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					return "mlanding.jsf?faces-redirect=true";
			}else{
				String msg=Message;
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					return "mlanding.jsf?faces-redirect=true";
			}
		}else{
			String msg="error occured!";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return "mlanding.jsf?faces-redirect=true";
		}
	}else{
		String msg="input is empty!";
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "mlanding.jsf?faces-redirect=true";
	}
	
}
private String updateBarAPI(String inputvalue, int barid, int proid) {
	String puturl=Utils.END_URL+"/updateBarName";
	String finaloutput=null;
	String output;
	try{
		 JSONObject inputjso=new JSONObject();
	        try{
	            inputjso.put("BarId",barid);
	            inputjso.put("BarName",inputvalue);
	            inputjso.put("UserProfileId", proid);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        System.out.println("input : "+inputjso.toString());
	        String input=String.valueOf(inputjso);
	        URL url = new URL(puturl);
	   		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	   		conn.setDoOutput(true);
	   		conn.setRequestMethod("PUT");
	   		conn.setRequestProperty("Content-Type", "application/json");
	   		java.io.OutputStream os = conn.getOutputStream();
	   		os.write(input.getBytes());
	   		os.flush();

	   		BufferedReader br = new BufferedReader(new InputStreamReader(
	   				(conn.getInputStream())));
	   		System.out.println("Output from Server .... \n");
	 		while ((output = br.readLine()) != null) {
				System.out.println(output);
				finaloutput=output;
			}

			conn.disconnect();

	}catch(Exception e){
		e.printStackTrace();
	}
return finaloutput;
}

}
