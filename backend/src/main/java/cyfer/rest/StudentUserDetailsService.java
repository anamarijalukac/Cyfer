package cyfer.rest;

import cyfer.service.impl.ShelterService;
import cyfer.service.impl.WalkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentUserDetailsService implements UserDetailsService {

    @Autowired
    private WalkerService walkerService;

    @Autowired
    private ShelterService shelterService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return new User(username, password preko service-a, authorities(username));
        return null;
    }

    private List<GrantedAuthority> authorities(String username) {
        return null;
    }
}

