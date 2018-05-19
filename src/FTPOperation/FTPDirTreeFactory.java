package FTPOperation;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPDirTreeFactory {
	private ArrayList<FTPDirTreeEnt> FTPDirTreeRoot=new ArrayList<FTPDirTreeEnt>();

	public FTPDirTreeFactory(FTPClient ftp,String pathName){
		FTPDirTreeEnt dirtreeent=new FTPDirTreeEnt();
		dirtreeent.setName("ftp://"+ftp.getRemoteAddress().getHostAddress());
		dirtreeent.setPathName("/");
		try {
			if(ftp.listDirectories("/").length>0)dirtreeent.setChildren(new ArrayList<FTPDirTreeEnt>());
		} catch (IOException e) {
			e.printStackTrace();
		}
		FTPDirTreeRoot.add(dirtreeent);
	}
	
	public static ArrayList<FTPDirTreeEnt> creatDirTree (FTPClient ftp,String pathName){
		ArrayList<FTPDirTreeEnt> root_temp=new ArrayList<FTPDirTreeEnt>();
		try {
			for(FTPFile file:ftp.listDirectories(pathName)){
				FTPDirTreeEnt dirtreeent=new FTPDirTreeEnt();
				dirtreeent.setName(file.getName());
				dirtreeent.setPathName(pathName+file.getName()+"/");
				if(ftp.listDirectories(pathName+file.getName()).length>0)dirtreeent.setChildren(new ArrayList<FTPDirTreeEnt>());
				root_temp.add(dirtreeent);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root_temp;
	}
	
	public ArrayList<FTPDirTreeEnt> getFTPDirTreeRoot(){
		return FTPDirTreeRoot;
	}
	
}
