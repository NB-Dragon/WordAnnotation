package FTPOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPConnection {
	private FTPClient ftp=null;
	private static final FTPConnection ftpADO=new FTPConnection();
	
	public FTPConnection(){
		ftp=new FTPClient();
		ftp.setControlEncoding("GBK");
	}
	
	public void ConnectFTP(String url, int port, String username, String password) throws SocketException, IOException{
		ftp.connect(url, port);
		Login(username, password);
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	}
	
	public int Login(String username, String password) throws IOException{
		ftp.login(username, password);
		return ftp.getReplyCode();
	}
	
	public boolean downloadFile(String localFile, String ftpFile) throws FileNotFoundException, IOException{
		boolean result=false;
		OutputStream os=new FileOutputStream(new File(localFile));
		result=ftp.retrieveFile(ftpFile,os);os.close();
		return result;
	}
	
	public boolean uploadFile(String localFile, String ftpFile) throws FileNotFoundException, IOException{
		boolean result=false;
		InputStream is=new FileInputStream(new File(localFile));
		result=ftp.storeFile(ftpFile,is);is.close();
		return result;
	}
	
	public ArrayList<String> getFileList(String pathName,String suf) throws IOException{
		ArrayList<String> filelist=new ArrayList<String>();
		for(FTPFile file:ftp.listFiles(pathName)){
			if(file.isFile()&&file.getName().endsWith(suf))filelist.add(file.getName());
		}
		return filelist;
	}
	
	public static FTPConnection getADO(){
		return ftpADO;
	}
	
	
	public FTPClient getFTP(){
		return ftp;
	}
	
	public String getReplyString(int num){
		switch(num){
		case 226:return "�ļ�����ɹ�";
		case 230:return "�û���½�ɹ�";
		case 530:return "�û�����������������¼���������ȷ��Ϣ";
		case 550:return "����ʧ�ܣ�ԭ���ǵ�ǰ��¼���û�Ȩ�޲���������ϵ����Ա���н��";
		}
		return null;
	}
}
