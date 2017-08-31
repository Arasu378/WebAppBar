package model;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
@ManagedBean(name = "barService")
@ApplicationScoped
public class Bar implements Serializable {
	private static final long serialVersionUID = 1L;
	
private int userProfileId;
private String barName;
private int barId;
private String createdOn;
public Bar(){
	
}
public int getUserProfileId() {
	return userProfileId;
}
public void setUserProfileId(int userProfileId) {
	this.userProfileId = userProfileId;
}
public String getBarName() {
	return barName;
}
public void setBarName(String barName) {
	this.barName = barName;
}
public int getBarId() {
	return barId;
}
public void setBarId(int barId) {
	this.barId = barId;
}
public String getCreatedOn() {
	return createdOn;
}
public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
}

}
