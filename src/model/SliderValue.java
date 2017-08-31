package model;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
@ManagedBean(name = "sliderService")
@ApplicationScoped
public class SliderValue implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String sliderValue;
public SliderValue(){
	
}
public String getSliderValue() {
	return sliderValue;
}
public void setSliderValue(String sliderValue) {
	this.sliderValue = sliderValue;
}

}
