package com.twitter;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class DependencyGenerator {

	/**
	 * This function parses the folder structure and populates the directoryMap.
	 * 
	 * The dependencies are linked by reverse mapping. For e.g. 
	 * As shown below, x and y are directories, x depends on y
	 * 
	 * The dependencies are populated like: y -> x (y has x in its dependencies list)
	 * 
	 *  x/
    	 DEPENDENCIES = "y\n"
        y/
        
        If there are owners in a folder, they are added to that Directory object
	 * 
	 * @param path
	 * @param directoryMap
	 * @throws Exception
	 */
	public void populateDependencies(String path, Map<String, Directory> directoryMap) throws Exception {

		File dirPath = new File(Utils.getAbsolutePath(System.getProperty(Constants.USER_DIR), path));

		for (File file : dirPath.listFiles()) {
			if (file.isFile() && file.getName().contains(Constants.DEPENDENCIES)) {
				// add dependencies
				Set<String> deps = Utils.readFile(file);
				for (String dep : deps) {
					String key = Utils.getAbsolutePath(Constants.REPO_ROOT, dep);

					directoryMap.putIfAbsent(key, new Directory(key));
					directoryMap.get(key).dependencies.add(directoryMap.get(path));
				}
			} else if (file.isFile() && file.getName().contains(Constants.OWNERS)) {
				// add owners
				directoryMap.get(path).owners.addAll(Utils.readFile(file));
			} else if (file.isDirectory()) {
				String dirName = Utils.getAbsolutePath(path, file.getName());
				directoryMap.putIfAbsent(dirName, new Directory(dirName));
				
				// recurse for sub directories
				populateDependencies(Utils.getAbsolutePath(path, file.getName()), directoryMap);
			}
		}
	}
}
