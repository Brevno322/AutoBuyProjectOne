package buy.autobuy.autobuyproject.repository;

import buy.autobuy.autobuyproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface SetLoginRepository extends JpaRepository <User,Integer> {
    @Modifying
    @Query(value = "INSERT INTO client(login) VALUES (:login)",
            nativeQuery = true)

    void addUserLogin( @Param("login") String login);
@Modifying

    @Query(value = "UPDATE client SET login=:newLogin WHERE(login=:login)",nativeQuery = true)
    Optional<Integer> newLogin(@Param("login")String login,@Param("newLogin") String newLogin);
}
