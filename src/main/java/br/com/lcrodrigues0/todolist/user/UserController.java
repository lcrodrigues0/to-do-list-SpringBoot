package br.com.lcrodrigues0.todolist.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @PostMapping("/")
    public UserModel create(@RequestBody UserModel user){
        var userCreated = this.userRepository.save(user);
        
        return userCreated;
    }
    
}
