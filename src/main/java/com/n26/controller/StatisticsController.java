package com.n26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.dto.StatisticsReport;
import com.n26.service.StatisticsReportService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	@Autowired
	private StatisticsReportService statisticsReportService;

	@GetMapping
	public StatisticsReport getStatistics() {
		return statisticsReportService.getStatReport();
	}
}