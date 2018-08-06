package com.n26.service;

import com.n26.dto.StatisticsReport;
import com.n26.dto.Transaction;

public interface StatisticsReportService {

	void prepareStatReport(Transaction transaction);

	StatisticsReport getStatReport();

	void deleteStatReport();
}
