package com.arasu;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class FileNotFoundController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FileNotFoundController(){
		
	}
	public String getStatusCode(){
		String val = String.valueOf((Integer)FacesContext.getCurrentInstance().getExternalContext().
				getRequestMap().get("javax.servlet.error.status_code"));
		if(val==null){
			val="4**";
		}
		System.out.println("Status Code : "+val);
		return val;
	}

	public String getMessage(){
		String val =  (String)FacesContext.getCurrentInstance().getExternalContext().
			getRequestMap().get("javax.servlet.error.message");
		if(val==null){
			val="empty message";
		}
		System.out.println("Message : "+val);

		return val;
	}

	public String getExceptionType(){
		String val = FacesContext.getCurrentInstance().getExternalContext().
			getRequestMap().get("javax.servlet.error.exception_type").toString();
		if(val==null){
			val="exception type";
				}
		System.out.println("Exception Type : "+val);

		return val;
	}

	public String getException(){
		String val =  (String)((Exception)FacesContext.getCurrentInstance().getExternalContext().
			getRequestMap().get("javax.servlet.error.exception")).toString();
		if(val==null){
			val="exception";
		}
		System.out.println("Exception : "+val);

		return val;
	}

	public String getRequestURI(){
		String val=(String)FacesContext.getCurrentInstance().getExternalContext().
				getRequestMap().get("javax.servlet.error.request_uri");
				
				if(val==null){
					val="request uri";
				}
				System.out.println("Request URI : "+val);

		return val;
	}

	public String getServletName(){
		String val=(String)FacesContext.getCurrentInstance().getExternalContext().
				getRequestMap().get("javax.servlet.error.servlet_name");
		if(val==null){
			val="servletname";
		}
		System.out.println("Servlet Name : "+val);

		return val;
	}


}
