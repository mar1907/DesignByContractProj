package validators;

import model.Account;
import model.SavingAccount;
import model.SpendingAccount;

public class InterestValidator implements Validator<Account> {
	public String isValid(Account t) {
		if(t instanceof SpendingAccount){
			return "";
		}
		else{
			if(((SavingAccount)t).getInterest()>0&&((SavingAccount)t).getInterest()<1){
				return "";
			}
			else{
				return "Invalid amount, should be between 0 and 1;\n";
			}
		}
	}
}
