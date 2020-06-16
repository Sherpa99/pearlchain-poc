package com.ibm.developer.pearlchaindbm.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department{
	@Id
	private long id;
	private Date creationDate;	
	private String creationUser;
	private Date modificationDate;	
	private String modificationUser;	
	private int archiveStatus;	
	private String uniqueIdentifier;	
	private int version;	
	private String code;	
	private String description;	
	private String state;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "countryId", referencedColumnName = "id")
	private long countryId;
	
	public Department() {}
	public Department(long id, Date creationDate, String creationUser, Date modificationDate, String modificationUser,
			int archiveStatus, String uniqueIdentifier, int version, String code, String description, String state,
			long countryId) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.creationUser = creationUser;
		this.modificationDate = modificationDate;
		this.modificationUser = modificationUser;
		this.archiveStatus = archiveStatus;
		this.uniqueIdentifier = uniqueIdentifier;
		this.version = version;
		this.code = code;
		this.description = description;
		this.state = state;
		this.countryId = countryId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreationUser() {
		return creationUser;
	}
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	public String getModificationUser() {
		return modificationUser;
	}
	public void setModificationUser(String modificationUser) {
		this.modificationUser = modificationUser;
	}
	public int getArchiveStatus() {
		return archiveStatus;
	}
	public void setArchiveStatus(int archiveStatus) {
		this.archiveStatus = archiveStatus;
	}
	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
}
