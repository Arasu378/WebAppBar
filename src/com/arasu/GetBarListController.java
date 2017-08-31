package com.arasu;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;







import utils.Utils;

@ManagedBean
@SessionScoped
public class GetBarListController implements Serializable {
	private static final long serialVersionUID = 1L;
@PostConstruct
public void getBarlist(){
	 FacesContext context = FacesContext.getCurrentInstance();
		 String	UserProfileId=(String)context.getExternalContext().getSessionMap().get("UserProfileId");
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
			
			}
			
				

    }catch(Exception e){
    	e.printStackTrace();
    }
    

}
public String logout(){
	System.out.println("Logout!");
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return "mlogin.jsf?faces-redirect=true";

}
}
