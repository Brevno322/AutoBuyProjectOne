package buy.autobuy.autobuyproject.repository;

import buy.autobuy.autobuyproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface SetPasswordRepository extends JpaRepository<User,Integer> {
    @Modifying
    @Query(value = "INSERT INTO client(password) VALUES (:password)",
            nativeQuery = true)

    void addUserPassword( @Param("password") String password);
    @Modifying

    @Query(value = "UPDATE client SET password=:newPassword WHERE(password=:password)",nativeQuery = true)
    Optional<Integer> newPassword(@Param("password")String login, @Param("newPassword") String newPassword);
    @Query(value = "SELECT password FROM client WHERE (login=:login)", nativeQuery = true)
    Optional<String> showPassword(@Param("login") String login);
}
