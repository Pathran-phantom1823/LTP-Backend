package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.springBootAuthentication.springBootAuthentication.model.CommentLikesModel;

public interface CommentLikesRepository extends JpaRepository<CommentLikesModel, Long>{
    
}
