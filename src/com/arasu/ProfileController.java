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

import model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Utils;

@ManagedBean
@RequestScoped
@SessionScoped
public class ProfileController implements Serializable {
	/**
	 * 
	 */
	private String authorizationKey=null;

	private static final long serialVersionUID = 1L;
	private String userString;
	private Object userDetails;
	private User registrationUser;
	private String fname;
	private String lname;
	private String mnumber;
	private String eemail;
	private String vname;
	private String ccountry;
	private String country; 
	private Map<String,String> countries;
	public ProfileController(){
		
		this.registrationUser=new User();
		FacesContext context = FacesContext.getCurrentInstance();
		userDetails=context.getExternalContext().getSessionMap().get("userDetails");
		userString=(String) userDetails;
		Object	authKey=context.getExternalContext().getSessionMap().get("AuthorizationKey");
		authorizationKey=(String)authKey;
		try{
			JSONObject obj=new JSONObject(userString);
                   JSONArray array=obj.getJSONArray("UserList");
                   for (int i=0;i<array.length();i++){
                       JSONObject first=array.getJSONObject(i);
                       int userprofile=first.getInt("UserProfileId");
                      String UserProfileId=String.valueOf(userprofile);
                        fname=first.getString("UserFirstName");
                        lname=first.getString("UserLastName");
                        mnumber=first.getString("UserMobileNumber");
                        eemail=first.getString("UserEmail");
                        vname=first.getString("UserVenueName");
               	
                        ccountry=first.getString("UserCountry");
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
	
	public User getRegistrationUser() {
		return registrationUser;
	}
	public void setRegistrationUser(User registrationUser) {
		this.registrationUser = registrationUser;
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

public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMnumber() {
		return mnumber;
	}

	public void setMnumber(String mnumber) {
		this.mnumber = mnumber;
	}

	public String getEemail() {
		return eemail;
	}

	public void setEemail(String eemail) {
		this.eemail = eemail;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getCcountry() {
		return ccountry;
	}

	public void setCcountry(String ccountry) {
		this.ccountry = ccountry;
	}

public void getProfileName(){
	 FacesContext context = FacesContext.getCurrentInstance();
		userDetails=context.getExternalContext().getSessionMap().get("userDetails");
		userString=(String) userDetails;
		try{
			JSONObject obj=new JSONObject(userString);
            JSONArray array=obj.getJSONArray("UserList");
            for (int i=0;i<array.length();i++){
            	 JSONObject first=array.getJSONObject(i);
                 int userprofile=first.getInt("UserProfileId");
              String  UserProfileId=String.valueOf(userprofile);
                String  firstName=first.getString("UserFirstName");
                String  lastName=first.getString("UserLastName");
                String  phone=first.getString("UserMobileNumber");
                String   email=first.getString("UserEmail");
                String venueSummary=first.getString("UserVenueName");
                String  country=first.getString("UserCountry");
                 String inventory=first.getString("UserOftenInventory");
                 int inventorytime=first.getInt("UserInventoryTime");
                 String ParentUserProfileId=first.getString("ParentUserProfileId");
                 String UserRole=first.getString("UserRole");
                 boolean active=first.getBoolean("IsActive");
                 String create=first.getString("CreatedOn");
                 String modify=first.getString("ModifiedOn");
                 int id=first.getInt("Id");
                 String   password=first.getString("Password");

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

public String getUserString() {
	return userString;
}

public void setUserString(String userString) {
	this.userString = userString;
}

public Object getUserDetails() {
	return userDetails;
}

public void setUserDetails(Object userDetails) {
	this.userDetails = userDetails;
}


public String updateProfile(){
//	String firstName=this.registrationUser.getFirstName();
//	String lastName=this.registrationUser.getLastName();
//	String mobileNumber=this.registrationUser.getPhone();
//	String email=this.registrationUser.getEmail();
//	String venueSummary=this.registrationUser.getVenueSummary();
//	String country=this.registrationUser.getCountry();
	
	String firstName=fname;
	String lastName=lname;
	String mobileNumber=mnumber;
	String email=eemail;
	String venueSummary=vname;
	String country=this.country;
	if(firstName!=null&&!firstName.isEmpty()&&lastName!=null&&!lastName.isEmpty()&&mobileNumber!=null&&!mobileNumber.isEmpty()&&
			email!=null&&!email.isEmpty()&&venueSummary!=null&&!venueSummary.isEmpty()&&country!=null&&!country.isEmpty()){
	String output=POSTUPDATEAPI(firstName,lastName,mobileNumber,email,venueSummary,country);
	if(output!=null){
		 JSONObject obj=new JSONObject(output);
         String message=obj.getString("Message");
         boolean success=obj.getBoolean("IsSuccess");
         if (success){
        	 FacesContext context = FacesContext.getCurrentInstance();
       		context.getExternalContext().getSessionMap().put("userDetails",output );
       		String msg="Updated successfully!";
    		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
    		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    	return "mmyprofile.jsf?faces-redirect=true";
         }else{
        	 String msg="update not successfull!";
     		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
     		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
     	return "mmyprofile.jsf?faces-redirect=true";
         }
         
		
	}else{
		String msg="Error output!";
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	return "mmyprofile.jsf?faces-redirect=true";
	}
		
	}else{
		String msg="Please enter all details!";
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	return "mmyprofile.jsf?faces-redirect=true";
	}
	
}

private String POSTUPDATEAPI(String firstName, String lastName,
		String mobileNumber, String email, String venueSummary, String country) {
    String posturl = Utils.END_URL+"/updateUserbyProfileId";
    String output;
    String finaloutput=null;
    FacesContext context = FacesContext.getCurrentInstance();
	userDetails=context.getExternalContext().getSessionMap().get("userDetails");
	userString=(String) userDetails;
	String UserProfileId=null;
	String Password=null;
	try{
		JSONObject obj=new JSONObject(userString);
        JSONArray array=obj.getJSONArray("UserList");
        for (int i=0;i<array.length();i++){
        	 JSONObject first=array.getJSONObject(i);
             int userprofile=first.getInt("UserProfileId");
            UserProfileId=String.valueOf(userprofile);
               Password=first.getString("Password");


        }
	}catch(Exception e){
		e.printStackTrace();
	}

    try{
    	 JSONObject inputLogin=new JSONObject();
         try{
             inputLogin.put("UserProfileId",Integer.parseInt(UserProfileId));
             inputLogin.put("Password",Password);
             inputLogin.put("UserFirstName",firstName);
             inputLogin.put("UserLastName",lastName);
             inputLogin.put("UserMobileNumber",mobileNumber);
             inputLogin.put("UserEmail",email);
             inputLogin.put("UserVenueName",venueSummary);
             inputLogin.put("UserCountry",country);
             inputLogin.put("UserOftenInventory","");
             inputLogin.put("UserInventoryTime",0);


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

}
