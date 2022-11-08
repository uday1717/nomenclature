package com.duetsche.nomenclature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
	private Long orderId;
	
	@Column(name = "level")
	private Integer level;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "parent")
	private String parent;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "item_include")
	private String itemInclude;
	
	@Column(name = "itemalso_include")
	private String itemalsoInclude;

	@Column(name = "rulings")
	private String rulings;
	
	@Column(name = "item_exclude")
	private String itemExclude;
	
	@Column(name = "reference")
	private String reference;
	
}
