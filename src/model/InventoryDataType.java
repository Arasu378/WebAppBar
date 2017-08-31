package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "inventoryServiceData")
@ApplicationScoped
@ViewScoped
public class InventoryDataType implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private    String [] bottleName=new String[]{"Absinthe","Beer","Bitters","Bourbon","Brandy","Cachaca","Cider","Cognac","Gin","Liqueur","Mezcal","Non-Alcoholic",
	    "Others","Rum","Rye","Sake","Scotch","Soju","Tequila","Vermouth","Vodka","Whiskey","Wine"};
private  String[]  bottlePictures=new String[]{"absinthe","beer","bitters","bourbon","brandy","cachasa","cider",
	    "cognac","gin","liquier","mezcal","non_alcoholic_image","others","rum","rye",
	   "japanesesake","scotch","koreansoju","tequila","vermouth","vodka","whiskey","desertwine"};
@ManagedProperty("#{inventoryService}")
private InventoryModel service;
List<InventoryModel> list = new ArrayList<InventoryModel>();
 public InventoryDataType(){
	    for(int i = 0 ; i < bottleName.length ; i++) {
	    	String bname=bottleName[i];
	    	String bpicture=bottlePictures[i];
	    	InventoryModel inventoryModel=new InventoryModel();
	    	inventoryModel.setBottleName(bname);
	    	inventoryModel.setBottlePictures(bpicture);

	    	list.add(inventoryModel);
	    }
	     System.out.println("List size : "+list.size());
	
}
public InventoryModel getService() {
	return service;
}
public void setService(InventoryModel service) {
	this.service = service;
}
public List<InventoryModel> getList() {
	return list;
}
public void setList(List<InventoryModel> list) {
	this.list = list;
}






}
