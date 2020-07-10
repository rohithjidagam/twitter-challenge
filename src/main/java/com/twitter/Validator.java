package com.twitter;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Validator {

	/**
	 * Function to validate changed files and approvers
	 * 
	 * @param directoryMap
	 * @param approvers
	 * @param changedFiles
	 * @return
	 */
	public boolean validateFiles(Map<String, Directory> directoryMap, String[] approvers, String[] changedFiles) {

		Set<String> approverSet = new HashSet<String>(Arrays.asList(approvers));
		for (String changedFile : changedFiles) {

			String dirName = Utils.getFileObject(changedFile).getParent();

			// Check for owners in the changed File directory
			if (!checkOwners(dirName, approverSet, directoryMap))
				return false;

			Set<Directory> visited = new HashSet<Directory>();
			visited.add(directoryMap.get(dirName));

			// Check for owners in the dependencies of changed File
			if (!dfs(dirName, visited, approverSet, directoryMap))
				return false;
		}

		return true;
	}

	/**
	 * depth first search all dependencies until we find a match
	 * 
	 * @param dependencies
	 * @param visited
	 * @param approverSet
	 * @return
	 */
	private boolean dfs(String dir, Set<Directory> visited, Set<String> approverSet,
			Map<String, Directory> directoryMap) {

		for (Directory dependency : directoryMap.get(dir).dependencies) {
			if (visited.contains(dependency))
				continue;

			visited.add(dependency);
			if (!checkOwners(dir, approverSet, directoryMap))
				return false;
			if (!dfs(dependency.name, visited, approverSet, directoryMap))
				return false;
		}

		return true;
	}

	/**
	 * Check whether approvers are in owners Set of each dependency.
	 * If not, recurse for each parent and check owners
	 * 
	 * @param ownersSet
	 * @param approverSet
	 * @param directoryMap
	 * @return
	 */
	private boolean checkOwners(String dir, Set<String> approverSet, Map<String, Directory> directoryMap) {
		if (directoryMap.get(dir) == null)
			return false;

		Set<String> owners = new HashSet<String>();
		owners.addAll(directoryMap.get(dir).owners);
		owners.retainAll(approverSet); // intersection

		return !owners.isEmpty() ? true
				: dir.lastIndexOf(File.separator) != -1
						&& checkOwners(dir.substring(0, dir.lastIndexOf(File.separator)), approverSet, directoryMap);
	}
}
