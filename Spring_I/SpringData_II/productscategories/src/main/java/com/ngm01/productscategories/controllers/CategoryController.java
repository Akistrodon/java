package com.ngm01.productscategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ngm01.productscategories.models.Category;
import com.ngm01.productscategories.models.Product;
import com.ngm01.productscategories.services.CategoryService;
import com.ngm01.productscategories.services.ProductService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	CategoryService catServ;
	ProductService prodServ;
	
	public CategoryController(CategoryService catServ, ProductService prodServ) {
		this.catServ = catServ;
		this.prodServ = prodServ;
	}
	
	@PostMapping("/new")
	public String addNew(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) {
			redirect.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/main";
		}
		else {
			catServ.createCategory(category);
			Long id = category.getId();
			return "redirect:/category/" + id;
		}
	}
	
	@RequestMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		
		//add the category to the model
		model.addAttribute("category", catServ.readOne(id));
		
		//
		List<Product> displayList = prodServ.readAll();
		List<Product> catsProd = catServ.readOne(id).getProducts();
		for(int i=0; i < catsProd.size(); i++) {
			if(displayList.contains(catsProd.get(i))) {
				displayList.remove(catsProd.get(i));
			}
		}
		model.addAttribute("categorysProducts", catsProd);
		model.addAttribute("availableProducts", displayList);
		return "showCategory.jsp";
	}
	
	@PostMapping("/addProduct")
	private String add(@RequestParam("product_id") Long product_id, @RequestParam("category_id") Long category_id) {
		
		//get category and product objects to be modified
		Category updatedCategory = catServ.readOne(category_id);
		Product addedProduct = prodServ.readOne(product_id);
		
		//get category's product list, add product to it
		List<Product> productList = updatedCategory.getProducts();
		productList.add(addedProduct);
		
		//set category's product list, update category
		updatedCategory.setProducts(productList);
		catServ.update(updatedCategory);
		
		return "redirect:/category/" + category_id;
	}
}
