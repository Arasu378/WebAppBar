package com.arasu;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.LiquorData;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
@ViewScoped
@ApplicationScoped

public class AddCustomBottleController implements Serializable {
	/**
	 * 
	 */
	    private LiquorData liquorData;
	private static final long serialVersionUID = 1L;
	private int SectionId;
	private int UserProfileId;
	private int BarId;
	 private DefaultStreamedContent content;
public DefaultStreamedContent getContent() {
		return content;
	}
	public void setContent(DefaultStreamedContent content) {
		this.content = content;
	}
public AddCustomBottleController(){
	 FacesContext context = FacesContext.getCurrentInstance();
		UserProfileId=(Integer)context.getExternalContext().getSessionMap().get("UserProfileId");
		BarId=(Integer)context.getExternalContext().getSessionMap().get("BarId");
		SectionId=(Integer)context.getExternalContext().getSessionMap().get("SectionId");
		liquorData=new LiquorData();
		System.out.println("AddCustomBottle : "+UserProfileId+" / "+BarId+" / "+SectionId);
		//return "mliquorlistcategory.jsf?faces-redirect=true";
}
public int getSectionId() {
	return SectionId;
}
public void setSectionId(int sectionId) {
	SectionId = sectionId;
}
public int getUserProfileId() {
	return UserProfileId;
}
public void setUserProfileId(int userProfileId) {
	UserProfileId = userProfileId;
}
public int getBarId() {
	return BarId;
}
public void setBarId(int barId) {
	BarId = barId;
}
private UploadedFile file;

public UploadedFile getFile() {
    return file;
}

public void setFile(UploadedFile file) {
    this.file = file;
}
 
public void upload() {
    if(file != null) {
    	
        FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

public LiquorData getLiquorData() {
	return liquorData;
}
public void setLiquorData(LiquorData liquorData) {
	this.liquorData = liquorData;
}
public StreamedContent getImage(){
	byte[] ola=null;
	String name=null;
	if(file!=null){
		 name=file.getFileName();
	 ola=file.getContents();
	//return file.getInputstream();
	}
    content = new DefaultStreamedContent(new ByteArrayInputStream(ola), "image/png", name+".png");

    return  content;

}
public String save(){
	String name=liquorData.getLiquorName();
	
	 if(file != null) {
	    	
	        FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	return "mliquorlistcategory.jsf?faces-redirect=true";

}
public String cancel(){
	return "mlanding.jsf?faces-redirect=true";

}
}
