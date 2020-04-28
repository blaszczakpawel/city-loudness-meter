package pl.pk.project.pz.sound_intensity.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pk.project.pz.sound_intensity.dao.UserRepo;
import pl.pk.project.pz.sound_intensity.dao.entity.User;

import java.util.List;

@Service
public class UserManager {

    UserRepo userRepo;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserManager(UserRepo userRepo,PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
    }


    public void AddUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepo.save(user);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }
}
