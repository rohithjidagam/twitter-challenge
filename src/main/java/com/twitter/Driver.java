package com.twitter;

/**
 * Driver Class that starts the execution of approval validator.
 * 
 * @author Rohith Jidagam
 *
 */
public class Driver {

	public static void main(String[] args) {
		if (args.length == 4) {
			try {
				Executor executor = new Executor();
				System.out.println(executor.execute(args[1], args[3]));
			} catch (Exception e) {
				System.err.println(e);
			}
		} else {
			System.err.println("Invalid arguments");
		}

	}
}