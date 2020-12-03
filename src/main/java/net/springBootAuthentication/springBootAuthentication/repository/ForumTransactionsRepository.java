package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomComment;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomForum;
import net.springBootAuthentication.springBootAuthentication.model.ForumTransactionsModel;

public interface ForumTransactionsRepository extends JpaRepository<ForumTransactionsModel, Long>{

    @Transactional
    @Procedure(procedureName = "getPost")
    public List<CustomForum> getPost();
    
    
    @Query(value = "{call getForumDetails(:id)}", nativeQuery = true)
    List<CustomForum> getForumDetails(@Param("id") Long id);

    
    @Query(value = "{call getComment(:id)}", nativeQuery = true)
    List<CustomForum> getComment(@Param("id") Long id);

    

}
