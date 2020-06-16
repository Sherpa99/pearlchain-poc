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
@Table(name="act_re_deployment",schema="dbo")
public class ActReDeployment {
	
	  @Id
	  @Column(name = "id_")
	  private String id;
	  @Column(name = "name_")
	  private String name;
	  @Column(name = "category_")
	  private String category;
	  @Column(name = "tenant_id_")
	  private String tenantId;
	  @Column(name = "deploy_time_")
	  private Date date;
	  
}
