package com.bharath.SpringSecurity.Service;

import com.bharath.SpringSecurity.Dao.UserDao;
import com.bharath.SpringSecurity.Model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;

@Service
public class MyUserDetailService  implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername called with: " + username);
        Userinfo user = userDao.findByUserName(username);

        if (user == null) {
            System.out.println("User Not Found for username: " + username);
            throw new UsernameNotFoundException("User Not Found");
        }
        else {
            System.out.println("Found user: " + user.getUserName() + " / " + user.getPassword());
        }

        UserDetails user1 = new User(user.getUserName(),user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        return  user1;
    }

}
