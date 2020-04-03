package com.example.demo.controller;

import com.example.demo.repository.CustomersRepository;
import com.example.demo.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomersController {
    private CustomersRepository customersRepository;

    @Autowired
    public CustomersController(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @GetMapping(value = "/customersList")
    public String listOfCustomers(String name, Model model) {
        List<Customers> customersList = customersRepository.findByCustomersName(name);
        if (customersList != null) {
            model.addAttribute("customers", customersList);
        }
        return "customersList";
    }


    @GetMapping(value = "/addCustomer")
    public String addCustomer(String reader, Model model){
        return "addCustomer";
    }

    @PostMapping(value = "/addCustomer")
    public String addToReadingList( String reader, Customers customers) {
        customers.setFirst_name(reader);
        customersRepository.save(customers);
        return "redirect:/customersList";
    }

    @GetMapping(value = "/")
    public String ind(){
        return "redirect:/customersList";
    }


}
