package com.build.supply.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.build.supply.controller.statistics.IncomingData;
import com.build.supply.controller.statistics.StatisticsData;
import com.build.supply.controller.statistics.StatisticsDataService;
import com.build.supply.utils.Constants;

@RestController
public class BuildSupplyController {
	
	@Autowired
	private StatisticsDataService service;
	
	@RequestMapping(value = "/hello")
	public String getMessage() {
		String res = System.getenv("HELLO_MESSAGE");
		return res == null ? "It's not working " : res;
	}
	
	@RequestMapping(value = "/statistics")
	public StatisticsData getStats() {
		return service.getStatistics();
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/transactions")
	public ResponseEntity<?> setTranscation(@RequestBody IncomingData data) {
		if(Instant.now().getEpochSecond() - data.getTimestamp() <= Constants.MAX_DIFFERENCE) {
			service.addAmount(data);
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
}
