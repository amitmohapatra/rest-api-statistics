package com.n26.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.dto.StatisticsReport;
import com.n26.dto.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsReportServiceTest {

	@Autowired
    private StatisticsReportService statisticsService;

	@Test
    public void checkCurrentStatistics(){
        statisticsService.prepareStatReport(new Transaction("1.0", LocalDateTime.now()+"Z"));
        statisticsService.prepareStatReport(new Transaction("2.0", LocalDateTime.now()+"Z"));
        statisticsService.prepareStatReport(new Transaction("3.0", LocalDateTime.now()+"Z"));
        statisticsService.prepareStatReport(new Transaction("4.0", LocalDateTime.now()+"Z"));

        StatisticsReport report = statisticsService.getStatReport();
        assertEquals(report.getCount(), 4);
    }
}
