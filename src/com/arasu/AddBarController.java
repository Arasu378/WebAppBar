package com.arasu;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.NamedEvent;

import org.primefaces.context.RequestContext;
 
@ManagedBean
@RequestScoped
@ViewScoped
@NamedEvent

public class AddBarController implements Serializable {
	private String addBar;
	private static final long serialVersionUID = 1L;
	private String val;
 
    public String getVal() {
        return val;
    }
 
    public void setVal(String val) {
        this.val = val;
    }
 
    public void closeDialog() {
        //pass back to level 2
        RequestContext.getCurrentInstance().closeDialog(val);
    }
   public String clicked(){
	   System.out.println("clidked : "+addBar);
	   return "true";
   }

public String getAddBar() {
	return addBar;
}

public void setAddBar(String addBar) {
	this.addBar = addBar;
}
   
}