package com.jiungkris.jjuproject.util;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

public class TimerForLogout {
	
	private int count = 0;
	private Timer timer;
	
	public TimerForLogout(final HttpSession session) {
		
		timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				if(count >= 3) {
					session.invalidate();
					timer.cancel();
				}
				count++;
			}
		};
		timer.schedule(task, 0, 1000);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void cancel() {
		timer.cancel();
	}
}
