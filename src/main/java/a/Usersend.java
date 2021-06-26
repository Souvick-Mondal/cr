package a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Usersend {
	@Autowired
    private BankAccountDAO bankAccountDAO;
	
	@RequestMapping(value = "/userMoney", method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model) {
        SendMoneyForm form = new SendMoneyForm(1L, 2L, 700d);
        model.addAttribute("sendMoneyForm", form);
        return "userMoneyPage";
    }
  
    @RequestMapping(value = "/userMoney", method = RequestMethod.POST)
    public String processSendMoney(Model model, SendMoneyForm sendMoneyForm) {
        System.out.println("Send Money: " + sendMoneyForm.getAmount());
        try {
            bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(), //
                    sendMoneyForm.getToAccountId(), //
                    sendMoneyForm.getAmount());
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/userMoneyPage";
        }
        return "success";
    }
}
