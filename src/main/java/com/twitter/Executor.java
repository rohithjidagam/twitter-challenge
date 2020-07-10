package com.twitter;

import java.util.HashMap;
import java.util.Map;

public class Executor {
	
	private Directory root;
	private Map<String, Directory> directoryMap;
	private DependencyGenerator parser;
	private Validator validator;

	public Executor() {
		root = new Directory(Constants.REPO_ROOT);
		directoryMap = new HashMap<String, Directory>();
		directoryMap.put(root.name, root);

		validator = new Validator();
		parser = new DependencyGenerator();
	}

	/**
	 * executor function to process the given input and output the result.
	 * 
	 * @param approversData
	 * @param filesData
	 * @return
	 * @throws Exception
	 */
	public String execute(String approversData, String filesData) throws Exception {

		try {
			parser.populateDependencies(root.name, directoryMap);
		} catch (Exception e) {
			throw new Exception("Error in population Dependencies", e);
		}
		
		String[] approvers = approversData.split(Constants.INPUT_SEPARATOR);
		String[] changedFiles = filesData.split(Constants.INPUT_SEPARATOR);
		
		return validator.validateFiles(directoryMap, approvers, changedFiles) ? Constants.APPROVED : Constants.INSUFFICIENT_APPROVALS;
	}
}
