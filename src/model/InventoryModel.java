package model;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
@ManagedBean(name = "inventoryService")
@ApplicationScoped
public class InventoryModel implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String bottleName;
	private String bottlePictures;
	public InventoryModel(){
		
	}
	public String getBottleName() {
		return bottleName;
	}
	public void setBottleName(String bottleName) {
		this.bottleName = bottleName;
	}
	public String getBottlePictures() {
		return bottlePictures;
	}
	public void setBottlePictures(String bottlePictures) {
		this.bottlePictures = bottlePictures;
	}
	

}
