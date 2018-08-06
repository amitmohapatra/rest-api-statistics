package com.n26.service;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.dto.Transaction;
import com.n26.service.impl.StatisticsReportServiceImpl;
import com.n26.service.impl.TransactionServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;

	@Mock
	private StatisticsReportServiceImpl statisticsServiceMock;

	@InjectMocks
	private TransactionServiceImpl transactionServiceMock;

	@Test(expected = com.n26.exception.InvalidJsonException.class)
	public void emptyRequestBody() {
		transactionService.addTransaction(null);
	}

	@Test(expected = com.n26.exception.JsonParseException.class)
	public void missingTimestampField() {
		transactionService.addTransaction(new Transaction("3.0", null));
	}

	@Test(expected = com.n26.exception.JsonParseException.class)
	public void missingAmountField() {
		transactionService.addTransaction(new Transaction(null, LocalDateTime.now() + "Z"));
	}
}
