package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.CategoryTransactionsModel;



@Repository
public interface CategoryTransactionsRepository extends JpaRepository<CategoryTransactionsModel, Long>{
   
}
