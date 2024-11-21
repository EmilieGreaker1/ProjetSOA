package fr.insa.ms.signUP.Service;

import fr.insa.ms.signUP.Entity.User;
import fr.insa.ms.signUP.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsersByfirstName(String firstName){
        return userRepository.findByFirstName(firstName);
    }

    // private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Crear un nuevo usuario
    public User createUser(User user) {
        // Validar si ya existe un usuario con el mismo email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está en uso.");
        }

        // GENERATE NICKNAME BEFORE SAVING NEW USER
        if (user.getNickName() == null || user.getNickName().isEmpty()) {
            user.setNickName((user.getFirstName() + user.getLastName()).replaceAll(" ", ""));
        }

        // Guardar el nuevo usuario
        return userRepository.save(user);
    }


}
