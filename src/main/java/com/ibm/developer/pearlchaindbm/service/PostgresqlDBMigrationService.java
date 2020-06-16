package com.ibm.developer.pearlchaindbm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.developer.pearlchaindbm.data.IActReDeploymentRepo;
import com.ibm.developer.pearlchaindbm.data.IStockHistoryRepo;
import com.ibm.developer.pearlchaindbm.domain.ActReDeployment;
import com.ibm.developer.pearlchaindbm.domain.StockHistory;

@Service
public class PostgresqlDBMigrationService {
	
	@Autowired
	private IActReDeploymentRepo ardRepo;
	
	@Autowired
	private IStockHistoryRepo shRepo;
	
	public ResponseEntity<Long> getAllARDCounts() {
		System.out.println("######Getting all ActReDeployment count******");
		return ResponseEntity.ok(ardRepo.count());
		
	}
	
	public ResponseEntity<Iterable<ActReDeployment>> getAllARDData() {
		System.out.println("######Getting all ActReDeployment Data******");
		return ResponseEntity.ok(ardRepo.findAll());
		
	}
	// new 
	public ResponseEntity<?> addSingleNewActReDeployment(@RequestBody ActReDeployment actReDeployment) {
		actReDeployment = ardRepo.save(actReDeployment);
		return new ResponseEntity<ActReDeployment>(actReDeployment, HttpStatus.CREATED);
	}
	public ResponseEntity<Iterable<ActReDeployment>> getAllARDDataByPage(int pageNumber, int pageSize) {
		System.out.println("@@@@@Getting into all pages retrieving section&&&&");
		
		Pageable pr = PageRequest.of(pageNumber, pageSize);
		
		return ResponseEntity.ok(ardRepo.findAll(pr).getContent());
		
	}
	
	//Insert one or more rows
	public ResponseEntity<?> addMultipleNewActReDeployment(@RequestBody Iterable<ActReDeployment> actReDeployment) {
		System.out.println("@@@@@ Inserting 1 or more row into a database &&&&");
		actReDeployment = ardRepo.saveAll(actReDeployment);
		return new ResponseEntity<Iterable<ActReDeployment>>(actReDeployment, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ActReDeployment> getArdById(String ardId) {
		return ResponseEntity.ok(ardRepo.findById(ardId).get());
	}
	
	public String getHello() {
		System.out.println("####Getting Hello World Text...");
		String str = "Hello World!";
		return str;
	}
	
	public ResponseEntity<Iterable<StockHistory>> getAllSHData() {
		System.out.println("----Getting all StockHistory Data#####");
		return ResponseEntity.ok(shRepo.findAll());
	}

	public ResponseEntity<StockHistory> getSHById(Long productId) {
		System.out.println("----Getting By Stock History ID Data ####");
		return ResponseEntity.ok(shRepo.findById(productId).get());
	}
	 
	
	public ResponseEntity<Long> getAllSHCounts() {
		System.out.println(">>>>>>Getting all StockHistoryRepo count#####");
		return ResponseEntity.ok(shRepo.count());
		
	}

}
