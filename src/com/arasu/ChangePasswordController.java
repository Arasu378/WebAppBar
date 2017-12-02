package com.arasu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Utils;
import model.User;

@ManagedBean
@RequestScoped
@SessionScoped
public class ChangePasswordController {
	private User registrationUser;
	private String authorizationKey=null;

	public ChangePasswordController(){
		this.registrationUser=new User();
	}
	public User getRegistrationUser() {
		return registrationUser;
	}
	public void setRegistrationUser(User registrationUser) {
		this.registrationUser = registrationUser;
	}
	public String changePassword(){
		String oldPassword=this.registrationUser.getOldPassword();
		String newPassword=this.registrationUser.getPassword();
		String confirmPassword=this.registrationUser.getConfirmPassword();
		 FacesContext context = FacesContext.getCurrentInstance();
		 String userDetails=((String) context.getExternalContext().getSessionMap().get("userDetails"));
		 String UserProfileId="0";
		 Object	authKey=context.getExternalContext().getSessionMap().get("AuthorizationKey");
			authorizationKey=(String)authKey;
		 try{
			 JSONObject obj=new JSONObject(userDetails);
             JSONArray array=obj.getJSONArray("UserList");
             for(int i=0; i<array.length();i++){
            	 JSONObject first=array.getJSONObject(i);
            	int  proid=first.getInt("UserProfileId");
            	UserProfileId=String.valueOf(proid);
            	 
             }
             
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 System.out.println("UserProfileId : "+UserProfileId);

		if(oldPassword!=null&&!oldPassword.isEmpty() &&UserProfileId!=null &&!UserProfileId.isEmpty()){
			if(newPassword.equals(confirmPassword)){
			String output=	ChangePasswordPOSTAPI(UserProfileId,oldPassword,newPassword,confirmPassword);
			if(output!=null){
				 JSONObject obj=new JSONObject(output);
                 String message=obj.getString("Message");
                 boolean success=obj.getBoolean("IsSuccess");
                 if(success){
                	 String msg="Password changed successfully!";
                	 FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
      	  			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
      	  	
      				return "mchangepassword.jsf?faces-redirect=true"; 
                 }else{
     	  			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,message,message));
     	  			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
     	  	
     				return "mchangepassword.jsf?faces-redirect=true"; 
                 }
			
			}else{
				String msg="Change password Error!";
	  			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
	  			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	  	
				return "mchangepassword.jsf?faces-redirect=true";
			}
				
			}else{
				String msg="Old password does not match new password!";
	  			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
	  			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	  	
				return "mchangepassword.jsf?faces-redirect=true";
			}
			
		}else{
				String msg="Old password is empty!";
  			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
  			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
  	
			return "mchangepassword.jsf?faces-redirect=true";
		}
		
		
	}
	private String ChangePasswordPOSTAPI(String UserProfileId,String oldPassword, String newPassword,
			String confirmPassword) {
	String finaloutput=null;
	String output;
	
    String posturl = Utils.END_URL+"/changePassword";
    			try{
    				  JSONObject inputLogin=new JSONObject();
    			        try{
    			            inputLogin.put("UserProfileId",Integer.parseInt(UserProfileId));
    			            inputLogin.put("OldPassword",oldPassword);
    			            inputLogin.put("NewPassword",newPassword);

    			        }catch (Exception e){
    			            e.printStackTrace();
    			        }
    			        System.out.println("inputJsonuser : "+inputLogin.toString());
    			        

    			        String input=String.valueOf(inputLogin);
    			        URL url = new URL(posturl);
    					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    					conn.setDoOutput(true);
    					conn.setRequestMethod("PUT");
    					conn.setRequestProperty("Content-Type", "application/json");
    			   		conn.setRequestProperty("Authorization", "Kyros "+authorizationKey);

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
	public String logout(){
		System.out.println("Logout!");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "mlogin.jsf?faces-redirect=true";

}
}
