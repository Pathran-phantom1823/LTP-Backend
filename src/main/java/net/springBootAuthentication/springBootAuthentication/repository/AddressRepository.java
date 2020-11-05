package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.AddressModel;

@Repository
public interface AddressRepository extends JpaRepository <AddressModel, Long>{
    AddressModel addressModel = new AddressModel();

    @Query(value = "{ call spInsertAddress(:a_city, :a_country, :a_street, :a_postalcode, :a_zipcode)}", nativeQuery = true)
    List<?> insertAddress(@Param("a_city") String city, @Param("a_street") String street,
            @Param("a_postalcode") Integer postalcode, @Param("a_zipcode") Integer zipcode);
}
