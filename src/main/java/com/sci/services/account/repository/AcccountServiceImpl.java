package com.sci.services.account.repository;

import java.util.Date;

import org.davidmoten.rx.jdbc.Database;
import org.springframework.stereotype.Service;

import com.sci.services.account.model.Accounts;
import com.sci.services.accountservice.constants.AccountQueryConstants;
import com.sci.services.accountservice.constants.AccountServiceConstants;
import com.sci.services.util.DatabaseUtil;

import io.reactivex.Flowable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AcccountServiceImpl implements AccountService {

	public Flux<Accounts> getAllAccounts() {
		Database db = DatabaseUtil.getInstance().getDatabase();
		Flowable<Accounts> accountFlowable = db.select(AccountQueryConstants.SELECT_ALL_ACCOUNT).get(rs -> {
			Accounts accounts = new Accounts();
			accounts.setId(rs.getInt(AccountServiceConstants.ACCOUNT_ID));
			accounts.setAccount_num(rs.getString(AccountServiceConstants.ACCOUNT_NUMBER));
			accounts.setAccount_type(rs.getString(AccountServiceConstants.ACCOUNT_TYPE));
			accounts.setFlag(rs.getString(AccountServiceConstants.ACTIVE_FLAG));
			accounts.setBalance(rs.getDouble(AccountServiceConstants.BALANCE));
			accounts.setCustomer_id(rs.getInt(AccountServiceConstants.CUSTOMER_ID));
			accounts.setAccount_created_datetime(rs.getDate(AccountServiceConstants.ACCOUNT_CREATED_DATETIME));
			return accounts;
		});
		return Flux.from(accountFlowable);
	}
	//================================================================================	
	public Mono<Accounts> createAccount(Mono<Accounts> accountMono) {
		Database db = DatabaseUtil.getInstance().getDatabase();
		return accountMono.flatMap(newAccount -> {
			Flowable<Integer> accountIds = db.select(AccountQueryConstants.SELECT_CUSTOMER_ID).parameters(newAccount.getCustomer_id())
					.getAs(Integer.class)
					.flatMap(customerId -> db.update(AccountQueryConstants.CREATE_ACCOUNT)
							.parameters(newAccount.getAccount_num(), newAccount.getAccount_type(), newAccount.getFlag(),
									newAccount.getBalance(), customerId,(new Date()))
							.returnGeneratedKeys().getAs(Integer.class));

			Flowable<Accounts> accountFlowable = db.select(AccountQueryConstants.SELECT_ACCOUNT).parameterStream(accountIds).get(rs -> {
				Accounts account = new Accounts();
				account.setId(rs.getInt(AccountServiceConstants.ACCOUNT_ID));
				account.setAccount_num(rs.getString(AccountServiceConstants.ACCOUNT_NUMBER));
				account.setAccount_type(rs.getString(AccountServiceConstants.ACCOUNT_TYPE));
				account.setFlag(rs.getString(AccountServiceConstants.ACTIVE_FLAG));
				account.setBalance(rs.getDouble(AccountServiceConstants.BALANCE));
				account.setCustomer_id(rs.getInt(AccountServiceConstants.CUSTOMER_ID));
				account.setAccount_created_datetime(new Date());

				return account;
			});

			return Mono.from(accountFlowable);
		});
	}
	//================================================================================	
	
	public Mono<Void> deleteAccount(String account_num) {
		Database db = DatabaseUtil.getInstance().getDatabase();
	    Flowable<Integer> counts = db.update(AccountQueryConstants.DELETE_ACCOUNT).parameter(account_num).counts();
	    return Mono.from(counts).then();
	  }
	//================================================================================	
	@Override 
	public Mono<Integer> updateAccInfo(Mono<Accounts> dbUser) {
		Database db = DatabaseUtil.getInstance().getDatabase();
		Mono<Integer> userCount = dbUser.flatMap(user -> {
			Flowable<Integer> updatedCount = db.select(AccountQueryConstants.SELECT_ACCOUNT_ID).parameter(user.getId()).getAs(Integer.class)
					.flatMap(accountId -> db.update(AccountQueryConstants.UPDATE_ACCOUNT).parameters(user.getAccount_num(), user.getAccount_type(), user.getCustomer_id(),
							user.getBalance(),user.getFlag(), new Date(),accountId).counts());
			return Mono.from(updatedCount);
		});
		return userCount;
		
	}
	//================================================================================	
}
