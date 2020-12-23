package cyfer.rest;

import cyfer.domain.Shelter;
import cyfer.domain.Walker;
import cyfer.service.impl.ShelterService;
import cyfer.service.impl.WalkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private WalkerService walkerService;

    @Autowired
    private ShelterService shelterService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Walker walker = walkerService.getByUsername(username);
        String password;
        if(walker == null) {
            Shelter shelter = shelterService.getByUsername(username);
            password = shelter.getPassword();
        }
        else {
            password = walker.getPassword();
        }
        return new User(username, password, authorities(username));
    }

    private List<GrantedAuthority> authorities(String username) {
        if(walkerService.getByUsername(username) != null) {
            return commaSeparatedStringToAuthorityList("ROLE_WALKER");
        } else if(shelterService.getByUsername(username) != null) {
            return commaSeparatedStringToAuthorityList("ROLE_SHELTER");
        } else {
            throw new UsernameNotFoundException("No such user!!");
        }
    }
}

