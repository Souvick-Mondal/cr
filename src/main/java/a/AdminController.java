package a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller

public class AdminController {
	@Autowired
	private AdminService service;
	@RequestMapping(value = "/login-admin", method = RequestMethod.POST)
	public String welcomePage(@ModelAttribute("admin") Admin a) {
		try{
			Admin f= service.get(a.getId());
			if(f!=null) {
				if(f.getPassword().equals(a.getPassword())) {
					return "home";
				}
			}
		} catch (Exception e) {
			return "login";
		}
		return "login";
	}
	@RequestMapping("/new_add")
	public String viewNewFacultyForm(Model model) {
		Admin fa = new Admin();
		model.addAttribute("admin", fa);
		return "signup";
	}

	@RequestMapping(value = "/save_faculty", method = RequestMethod.POST)
	public String addNewFaculty(@ModelAttribute("admin") Admin fa) {
		service.save(fa);
		return "login";
	}

}
