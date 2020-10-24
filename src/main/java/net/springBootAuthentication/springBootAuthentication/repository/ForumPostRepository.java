package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.springBootAuthentication.springBootAuthentication.model.ForumPostModel;

public interface ForumPostRepository extends JpaRepository<ForumPostModel, Long>{
    
}
