/**
 * @author zhoutao
 *  2010-6-13
 */
package com.jlt.quartz;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zhoutao
 * 
 */
public class ScanDirectoryJob implements Job {

	static Log logger = LogFactory.getLog(ScanDirectoryJob.class);

	public void execute(JobExecutionContext jec) throws JobExecutionException {
		
		// Every job has its own job detail
		JobDetail jd = jec.getJobDetail();
		
		// The name is defined in the job definition
		String jobName = jd.getName();
		
		// Log the time the job started
		logger.info(jobName + "fired at " + new Date());
		
		// The directory to scan is stored in the job map
		JobDataMap jdm = jd.getJobDataMap();
		String dirName = jdm.getString("SCAN_DIR");
		
		// Validate the required input
		if (dirName == null) {
			throw new JobExecutionException("Directory not configured");
		}

		// Make sure the directory exists
		File dir = new File(dirName);
		if (!dir.exists()) {
			throw new JobExecutionException("Invalid Dir " + dirName);
		}

		// Use FileFilter to get only XML files
		FilenameFilter filter = new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				if(!name.endsWith(".txt"))
					return false;
				else
					return true;
			}
		};

		File[] files = dir.listFiles(filter);

		if (files == null || files.length <= 0) {
			logger.info("No TXT files found in " + dir);

			// Return since there were no files
			return;
		}

		// The number of TXT files
		int size = files.length;

		// Iterate through the files found
		for (int i = 0; i < size; i++) {

			File file = files[i];

			// Log something interesting about each file.
			File aFile = file.getAbsoluteFile();
			long fileSize = file.length();
			String msg = aFile + " - Size: " + fileSize;
			logger.info(msg);
		}

	}

}
