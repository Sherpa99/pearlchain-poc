package com.ibm.developer.pearlchaindbm.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ibm.developer.pearlchaindbm.domain.StockHistory;

public interface IStockHistoryRepo extends CrudRepository<StockHistory, Long> {
	
	@Query("select c from StockHistory c")
	Page<StockHistory> findAllPage(Pageable pageable);
	
	@Query("select c from StockHistory c")
	Slice<StockHistory> findAllSlice(Pageable pageable);

}
