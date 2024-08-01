package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.TransactionsEntity;
import com.res.main.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public ApiResponse<List<TransactionsEntity>> getAllTransactions() {
        List<TransactionsEntity> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()) {
            return new ApiResponse<>(false, "No transactions found", transactions);
        }
        return new ApiResponse<>(true, "Transactions retrieved successfully", transactions);
    }

    public ApiResponse<TransactionsEntity> createTransaction(TransactionsEntity transaction) {
        TransactionsEntity savedTransaction = transactionRepository.save(transaction);
        return new ApiResponse<>(true, "Transaction created successfully", savedTransaction);
    }

    public ApiResponse<TransactionsEntity> getTransactionById(long id) {
        Optional<TransactionsEntity> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return new ApiResponse<>(true, "Transaction retrieved successfully", transaction.get());
        }
        return new ApiResponse<>(false, "Transaction not found", null);
    }

    public ApiResponse<TransactionsEntity> updateTransaction(long id, TransactionsEntity updatedTransaction) {
        if (transactionRepository.existsById(id)) {
            updatedTransaction.setId(id);
            TransactionsEntity savedTransaction = transactionRepository.save(updatedTransaction);
            return new ApiResponse<>(true, "Transaction updated successfully", savedTransaction);
        }
        return new ApiResponse<>(false, "Transaction not found", null);
    }

    public ApiResponse<String> deleteTransaction(long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return new ApiResponse<>(true, "Transaction deleted successfully", "Transaction deleted");
        }
        return new ApiResponse<>(false, "Transaction not found", null);
    }
}
