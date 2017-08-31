package com.arasu;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.json.JSONArray;
import org.json.JSONObject;
@ManagedBean
@RequestScoped
@SessionScoped
public class HeaderController implements Serializable {
	private String userName;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String logout(){
		System.out.println("Logout!");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "mlogin.jsf?faces-redirect=true";

}
	public HeaderController(){
		 FacesContext context = FacesContext.getCurrentInstance();
			String value=(String)context.getExternalContext().getSessionMap().get("userDetails");
			System.out.println("userDetails header : "+value);
			if(value!=null){
				try{
					JSONObject obj=new JSONObject(value);
					boolean issuccess=obj.getBoolean("IsSuccess");
					if(issuccess){
						JSONArray arry=obj.getJSONArray("UserList");
						for(int o=0; o<arry.length();o++){
							JSONObject first=arry.getJSONObject(o);
							String fname=first.getString("UserFirstName");
							String lname=first.getString("UserLastName");
							String finalname=fname+" "+lname;
							userName=finalname;
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


}
