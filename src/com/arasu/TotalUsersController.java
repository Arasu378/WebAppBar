package com.arasu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Utils;
import model.User;

@ManagedBean
@RequestScoped
@SessionScoped
@ViewScoped
public class TotalUsersController {
	 private List<User> userlist=new ArrayList<User>();
	 @ManagedProperty("#{userService}")
	 private User service;
	public User getService() {
		return service;
	}
	public void setService(User service) {
		this.service = service;
	}
	public List<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	@PostConstruct
	public void getBarlistM(){
		getUsersListAPI();
	}
	private void getUsersListAPI() {
		String geturl=Utils.END_URL+"/userservice/users";
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
						JSONObject obj=new JSONObject(output);
						boolean IsSuccess=obj.getBoolean("IsSuccess");
						String Message=obj.getString("Message");
						if(IsSuccess){
							JSONArray array=obj.getJSONArray("UserList");
							for(int i=0;i<array.length();i++){
								JSONObject first=array.getJSONObject(i);
								int UserProfileId=first.getInt("UserProfileId");
								String UserFirstName=first.getString("UserFirstName");
								String UserMobileNumber=first.getString("UserMobileNumber");
								String UserEmail=first.getString("UserEmail");
								String UserVenueName=first.getString("UserVenueName");
								service=new User();
								service.setFirstName(UserFirstName);
								service.setuserProfileId(UserProfileId);
								service.setPhone(UserMobileNumber);
								service.setEmail(UserEmail);
								service.setVenueSummary(UserVenueName);
								userlist.add(service);
								}
							
						}
					}
					
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
