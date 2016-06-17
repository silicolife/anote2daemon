package com.silicolife.anote2daemon.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public abstract class SpringRunnable implements Runnable{

	private RequestAttributes requestAttributes;
	private Thread thread;


	public SpringRunnable(){
		this.requestAttributes = RequestContextHolder.getRequestAttributes();
		this.thread = Thread.currentThread();
	}


	@Override
	public void run() {
		try {
			RequestContextHolder.setRequestAttributes(requestAttributes);
			onRun();
		} finally {
			if (Thread.currentThread() != thread) {
				RequestContextHolder.resetRequestAttributes();
			}
			thread = null;
		}
	}


	protected abstract void onRun();

}
