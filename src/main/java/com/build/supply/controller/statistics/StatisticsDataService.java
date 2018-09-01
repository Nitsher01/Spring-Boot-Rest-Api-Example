package com.build.supply.controller.statistics;

import java.time.Instant;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.build.supply.utils.Constants;

@Service
public class StatisticsDataService {

	@Autowired
	private StatsRepo statsRepo;

	private ConcurrentNavigableMap<Long, IncomingData> incomingDataMap = new ConcurrentSkipListMap<>();

	public void addAmount(IncomingData data) {
		incomingDataMap.put(data.getTimestamp(), data);
		statsRepo.save(data);
	}

	public StatisticsData getStatistics() {
		ConcurrentNavigableMap<Long, IncomingData> subMap = incomingDataMap.subMap(Instant.now().getEpochSecond() - Constants.MAX_DIFFERENCE, Instant.now().getEpochSecond());
		if(subMap != null && !subMap.isEmpty()) {
			double statsSum = 0.0;
			double maximum = Double.MIN_VALUE;
			double minimum = Double.MAX_VALUE;
			long count = 0;// map.size return int , might overflow 
			for(Long id : subMap.keySet()) {
				count++;
				double amount = subMap.get(id).getAmount();
				statsSum += amount;
				if(amount > maximum) {
					maximum = amount;
				}
				if(amount <= minimum) {
					minimum = amount;
				}
			}
			return new StatisticsData(statsSum, statsSum/count, maximum, minimum, count);
		}else {
			return new StatisticsData();
		}
	}
}
