package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.TransactionsEntity;
import com.res.main.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<?> getAllTransactions() {
        ApiResponse<List<TransactionsEntity>> response = transactionService.getAllTransactions();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionsEntity transaction) {
        ApiResponse<TransactionsEntity> response = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable long id) {
        ApiResponse<TransactionsEntity> response = transactionService.getTransactionById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable long id, @RequestBody TransactionsEntity updatedTransaction) {
        ApiResponse<TransactionsEntity> response = transactionService.updateTransaction(id, updatedTransaction);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable long id) {
        ApiResponse<String> response = transactionService.deleteTransaction(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
