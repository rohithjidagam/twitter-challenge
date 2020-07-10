package com.twitter;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DependencyGeneratorTest {

	private DependencyGenerator dependencyGenerator = new DependencyGenerator();

	@Test
	public void testPopulateDependencies() {
		Map<String, Directory> directoryMap = new HashMap<String, Directory>();
		String path = "repo_root";
		Directory directory = new Directory(path);
		directoryMap.put(path, directory);

		try {
			dependencyGenerator.populateDependencies(path, directoryMap);
		} catch (Exception e) {
			fail();
		}
		assertTrue("Check the dependency directory map contains more than one entry", directoryMap.size() > 1);
	}

}
