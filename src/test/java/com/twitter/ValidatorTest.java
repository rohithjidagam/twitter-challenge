package com.twitter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ValidatorTest {

	private final Validator validator = new Validator();

	@Test
	public void testValidateFileswithNullPointerException() {
		String changedFiles = "src/com/twitter/tweet/Tweet.java";
		String approvers = "mfox";

		Map<String, Directory> directoryMap = new HashMap<String, Directory>();
		String path = "repo_root";
		Directory directory = new Directory(path);
		directoryMap.put(path, directory);
		try {
			validator.validateFiles(directoryMap, approvers.split(","), changedFiles.split(","));
		} catch (Exception e) {
			assertTrue("Check for null pointer", e instanceof NullPointerException);
		}
	}

	@Test
	public void testValidateFiles() {
		String testFile = "src" + File.separator + "com" + File.separator + "twitter" + File.separator + "tweet";
		String changedFiles = testFile + File.separator + "Tweet.java";
		String approvers = "mfox";

		Map<String, Directory> directoryMap = new HashMap<String, Directory>();
		String path = "repo_root";
		Directory directory = new Directory(path);
		directoryMap.put(path, directory);
		directoryMap.put(path + File.separator + testFile, new Directory(path + File.separator + testFile));

		boolean validateFiles = validator.validateFiles(directoryMap, approvers.split(","), changedFiles.split(","));
		assertFalse(validateFiles);
	}
}
