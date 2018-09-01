package com.build.supply.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class Worker extends Thread {
	Date startDate = new Date();
	
	@Override
	public void run() {
		try {
			while(true) {
				HttpURLConnection urlConnection = (HttpURLConnection) new URL("https://protected-sands-59177.herokuapp.com/hello").openConnection();
				Worker.sleep(840000);//840000
				System.out.println("Woke Up");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
