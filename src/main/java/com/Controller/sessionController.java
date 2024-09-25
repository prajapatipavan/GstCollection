package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.Entity.CustomerEntity;
import com.Entity.GstTransaction;
import com.Entity.UserEntity;
import com.dto.Customerdto;
import com.dto.GstTransactiondto;
import com.dto.Userdto;
import com.factory.ServiceFactory;
import com.service.Userservice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class sessionController {

	@Autowired
	ServiceFactory servicefactory;

	@GetMapping("login")
	public String login() {

		return "login";
	}

	@PostMapping("Authenticate")
	public String Authenticate(HttpSession session, UserEntity user, HttpServletResponse response) {

		Userdto loggeduser = servicefactory.getUserservice().findUserByEmail(user.getEmail());

		Boolean passwordlogg = servicefactory.getPassencode().matches(user.getPassword(), loggeduser.getPassword());

		session.setAttribute("logginuser", loggeduser);
		session.setAttribute("userRole", loggeduser.getRole().getRoleName());

		if (loggeduser == null || passwordlogg == false || loggeduser.getActive() == false) {

			return "login";

		} else if (loggeduser.getRole().getRoleId() == 1) {
			session.setAttribute("logginuser", loggeduser);

			return "redirect:/admindashbord";

		} else if (loggeduser.getRole().getRoleId() == 2) {
			session.setAttribute("logginuser", loggeduser);
			return "redirect:/officedashbord";
		}

		else {

			return "login";
		}

	}

	@GetMapping("admindashbord")
	public String admindashbord(Model model) {

		List<GstTransactiondto> gsttansaction = servicefactory.getGsttransactions().ListTransaction();

		double totalAmount = gsttansaction.stream().mapToDouble(transaction -> {
			String amountStr = transaction.getTotalAmount();

			if (amountStr == null || amountStr.trim().isEmpty()) {
				return 0.0;
			}
			try {
				return Double.parseDouble(amountStr);
			} catch (NumberFormatException e) {
				// Handle any unexpected format, returning 0.0 for this case
				return 0.0;
			}
		}).sum();

		model.addAttribute("gsttansaction", gsttansaction);

		String formattedTotalAmount = String.format("%.2f", totalAmount);

		model.addAttribute("totalAmount", formattedTotalAmount);

		List<GstTransactiondto> transactiontoday = servicefactory.getGsttransactions().ListTransactiontoday();

		double totalAmounttoday = transactiontoday.stream().mapToDouble(transaction -> {
			String amountStr = transaction.getTotalAmount();

			if (amountStr == null || amountStr.trim().isEmpty()) {
				return 0.0;
			}
			try {
				return Double.parseDouble(amountStr);
			} catch (NumberFormatException e) {

				return 0.0;
			}
		}).sum();

		model.addAttribute("transactiontoday", transactiontoday);

		String formattedTotalAmounttoday = String.format("%.2f", totalAmounttoday);

		model.addAttribute("totalAmountToday", formattedTotalAmounttoday);

		List<CustomerEntity> customer = servicefactory.getCustomerservice().ListAllCustomer();

		model.addAttribute("customer", customer);

		

		return "Admin";
	}

	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletResponse response) {

		session.invalidate();

		return "redirect:/login";
	}

	@GetMapping("officedashbord")
	public String officeDashboard(Model model, HttpSession session) {

		Userdto loggedInUser = (Userdto) session.getAttribute("logginuser");

		if (loggedInUser == null) {

			return "redirect:/login";
		}

		List<GstTransactiondto> transactions = servicefactory.getUserservice().listTransactionLoginUser(loggedInUser);

		double totalAmount = transactions.stream().mapToDouble(transaction -> {
			String amountStr = transaction.getTotalAmount();

			if (amountStr == null || amountStr.trim().isEmpty()) {
				return 0.0;
			}
			try {
				return Double.parseDouble(amountStr);
			} catch (NumberFormatException e) {
				// Handle any unexpected format, returning 0.0 for this case
				return 0.0;
			}
		}).sum();

		model.addAttribute("gsttansaction", transactions);

		String formattedTotalAmount = String.format("%.2f", totalAmount);

		model.addAttribute("totalAmount", formattedTotalAmount);
		
		

		List<GstTransactiondto> listTransaction = servicefactory.getGsttransactionservice().ListTransaction();
		
		
		double totalAmounts = listTransaction.stream().mapToDouble(transaction -> {
			String amountStr = transaction.getTotalAmount();

			if (amountStr == null || amountStr.trim().isEmpty()) {
				return 0.0;
			}
			try {
				return Double.parseDouble(amountStr);
			} catch (NumberFormatException e) {
				// Handle any unexpected format, returning 0.0 for this case
				return 0.0;
			}
		}).sum();

	

		String formattedTotalAmounts = String.format("%.2f", totalAmounts);

		model.addAttribute("totalAmounts", formattedTotalAmounts);
		model.addAttribute("listTransaction", listTransaction);
		
		  Userdto user = (Userdto) session.getAttribute("logginuser");
		  
		      UserEntity users  =servicefactory.getUserservice().userdtoToUserEntity(user);
		
		       List<CustomerEntity> listAllCustomer = servicefactory.getCustomerservice().listOfCustomer(users);
		       model.addAttribute("listAllcustomer", listAllCustomer);
		     
		       System.out.println("hello");
		       for (CustomerEntity customerEntity : listAllCustomer) {
		    	   
		    	   System.out.println(customerEntity.getCustomerName());
		    	   System.out.println(customerEntity.isActive());
				
			}

		return "Office";
	}
	
	
	@GetMapping("listalltransaction")
	public String listallTransaction(Model model,HttpSession session) {
		
		  Userdto user = (Userdto) session.getAttribute("logginuser");
		  
	
		
		List<GstTransactiondto> listTransaction = servicefactory.getGsttransactionservice().ListTransactionUserswise(user);
		model.addAttribute("listTransaction", listTransaction);
		
		return "listloginuseralltransaction";
	}

}
