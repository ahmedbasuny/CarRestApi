package com.evolvice.task.evolvicecar.car;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description="Car Details")
public class Car {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ApiModelProperty(notes="Brand shouldn't be blank")
	private String brand;
	
	@ApiModelProperty(notes="Model shouldn't be blank")
	private String model;
	
	@ApiModelProperty(notes="Production Year shouldn't be blank")
	private String productionYear;
	
	private String modelDetails;
	
	public Car() {}
	
	public Car(String brand, String model, String productionYear, String modelDetails) {
		super();
		this.brand = brand;
		this.model = model;
		this.productionYear = productionYear;
		this.modelDetails = modelDetails;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(String productionYear) {
		this.productionYear = productionYear;
	}

	public String getModelDetails() {
		return modelDetails;
	}

	public void setModelDetails(String modelDetails) {
		this.modelDetails = modelDetails;
	}
	
	
}
