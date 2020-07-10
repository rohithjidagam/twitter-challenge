package com.twitter;

import java.util.HashSet;
import java.util.Set;

/**
 * Directory Class
 * 
 * Each directory has name, Set of owners and Set of dependencies.
 * 
 */
public class Directory {

	String name;
	Set<String> owners;
	Set<Directory> dependencies;

	public Directory(String path) {
		this.name = path;
		owners = new HashSet<String>();
		dependencies = new HashSet<Directory>();
	}
	
	@Override
	public String toString() {
		return name;
	}
}
