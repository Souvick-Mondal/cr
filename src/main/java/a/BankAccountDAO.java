package a;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BankAccountDAO {
	@Autowired
	private EntityManager entityManager;

	public BankAccountDAO() {
	}
	
	public Product findById(Long id) { 
        return this.entityManager.find(Product.class, id);
    }
	
	// MANDATORY: Transaction must be created before.
    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(Long id, double amount) throws BankTransactionException {
        Product account = this.findById(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        double newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new BankTransactionException("The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
    }
 
    // Do not catch BankTransactionException in this method.
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException {
    	addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void depositMoney(Long accountId,double amount) throws BankTransactionException{
    	addAmount(accountId, amount);
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void withdrawMoney(Long accountId,double amount) throws BankTransactionException{
    	addAmount(accountId, -amount);
    }
}
