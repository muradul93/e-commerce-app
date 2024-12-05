package com.murad.ecommerce.customer;

import com.ctc.wstx.util.StringUtil;
import com.murad.ecommerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer( CustomerRequest customerRequest) {

        Customer customer = customerMapper.toCustomer(customerRequest);
        customerRepository.save(customer);
        return "Customer created successfully";
    }

    public void updateCustomer(CustomerRequest customerRequest) {


        Customer customer = customerRepository
                .findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException("Cannot Update Customer. Customer not found with id: " + customerRequest.id()));

        mergeCustomer(customer,customerRequest);
        customerRepository.save(customer);


    }

    private void mergeCustomer(Customer customer,CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstname())){
            customer.setFirstname(customerRequest.firstname());
        }
        if(StringUtils.isNotBlank(customerRequest.lastname())){
            customer.setLastname(customerRequest.lastname());
        }
        if(StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if(StringUtils.isNotBlank(customerRequest.phone())){
            customer.setPhone(customerRequest.phone());
        }
        if(customerRequest.address()!=null){
            customer.setAddress(customerRequest.address());
        }
    }



    public List<CustomerResponse> findAllCustomers() {
        return  customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public CustomerResponse findById(String id) {
        return this.customerRepository.findById(id)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
    }

    public boolean existsById(String id) {
        return this.customerRepository.findById(id)
                .isPresent();
    }

    public void deleteCustomer(String id) {
        this.customerRepository.deleteById(id);
    }
}
