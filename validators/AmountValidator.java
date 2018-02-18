package validators;

import model.Account;

public class AmountValidator implements Validator<Account> {
	public String isValid(Account t) {
		if(t.getAmount()>=0){
			return "";
		}
		else{
			return "Invalid amount, should be >=0;\n";
		}
	}
}
