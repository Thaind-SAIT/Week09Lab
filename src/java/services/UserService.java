/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.UserDB;
import java.util.List;
import models.User;

/**
 *
 * @author 808278
 */
public class UserService {
    private UserDB userDB;
    
    public UserService() {
    }
    
    public User get(String username) throws Exception {
        this.userDB = new UserDB();
        return this.userDB.getUser(username);
    }

    public List<User> getAll() throws Exception {
        this.userDB = new UserDB();
        return this.userDB.getAll();
    }

    public void update(String username, String password, String firstname, String lastname, String email) throws Exception {
        this.userDB = new UserDB();
        User user = this.userDB.getUser(username);
        if ( !password.equals("") ) user.setPassword(password);
        if ( !firstname.equals("") ) user.setFirstname(firstname);
        if ( !lastname.equals("") ) user.setLastname(lastname);
        if ( !email.equals("") ) user.setEmail(email);
        this.userDB.update(user);
    }

    public void delete(String username) throws Exception {
        this.userDB = new UserDB();
        this.userDB.delete(username);
    }

    public void insert(String username, String password, String firstname, String lastname, String email) throws Exception {
        this.userDB = new UserDB();
        User user = new User(username, password);
        if ( !firstname.equals("") ) user.setFirstname(firstname);
        if ( !lastname.equals("") ) user.setLastname(lastname);
        if ( !email.equals("") ) user.setEmail(email);
        this.userDB.insert(user);
    }
}
