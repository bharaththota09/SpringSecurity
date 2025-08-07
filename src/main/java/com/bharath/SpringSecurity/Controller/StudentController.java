package com.bharath.SpringSecurity.Controller;

import com.bharath.SpringSecurity.Model.Login;
import com.bharath.SpringSecurity.Model.Student;
import com.bharath.SpringSecurity.Model.Userinfo;
import com.bharath.SpringSecurity.Service.JwtService;
import com.bharath.SpringSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    private  List<Student> students = new ArrayList<>(List.of(new Student(1,"Bharath")));
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return  students;
    }

    @GetMapping("/students/{sid}")
    public Student getStudent(@PathVariable int sid){
        return  students.stream().filter(student->student.getSid() ==sid).findAny().get();
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student){
          students.add(student);
          return  student;
    }

   @PostMapping("/register")
    public String  registerUser(@RequestBody Userinfo user){

        return  userService.registerUser(user);
   }

   @PostMapping("/login")
    public String userLogin(@RequestBody Login userData){
       if( authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userData.getUsername(),userData.getPassword())).isAuthenticated()){

           return  jwtService.generateToken(userData);
       }
       else{
           throw  new UsernameNotFoundException("User Not Found");
       }

   }

}
