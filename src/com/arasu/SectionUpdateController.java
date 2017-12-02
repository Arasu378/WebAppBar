package com.arasu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.json.JSONObject;

import utils.Utils;
import model.Bar;
import model.Section;

@ManagedBean
@RequestScoped
@SessionScoped
@ViewScoped
public class SectionUpdateController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String section_Name;
	private int UserProfileId;
	private int barid;
	private String authorizationKey=null;

	private String section_NameUpdate;
	public SectionUpdateController(){
		 FacesContext context = FacesContext.getCurrentInstance();
			Object	userDetails=context.getExternalContext().getSessionMap().get("UserProfileId");
			Object secid=context.getExternalContext().getSessionMap().get("BarId");
			barid=(Integer)secid;
			UserProfileId=(Integer) userDetails;
			Object	authKey=context.getExternalContext().getSessionMap().get("AuthorizationKey");
			authorizationKey=(String)authKey;
	}
	public String getSection_Name() {
		return section_Name;
	}
	
	public int getBarid() {
		return barid;
	}
	public void setBarid(int barid) {
		this.barid = barid;
	}
	public int getUserProfileId() {
		return UserProfileId;
	}
	public void setUserProfileId(int userProfileId) {
		UserProfileId = userProfileId;
	}
	public String getSection_NameUpdate() {
		return section_NameUpdate;
	}
	public void setSection_NameUpdate(String section_NameUpdate) {
		this.section_NameUpdate = section_NameUpdate;
	}
	public void setSection_Name(String section_Name) {
		this.section_Name = section_Name;
	}
	public String clicked(String inputvalue){
		if(inputvalue!=null &&!inputvalue.isEmpty()&&UserProfileId!=0){
			String output=CreateSectionAPI(inputvalue,UserProfileId);
			if(output!=null){
				JSONObject obj=new JSONObject(output);
				boolean issuccess=obj.getBoolean("IsSuccess");
				String Message=obj.getString("Message");
				if(issuccess){
					String msg="Successfully added!";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					return "msection.jsf?faces-redirect=true";
				}else{
					String msg="Section Not added!";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					return "msection.jsf?faces-redirect=true";

				}
			}
		}
		String msg="some error occured!";
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		System.out.println("Cloicked : "+inputvalue+" / UserProfileId : "+UserProfileId);
			return "msection.jsf?faces-redirect=true";
	}
	private String CreateSectionAPI(String inputvalue, int userProfileId2) {
		String posturl=Utils.END_URL+"/InsertSection";
		String finaloutput=null;
		try{
			JSONObject inputLogin=new JSONObject();
			String output;
	        try{
	        	  inputLogin.put("UserProfileId",userProfileId2);
	              inputLogin.put("SectionName",inputvalue);
	              inputLogin.put("BarId",barid);
	           }catch (Exception e){
	            e.printStackTrace();
	        }
	        System.out.println("inputJsonuser : "+ inputLogin.toString());
	        String input=String.valueOf(inputLogin);
	        URL url = new URL(posturl);
	   		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	   		conn.setDoOutput(true);
	   		conn.setRequestProperty("Authorization", "Kyros "+authorizationKey);

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
	public String update(String inputvalue){
		 FacesContext context = FacesContext.getCurrentInstance();
			Object	userDetails=context.getExternalContext().getSessionMap().get("SectionIdUpdate");
			int barid=(Integer) userDetails;
			int proid=(Integer)context.getExternalContext().getSessionMap().get("UserProfileId");
		if(inputvalue!=null&&!inputvalue.isEmpty()&&barid!=0&&proid!=0){
			String output=updateSectionAPI(inputvalue,barid,proid);
			if(output!=null){
				JSONObject obj=new JSONObject(output);
				boolean issuccess=obj.getBoolean("IsSuccess");
				String Message=obj.getString("Message");
				if(issuccess){
					String msg="Successfully updated!";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
						return "msection.jsf?faces-redirect=true";
				}else{
					String msg=Message;
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
						return "msection.jsf?faces-redirect=true";
				}
			}else{
				String msg="error occured!";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					return "msection.jsf?faces-redirect=true";
			}
		}else{
			String msg="input is empty!";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return "msection.jsf?faces-redirect=true";
		}
		
	}
	private String updateSectionAPI(String inputvalue, int barid2, int proid2) {
		String puturl=Utils.END_URL+"/updateSectionBottles";
		String finaloutput=null;
		String output;
		try{
			 JSONObject inputjso=new JSONObject();
			 try{
		            inputjso.put("SectionId",barid2);
		            inputjso.put("SectionName",inputvalue);
		            inputjso.put("UserProfileId", proid2);
		        }catch (Exception e){
		            e.printStackTrace();
		        }
		        System.out.println("input : "+inputjso.toString());
		        String input=String.valueOf(inputjso);
		        URL url = new URL(puturl);
		   		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		   		conn.setDoOutput(true);
		   		conn.setRequestProperty("Authorization", "Kyros "+authorizationKey);

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
	public void updatesecid(Section bar){
		int barid=bar.getSectionId();
		System.out.println("Seciton id update : "+barid);
		 FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("SectionIdUpdate",barid);
		
	}
	public void deletesecid(Section section){
		int sectionid=section.getSectionId();
		int proid=section.getUserProfileId();
		System.out.println("Seciton id delete : "+sectionid+" / "+proid);
		 FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("SectionIdDelete",sectionid);
			context.getExternalContext().getSessionMap().put("UserProfileIdDelete", proid);
	}
	public String delete(){
		 FacesContext context = FacesContext.getCurrentInstance();
		 int  barId=(Integer)	context.getExternalContext().getSessionMap().get("SectionIdDelete");
		 int proid=(Integer)	context.getExternalContext().getSessionMap().get("UserProfileIdDelete");
		
		if(barId!=0&&proid!=0){
			String output=DeleteSectionAPI(barId,proid);
			if(output!=null){
				JSONObject obj=new JSONObject(output);
				boolean issuccess=obj.getBoolean("IsSuccess");
				String Message =obj.getString("Message");
				if(issuccess){
					String msg="Successfully deleted!";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
						return "msection.jsf?faces-redirect=true";
				}else{
					String msg=Message;
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
						return "msection.jsf?faces-redirect=true";
				}
			}else{
				String msg="some error occured!";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					return "msection.jsf?faces-redirect=true";
			}
		}else{
			String msg="Section id empty!";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return "msection.jsf?faces-redirect=true";
		}
	}
	private String DeleteSectionAPI(int barId,int proid) {
		String geturl=Utils.END_URL+"/DeleteSection/"+proid+"/"+barId;
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
}
