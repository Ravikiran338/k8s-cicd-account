package com.sci.services.account.repository;

import com.sci.services.account.model.Accounts;
import com.sci.services.account.model.Status;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
	
	public Flux<Accounts> getAllAccounts();
	public Mono<Accounts> createAccount(Mono<Accounts> accounts);
	public Mono<Void> deleteAccount(String account_num);
	public Mono<Integer> updateAccInfo(Mono<Accounts> accntMono);

}
