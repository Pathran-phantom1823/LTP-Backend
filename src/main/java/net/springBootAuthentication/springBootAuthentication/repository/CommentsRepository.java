package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.springBootAuthentication.springBootAuthentication.model.CommentsModel;

public interface CommentsRepository extends JpaRepository<CommentsModel, Long>{
    
}
