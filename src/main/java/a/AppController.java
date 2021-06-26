package a;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	@Autowired
    private ProductService service;
	
	@RequestMapping("/")
	public String login() {
		return "login";
	}
	
	
	@RequestMapping("/existing")
	public String viewExistingPage(Model model,@Param("keyword") Long keyword) {
		List<Product> listProducts = service.listAll(keyword);
	    model.addAttribute("listProducts", listProducts);
	    model.addAttribute("keyword", keyword);
	    return "index";
	}
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
	    model.addAttribute("product", product);
	    return "new_product";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
	    service.save(product); 
	    return "redirect:/existing";
	}
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
	    ModelAndView mav = new ModelAndView("edit_product");
	    Product product = service.get(id);
	    mav.addObject("product", product);
	    return mav;
	}
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
	    service.delete(id);
	    return "redirect:/existing";       
	}
}
