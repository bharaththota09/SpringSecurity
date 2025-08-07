package com.bharath.SpringSecurity.Service;

import com.bharath.SpringSecurity.Dao.UserDao;
import com.bharath.SpringSecurity.Model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public String registerUser(Userinfo user) {
        System.out.println(user.getUserId());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserName(user.getUserName());
        user.setUserId(user.getUserId());
        System.out.println(user.getPassword());
        userDao.save(user);
        return  "User Register Sucessfully";
    }


}
