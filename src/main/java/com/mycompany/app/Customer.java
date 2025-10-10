package com.mycompany.app;
//for each customer of the bank//
public class Customer{
  //Customer Attributes//
  private String FirstName;
  private String LastName;
  private String Address;
  private String Employer;
}

//constractor to initialize customer//
public Customer(String FirstName,String LastName,String Address,String Employer){
  this.FirstName = FirstName;
  this.LastName =LastName;
  this.Address = Address;
  this.Employer = Employers;
}

//*so next ke dibehaviour/methods of Customer class*//

//Creating customer savings account//
public SavingsAccount openSavings(String AccNo, String branch){
  return new SavingsAccount(AccNo,this,branch);
}

//Creating customer Investment account//
public InvestmentAccount openInvestment(String AccNo, String branch, double deposit){
  return new InvestmentAccount(AccNo,this,branch,deposit);
}

//Creating customer Cheque Account//
public ChequeAccount openCheque(String AccNo, String branch, String employer){
  return new ChequeAccount(AccNo,this,branch,employer);
}

//Getters//
public String getFirstName() { return FirstName; }
public String getLastName() { return LastName; }
public String getAddress() { return Address; }
public String getEmployer() { return Employer; }
