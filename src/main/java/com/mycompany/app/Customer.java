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
  return new SavingsAccount(accNo,this,branch);
}

//Creatin customer Investment accont//
public InvestmentAccount openInvestment(String AccNo, String branch, double deposit){
  return new InvestmentAccount(accNo,this,branch,deposit);
}
