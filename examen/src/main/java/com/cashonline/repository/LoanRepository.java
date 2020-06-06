package com.cashonline.repository;

import com.cashonline.models.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LoanRepository extends CrudRepository<Loan,Long>{
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Loan l WHERE l.user.idUser = ?1")
	public void deleteByIdUser(Long idUser);
	
	@Query("Select l from Loan l where l.user.idUser = :idUser ")
    Page<Loan> findByIdUser(@Param("idUser") Long idUser,Pageable pageable);
	
	@Query("Select l from Loan l")
    Page<Loan> findWithPage(Pageable pageable);
}
