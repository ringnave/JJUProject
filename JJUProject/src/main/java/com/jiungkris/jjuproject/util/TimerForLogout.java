package com.jiungkris.jjuproject.util;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

public class TimerForLogout {
	private int count = 0;
	private Timer timer;
	private boolean isPaused = false;
	
	public TimerForLogout(final HttpSession session) {
		
		timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				
				// isPaused is for when you change a tab in a browser.
				if(isPaused == false) {
					
					// If you don't reset the count to 0, by the time over 3 seconds, it'll sign out.
					if(count >= 3) {
						try {
							session.invalidate();
						} catch (Exception e) {
							e.getStackTrace();
						}
						timer.cancel();
					}
					count++;
				}
				else {
					count = 0;
				}
			}
		};
		timer.schedule(task, 0, 1000);
	}

	
	
	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
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
