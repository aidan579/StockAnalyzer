import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.*;  

import java.util.Scanner;

public class Stock {
	static ArrayList<Account> listOfAccounts = new ArrayList<Account>();
	//HELPER FUNCTIONS
	public static boolean isValid(String soc)
	{
	  for(Account a : listOfAccounts)
	  {
	    if(a.getSocial() == soc)
	      return true;
	  }
	  return false;   
	}
	
	public static void addAccount(Account a) {
		listOfAccounts.add(a);
	}

public static void main(String[] args) throws IOException {
	
	//GETS REAL TIME DATA HERE
	ArrayList<Element> stocks = new ArrayList<Element>();
	ArrayList<String> companies = new ArrayList<String>();
	ArrayList<Double> dailyPercentChange = new ArrayList<Double>();
	
	//GETS REAL TIME DATA HERE
	for(int i=1; i<11;i++) 
	{
		Document doc = Jsoup.connect("https://markets.businessinsider.com/index/components/s&p_500?p="+i).get();  
		Elements table = doc.getElementsByTag("table");
	    Elements tbody = table.select("tbody");
	    Elements tr = tbody.select("tr");
	    for (Element e : tr.next()) 
	    {
	    	stocks.add(e);
	    }
		for(Element s: stocks) 
		{
			//Company Names
			String temp = s.toString();
			int start = temp.indexOf("title=\"");
			temp = temp.substring(start+7);
			//2nd half here
			int end = temp.indexOf("\">");
			temp = temp.substring(0,end);
			companies.add(temp);
			//Percent Increase or Decrease
			String t = s.toString();
			if(t.contains("%"))
				t = t.substring(t.indexOf("%")-5,t.indexOf("%"));
			if(t.contains(">"))
				t = t.substring(t.indexOf(">")+1);
			double num = Double.parseDouble(t);
			dailyPercentChange.add(num);
		}
	}
	//GETS REAL TIME DATA HERE	
	
	//USER FUNCTIONALITY START HERE
	Scanner sc = new Scanner(System.in);

    //ACCOUNT CREATION
   String tfn = " ";
   String tln = " ";
   String tsoc = " ";
   String tpw = " ";

   System.out.println("### Welcome to Your Investment Analyzer ###");
   System.out.println("*Login to your account*");
   
   System.out.print("first name: ");
   tfn = sc.next();
   System.out.print("last name: ");
   tln = sc.next();

   do{    
	   System.out.print("social security: ");       
   }while(sc.next().length() != 9);    
   
   System.out.print("password: ");      
   tpw = sc.next();
   //Account a = new Account(tfn, tln, tsoc, tpw);
      
   if(isValid(tsoc)) // checks socialsec in list
   {
     //they dont have an account
     System.out.println("");
     System.out.println("*Enter the information to create an account*");
     System.out.print("first name: ");
     tfn = sc.next();
     System.out.print("last name: ");
     tln = sc.next();
     System.out.print("social security: ");
     tsoc = sc.next();
     System.out.print("password: ");
     tpw = sc.next();
     Account newAccount = new Account(tfn, tln, tsoc, tpw);
     addAccount(newAccount);
     System.out.println("");
   }
    
   //at this point the user has the account
   //APP FUNCTIONALITY
   int choice = 0;
   boolean app = true;
   while (app)
   {
	   System.out.println("(1) College\n(2) Retirement\n(3) Car\n(4) Home");
	   System.out.println("Enter what you would like to save up for: ");  	
	   choice = sc.nextInt();
	   if(choice==1 || choice==2 ||choice==3||choice==4) {
		   app = false;
	   }
   }
 
   if(choice == 1)
   {
     //1
     int years = 0;
     int price = 0;
     System.out.print("Enter the annual price of a college: ");
     price = sc.nextInt();
     System.out.print("Enter the years until your child enters college: ");
     years = sc.nextInt();   
     //2
     double reccomendation = ((price*4)/years)/14;
     ArrayList<String> comps = new ArrayList<String>();
     for(int i=0; i<dailyPercentChange.size(); i++) {
    	 if(dailyPercentChange.get(i) > 2)
    		 comps.add(companies.get(i));
     }
     //3
     System.out.println("We reccomend investing $"+ reccomendation + " dollars monthly divided between " + comps);
   }
   if(choice == 2)
   {
	 //1
	     int y = 0;
	     int p = 0;
	     System.out.print("Enter your annual salary: ");
	     p = sc.nextInt();
	     System.out.print("Enter the years until you plan to retire: ");
	     y = sc.nextInt();   
	     //2
	     double r = ((8*p)/y)/12;
	     ArrayList<String> c = new ArrayList<String>();
	     for(int i=0; i<dailyPercentChange.size(); i++) {
	    	 if(dailyPercentChange.get(i) > 2)
	    		 c.add(companies.get(i));
	     }
	     //3
	     System.out.println("We reccomend investing $"+ r + " dollars monthly divided between " + c);
   }
   if(choice == 3)
   {
	 //1
	     int yr = 0;
	     int pr = 0;
	     System.out.print("Enter the price of the car down payment: ");
	     pr = sc.nextInt();
	     System.out.print("Enter the years until you want the car: ");
	     yr = sc.nextInt();   
	     //2
	     double reco = (pr/yr)/12;
	     ArrayList<String> cs = new ArrayList<String>();
	     for(int i=0; i<dailyPercentChange.size(); i++) {
	    	 if(dailyPercentChange.get(i) > 2)
	    		 cs.add(companies.get(i));
	     }
	     //3
	     System.out.println("We reccomend investing $"+ reco + " dollars monthly divided between " + cs);
   }
   if(choice == 4)
   {
	 //1
	     int year = 0;
	     int pri = 0;
	     System.out.print("Enter the price of the home down payment: ");
	     pri = sc.nextInt();
	     System.out.print("Enter the years until you want the home: ");
	     year = sc.nextInt();   
	     //2
	     double reccomend = (pri/year);
	     ArrayList<String> com = new ArrayList<String>();
	     for(int i=0; i<dailyPercentChange.size(); i++) {
	    	 if(dailyPercentChange.get(i) > 2)
	    		 com.add(companies.get(i));
	     }
	     //3
	     System.out.println("We reccomend investing $"+ reccomend + " dollars monthly divided between " + com);
   }	
	
	
}

}