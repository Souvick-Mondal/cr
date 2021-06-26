package a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DepositController {

	@Autowired
	private BankAccountDAO bankAccountDAO;
	
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String viewDepositPage(Model model) {
		Deposit form = new Deposit(1L, 500d);
        model.addAttribute("deposit", form);
        return "depositPage";
    }
  
    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String processDeposit(Model model, Deposit d) {
        System.out.println("Money Deposited: " + d.getAmount());
        try {
            bankAccountDAO.depositMoney(d.getAccountId(), d.getAmount());
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/depositPage";
        }
        return "redirect:/existing";
    }
}
