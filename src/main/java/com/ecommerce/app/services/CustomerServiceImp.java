package com.ecommerce.app.services;

import com.ecommerce.app.entity.City;
import com.ecommerce.app.entity.Contry;
import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.paging.CustomerPage;
import com.ecommerce.app.repository.CityRepository;
import com.ecommerce.app.repository.ContryRepository;
import com.ecommerce.app.repository.CustomerCriteriaRepository;
import com.ecommerce.app.repository.CustomerRepository;
import com.ecommerce.app.search.CustomerCriteriaSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ContryRepository contryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CustomerCriteriaRepository customerCriteriaRepository;


    @Override
    public Page<Customer> customersListWithPagination(CustomerCriteriaSearch customerCriteriaSearch, CustomerPage customerPage) {
        return customerCriteriaRepository.findAllWithFilters(customerPage , customerCriteriaSearch);
    }

    @Override
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> listAll(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> {
                    throw  new RuntimeException("this city with id " + cityId + " is not d" +
                            "found");
                });
        return customerRepository.findAllByCity(city);
    }

    @Override
    public Customer save(Long countryId, Long cityId, Customer customer, MultipartFile multipartFile) throws IOException {

        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> {
                   throw new RuntimeException("this city with " + cityId +
                           " is not found ");
                });

        Contry contry = contryRepository.findById(countryId)
                .orElseThrow(() -> {
                    throw new RuntimeException("this country with " + countryId  +
                            " is not found");
                });

        customerRepository.findByEmail(customer.getEmail())
                .ifPresent((c) -> {
                    throw new RuntimeException("this customer with this email " + c.getEmail() +
                            " is already exist");
                });
        customer.setCity(city);
        customer.getCity().setContry(contry);
        customer.setImage(multipartFile.getBytes());
        return customerRepository.save(customer);
    }

    @Override
    public Customer get(Long countryId, Long cityId, Long customerId) {
         cityRepository.findById(cityId)
                .orElseThrow(() -> {
                    throw new RuntimeException("this city with " + cityId +
                            " is not found");
                });

        return customerRepository.findByCustomerIdAndCityCityId(customerId, cityId)
                .orElseThrow(() -> {
                    throw new RuntimeException("this customer is not found");
                });
    }

    @Override
    public Customer update(Long countryId, Long cityId, Long customerId, Customer customer, MultipartFile multipartFile) throws IOException {
        City maybeCity = cityRepository.findById(cityId)
                .orElseThrow(() -> {
                    throw new RuntimeException("this city with this id " + cityId +
                            " is not found");
                });
        Customer maybeCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> {
                   throw new RuntimeException("this customer with the id" + customerId +
                           " is not found");
                });
        Optional<Customer> checkCustomerEmail = this.customerRepository.findByEmail(customer.getEmail());
        Optional<Customer> customerById = this.customerRepository.findById(customerId);
        if(checkCustomerEmail.isPresent() && customerById.get().getCustomerId() != customerById.get().getCustomerId()) {
           throw new RuntimeException("this customer is already token");
        }
        maybeCustomer.setCustomerId(customer.getCustomerId());
        maybeCustomer.setCity(customer.getCity());
        maybeCustomer.setEmail(customer.getEmail());
        maybeCustomer.setImage(multipartFile.getBytes());
        maybeCustomer.setAddress(customer.getAddress());
        maybeCustomer.setFirstName(customer.getFirstName());
        maybeCustomer.setLastName(customer.getLastName());
        maybeCustomer.setMygender(customer.getMygender());
        maybeCustomer.setCity(maybeCity);
        maybeCustomer.setPassword(customer.getPassword());
        maybeCustomer.setZipcode(customer.getZipcode());
        return maybeCustomer;
    }

    @Override
    public void delete(Long customerId) {
         customerRepository.findById(customerId).
                orElseThrow(() -> {
                    throw new RuntimeException("this customer with id " + customerId +
                     " is not found");
                });
        customerRepository.deleteById(customerId);
    }

    @Override
    public byte[] compressFile(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1204];
        if(!deflater.finished()) {
           int count = deflater.deflate(buffer);
           byteArrayOutputStream.write(buffer , 0 , count);
        }
        try {
           byteArrayOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Compress Image Byte Size " + byteArrayOutputStream.toByteArray().length);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public byte[] decompressFile(byte[] data) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        inflater.finished();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1204];
        if(!inflater.finished()) {
            int count = inflater.inflate(buffer);
            byteArrayOutputStream.write(buffer , 0 , count);
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Decompress Image with Byte Size " + byteArrayOutputStream.toByteArray().length);
        return byteArrayOutputStream.toByteArray();
    }
}
