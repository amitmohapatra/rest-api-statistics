package com.n26.service;

import com.n26.dto.Transaction;

public interface TransactionService {
	void addTransaction(Transaction transaction);

	void deleteTransaction();
}
