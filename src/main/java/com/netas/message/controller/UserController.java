package com.netas.message.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.netas.message.model.User;
import com.netas.message.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String getIndex() {
        return "login";
    }

    @GetMapping("/user/{name}")
    @ResponseBody
    public User getUserByName(@PathVariable String name, HttpServletResponse response) {
        User user = userService.findByName(name);
        if (user != null)
            return user;
        response.setStatus(400);
        return null;
    }

    @GetMapping("/user/{name}/{password}")
    @ResponseBody
    public User getUserByName(@PathVariable("name") String name, @PathVariable("password") String password, HttpServletResponse response) {
        User user = userService.findByNameAndPassword(name, password);
        if (user != null) {
            return user;
        } else if (userService.findByName(name) != null) {
            response.setStatus(400);
        } else {
            response.setStatus(404);
        }
        return null;
    }

    @PutMapping("/user/{name}")
    @ResponseBody
    public User updateUserByName(@PathVariable String name, @RequestBody User user) {
        User oldUser = userService.findByName(name);
        if (oldUser == null)
            return null;
        return userService.saveUser(user);
    }

    @GetMapping("/user")
    @ResponseBody
    public List<User> getAllUser() {
        return userService.findAllUsers();
    }

    @PostMapping("/user")
    @ResponseBody
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/user/{name}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> deleteUserByName(@PathVariable String name) {
        userService.deleteUserById(name);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/topic/{topic}/{group}")
    @ResponseBody
    public void changeTopic(@PathVariable("topic") String topic,@PathVariable("group") String group) {
        userService.changeTopic(topic,group);
    }
}
