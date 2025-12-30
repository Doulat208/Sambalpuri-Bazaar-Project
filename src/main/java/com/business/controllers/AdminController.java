package com.business.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.business.entities.Admin;
import com.business.entities.Orders;
import com.business.entities.Product;
import com.business.entities.User;
import com.business.loginCredentials.AdminLogin;
import com.business.services.AdminServices;
import com.business.services.OrderServices;
import com.business.services.ProductServices;
import com.business.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminServices adminServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private ProductServices productServices;

	@Autowired
	private OrderServices orderServices;

	// ================= ADMIN LOGIN =================

	@PostMapping("/adminLogin")
	public String adminLogin(
			@ModelAttribute AdminLogin login,
			HttpSession session,
			Model model) {

		boolean isValid = adminServices.validateAdminCredentials(
				login.getEmail(),
				login.getPassword());

		if (!isValid) {
			model.addAttribute("error", "Invalid admin email or password");
			return "Login";
		}

		session.setAttribute("adminLoggedIn", true);
		return "redirect:/admin/dashboard";
	}

	// ================= ADMIN LOGOUT =================

	@GetMapping("/admin/logout")
	public String adminLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	// ================= ADMIN DASHBOARD =================

	@GetMapping("/admin/dashboard")
	public String adminDashboard(HttpSession session, Model model) {

		Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
		if (adminLoggedIn == null || !adminLoggedIn) {
			return "redirect:/login";
		}

		List<User> users = userServices.getAllUser();
		List<Admin> admins = adminServices.getAll();
		List<Product> products = productServices.getAllProducts();
		List<Orders> orders = orderServices.getOrders();

		model.addAttribute("users", users);
		model.addAttribute("admins", admins);
		model.addAttribute("products", products);
		model.addAttribute("orders", orders);

		return "Admin_Page";
	}

	// ================= ADMIN MANAGEMENT =================

	@GetMapping("/addAdmin")
	public String addAdminPage() {
		return "Add_Admin";
	}

	@PostMapping("/addingAdmin")
	public String addAdmin(@ModelAttribute Admin admin) {
		adminServices.addAdmin(admin);
		return "redirect:/admin/dashboard";
	}

	@GetMapping("/updateAdmin/{id}")
	public String updateAdminPage(@PathVariable int id, Model model) {
		Admin admin = adminServices.getAdmin(id);
		model.addAttribute("admin", admin);
		return "Update_Admin";
	}

	@PostMapping("/updatingAdmin/{id}")
	public String updateAdmin(@ModelAttribute Admin admin, @PathVariable int id) {
		adminServices.update(admin, id);
		return "redirect:/admin/dashboard";
	}

	@GetMapping("/deleteAdmin/{id}")
	public String deleteAdmin(@PathVariable int id) {
		adminServices.delete(id);
		return "redirect:/admin/dashboard";
	}

	// ================= USER MANAGEMENT =================

	@GetMapping("/addUser")
	public String addUserPage() {
		return "Add_User";
	}

	@GetMapping("/updateUser/{id}")
	public String updateUserPage(@PathVariable int id, Model model) {
		User user = userServices.getUser(id);
		model.addAttribute("user", user);
		return "Update_User";
	}

	// ================= PRODUCT MANAGEMENT =================

	@GetMapping("/admin/product/add")
	public String addProductPage(Model model) {
		model.addAttribute("product", new Product());
		return "Add_Product";
	}

	@GetMapping("/updateProduct/{id}")
	public String updateProductPage(@PathVariable int id, Model model) {
		Product product = productServices.getProduct(id);
		model.addAttribute("product", product);
		return "Update_Product";
	}

	@PostMapping("/admin/product/save")
	public String saveProduct(
			@ModelAttribute Product product,
			@RequestParam("image") MultipartFile file) {

		try {
			if (!file.isEmpty()) {

				// Unique image name
				String imageName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

				// Upload directory
				String uploadDir = "src/main/resources/static/uploads";

				// Create folder if not exists
				File dir = new File(uploadDir);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				// Save image
				Path imagePath = Paths.get(uploadDir, imageName);
				Files.write(imagePath, file.getBytes());

				// Save image name in product
				product.setImageName(imageName);
			}

			productServices.addProduct(product);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/admin/dashboard";
	}

}
