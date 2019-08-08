package com.sci.services.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sci.services.account.model.Accounts;
import com.sci.services.account.repository.AccountService;
//import com.sci.services.account.repository.DeleteAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountConroller {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountConroller.class);
	@Autowired
	AccountService accountService;
	
	/*
	 * @Autowired DeleteAccountService deleteAccountService;
	 */
	
	@GetMapping("/")
	public Flux<Accounts> getAccounts() {
		return accountService.getAllAccounts();
	}

	
	  @PostMapping("/create") public Mono<Accounts> createUser(@RequestBody  Accounts accounts) 
	  { 
		  LOGGER.info("create: {}", accounts);
			Mono<Accounts> accountMono = Mono.just(accounts);
			return accountService.createAccount(accountMono);
	  }
	 

	
	@DeleteMapping("/delete/{account_num}")
	public Mono<Void> deleteAccount(@PathVariable("account_num") String account_num) {
		return accountService.deleteAccount(account_num);
	}

	@PutMapping("/update")
	public Mono<Integer> updateAccInfo(@RequestBody Accounts acc) {
		Mono<Accounts> accntMono = Mono.just(acc);
		return accountService.updateAccInfo(accntMono);
	}
	
	/*
	 * @PutMapping public Mono<Status> deactivateAccount(@RequestBody Accounts acc){
	 * Mono<Accounts> customerMono = Mono.just(acc); return
	 * deleteAccountService.deactivateAccount(customerMono); }
	 */
}
