package com.ibm.developer.pearlchaindbm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PearlchaindbmApplication {

	public static void main(String[] args) {
		SpringApplication.run(PearlchaindbmApplication.class, args);
		System.setProperty("https.protocols", "SSLv3");
	}

}
