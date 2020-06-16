package com.ibm.developer.pearlchaindbm.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ibm.developer.pearlchaindbm.domain.ActReDeployment;

/*public interface IActReDeploymentRepo extends CrudRepository<ActReDeployment, String> {

}*/
public interface IActReDeploymentRepo extends PagingAndSortingRepository<ActReDeployment, String> {

	@Query("select c from ActReDeployment c")
	Page<ActReDeployment> findAllPage(Pageable pageable);
}