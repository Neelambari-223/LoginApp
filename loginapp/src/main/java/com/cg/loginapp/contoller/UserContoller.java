package com.cg.loginapp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.loginapp.entity.User;
import com.cg.loginapp.model.UserDTO;
import com.cg.loginapp.service.AdminServices;
import com.cg.loginapp.service.UserServices;

// Sai Vineeth neeli 

@RestController
public class UserContoller {
	
	@Autowired
	private UserServices service;
	
	@Autowired
	private AdminServices adminService;
    
	
	@PostMapping(value="/SignUp")
	public ResponseEntity<String> signUpPage(@RequestBody UserDTO userDto) throws SignUpExceptions
	{
		service.addSignUpDetails(userDto);
		return new ResponseEntity<>("Added Successfully",HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/Login/{emailId}/{password}/{userType}")
	public ResponseEntity<String> loginPage(@PathVariable String emailId , @PathVariable String password , @PathVariable String userType) throws SignUpExceptions,NullPointerException
	{
        String s = service.login(emailId,password,userType);
         return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(value="/User/forgotPassword/{emailId}/{userType}/{securityAns}/{newPassword}/{reTypePassword}")
	public ResponseEntity<String> forgotPassWord(@PathVariable String emailId , @PathVariable String userType , @PathVariable String securityAns ,@PathVariable String newPassword,@PathVariable String reTypePassword) throws SignUpExceptions,NullPointerException
	{
		String s = service.forgotPassword(emailId, userType, securityAns, newPassword, reTypePassword);
		return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/details")
	public List<User> details()
	{
		return service.getDetails();
		
	}
	
	
	@PostMapping(value="/admin/addUser") 
	public ResponseEntity<String> adminAddUser(@RequestBody UserDTO userdto) throws SignUpExceptions
	{
		String s = adminService.addUser(userdto);
		return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
	}
		
	
	@PutMapping(value="/admin/{emailId}/{userType}")
	public ResponseEntity<String> adminUpdateUser(@PathVariable String emailId , @PathVariable String userType ,@RequestBody UserDTO userdto) throws UserNotFoundException
	{ 
		String s = adminService.updateUser(emailId, userType, userdto);
		return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping(value="/admin/getAllUser") //done
	public List<User> adminGetAllUser()
	{
		return adminService.listAllUsers();
	}
	
/*/@GetMapping(value="/admin/getById/{emailId}/{userType}")
	public UserDTO adminGetUserById(@PathVariable String emailId , @PathVariable String userType) throws UserNotFoundException
	{
		return adminService.listUser(emailId,userType);
	}*/
	
	@DeleteMapping(value="/admin/deleteById/{emailId}/{userType")
	public String adminDeleteUserById(@PathVariable String emailId , @PathVariable String userType) throws UserNotFoundException
	{
		return adminService.deleteUser(emailId, userType);
	}
}
