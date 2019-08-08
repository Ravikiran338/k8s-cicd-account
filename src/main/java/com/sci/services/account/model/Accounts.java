package com.sci.services.account.model;

import java.io.Serializable;
import java.util.Date;


public class Accounts implements Serializable {
    
    private int id;
    private String account_num;
    private String account_type;
    private String flag;
    private double balance;
    private int customer_id;
    private Date account_created_datetime;
    
    public Accounts() {
    	
    }
    
    
	public Accounts(int id, String account_num, String account_type, String flag, double balance,
			int customer_id, Date account_created_datetime) {
		super();
		this.id = id;
		this.account_num = account_num;
		this.account_type = account_type;
		this.flag = flag;
		this.balance = balance;
		this.customer_id = customer_id;
		this.account_created_datetime = account_created_datetime;
	}


	

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAccount_num() {
		return account_num;
	}


	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}


	public String getAccount_type() {
		return account_type;
	}


	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public int getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}


	public Date getAccount_created_datetime() {
		return account_created_datetime;
	}


	public void setAccount_created_datetime(Date account_created_datetime) {
		this.account_created_datetime = account_created_datetime;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", account_num=" + account_num + ", account_type=" + account_type
				+ ", active_flag=" +flag + ", balance=" + balance + ", customer_id=" + customer_id
				+ ", account_created_datetime=" + account_created_datetime + "]";
	}

	
}