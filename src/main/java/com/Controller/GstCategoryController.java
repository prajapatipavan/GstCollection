package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.Entity.GstCategoryEntity;
import com.dto.GstCategorydto;
import com.factory.ServiceFactory;
import com.service.GstCategoryService;

@Controller
public class GstCategoryController {
	
    
	 @Autowired
	 ServiceFactory servicefactory;
	
	@GetMapping("addgstcategory")
	public String gstCategoryController() {
		
		return "AddGstCatagory";
	}

	
	@PostMapping("createGSTCategory")
	public String createGSTCategory(GstCategorydto catagory) {
		
		  servicefactory.getGstcatagory().addcatogory(catagory);
		
		return "redirect:/addgstcategory";
	}
	
	@GetMapping("listcatagory")
	public String listcatagory(Model model) {
	 List<GstCategorydto> gstcatagory=  servicefactory.getGstcatagory().listCategory();
	  model.addAttribute("gstcatgory",gstcatagory);
		return "ListCatagory";
	}
}
