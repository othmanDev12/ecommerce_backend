package com.ecommerce.app.controller;

import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.paging.CustomerPage;
import com.ecommerce.app.repository.CustomerRepository;
import com.ecommerce.app.search.CustomerCriteriaSearch;
import com.ecommerce.app.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/listCustomers")
    public ResponseEntity<Page<Customer>> listAll(CustomerPage customerPage , CustomerCriteriaSearch customerCriteriaSearch) {
        return new ResponseEntity<>(customerService.customersListWithPagination(customerCriteriaSearch , customerPage) , HttpStatus.OK);
    }


}
