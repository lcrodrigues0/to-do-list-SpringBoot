package br.com.lcrodrigues0.todolist.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired                                  // Automatically injects dependencies.
    private IUserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
    
    @SuppressWarnings("rawtypes")
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel user){
        var userFound = this.userRepository.findByUsername(user.getUsername());

        if(userFound != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists.");
        }

        var userCreated = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
    
}
