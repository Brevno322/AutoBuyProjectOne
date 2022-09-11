package buy.autobuy.autobuyproject.repository;

import buy.autobuy.autobuyproject.entity.InfoClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface InfoUserRepository extends JpaRepository<InfoClient,Integer> {
    @Modifying
    @Query(value = " INSERT INTO client_info(id,name,phone,surname,country,city,gender,age) " +
            "VALUES (:id,:name,:phone,:surname,:country,:city,:gender,:age)",
            nativeQuery = true)

    void addInfoUser(@Param("id")int id,@Param("name")String name, @Param("phone")String phone, @Param("surname")String surname
            , @Param("country")String country, @Param("city")String city,@Param("gender")String gender,@Param("age")String age);

}
