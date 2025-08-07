package com.bharath.SpringSecurity.Dao;

import com.bharath.SpringSecurity.Model.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Userinfo,Integer> {

   public Userinfo findByUserName(String userName);
}
