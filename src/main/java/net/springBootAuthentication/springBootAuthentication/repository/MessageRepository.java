package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomChats;
import net.springBootAuthentication.springBootAuthentication.model.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, Long> {
    @Query(value = "{call getRoomsById(:id)}", nativeQuery = true)
    List<CustomChats> getRoomsById(@Param("id") Long id);
}
