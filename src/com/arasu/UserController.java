package com.arasu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.json.JSONObject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import utils.Utils;
import model.User;


@ManagedBean
@RequestScoped
@SessionScoped

public class UserController implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private User registrationUser;
private String val;
private String country; 
private Map<String,String> countries;
private String authorizationKey=null;

public UserController(){
	this.registrationUser=new User();
	
}
public User getRegistrationUser() {
	return registrationUser;
}
public void setRegistrationUser(User registrationUser) {
	this.registrationUser = registrationUser;
}
public String getVal() {
    return val;
}

@PostConstruct
public void init() {
    countries  = new HashMap<String, String>();
    countries.put("USA", "USA");
    countries.put("Germany", "Germany");
    countries.put("Brazil", "Brazil");
    countries.put("INDIA", "INDIA");
    countries.put("UK", "UK");


     
   
}

public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public Map<String, String> getCountries() {
	return countries;
}
public void setCountries(Map<String, String> countries) {
	this.countries = countries;
}
public void setVal(String val) {
    this.val = val;
}

public void closeDialog() {
    //pass back to level 2
    RequestContext.getCurrentInstance().closeDialog(val);
}

public String register(){
	
	String firstName=this.registrationUser.getFirstName();
	String lastName=this.registrationUser.getLastName();
	String mobileNumber=this.registrationUser.getPhone();
	String email=this.registrationUser.getEmail();
	String venueName=this.registrationUser.getVenueSummary();
	String country=this.country;
	if(firstName!=null&&!firstName.isEmpty()&&lastName!=null&&!lastName.isEmpty()&&mobileNumber!=null&&!mobileNumber.isEmpty()&&
			email!=null&&!email.isEmpty()&&venueName!=null&&!venueName.isEmpty()&&country!=null&&!country.isEmpty()){
		String output=RegisterPOSTApi(firstName,lastName,mobileNumber,email,venueName,country);
		if(output!=null){
			String msg="Registration is successfull please login !";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "mlogin.jsf?faces-redirect=true";	
		}else{
			String msg="Registration is not successfull!";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "mregistration.jsf?faces-redirect=true";	
		}
	}else{
		String msg="Please enter all details!";
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	return "mregistration.jsf?faces-redirect=true";	
	}
	
	
	
}

private String RegisterPOSTApi(String firstName, String lastName,
		String mobileNumber, String email, String venueName, String country) {
	String finaloutput=null;
	 FacesContext context = FacesContext.getCurrentInstance();

	Object	authKey=context.getExternalContext().getSessionMap().get("AuthorizationKey");
	authorizationKey=(String)authKey;
	
	if(firstName!=null &&!firstName.isEmpty()&&lastName!=null &&!lastName.isEmpty()&&mobileNumber!=null &&!mobileNumber.isEmpty()&&
			email!=null &&!email.isEmpty()&&venueName!=null &&!venueName.isEmpty()&&country!=null &&!country.isEmpty()){
		
		 String posturl = Utils.END_URL+"/manageUserProfile";
		JSONObject inputLogin=new JSONObject();
		String output;
        try{
            inputLogin.put("UserFirstName",firstName);
            inputLogin.put("UserLastName",lastName);
            inputLogin.put("UserMobileNumber",mobileNumber);
            inputLogin.put("UserEmail",email);
            inputLogin.put("UserVenueName",venueName);
            inputLogin.put("UserCountry",country);
            inputLogin.put("UserOftenInventory","");
            inputLogin.put("UserInventoryTime",0);
            inputLogin.put("UserRole","admin");
            inputLogin.put("ParentUserProfileId",JSONObject.NULL);


        }catch (Exception e){
            e.printStackTrace();
        }
        try{
        	 System.out.println("inputJsonuser : "+ inputLogin.toString());
             String input=String.valueOf(inputLogin);
             URL url = new URL(posturl);
     		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
     		conn.setDoOutput(true);
     		conn.setRequestMethod("POST");
       		conn.setRequestProperty("Authorization", "Kyros "+authorizationKey);

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
	}else{
		
		return finaloutput;
	}
}
public String login(){
	System.out.println("Login User Email : "+this.registrationUser.getEmail());
	System.out.println("Login User Password : "+this.registrationUser.getPassword());
	 FacesContext context = FacesContext.getCurrentInstance();
	String value=(String)context.getExternalContext().getSessionMap().get("userDetails");
	if(value!=null){
		return "mlanding.jsf?faces-redirect=true";
	}else{
		
		String email=this.registrationUser.getEmail();
		String password=this.registrationUser.getPassword();
		if(email!=null && !email.isEmpty()){
			if(password!=null && !password.isEmpty()){
			String output=LoginAPI(email,password);
			System.out.println("Login Output : "+output);
			if(output!=null){
				try{
					JSONObject obj=new JSONObject(output);
					  String message=obj.getString("Message");
		              boolean success=obj.getBoolean("IsSuccess");
		              System.out.println("Login Value: "+success+" / "+message);
		              if (success){              
		          		context.getExternalContext().getSessionMap().put("userDetails",output );
		          		//logout paste this
		          		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		          			return "mlanding.jsf?faces-redirect=true";
		        

		              }else {
		            	  	String msg=message;
		          			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		          			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		          		return "mlogin.jsf?faces-redirect=true";
		              }

				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}else{
				String msg="Login Error Occured !";
      			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
      			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
      		return "mlogin.jsf?faces-redirect=true";
			}
			
			
			
			}else{
				String msg="Password is empty!";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "mlogin.jsf?faces-redirect=true";
			}
			
		}else{
			String msg="Email is empty!";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "mlogin.jsf?faces-redirect=true";	
		}
		String msg="Welcome";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "mlogin.jsf?faces-redirect=true";
	}
		
	
}


private String LoginAPI(String email,String password){
	String output=null;
	String finaloutput=null;
	try{
		 String posturl=Utils.END_URL+"/userLogin";
		 JSONObject inputLogin=new JSONObject();
	        try{
	            inputLogin.put("Password",password);
	            inputLogin.put("UserEmail",email);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        System.out.println("inputJsonuser : "+ inputLogin.toString());

	        String input=String.valueOf(inputLogin);
	        URL url = new URL(posturl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
	   		conn.setRequestProperty("Authorization", "Kyros "+authorizationKey);

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
public String registerhere(){
	System.out.println("Registration running here..");
	return "mregistration.jsf?faces-redirect=true";

}
public String logout(){
	System.out.println("Logout!");
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return "mlogin.jsf?faces-redirect=true";

}
public void openLevel1() {
    Map<String,Object> options = new HashMap<String, Object>();
    options.put("modal", true);
    RequestContext.getCurrentInstance().openDialog("addbar", options, null);
}
 
public void onReturnFromLevel1(SelectEvent event) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data Returned", event.getObject().toString()));
    System.out.println("Bar Name given : "+event.getObject().toString());
}
}
