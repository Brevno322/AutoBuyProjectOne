package buy.autobuy.autobuyproject.repository;

import buy.autobuy.autobuyproject.entity.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromoRepository extends JpaRepository<Automobile,Integer> {
    @Modifying
    @Query(value = " INSERT INTO auto(id_client,type,kpp,colour,marka,engine) " +
            "VALUES (:id_client,:type,:kpp,:colour,:marka,:engine)",
    nativeQuery = true)

    void addPromo(@Param("id_client")int id_client,@Param("type")String type,@Param("kpp")String kpp,@Param("colour")String colour
    ,@Param("marka")String marka,@Param("engine")double engine);
    @Query (value="SELECT id,type,kpp,colour,marka,engine FROM auto WHERE (id_client=:id_client)",nativeQuery = true)
    List<Automobile>searchAuto(@Param("id_client")int id_client);

    @Query (value="SELECT id,type,kpp,colour,marka,engine FROM auto WHERE (id_client=:id_client)",nativeQuery = true)
    List<Automobile>watchDeclaration(@Param("id_client")int id_client);
    @Modifying

    @Query(value = "DELETE  FROM auto WHERE (id=:id)",nativeQuery = true)
    Optional<Integer>deleteDeclaration(@Param("id")int id);

@Query(value = "SELECT id FROM auto WHERE (id_client=:id_client)",nativeQuery=true)
List<Integer>showIdDeclaration(@Param("id_client")int id_client);
    @Query(value = "UPDATE auto SET type=:newType,kpp=:newKpp,colour=:newColour,marka=:newMarka,engine=:newEngine WHERE(type=:type,kpp=:kpp,colour=:colour,marka=:marka,engine=:engine)",nativeQuery = true)
    Optional<Integer> UpdateDeclaration(@Param("type")String type, @Param("newType") String newType,
                                        @Param("kpp")String kpp, @Param("newKpp") String newKpp,
                                        @Param("colour")String colour, @Param("newColour") String newColour,
                                        @Param("marka")String marka, @Param("newMarka") String newMarka,
                                        @Param("engine")double engine, @Param("newEngine") double newEngine);


}

