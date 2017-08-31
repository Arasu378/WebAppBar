package com.arasu;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.InventoryModel;

@ManagedBean
@ViewScoped

public class AddBottleController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categoryName;
	public AddBottleController(){
		
	}
	public String listCategory(InventoryModel model){
		String name=model.getBottleName();
		categoryName=name;
		 FacesContext context = FacesContext.getCurrentInstance();
		 System.out.println("Category Bottle TYpe: "+name);
			Object	userDetails=context.getExternalContext().getSessionMap().put("CategoryBottle",name);
			return "mliquorlistcategory.jsf?faces-redirect=true";
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
