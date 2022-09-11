package buy.autobuy.autobuyproject.repository;

import buy.autobuy.autobuyproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.OptionalInt;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query(value = "INSERT INTO client(password,login,email) VALUES (:password,:login,:email)",
            nativeQuery = true)

    void addUser(@Param("password") String password, @Param("login") String login, @Param("email") String email);

    @Query(value = "SELECT id FROM client WHERE (login=:login AND password=:password)", nativeQuery = true)
    Optional<Integer> findUser(@Param("password") String password, @Param("login") String login);


    @Query(value = "SELECT id FROM client WHERE (login=:login)", nativeQuery = true)
    Optional<Integer> checkLogin(@Param("login") String login);

    @Query(value = "SELECT id FROM client WHERE (email=:email)", nativeQuery = true)
    Optional<Integer> checkEmail(@Param("email") String email);

    @Query(value = "SELECT email FROM client WHERE (login=:login)", nativeQuery = true)
    Optional<String> showEmail(@Param("login") String login);





}

