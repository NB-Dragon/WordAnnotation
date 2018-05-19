package FTPOperation;

import java.util.ArrayList;

public class FTPDirTreeEnt {
	private String name;
	private String pathName;
	private ArrayList<FTPDirTreeEnt> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public ArrayList<FTPDirTreeEnt> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<FTPDirTreeEnt> children) {
		this.children = children;
	}

}
