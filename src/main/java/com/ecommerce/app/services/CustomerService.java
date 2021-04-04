package com.ecommerce.app.services;

import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.paging.CustomerPage;
import com.ecommerce.app.search.CustomerCriteriaSearch;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

public interface CustomerService {

    public Page<Customer> customersListWithPagination(CustomerCriteriaSearch customerCriteriaSearch , CustomerPage customerPage);

    public List<Customer> listCustomers();

    public List<Customer> listAll( Long cityId);

    public Customer save(Long countryId , Long cityId , Customer customer , MultipartFile multipartFile) throws IOException;

    public Customer get(Long countryId , Long cityId , Long customerId);

    public Customer update(Long countryId , Long cityId , Long customerId , Customer customer , MultipartFile multipartFile) throws IOException;

    public void delete(Long customerId);

    public byte[] compressFile(byte[] data);

    public byte[] decompressFile(byte[] data) throws DataFormatException;

}
