package com.twitter;

import org.junit.Assert;
import org.junit.Test;

public class DirectoryTest {

	@Test
	public void testDirectory() {
		String expectedName = "src/com/test";
		Directory dir = new Directory(expectedName);

		Assert.assertNotNull(dir);
		Assert.assertEquals(expectedName, dir.name);
		Assert.assertNotNull(dir.owners);
		Assert.assertNotNull(dir.dependencies);
		Assert.assertEquals(0, dir.owners.size());
		Assert.assertEquals(0, dir.dependencies.size());
	}
}
