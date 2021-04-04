package com.ecommerce.app.services;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;


import com.ecommerce.app.paging.UsersPage;
import com.ecommerce.app.search.UsersCriteriaSearch;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.entity.Users;

public interface UsersService {
	
	public Users save(Users users , MultipartFile file) throws IOException;

	public List<Users> listAll();
	
	public Page<Users> findAll(UsersPage usersPage , UsersCriteriaSearch usersCriteriaSearch);
	
	public Users findById(Long userId);
	
	public Users update(Long userId , Users users , MultipartFile file) throws IOException;
	
	public void delete(Long userId);

	public Users findByFullname(String fullname);
	
	public Users findByFullNameAndPassword(String fullname , String password);
	
	public byte[] compressFile(byte[] data);
	
	public byte[] decompressFile(byte[] data) throws DataFormatException;

	public Users findByEmail(String email);
}
