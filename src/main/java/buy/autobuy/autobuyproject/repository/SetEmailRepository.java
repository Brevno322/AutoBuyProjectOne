package buy.autobuy.autobuyproject.repository;

import buy.autobuy.autobuyproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface SetEmailRepository extends JpaRepository<User,Integer> {
    @Modifying
    @Query(value = "INSERT INTO client(email) VALUES (:email)",
            nativeQuery = true)

    void addUserEmail( @Param("email") String email);
    @Modifying

    @Query(value = "UPDATE client SET email=:newEmail WHERE(email=:email)",nativeQuery = true)
    Optional<Integer> newEmail(@Param("email")String login, @Param("newEmail") String newLogin);
}
