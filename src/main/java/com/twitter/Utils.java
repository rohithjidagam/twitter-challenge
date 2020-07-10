package com.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Utils {

	/**
	 * function to read file and process input line by line
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Set<String> readFile(File file) throws Exception {
		BufferedReader reader;
		Set<String> set = new HashSet<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				set.add(normalize(line));
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			throw new Exception("File processing error", e);
		}
		return set;
	}

	/**
	 * function to normalize string
	 * 
	 * converts the folder format to 1 type: 
	 * e.g: string src/main/java converts to src\main\java in windows
	 * in linux, File separator will be / and string remains same.
	 * 
	 * @param line
	 * @return
	 */
	public static String normalize(String line) {
		StringBuilder sb = new StringBuilder();
		for (String st : line.split("/")) {
			sb.append(st).append(File.separator);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	/**
	 * function to get the absolute path
	 * 
	 * @param root
	 * @param curDir
	 * @return
	 */
	public static String getAbsolutePath(String root, String curDir) {
		return root + File.separator + curDir;
	}
	
	/**
	 * function to get file object from name string
	 * 
	 * @param name
	 * @return
	 */
	public static File getFileObject(String name) {
		return new File(Constants.REPO_ROOT + File.separator + normalize(name));
	}
}
