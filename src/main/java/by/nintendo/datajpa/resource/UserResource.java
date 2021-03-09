package by.nintendo.datajpa.resource;


import by.nintendo.datajpa.model.User;
import by.nintendo.datajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserResource {

    @Autowired
    private UserService userService;


    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
        User user = userService.getUserByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping(path = "/{name}")
    public ResponseEntity<Object> updateUser(@PathVariable("name") String name, @Valid @RequestBody User user) {
        userService.updateUser(name, user);
        return new ResponseEntity<>("User update", HttpStatus.OK);
    }


    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<String> authorithation(@RequestBody User user) {
        if (userService.auth(user)!=null) {
            return new ResponseEntity<>(userService.auth(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User create", HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/{name}")
    public ResponseEntity<Object> deleateUserByName(@PathVariable("name") String name) {
        userService.deleateUser(name);
        return new ResponseEntity<>("User delete", HttpStatus.OK);
    }

}



