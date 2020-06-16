package com.ibm.developer.pearlchaindbm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="stockhistory",schema="dbo")
public class StockHistory {
	
	@Id
	@Column(name="productid")
	private Long productId;
	@Column(name="warehouse")
	private String warehouse;
	@Column(name="locationid")
	private Long locationId;
	@Column(name="lotid")
	private Long lotId;
	@Column(name="quantity")
	private Long quantity;
	@Column(name="purchaselistprice")
	private Float purchaseListPrice;
	@Column(name="stockdate")
	private Date stockDate;	
	@Column(name="quality")
	private String quality;	
	@Column(name="salelistprice")
	private Float saleListPrice;
}
