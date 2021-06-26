package a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WithdrawController {
	
	@Autowired
	private BankAccountDAO bankAccountDAO;
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model) {
        Withdraw form = new Withdraw(1L, 500d);
        model.addAttribute("withdraw", form);
        return "withdrawPage";
    }
  
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String processSendMoney(Model model, Withdraw w) {
        System.out.println("Money withdrawn: " + w.getAmount());
        try {
            bankAccountDAO.withdrawMoney(w.getAccountId(), w.getAmount());
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/withdrawPage";
        }
        return "redirect:/existing";
    }
}
