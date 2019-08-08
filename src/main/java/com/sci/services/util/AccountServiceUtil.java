package com.sci.services.util;

public class AccountServiceUtil {
	private static AccountServiceUtil accountServiceUtil = null;

	private AccountServiceUtil() {

	}
	public static AccountServiceUtil getInstance() {
		if (accountServiceUtil == null){
			accountServiceUtil = new AccountServiceUtil();
			
		}
		return accountServiceUtil;

}
}