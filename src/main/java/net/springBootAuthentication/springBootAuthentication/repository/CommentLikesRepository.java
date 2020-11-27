package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.model.CommentLikesModel;

public interface CommentLikesRepository extends JpaRepository<CommentLikesModel, Long>{
    
    @Transactional
    @Query(value = "{call getLikes(:commentId, :likeById)}", nativeQuery = true)
    Long getLikes(@Param("commentId") Long commentId, @Param("likeById") Long likeById);
}
