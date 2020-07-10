package com.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Driver Class that starts the execution of approval validator.
 * 
 * @author Rohith Jidagam
 *
 */
public class Driver {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Driver.class);
		if (args.length == 4) {
			try {
				Executor executor = new Executor();
				logger.info(executor.execute(args[1], args[3]));
			} catch (Exception e) {
				logger.error("Internal Server Error", e);
			}
		} else {
			logger.error("Invalid arguments");
		}
	}
}