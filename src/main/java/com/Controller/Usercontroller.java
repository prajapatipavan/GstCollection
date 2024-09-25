package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Entity.CustomerEntity;
import com.Entity.GstTransaction;
import com.Entity.RoleEntity;
import com.dto.Roledto;
import com.dto.Userdto;
import com.factory.ServiceFactory;
import com.service.Mailservice;
import jakarta.servlet.http.HttpSession;

@Controller
public class Usercontroller {

    @Autowired
    ServiceFactory servicefactory;

    @Autowired
    Mailservice mailservice;

  
    @GetMapping("Adduser")
    public String createUser(Model model) {
        List<Roledto> listrole = servicefactory.getRoleservice().listofrole();
        model.addAttribute("listrole", listrole);
        return "user"; 
    }

    
    @PostMapping("saveuser")
    public String saveUser(Userdto userdto, RedirectAttributes redirectAttributes, RoleEntity role) {
        redirectAttributes.addFlashAttribute("addusermsg", "User Added Successfully!!!");

        
        String plaintext = userdto.getPassword();
        String encodepass = servicefactory.getPassencode().encode(plaintext);
        userdto.setPassword(encodepass);

     
        // mailservice.sendmessagemail(userdto.getEmail(), userdto.getUsername(), plaintext);

       
        servicefactory.getUserservice().saveUser(userdto);

        return "redirect:/Adduser"; 
    }

  
    @GetMapping("listuser")
    public String listUser(Model model, HttpSession session) {
        Userdto loggedUser = (Userdto) session.getAttribute("logginuser"); 

        List<Userdto> listUser = servicefactory.getUserservice().listUsers(loggedUser); 
        model.addAttribute("listuser", listUser); 

        return "Listuser"; 
    }

  
    @GetMapping("deleteuser")
    public String deleteUser(@RequestParam("userId") Integer userId, HttpSession session, RedirectAttributes redirectAttributes) {
        Userdto loggedUser = (Userdto) session.getAttribute("logginuser");

		/*
		 * // Prevent deleting the currently logged-in user if
		 * (loggedUser.getUserId().equals(userId)) {
		 * redirectAttributes.addFlashAttribute("error", "You cannot delete yourself!");
		 * return "redirect:/listuser"; }
		 */

        // Retrieve the user to be deleted
        Userdto deletedUser = servicefactory.getUserservice().findById(userId);
        
        if (deletedUser != null) {
            // Step 1: Find all customers associated with the user
            List<CustomerEntity> customers = servicefactory.getCustomerservice().findAllCustomersByUserId(userId);

            // Step 2: Handle associated transactions
            for (CustomerEntity customer : customers) {
                if (customer.getTransaction() != null) {
                    // Set associated transactions to inactive or delete them
                    GstTransaction transaction = customer.getTransaction();
                    transaction.setActive(false); // Mark transaction as inactive
                    servicefactory.getGsttransactionservice().saveTransaction(transaction);
                }

                // Step 3: Set all associated customers to inactive
                customer.setActive(false);
                servicefactory.getCustomerservice().saveCustomer(customer);
            }

            // Step 4: Set the user as inactive
            deletedUser.setActive(false); // Or you can remove the user entirely if desired
            servicefactory.getUserservice().saveUser(deletedUser); // Save user as inactive
        }

        return "redirect:/listuser";
    }


}
