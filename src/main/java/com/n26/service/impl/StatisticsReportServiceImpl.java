package com.n26.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.n26.common.CommonUtils;
import com.n26.common.CustomTimeUtils;
import com.n26.dto.Statistics;
import com.n26.dto.StatisticsReport;
import com.n26.dto.Transaction;
import com.n26.exception.OlderThan60Exception;
import com.n26.service.StatisticsReportService;

@Service
public class StatisticsReportServiceImpl implements StatisticsReportService {

	private static final int MIN_SECS = 60;
	private static final Map<Long, Statistics> statisticsOfLast60Secs = new ConcurrentHashMap<>(MIN_SECS);

	@Override
	public void prepareStatReport(Transaction transaction) {
		LocalDateTime localTime = CustomTimeUtils.getLocalTime(transaction.getTimestamp());
		BigDecimal amount = CommonUtils.convertoToBigDecimal(transaction.getAmount());

		if (CustomTimeUtils.getTimeDiffInSecs(localTime) < MIN_SECS) {
			Long secsDiff = CustomTimeUtils.getTimeDiffInSecs(localTime);
			statisticsOfLast60Secs.compute(secsDiff, (key, val) -> {
				if (val == null || CustomTimeUtils.getTimeDiffInSecs(val.getTimestamp()) >= MIN_SECS) {
					val = new Statistics();
					val.setTimestamp(localTime);
					val.setSum(amount);
					val.setMax(amount);
					val.setMin(amount);
					val.setCount(1l);
					return val;
				}
				val.setCount(val.getCount() + 1);
				val.setSum(val.getSum().add(amount));
				if (amount.compareTo(val.getMax()) == 1)
					val.setMax(amount);
				if (amount.compareTo(val.getMin()) == -1)
					val.setMin(amount);
				return val;
			});
		} else {
			throw new OlderThan60Exception();
		}

	}

	@Override
	public StatisticsReport getStatReport() {
		StatisticsReport report = statisticsOfLast60Secs.values().stream()
				.filter(obj -> CustomTimeUtils.getTimeDiffInSecs(obj.getTimestamp()) < MIN_SECS)
				.map(StatisticsReport::new).reduce(new StatisticsReport(), (obj1, obj2) -> {
					obj1.setSum(CommonUtils.convertoToBigDecimal(obj1.getSum())
							.add(CommonUtils.convertoToBigDecimal(obj2.getSum())).toString());
					obj1.setCount(obj1.getCount() + obj2.getCount());
					obj1.setMax(CommonUtils.convertoToBigDecimal(obj1.getMax())
							.compareTo(CommonUtils.convertoToBigDecimal(obj2.getMax())) == 1 ? obj1.getMax()
									: obj2.getMax());
					obj1.setMin(CommonUtils.convertoToBigDecimal(obj1.getMin())
							.compareTo(CommonUtils.convertoToBigDecimal(obj2.getMin())) == -1 ? obj1.getMin()
									: obj2.getMin());
					return obj1;
				});

		report.setSum(CommonUtils.convertoToBigDecimal(report.getSum())
				.compareTo(CommonUtils.convertoToBigDecimal("0.00")) == 0 ? "0.00"
						: CommonUtils.convertoToBigDecimal(report.getSum()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.toString());
		report.setMin(CommonUtils.convertoToBigDecimal(report.getMin())
				.compareTo(CommonUtils.convertoToBigDecimal(Double.MAX_VALUE + "")) == 0 ? "0.00"
						: CommonUtils.convertoToBigDecimal(report.getMin()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.toString());
		report.setMax(CommonUtils.convertoToBigDecimal(report.getMax())
				.compareTo(CommonUtils.convertoToBigDecimal(Double.MIN_VALUE + "")) == 0 ? "0.00"
						: CommonUtils.convertoToBigDecimal(report.getMax()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.toString());
		report.setAvg(
				report.getCount() > 0l
				? CommonUtils.convertoToBigDecimal(report.getSum())
						.divide(CommonUtils.convertoToBigDecimal(report.getCount() + ""), 2,
								BigDecimal.ROUND_HALF_UP)
						.toString()
						: "0.00");
		return report;
	}

	@Override
	public void deleteStatReport() {
		statisticsOfLast60Secs.clear();
	}

}
