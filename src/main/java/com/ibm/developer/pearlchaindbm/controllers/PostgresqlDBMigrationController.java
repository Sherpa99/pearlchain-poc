package com.ibm.developer.pearlchaindbm.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.developer.pearlchaindbm.data.IActReDeploymentRepo;
import com.ibm.developer.pearlchaindbm.data.IStockHistoryRepo;
import com.ibm.developer.pearlchaindbm.domain.ActReDeployment;
import com.ibm.developer.pearlchaindbm.domain.StockHistory;
import com.ibm.developer.pearlchaindbm.service.PostgresqlDBMigrationService;

@RestController
@RequestMapping("/")
public class PostgresqlDBMigrationController {

	@Autowired
	private PostgresqlDBMigrationService svc;
	
	@Autowired
	private IStockHistoryRepo shRepo;
	
	@Autowired
	private IActReDeploymentRepo ardRepo;
	// ActReDeployment Table Controller
	@GetMapping("ard/rcount")
	public ResponseEntity<Long> findARDCounts() {

		return svc.getAllARDCounts();
	}

	@GetMapping("ard/data")
	public ResponseEntity<Iterable<ActReDeployment>> findAllARDData() {

		return (svc.getAllARDData());
	}

	@GetMapping("ard/{id}")
	public ResponseEntity<ResponseEntity<ActReDeployment>> findARDById(@PathVariable String id)
			throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
			CertificateException, FileNotFoundException, IOException {
		return ResponseEntity.ok(svc.getArdById(id));
	}

	// new method
	@GetMapping("ard/page")
	public Page<ActReDeployment> loadActReDeploymentPage(
			@PageableDefault(page = 0, size = 20) @SortDefault.SortDefaults({
					@SortDefault(sort = "name", direction = Sort.Direction.DESC),
					@SortDefault(sort = "id", direction = Sort.Direction.ASC) }) Pageable pageable) {
		return ardRepo.findAllPage(pageable);
	}
	@GetMapping("ard/byparam")
	public ResponseEntity<Iterable<ActReDeployment>> findAllSHDataByPagePram(@RequestParam int pageNumber, @RequestParam int pageSize) {
		return svc.getAllARDDataByPage(pageNumber, pageSize);
	}
	
	@GetMapping("ard/bypath/{pageNumber}/{pageSize}")
	public ResponseEntity<Iterable<ActReDeployment>> findAllSHDataByPagePath(@PathVariable int pageNumber, @PathVariable int pageSize) {
		System.out.println("PageNumber: "+pageNumber +"PageSize: "+pageSize);
        Pageable pr = PageRequest.of(pageNumber, pageSize);
		
		return ResponseEntity.ok(ardRepo.findAll(pr).getContent());
		//return svc.getAllARDDataByPage(pageNumber, pageSize);
	}
	
	@PostMapping("ard/addsingle")
	public ResponseEntity<?> addNewActReDeployment(@RequestBody ActReDeployment newActReDeployment) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException {
		
		return svc.addSingleNewActReDeployment(newActReDeployment);
	}
	
	@PostMapping("ard/addmultiple")
	public ResponseEntity<?> addNewActReDeployment(@RequestBody Iterable<ActReDeployment> newActReDeployment) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException {
		
		return svc.addMultipleNewActReDeployment(newActReDeployment);
	}
	// new method ends here
	@GetMapping("test")
	public String helloWrold() {

		return svc.getHello();
	}

	// History Table Controller
	@GetMapping("sh/data")
	public ResponseEntity<Iterable<StockHistory>> findAllSHData() {
		return svc.getAllSHData();
	}
	// new method
	@GetMapping("sh/page")
	public Page<StockHistory> loadStockHistoryPage(
			@PageableDefault(page = 0, size = 100) @SortDefault.SortDefaults({
					//@SortDefault(sort = "warehouse", direction = Sort.Direction.DESC),
					@SortDefault(sort = "productId", direction = Sort.Direction.ASC) }) 
			Pageable pageable) {
		return shRepo.findAllPage(pageable);
	}

	@GetMapping(path = "/sh/slicepage")
	Slice<StockHistory> loadCharactersSlice(Pageable pageable) {
		return shRepo.findAllSlice(pageable);
	}
	// new method ends here

	@GetMapping("sh/{id}")
	public ResponseEntity<ResponseEntity<StockHistory>> findSHById(@PathVariable String id)
			throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
			CertificateException, FileNotFoundException, IOException {
		return ResponseEntity.ok(svc.getSHById(Long.parseUnsignedLong(id)));
	}

	@GetMapping("sh/count")
	public ResponseEntity<Long> findSHTotalCounts() {

		return svc.getAllSHCounts();
	}
}
