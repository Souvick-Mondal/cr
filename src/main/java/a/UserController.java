package a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class UserController {
	@Autowired
	private ProductService service;
	@RequestMapping(value = "/login-user", method = RequestMethod.POST)
	public String welcomePage(Model model,@ModelAttribute("admin") Product a) {
		
		try{
			Product f= service.get(a.getId());
			if(f!=null) {
				if(f.getPassword().equals(a.getPassword())) {
					String q=f.getFullName();
					Double b=f.getBalance();
					  model.addAttribute("product", q);
					  model.addAttribute("balance", b);
					return "userhome";
				}
			}
		} catch (Exception e) {
			return "login";
		}
		return "login";
	}
	
	@RequestMapping("/tran")
	public String transaction() {
		return "transaction";
	}
	

	

}

