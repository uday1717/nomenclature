package com.duetsche.nomenclature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nomenclature {

	@Id
	@Column(name = "order_Id")
	@ApiModelProperty(notes = "NAC Order ID", example = "388765") 
	private Long orderId;
	
	@Column(name = "level")
	@ApiModelProperty(notes = "NAC level info", example = "1") 
	private Integer level;
	
	@Column(name = "code")
	@ApiModelProperty(notes = "NAC code", example = "1.21") 
	private String code;
	
	@Column(name = "parent")
	@ApiModelProperty(notes = "NAC Parent info", example = "1.2") 
	private String parent;
	
	@Column(name = "description")
	@ApiModelProperty(notes = "NAC description", example = "Plant propagation") 
	private String description;
	
	@Column(name = "item_include")
	@ApiModelProperty(notes = "Gives details about NAC item group", example = "growing of non-perennial crops") 
	private String itemInclude;
	
	@Column(name = "itemalso_include")
	@ApiModelProperty(notes = "Gives details about NAC item division", example = "To inncludes service activities") 
	private String itemalsoInclude;

	@Column(name = "rulings")
	@ApiModelProperty(notes = "Includes NAC ruling info", example = "Name") 
	private String rulings;
	
	@Column(name = "item_exclude")
	@ApiModelProperty(notes = "Gives details about NAC item exclusions", example = "Exclude tobacco products") 
	private String itemExclude;
	
	@Column(name = "reference")
	@ApiModelProperty(notes = "Includes NAC ruling info", example = "Name") 
	private String reference;
	
}
