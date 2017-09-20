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
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService prodServ;
	private final CategoryService catServ;
	
	public ProductController(ProductService prodServ, CategoryService catServ) {
		this.catServ = catServ;
		this.prodServ = prodServ;
	}
	
	@RequestMapping("/new")
	public String mainRedirect() {
		return "redirect:main";
	}
	
	@PostMapping("/new")
	public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) {
			redirect.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/main";
		}
		else {
			prodServ.create(product);
			return "redirect:/products/" + product.getId();
		}
	}
	
	@RequestMapping("/{id}")
	public String showProd(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", prodServ.readOne(id));
		List<Category> availableList = catServ.readAll();
		List<Category> prodsCat = prodServ.readOne(id).getCategories();
		for(int i=0; i < prodsCat.size(); i++) {
			if(availableList.contains(prodsCat.get(i))) {
				availableList.remove(prodsCat.get(i));
			}
		}
		model.addAttribute("availableCategories", availableList);
		model.addAttribute("productsCategories", prodsCat);
		return "showProduct.jsp";
	}
	
	@PostMapping("/addCategory")
	public String addCat(@RequestParam("newCategory") Long cat_id, @RequestParam("product") Long prod_id, Model model) {
		
		
		//get category and product objects to be modified
		Category newCat = catServ.readOne(cat_id);
		Product updatedProduct = prodServ.readOne(prod_id);
		
		//get product's category list, add category to it
		List<Category> categoryList  = updatedProduct.getCategories();
		categoryList.add(newCat);
		
		//set product's category list, update product
		updatedProduct.setCategories(categoryList);
		prodServ.update(updatedProduct);
		
		return "redirect:/products/" + prod_id;
	}
	

}
