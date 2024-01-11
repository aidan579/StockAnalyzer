
public class Account
{	
  private String firstName;
  private String lastName;
  
  private String socialSecurity;
  private String password;
  
  public Account()
  {
    firstName = " ";
    lastName = " ";
    socialSecurity = " ";
    password = " ";
  }
  public Account(String fn, String ln, String soc, String pw)
  {
    firstName = fn;
    lastName = ln;
    socialSecurity = soc;
    password = pw;
  }

  public String getSocial()
  {
    return socialSecurity;
  }

  public String getPassword()
  {
    return password;
  }   
}
