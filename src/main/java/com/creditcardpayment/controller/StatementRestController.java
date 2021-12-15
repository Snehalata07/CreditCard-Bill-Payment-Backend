package com.creditcardpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcardpayment.bean.Statement;
import com.creditcardpayment.exception.CreditCardException;
import com.creditcardpayment.exception.StatementException;
import com.creditcardpayment.service.IStatementService;

@RestController
@RequestMapping("/statements")
public class StatementRestController {

	@Autowired
	private IStatementService statementService;
	
	@GetMapping("/getAllStatements")
	public ResponseEntity<List<Statement>> findAll() {
		return ResponseEntity.ok(statementService.findAll());
	}
	
	@GetMapping("/getStatement/{statementId}")
	public ResponseEntity<Statement> findById(@PathVariable("statementId") Long statementId) throws StatementException{
		return ResponseEntity.ok(statementService.findById(statementId));
	}
	
	@PostMapping("/addStatement")
	public ResponseEntity<Statement> add(@RequestBody Statement statement) throws StatementException {
		statement=statementService.add(statement);
		return ResponseEntity.ok(statement);
	}
	
	
	@PutMapping("/updateStatement")
	public ResponseEntity<Statement> updateUser(@RequestBody Statement statement) throws StatementException{
		statement =statementService.save(statement);
		return ResponseEntity.ok(statement);
	}
	
	@GetMapping("/generateBill/{cardNumber}")
	public ResponseEntity<Statement> getBill(@PathVariable("cardNumber") String cardNumber) throws CreditCardException, StatementException{
		return ResponseEntity.ok(statementService.getBilledStatement(cardNumber));
	}
	
	@GetMapping("/generateUnBilled/{cardNumber}")
	public ResponseEntity<Statement> getUnBill(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		return ResponseEntity.ok(statementService.getUnBilledStatement(cardNumber));
	}
	
	@GetMapping("/statementHistory/{cardNumber}")
	public ResponseEntity<List<Statement>> statementHistory(@PathVariable("cardNumber") String cardNumber) throws CreditCardException {
		return ResponseEntity.ok(statementService.statementHistory(cardNumber));
	}
	
}

