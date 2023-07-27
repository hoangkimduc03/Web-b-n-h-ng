package com.demo.controller;

import com.demo.model.*;
import com.demo.repo.AccountRepo;
import com.demo.repo.OrderDetailRepo;
import com.demo.repo.OrderRepo;
import com.demo.repo.ProductRepo;
import com.demo.service.CartService;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
	@Autowired
	HttpSession session;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	CartService cart;

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	OrderRepo orderRepo;
	@Autowired
	OrderDetailRepo orderDetailRepo;

	@ModelAttribute("cart")
	CartService getCart(){
		return cart;
	}

	@Data @AllArgsConstructor @Getter @Setter
	public static class PriceRange{
		int id;
		int minValue;
		int maxValue;
		String display;
	}

	List<PriceRange> priceRangeList = Arrays.asList(
		new PriceRange(0,0, Integer.MAX_VALUE, "Tất cả"),
		new PriceRange(1,0, 10000000, "Dưới 10 triệu"),
		new PriceRange(2,10000000, 20000000, "Từ 10-20 triệu"),
		new PriceRange(3,20000000, Integer.MAX_VALUE, "Trên 20 triệu")
	);

	@RequestMapping("/")
	public String index(
			@RequestParam(defaultValue = "") Optional<String> keyword,
			@RequestParam("page") Optional<Integer> page1,
			@RequestParam(defaultValue="") String categoryId,
			@RequestParam(defaultValue="0") int priceRangeId,
			Model model) {

		if(session.getAttribute("username") == null){
			return  "redirect:/login";
		}
		model.addAttribute("priceRangeList", priceRangeList);
		model.addAttribute("categoryList", categoryService.getAll());
		Pageable pageable = PageRequest.of(page1.orElse(0),5 );

		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;

		for (PriceRange priceRange : priceRangeList) {
			if (priceRange.getId() == priceRangeId) {
				minPrice = priceRange.getMinValue();
				maxPrice = priceRange.getMaxValue();
				break;
			}
		}

		if (categoryId.isEmpty()) {
			if (keyword.isPresent()) {
				String kw = "%" + keyword.get() + "%";
				Page<Product> searchResult = productRepo.searchByNamePrice(kw, minPrice, maxPrice, pageable);
				model.addAttribute("products", searchResult);
			} else {
				Page<Product> allProducts = productRepo.findAll(pageable);
				model.addAttribute("products", allProducts);
			}
		} else{
			if (keyword.isPresent()) {
				String kw = "%" + keyword.get() + "%";
				Category category = categoryService.getCategoryById(categoryId);
				Page<Product> searchResult = productRepo.searchByCategoryNamePrice(category.getId(), kw, minPrice, maxPrice, pageable);
				model.addAttribute("products", searchResult);
			} else {
				Category category = categoryService.getCategoryById(categoryId);
				Page<Product> categoryProducts = productRepo.findByCategory(category, pageable);
				model.addAttribute("products", categoryProducts);
			}
		}

		return "home/index";
	}

	@GetMapping("/detail/{id}")
	public String viewProduct(@PathVariable int id, Model model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "home/detail";
	}

	@RequestMapping("/add-to-cart/{id}")
	public String addToCart(@PathVariable int id){
		cart.add(id);
		return "redirect:/cart";
	}

	@RequestMapping("/remove-cart/{id}")
	public String removeCart(@PathVariable int id) {
		cart.remove(id);
		if(cart.getTotal() == 0){
			return "redirect:/";
		}
		return "redirect:/cart";
	}

	@RequestMapping("/update-cart/{id}")
	public String updateCart(@PathVariable int id, int quantity) {
		cart.update(id, quantity);
		return "redirect:/cart";
	}

	@GetMapping("/cart")
	public String cart(){
		return "home/cart";
	}

	@GetMapping("/confirm")
	public String confirm(){
		return "home/confirm";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		return "home/about";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
		// TODO: Check if user/password exists in database
		Account acc = accountRepo.findByUsernameAndPassword(
						username, password).orElse(null);

		if(acc != null) {
			session.setAttribute("username", username);
			return "redirect:/";
		}
		model.addAttribute("message", "Tên đăng nhập/mật khẩu không đúng");
		return "login";
	}

	@PostMapping("/purchase")
	public String purchase(@RequestParam String address){
		System.out.println("address=" + address);
		System.out.println("items=" + cart.getItems());
		String un = (String) session.getAttribute("username");
		Account acc = accountRepo.findById(un).orElse(null);
		if(acc != null) {
			Order order = new Order();
			order.setAccount(acc);
			order.setAddress(address);
			orderRepo.save(order);
			//TODO: orderRepo.save(order);


			for(OrderDetail item : cart.getItems()){
				item.setOrder(order);
				orderDetailRepo.save(item);
				// TODO: orderDetailRepo.save(item);
			}
			// TODO :clear cart
		}
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(){
		session.removeAttribute("username");
		return "redirect:/login";
	}
}
