package pl.pk.project.pz.sound_intensity.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pk.project.pz.sound_intensity.dao.UserRepo;
//import pl.pk.project.pz.sound_intensity.dao.entity.User;

import static java.util.Collections.emptyList;


@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepository)
    {
        this.userRepo = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        pl.pk.project.pz.sound_intensity.dao.entity.User user  = userRepo.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(),user.getPassword(),emptyList());
    }


}
