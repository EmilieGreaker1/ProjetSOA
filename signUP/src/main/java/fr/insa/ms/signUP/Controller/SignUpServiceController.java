package fr.insa.ms.signUP.Controller;

import fr.insa.ms.signUP.Entity.User;
import fr.insa.ms.signUP.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class SignUpServiceController {


    @Autowired
    private final UserService userService;

    public SignUpServiceController(UserService userService) {
        this.userService = userService;
    }

    private List<User> users = new ArrayList<>();

    // Method to GET all users (To test if POST is working)
    @GetMapping("/users")
    public List<User> getAllStudents() {
        return users;
    }

    // Method to ADD a user
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/sign-up")
    public User addUser(@RequestBody User user) {
        // User newUser = userService.createUser(user);
        return userService.createUser(user);
    }


        @PostMapping("/signup")
        public ResponseEntity<?> registerUser(@RequestBody User user) {
            // Valida los datos y guarda el usuario
            // Puedes agregar lógica para validar y guardar el usuario en la base de datos.
            if (user.getFirstName() == null || user.getEmail() == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Missing required fields"));
            }
            // Supongamos que todo esta bien
            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        }



    // OTHERS METHODS

    // Method GET to find a student by ID
    @GetMapping("/users/{userID}")
    public User getStudentById(@PathVariable String userID) {
        for (User user : users) {
            if (Objects.equals(user.getNickName(), userID)) {
                return user;
            }
        }
        return null; // return null if student is not found
    }

    // Method to UPDATE a student by ID
    @PutMapping("/users/{userID}")
    public User updateStudentById(@PathVariable String nickName, @RequestBody User userUpdate) {
        for (User user : users) {
            if (Objects.equals(user.getNickName(), nickName)) {
                user.setNickName(nickName);
                user.setFirstName(userUpdate.getFirstName());
                user.setLastName(userUpdate.getLastName());
                user.setEmail(userUpdate.getEmail());
                user.setPassword(userUpdate.getPassword());
                user.setUserType(userUpdate.getUserType());
                return user;
            }
        }
        return null; // return null if student is not found
    }

    // Method to DELETE a student by ID
    @DeleteMapping("/users/{userID}")
    public String deleteStudentById(@PathVariable String userID) {
        for (User user : users) {
            if (Objects.equals(user.getNickName(), userID)) {
                users.remove(user);
                return "User " + userID + " has been deleted.";
            }
        }
        return null; // return null if student is not found
    }
}