package com.twitter;

import java.io.File;
import java.io.FileWriter;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

	@Test
	public void testReadFile() {
		try {
			File file = new File("testFile");
			FileWriter myWriter = new FileWriter(file);
			myWriter.write("src/com/File.java\nsrc/com/Test.java");
			myWriter.close();
			Set<String> readFile = Utils.readFile(file);
			Assert.assertNotNull(readFile);
			Assert.assertEquals(2, readFile.size());
			file.delete();
		} catch (Exception e) {
			System.err.println("An error occurred");
		}
	}

	@Test
	public void testNormalize() {
		String result = Utils.normalize("src/com/File.java");
		Assert.assertNotNull(result);
		Assert.assertEquals(true, result.contains(File.separator));
	}

	@Test
	public void testgetAbsolutePath() {
		String result = Utils.getAbsolutePath("root", "curDir");
		Assert.assertNotNull(result);
		Assert.assertEquals(true, result.contains(File.separator));
	}

	@Test
	public void testgetFileObject() {
		File result = Utils.getFileObject("src/com/File.java");
		Assert.assertNotNull(result);
		Assert.assertTrue(result instanceof File);
	}
}
