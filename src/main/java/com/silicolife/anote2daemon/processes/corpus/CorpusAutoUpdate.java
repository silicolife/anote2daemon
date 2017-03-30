package com.silicolife.anote2daemon.processes.corpus;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.silicolife.textmining.core.datastructures.exceptions.process.InvalidConfigurationException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class CorpusAutoUpdate {
	
	static Logger logger = Logger.getLogger(CorpusAutoUpdate.class.getName());
	
	private static long ioExceptionProblemSleepingTimeHours = 10;
	private static long ioExceptionProblemSleepingProblemSleepingTime =  ioExceptionProblemSleepingTimeHours * 60 * 1000;
	private static long delayCycleDays = 7;
	private static long delayCycle = delayCycleDays*24*60*60*1000;
	
	public static void run()
	{
		pubmedUpdate();
		pmcUpdate();
	}
	
	private static void pmcUpdate() {
		new Thread() {
            public void run() {
				try {
					pmcCycleUpdate();
				} catch (ANoteException e) {
					logger.error("ANoteException" + e.getMessage());
					e.printStackTrace();
				} catch (InterruptedException e) {
					logger.error("InterruptedException" + e.getMessage());
					e.printStackTrace();
				} catch (InvalidConfigurationException e) {
					logger.error("InvalidConfigurationException" + e.getMessage());
					e.printStackTrace();
				}          
        	}
        }.start();				
	}

	private static void pubmedUpdate() {
		new Thread() {
            public void run() {
				try {
					pubmedCycleUpdate();
				} catch (ANoteException e) {
					logger.error("ANoteException" + e.getMessage());
					e.printStackTrace();
				} catch (InterruptedException e) {
					logger.error("InterruptedException" + e.getMessage());
					e.printStackTrace();
				} catch (InvalidConfigurationException e) {
					logger.error("InvalidConfigurationException" + e.getMessage());
					e.printStackTrace();
				}          
        	}
        }.start();				
	}
	
	private static void pmcCycleUpdate() throws ANoteException, InterruptedException, InvalidConfigurationException{
		boolean keepRunning = true;
		while(keepRunning)
		{
			try {
				PMCUpdateExecutionManager.getInstance().startExecution();
			} catch (IOException e) {
				logger.info("IOException : sleeping "+ioExceptionProblemSleepingTimeHours + " hour(s)",e);
				Thread.sleep(ioExceptionProblemSleepingProblemSleepingTime);
			} 
			logger.info("Delay Cycle "+delayCycleDays  + " day(s)");
			Thread.sleep(delayCycle);
		}
	}
	
	private static void pubmedCycleUpdate() throws ANoteException, InterruptedException, InvalidConfigurationException{
		boolean keepRunning = true;
		while(keepRunning)
		{
			try {
				PubmedUpdateExecutionManager.getInstance().startExecution();
			} catch (IOException e) {
				logger.info("IOException : sleeping "+ioExceptionProblemSleepingTimeHours + " hour(s)",e);
				Thread.sleep(ioExceptionProblemSleepingProblemSleepingTime);
			} 
			logger.info("Delay Cycle "+delayCycleDays  + " day(s)");
			Thread.sleep(delayCycle);
		}
	}

}
