package com.rnickerson.code.mediabackup.core;

public class FSObject {
	
	public enum objectType { FILE, FOLDER };
	
	private objectType type;
	private String abspath;
	private String relpath;
	private String name;
	private boolean backedup;
	
	public FSObject() {
		this.type = objectType.FILE;
		this.abspath = null;
		this.relpath = null;
		this.name = null;
		this.backedup = false;
	}

	public objectType getType() {
		return type;
	}

	public void setType(objectType type) {
		this.type = type;
	}

	public String getAbspath() {
		return abspath;
	}

	public void setAbspath(String abspath) {
		this.abspath = abspath;
	}

	public String getRelpath() {
		return relpath;
	}

	public void setRelpath(String relpath) {
		this.relpath = relpath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBackedup() {
		return backedup;
	}

	public void setBackedup(boolean backedup) {
		this.backedup = backedup;
	}

}
