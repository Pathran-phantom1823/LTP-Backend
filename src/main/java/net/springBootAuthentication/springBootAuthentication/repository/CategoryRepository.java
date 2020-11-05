package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.CategoryModel;
@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long>{
    
}