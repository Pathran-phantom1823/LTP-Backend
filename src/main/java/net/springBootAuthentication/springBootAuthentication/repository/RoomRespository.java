package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomRooms;
import net.springBootAuthentication.springBootAuthentication.model.RoomModel;

public interface RoomRespository extends JpaRepository<RoomModel, Long>{

    @Transactional
	@Modifying
    @Query(value = "{call getMyRooms(:id)}", nativeQuery = true)
    List<CustomRooms> getMyRooms(@Param("id") Long id);

    @Transactional
	@Modifying
    @Query(value = "{call getEndpoints()}", nativeQuery = true)
    List<String> getEndpoints();
    
}
