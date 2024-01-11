
import java.util.ArrayList;

public class ListOfAccounts extends Account
{
	
  private ArrayList<Account> accountList = new ArrayList<Account>();
  
  public ListOfAccounts(String firstName, String lastName, String socialSecurity, String password)
  {
  	super(firstName, lastName, socialSecurity, password);    
    Account t = new Account(firstName,lastName,socialSecurity,password);
  	accountList.add(t);
  }
  
  public ArrayList<Account> getListOfAccounts() {
	  return accountList;
  }
  
  public void addAccount(Account a) {
	  accountList.add(a);
  }
  
  public boolean isValid(String soc)
  {
    for(Account a : accountList)
    {
      if(a.getSocial() == soc)
        return true;
    }
    return false;   
  }
}