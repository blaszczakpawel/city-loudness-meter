package pl.pk.project.pz.sound_intensity.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import pl.pk.project.pz.sound_intensity.dao.entity.User;
import pl.pk.project.pz.sound_intensity.manager.UserManager;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private UserManager userManager;

    @Autowired
    public UserApi(UserManager userManager){
        this.userManager=userManager;
    }

    @PostMapping
    public ResponseEntity<Void> AddUser(@Valid @RequestBody User user){
        userManager.AddUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userManager.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<User> getUserByUsername(@RequestParam(name = "name") String Username){
        return new ResponseEntity<>(userManager.getUserByUsername(Username),HttpStatus.OK);
    }
}
