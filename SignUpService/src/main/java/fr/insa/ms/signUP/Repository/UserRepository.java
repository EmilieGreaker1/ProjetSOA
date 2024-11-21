package fr.insa.ms.signUP.Repository;

import fr.insa.ms.signUP.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    // Encuentra usuarios por primer nombre
    List<User> findByFirstName(String firstName);

    // Encuentra usuarios por apellido
    List<User> findByLastName(String lastName);

    // Consulta personalizada para nombre y apellido
    @Query("SELECT s FROM User s WHERE s.firstName = :firstName AND s.lastName = :lastName")
    List<User> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
