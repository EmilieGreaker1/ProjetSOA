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


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User createUser(User user) {
        // VERIFY IF USER EXISTS
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("This email is already in use.");
        }

        // GENERATE NICKNAME BEFORE SAVING NEW USER
        if (user.getNickName() == null || user.getNickName().isEmpty()) {
            user.setNickName((user.getFirstName() + user.getLastName()).replaceAll(" ", ""));
        }

        // SAVE THE USER
        new IllegalArgumentException("This email is already in use.");
        return userRepository.save(user);
    }


}
