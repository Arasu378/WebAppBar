package com.arasu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import utils.AndroidMultiPartEntity;
import utils.Utils;

import com.sun.faces.util.Util;

import model.LiquorData;

@ManagedBean
@ViewScoped
public class LiquorCategoryInsertController implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
     String quantity;
     String category;
     String pic;
     String subcategory;
     String max_height;
     String min_height;
     int UserProfileId;
     private int BarId;
     private int SectionId;
     private String authorizationKey=null;

     @ManagedProperty("#{liquorService}")
     private LiquorData liqData;
public LiquorCategoryInsertController(){
	FacesContext context = FacesContext.getCurrentInstance();
	this.liqData=new LiquorData();
	Object	authKey=context.getExternalContext().getSessionMap().get("AuthorizationKey");
	authorizationKey=(String)authKey;
	name=(String)context.getExternalContext().getSessionMap().get("LiquorNameInsert");
	quantity=(String)context.getExternalContext().getSessionMap().get("LiquorQuantityInsert");
	category=(String)context.getExternalContext().getSessionMap().get("CategoryInsert");
	pic=(String)context.getExternalContext().getSessionMap().get("LiquorPictureURLInsert");
	subcategory=(String)context.getExternalContext().getSessionMap().get("SubCategoryInsert");
	max_height=(String)context.getExternalContext().getSessionMap().get("MaxValueInsert");
	min_height=(String)context.getExternalContext().getSessionMap().get("MinValueInsert");
	UserProfileId=(Integer)context.getExternalContext().getSessionMap().get("UserProfileId");
	BarId=(Integer)context.getExternalContext().getSessionMap().get("BarId");
	SectionId=(Integer)context.getExternalContext().getSessionMap().get("SectionId");
	System.out.println("Values are : "+name+" / "+quantity+" / "+category+" / "+subcategory+" / "+max_height+" / "+min_height+" / "+UserProfileId+" / "+pic);

}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getQuantity() {
	return quantity;
}
public void setQuantity(String quantity) {
	this.quantity = quantity;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getPic() {
	return pic;
}
public void setPic(String pic) {
	this.pic = pic;
}
public String getSubcategory() {
	return subcategory;
}
public void setSubcategory(String subcategory) {
	this.subcategory = subcategory;
}
public String getMax_height() {
	return max_height;
}
public void setMax_height(String max_height) {
	this.max_height = max_height;
}
public String getMin_height() {
	return min_height;
}
public void setMin_height(String min_height) {
	this.min_height = min_height;
}
public int getUserProfileId() {
	return UserProfileId;
}
public void setUserProfileId(int userProfileId) {
	UserProfileId = userProfileId;
}
public LiquorData getLiqData() {
	return liqData;
}
public void setLiqData(LiquorData liqData) {
	this.liqData = liqData;
}
public String save(){
	byte[] bytearrayprofile=getbytefromurl(pic);
	String parValue=liqData.getParLevel();
	String distributorName=liqData.getDistributorName();
	String price=liqData.getPrice();
	String binNumber=liqData.getBinNumber();
	String productCode=liqData.getProductCode();
	String shots=liqData.getShots();
	System.out.println("Insert Values are: parvalue: "+parValue+" / distribut Name: "+distributorName+" / price : "+price+
			"/ binNumber : "+binNumber+" / product code: "+productCode+" / shots: "+shots+" / proid: "+UserProfileId+" / barid :"+BarId+
			" / secitonid: "+SectionId+" / name: "+name+" / quantity : "+quantity+" / category : "+category+" / subcategory: "+subcategory);
	
	
	if(UserProfileId!=0&&SectionId!=0&&BarId!=0&&name!=null&&!name.isEmpty()&&quantity!=null &&!quantity.isEmpty()
			&&category!=null &&!category.isEmpty()&&subcategory!=null && !subcategory.isEmpty()&&parValue!=null&&!parValue.isEmpty()
			&&distributorName!=null &&!distributorName.isEmpty()&&price!=null &&!price.isEmpty()&&binNumber!=null&&!binNumber.isEmpty()
			&&productCode!=null &&!productCode.isEmpty()&&shots!=null&&!shots.isEmpty()&&min_height!=null &&!min_height.isEmpty()
			&&max_height!=null &&!max_height.isEmpty()){
		String output=saveliquordata(bytearrayprofile,UserProfileId,BarId,SectionId,name,quantity,category,subcategory,parValue,distributorName,
				price,binNumber,productCode,shots,min_height,max_height);
		if(output!=null){
			JSONObject obj=new JSONObject(output);
			boolean issuccess=obj.getBoolean("IsSuccess");
			String message=obj.getString("Message");
			if(issuccess){
				String msg="Successfully Added to the list";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return  "mbottleslist.jsf?faces-redirect=true";
			}else{
				String msg=message;
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return  "mliquorcategoryinsert.jsf?faces-redirect=true";

			}
		}else{
			String msg="Something occured!";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return  "mliquorcategoryinsert.jsf?faces-redirect=true";

		}
		
	}else{
		String msg="Please fill all details!";
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return  "mliquorcategoryinsert.jsf?faces-redirect=true";

	}
	
	
	
}
@SuppressWarnings("deprecation")
private String saveliquordata(byte[] bytearrayprofile, int userProfileId2, int barId2, int sectionId2,
		String name2, String quantity2, String category2, String subcategory2,
		String parValue, String distributorName, String price,
		String binNumber, String productCode, String shots, String min_height2, String max_height2) {
	String output;
	String finaloutput=null;
	 String responseString = null;
	try{

	        HttpClient httpclient = new DefaultHttpClient();
	        String url = Utils.END_URL+ "/insertUserLiquorlistM";
	        HttpPost httppost = new HttpPost(url);
	        httppost.setHeader("Authorization", "Kyros "+authorizationKey);

	        try {
	            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
	                    new AndroidMultiPartEntity.ProgressListener() {

	                        @Override
	                        public void transferred(long num) {
	                        }
	                    });




	            double minval=Double.parseDouble(min_height2);
	            double maxval=Double.parseDouble(max_height2);
//	            minval=minval/100;
//	            maxval=maxval/100;
	            String fminval=String.valueOf(minval);
	            String fmaxval=String.valueOf(maxval);
	            entity.addPart("image", new ByteArrayBody(bytearrayprofile, userProfileId2 + "liq.png"));
	            entity.addPart("userprofileid", new StringBody(String.valueOf(userProfileId2), ContentType.TEXT_PLAIN));
	            entity.addPart("barid", new StringBody(String.valueOf(barId2), ContentType.TEXT_PLAIN));
	            entity.addPart("sectionid", new StringBody(String.valueOf(sectionId2), ContentType.TEXT_PLAIN));
	            entity.addPart("liquorname", new StringBody(name2, ContentType.TEXT_PLAIN));
	            entity.addPart("liquorquantitiy", new StringBody(quantity2, ContentType.TEXT_PLAIN));
	            entity.addPart("category", new StringBody(category2, ContentType.TEXT_PLAIN));
	            entity.addPart("subcategory", new StringBody(subcategory2, ContentType.TEXT_PLAIN));
	            entity.addPart("parvalue", new StringBody(parValue, ContentType.TEXT_PLAIN));
	            entity.addPart("distributorname", new StringBody(distributorName, ContentType.TEXT_PLAIN));
	            entity.addPart("price", new StringBody(price, ContentType.TEXT_PLAIN));
	            entity.addPart("binnumber", new StringBody(binNumber, ContentType.TEXT_PLAIN));
	            entity.addPart("productcode", new StringBody(productCode, ContentType.TEXT_PLAIN));
	            entity.addPart("minvalue", new StringBody(fminval, ContentType.TEXT_PLAIN));
	            entity.addPart("maxvalue", new StringBody(fmaxval, ContentType.TEXT_PLAIN));
	            entity.addPart("shots", new StringBody(shots, ContentType.TEXT_PLAIN));
	            entity.addPart("type",new StringBody("bottle",ContentType.TEXT_PLAIN));

	            long totalSize = entity.getContentLength();
	            httppost.setEntity(entity);

	            // Making server call
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity r_entity = response.getEntity();
	            try{
	                System.out.println("outputentity : "+entity.toString());
	            }catch(Exception e){
	                e.printStackTrace();
	            }

	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode == 200) {
	                // Server response
	                responseString = EntityUtils.toString(r_entity);
	            } else {
	                responseString = "Error occurred! Http Status Code: "
	                        + statusCode;

	            }
	            System.out.println("response : "+responseString);


	        } catch (ClientProtocolException e) {
	            responseString = e.toString();
	        }
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	
	
	
	return responseString;
}
public String cancel(){
	return  "mliquorlistcategory.jsf?faces-redirect=true";
}
private byte[] getbytefromurl(String pictureurl){
	URL url = null;
	try {
		url = new URL(pictureurl);
	} catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	InputStream is = null;
	try {
	  is = url.openStream ();
	  byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
	  int n;

	  while ( (n = is.read(byteChunk)) > 0 ) {
	    baos.write(byteChunk, 0, n);
	  }
	}
	catch (IOException e) {
	  System.err.printf ("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
	  e.printStackTrace ();
	  // Perform any other exception handling that's appropriate.
	}
	finally {
	  if (is != null) { try {
		is.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }
	}
	return baos.toByteArray();
}

}
