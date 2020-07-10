package com.twitter;

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
			
			System.out.println(dirName);

			// Check for owners in the changed File directory
			if (checkOwners(directoryMap.get(dirName).owners, approverSet))
				return false;

			Set<Directory> visited = new HashSet<Directory>();
			visited.add(directoryMap.get(dirName));

			// Check for owners in the dependencies of changed File
			if (!dfs(directoryMap.get(dirName).dependencies, visited, approverSet))
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
	private boolean dfs(Set<Directory> dependencies, Set<Directory> visited, Set<String> approverSet) {

		for (Directory dependency : dependencies) {
			if (visited.contains(dependency))
				continue;

			visited.add(dependency);
			if (checkOwners(dependency.owners, approverSet))
				return false;
			if (!dfs(dependency.dependencies, visited, approverSet))
				return false;
		}

		return true;
	}

	/**
	 * Check whether approvers are in owners Set of each dependency.
	 * @param ownersSet
	 * @param approverSet
	 * @return
	 */
	private boolean checkOwners(Set<String> ownersSet, Set<String> approverSet) {
		Set<String> owners = new HashSet<String>();
		owners.addAll(ownersSet);
		owners.retainAll(approverSet); // intersection
		return owners.isEmpty();
	}
}
