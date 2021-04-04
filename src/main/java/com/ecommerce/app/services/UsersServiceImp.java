package com.ecommerce.app.services;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.ecommerce.app.paging.UsersPage;
import com.ecommerce.app.search.UsersCriteriaSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.entity.Users;
import com.ecommerce.app.repository.UserCriteriaRepository;
import com.ecommerce.app.repository.UserRepository;


@Service
public class UsersServiceImp implements UsersService  {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserCriteriaRepository userCriteriaRepository;

	
	public UsersServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	public UsersServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	/*@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users users = userRepository.findByEmail(email)
				.orElseThrow(() -> {
					throw new RuntimeException("user is not found by username: " + email);
				});
		 userRepository.save(users);
		UserPrincipal userPrincipal = new UserPrincipal(users);
		return userPrincipal;

	}

	 */

	@Override
	public Users save(Users users , MultipartFile file) throws IOException  {
		userRepository.findByEmail(users.getEmail()).ifPresent(
				u -> {
					throw new RuntimeException("this user with this email: " + u.getEmail() + " is already exsist");
				}
			);
		users.setImage(file.getBytes());
		return userRepository.save(users);
		 
	}




	@Override
	public List<Users> listAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<Users> findAll(UsersPage usersPage , UsersCriteriaSearch usersCriteriaSearch) {
		return userCriteriaRepository.findAllWithFilters(usersPage , usersCriteriaSearch);
	}
	

	@Override
	public Users findById(Long usersId)  {
		// check if the user is in the database or note
        return userRepository.findById(usersId)
            .orElseThrow(() -> {
                    throw new RuntimeException("the user not found");
                });
	}

	@Override
	public Users update(Long userId, Users users , MultipartFile file) throws IOException  {
		// check if the user is in the database or not.
		Users maybeUsers = userRepository.findById(userId).orElseThrow( () -> {
			throw new RuntimeException("the user is not found");
		}
	  );
		Optional<Users> checkEmail = userRepository.findByEmail(users.getEmail());
		Optional<Users> findBId = userRepository.findById(userId);
		if(checkEmail.isPresent() && findBId.get().getUserId() != checkEmail.get().getUserId())  {
			throw new RuntimeException("the email user is already token");
		}
		maybeUsers.setEmail(users.getEmail());
		maybeUsers.setFullname(users.getFullname());
		maybeUsers.setImage(file.getBytes());
		maybeUsers.setPassword(users.getPassword());
		return userRepository.save(maybeUsers);
		
	}

	@Override
	public void delete(Long userId) {
		Optional<Users> maybeUser = userRepository.findById(userId);
		maybeUser.orElseThrow(() -> {
			throw new RuntimeException("user the you want to delete is not found");
		});
		userRepository.deleteById(userId);
		
	}

	@Override
	public byte[] compressFile(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		if(!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	@Override
	public byte[] decompressFile(byte[] data) throws DataFormatException {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		inflater.finished();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		if(!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer , 0 , count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Decompress Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();	
	}

	@Override
	public Users findByEmail(String email) {
		return userRepository.findByEmail(email).get();
	}

	@Override
	public Users findByFullname(String fullname) {
		return userRepository.findByFullname(fullname)
				.orElseThrow(() -> {
					throw new RuntimeException("this fullname is not found");
				});
	}

	@Override
	public Users findByFullNameAndPassword(String fullname, String password) {
		return null;
	}

}
