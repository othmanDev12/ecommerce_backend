package com.ecommerce.app.controller;


import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import com.ecommerce.app.paging.UsersPage;
import com.ecommerce.app.search.UsersCriteriaSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.entity.Users;
import com.ecommerce.app.services.UsersService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	private  UsersService usersServices;
	/*@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<Users> login(@RequestBody Users users) {
		authenticate(users.getEmail() , users.getPassword());
		Users loginUsers = usersServices.findByEmail(users.getEmail());
		UserPrincipal userPrincipal = new UserPrincipal(loginUsers);
		HttpHeaders httpHeaders = JwtHeaders(userPrincipal);
		return new ResponseEntity<>(loginUsers , httpHeaders , OK);
	}

	private HttpHeaders JwtHeaders(UserPrincipal userPrincipal) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JWT_TOKEN_HEADER , jwtTokenProvider.generateJwtToken(userPrincipal));
		return httpHeaders;
	}


	private void authenticate(String email, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email , password));
	}

	 */

	@PostMapping("/save")
	public ResponseEntity<Users> saveUser(@RequestParam MultipartFile file , @ModelAttribute Users users) throws IOException {
	
	Users createUsers = usersServices.save(users, file);
		
		try {
			return ResponseEntity.status(CREATED).body(createUsers);
		} catch (Exception e) {
			return ResponseEntity.status(BAD_REQUEST).build();
		}
	}

	@GetMapping("/listUsers")
	public ResponseEntity<List<Users>> listAllUsers() {
		return new ResponseEntity<>(usersServices.listAll() , OK);
	}
	
	@GetMapping("/{id}")
	public Users getUserById(@PathVariable Long id) throws DataFormatException, IOException {
		return usersServices.findById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Users> updateUser(@RequestParam MultipartFile file, @PathVariable Long id , @ModelAttribute Users users) throws IOException {
	Users updateUsers = usersServices.update(id, users , file);
		try {
			return ResponseEntity.status(CREATED).body(updateUsers);
		} catch (Exception e) {
			return ResponseEntity.status(BAD_REQUEST).build();
		}
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		usersServices.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
	
	
	@GetMapping("/allUsers")
	public ResponseEntity<Page<Users>> findAllUsers(UsersPage usersPage , UsersCriteriaSearch usersCriteriaSearch) {
		return new ResponseEntity<>(usersServices.findAll(usersPage , usersCriteriaSearch) , OK);
	}

}
