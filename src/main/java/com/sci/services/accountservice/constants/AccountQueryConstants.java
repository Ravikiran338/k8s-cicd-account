/**
 * 
 */
package com.sci.services.accountservice.constants;

/**
 * @author mn259
 *
 */
public class AccountQueryConstants {
	public static final String SELECT_ALL_ACCOUNT = "SELECT * FROM accounts_tbl";
	public static final String CREATE_ACCOUNT = "INSERT INTO accounts_tbl (account_num,account_type,active_flag,balance,customer_id,account_created_datetime) VALUES (?,?,?,?,?,?)";
	
	public static final String SELECT_ACCOUNT = "SELECT account_id,account_num,account_type,balance,e.active_flag,e.customer_id,account_created_datetime FROM accounts_tbl AS e "
	+ "JOIN customer_details_tbl AS d ON e.customer_id = d.customer_id " + "WHERE e.customer_id = ?";
	
	public static final String SELECT_CUSTOMER_ID = "SELECT customer_id from customer_details_tbl where customer_id = ?";
	
	public static final String DELETE_ACCOUNT = "DELETE FROM accounts_tbl WHERE account_num = ?";
	
	
	public static final String SELECT_ACCOUNT_ID = "SELECT account_id FROM accounts_tbl e " + "WHERE account_id = ?";
	
	
	public static final String UPDATE_ACCOUNT = "UPDATE accounts_tbl set account_num=?, account_type=?,customer_id=?, balance=?,active_flag= ?, account_created_datetime=? where account_id =?";
}