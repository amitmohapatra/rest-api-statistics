package com.n26.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.n26.common.CustomTimeUtils;
import com.n26.dto.Transaction;
import com.n26.exception.InvalidJsonException;
import com.n26.exception.JsonParseException;
import com.n26.service.StatisticsReportService;
import com.n26.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private StatisticsReportService statisticsService;

	@Override
	public void addTransaction(Transaction transaction) {
		if (transaction == null)
			throw new InvalidJsonException();
		if (Strings.isNullOrEmpty(transaction.getAmount()) || Strings.isNullOrEmpty(transaction.getTimestamp())) {
			throw new JsonParseException();
		}
		;
		if (CustomTimeUtils.isFutureDate(transaction.getTimestamp()))
			throw new JsonParseException();
		statisticsService.prepareStatReport(transaction);
	}

	@Override
	public void deleteTransaction() {
		statisticsService.deleteStatReport();
	}

}
