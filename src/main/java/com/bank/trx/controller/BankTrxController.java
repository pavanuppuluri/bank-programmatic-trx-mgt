package com.bank.trx.controller;

import java.util.List;

import com.bank.trx.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bank.trx.model.Account;
import com.bank.trx.model.AccountTransferRestModel;

@RestController
@RequestMapping("/services")
public class BankTrxController {

	@Autowired
	@Qualifier("progTrxManagerBean")
	private IAccountService firstProgAccountService;

	@Autowired
	@Qualifier("progTrxTemplateBean")
	private IAccountService secondProgAccountService;

	@GetMapping("programmatic/trxmanager/accounts")
	public ResponseEntity<List<Account>> getAllProg1Accounts() {
		List<Account> list = firstProgAccountService.getAllAccounts();
		return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
	}

	@PostMapping("programmatic/trxmanager/transfer")
	public ResponseEntity<Void> prog1TransferAccntToAccnt(
			@RequestBody AccountTransferRestModel accountTransferRestModel) {

		firstProgAccountService.transferMoney(accountTransferRestModel.getFrom(), accountTransferRestModel.getTo(),
				accountTransferRestModel.getAmount(), 5);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("programmatic/trxtemplate/accounts")
	public ResponseEntity<List<Account>> getAllProg2Accounts() {
		List<Account> list = secondProgAccountService.getAllAccounts();
		return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
	}

	@PostMapping("programmatic/trxtemplate/transfer")
	public ResponseEntity<Void> prog2TransferAccntToAccnt(
			@RequestBody AccountTransferRestModel accountTransferRestModel) {

		secondProgAccountService.transferMoney(accountTransferRestModel.getFrom(), accountTransferRestModel.getTo(),
				accountTransferRestModel.getAmount(), 5);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
