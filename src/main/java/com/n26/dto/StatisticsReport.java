package com.n26.dto;

public class StatisticsReport {

	private String sum = "0.00";
	private long count = 0l;
	private String max = Double.MIN_VALUE + "";
	private String min = Double.MAX_VALUE + "";
	private String avg = "0.00";

	public StatisticsReport() {
	}

	public StatisticsReport(Statistics statistics) {
		this.sum = statistics.getSum().toString();
		this.count = statistics.getCount();
		this.max = statistics.getMax().toString();
		this.min = statistics.getMin().toString();
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avg == null) ? 0 : avg.hashCode());
		result = prime * result + (int) (count ^ (count >>> 32));
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		result = prime * result + ((sum == null) ? 0 : sum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatisticsReport other = (StatisticsReport) obj;
		if (avg == null) {
			if (other.avg != null)
				return false;
		} else if (!avg.equals(other.avg))
			return false;
		if (count != other.count)
			return false;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		if (sum == null) {
			if (other.sum != null)
				return false;
		} else if (!sum.equals(other.sum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatisticsReport [sum=" + sum + ", count=" + count + ", max=" + max + ", min=" + min + ", avg=" + avg
				+ "]";
	}

}
