package MainVisual;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

import FTPOperation.FTPConnection;
import FTPOperation.FTPDirTreeEnt;
import FTPOperation.FTPDirTreeFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class FtpVisual extends Shell {
	private static class ViewerLabelProvider_1 extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return ((FTPDirTreeEnt)element).getName();
		}
	}
	private static class TreeContentProvider implements ITreeContentProvider {
		public Object[] getElements(Object inputElement) {
			return ((FTPDirTreeFactory)inputElement).getFTPDirTreeRoot().toArray();
		}
		public Object[] getChildren(Object parentElement) {
			((FTPDirTreeEnt)parentElement).setChildren(FTPDirTreeFactory.creatDirTree(ftpADO.getFTP(), ((FTPDirTreeEnt)parentElement).getPathName()));
			return ((FTPDirTreeEnt)parentElement).getChildren().toArray();
		}
		public boolean hasChildren(Object element) {
			return ((FTPDirTreeEnt)element).getChildren()!=null;
		}
		public Object getParent(Object arg0) {
			return null;
		}
	}
	
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return ((ArrayList<?>)inputElement).toArray();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return (String)element;
		}
	}
	
	private Text text_Address;
	private Text text_User;
	private Text text_Password;
	private Text text_Port;
	private Text text_ftpPath;
	private TreeViewer treeViewer;
	private Tree tree;
	private ListViewer listViewer;
	private List list;
	private static final FTPConnection ftpADO=FTPConnection.getADO();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			FtpVisual shell = new FtpVisual(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public FtpVisual(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(2, false));
		
		Group group_Ftp = new Group(this, SWT.NONE);
		group_Ftp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		group_Ftp.setText("ftp\u64CD\u4F5C\u5668");
		group_Ftp.setLayout(new GridLayout(5, false));
		
		Label lblftp = new Label(group_Ftp, SWT.NONE);
		lblftp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblftp.setText("\u670D\u52A1\u5668\u5730\u5740\uFF1A");
		
		text_Address = new Text(group_Ftp, SWT.BORDER);
		GridData gd_text_Address = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_Address.minimumWidth = 80;
		gd_text_Address.widthHint = 100;
		text_Address.setLayoutData(gd_text_Address);
		
		Label label_2 = new Label(group_Ftp, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u7AEF\u53E3\u53F7\uFF1A");
		
		text_Port = new Text(group_Ftp, SWT.BORDER);
		text_Port.setToolTipText("\u9ED8\u8BA4\u7AEF\u53E3\u53F7\u4E3A21\uFF0C\u9664\u7279\u6B8A\u60C5\u51B5\u4EE5\u5916\u4E0D\u8981\u66F4\u6539");
		text_Port.setText("21");
		GridData gd_text_Port = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_Port.minimumWidth = 80;
		gd_text_Port.widthHint = 100;
		text_Port.setLayoutData(gd_text_Port);
		
		Button button_connect = new Button(group_Ftp, SWT.NONE);
		GridData gd_button_connect = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_connect.widthHint = 80;
		button_connect.setLayoutData(gd_button_connect);
		button_connect.setText("\u8FDE\u63A5\u670D\u52A1\u5668");
		
		Label label = new Label(group_Ftp, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u7528\u6237\u540D\uFF1A");
		
		text_User = new Text(group_Ftp, SWT.BORDER);
		text_User.setText("Anonymous");
		GridData gd_text_User = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_User.minimumWidth = 80;
		gd_text_User.widthHint = 100;
		text_User.setLayoutData(gd_text_User);
		
		Label label_1 = new Label(group_Ftp, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u5BC6\u7801\uFF1A");
		
		text_Password = new Text(group_Ftp, SWT.BORDER | SWT.PASSWORD);
		GridData gd_text_Password = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_Password.minimumWidth = 80;
		gd_text_Password.widthHint = 100;
		text_Password.setLayoutData(gd_text_Password);
		
		Button button_Login = new Button(group_Ftp, SWT.NONE);
		GridData gd_button_Login = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_Login.widthHint = 80;
		button_Login.setLayoutData(gd_button_Login);
		button_Login.setText("\u767B\u5F55\u670D\u52A1\u5668");
		
		Group group_List = new Group(this, SWT.NONE);
		group_List.setText("\u6587\u4EF6\u5217\u8868");
		group_List.setLayout(new GridLayout(2, false));
		group_List.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		text_ftpPath = new Text(group_List, SWT.BORDER | SWT.READ_ONLY);
		text_ftpPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		treeViewer = new TreeViewer(group_List, SWT.BORDER);
		treeViewer.setExpandPreCheckFilters(true);
		tree = treeViewer.getTree();
		tree.setLinesVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		treeViewer.setLabelProvider(new ViewerLabelProvider_1());
		treeViewer.setContentProvider(new TreeContentProvider());
		
		listViewer = new ListViewer(group_List, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		listViewer.setUseHashlookup(true);
		list = listViewer.getList();
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Group group_Operate = new Group(this, SWT.NONE);
		group_Operate.setText("\u6587\u4EF6\u64CD\u4F5C");
		group_Operate.setLayout(new GridLayout(1, false));
		group_Operate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Button button_Up = new Button(group_Operate, SWT.NONE);
		GridData gd_button_Up = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_Up.widthHint = 80;
		button_Up.setLayoutData(gd_button_Up);
		button_Up.setText("\u4E0A\u4E00\u4E2A");
		
		Button button_Down = new Button(group_Operate, SWT.NONE);
		GridData gd_button_Down = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_Down.widthHint = 80;
		button_Down.setLayoutData(gd_button_Down);
		button_Down.setText("\u4E0B\u4E00\u4E2A");
		
		Button button_NewIn = new Button(group_Operate, SWT.NONE);
		GridData gd_button_NewIn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_NewIn.widthHint = 80;
		button_NewIn.setLayoutData(gd_button_NewIn);
		button_NewIn.setText("\u5BFC\u5165\u8BE5\u6587\u4EF6");
		
		Button button_Del = new Button(group_Operate, SWT.NONE);
		GridData gd_button_Del = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_Del.widthHint = 80;
		button_Del.setLayoutData(gd_button_Del);
		button_Del.setText("\u5220\u9664\u8BE5\u6587\u4EF6");
		
		Button button_Upload = new Button(group_Operate, SWT.NONE);
		GridData gd_button_Upload = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_Upload.widthHint = 80;
		button_Upload.setLayoutData(gd_button_Upload);
		button_Upload.setText("\u6279\u91CF\u4E0A\u4F20");
		
		Button button_Download = new Button(group_Operate, SWT.NONE);
		GridData gd_button_Download = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_Download.widthHint = 80;
		button_Download.setLayoutData(gd_button_Download);
		button_Download.setText("\u6279\u91CF\u4E0B\u8F7D");
		listViewer.setContentProvider(new ContentProvider());
		listViewer.setLabelProvider(new ViewerLabelProvider());
		createContents();
		
		button_connect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(text_Address.getText().equals("")||text_Port.getText().equals(""))return;
					ftpADO.ConnectFTP(text_Address.getText(), Integer.parseInt(text_Port.getText()), text_User.getText(), text_Password.getText());
					treeViewer.setInput(new FTPDirTreeFactory(ftpADO.getFTP(), "/"));
					listViewer.setInput(ftpADO.getFileList("",".ann"));
					text_ftpPath.setText("/");
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SocketException e1) {
					MessageDialog.openInformation(getShell(), "温馨提示","连接超时，可能对方ftp服务器尚未开启，请检查后重新连接");
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		button_Login.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					MessageDialog.openInformation(getShell(), "温馨提示",ftpADO.getReplyString(ftpADO.Login(text_User.getText(), text_Password.getText())));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		button_Up.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list.setSelection(list.getSelectionIndex()-1);
			}
		});
		
		button_Down.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list.setSelection(list.getSelectionIndex()+1);
			}
		});
		
		button_NewIn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(list.getSelectionIndex()<0)return;
					if(MainVisual.state){if(MainVisual.message_save()==0)return;}
					String file=list.getItem(list.getSelectionIndex());
					MainVisual.ReadFile(ftpADO.getFTP().retrieveFileStream(text_ftpPath.getText()+file.substring(0,file.lastIndexOf("."))+".txt"));
					System.out.println(ftpADO.getReplyString(ftpADO.getFTP().getReply()));
					MainVisual.ReadFormat(ftpADO.getFTP().retrieveFileStream(text_ftpPath.getText()+file));
					System.out.println(ftpADO.getReplyString(ftpADO.getFTP().getReply()));
					MainVisual.lable_filepath.setText("ftp://"+text_Address.getText()+text_ftpPath.getText());
					MainVisual.lable_filepath.setToolTipText(file);
					MainVisual.shell.setText(MainVisual.shell.getText().substring(0,MainVisual.shell.getText().lastIndexOf("[")+1)+file+"]");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		button_Del.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(ftpADO.getFTP().deleteFile(text_ftpPath.getText()+list.getItem(list.getSelectionIndex()))){
						list.remove(list.getSelectionIndex());
					}else{
						MessageDialog.openInformation(getShell(), "警告",ftpADO.getReplyString(550));
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		button_Upload.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(transport("上传")!=1)return;
				Object[] object=OpenFile(2,SWT.OPEN | SWT.MULTI);if(((String)object[0]).equals(""))return;
				for(String temp:(String[])object[1]){
					try {
						if(!ftpADO.uploadFile((String)object[0]+"\\"+temp,text_ftpPath.getText()+temp)){
							MessageDialog.openInformation(getShell(), "警告",ftpADO.getReplyString(550));return;
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				MessageDialog.openInformation(getShell(), "提示","所选文件均上传成功");
			}
		});
		
		button_Download.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(transport("下载")!=1)return;
				String path=SaveDir(0,SWT.OPEN);if(path==null)return;
				for(int i=0;i<list.getSelection().length;i++){
					try {
						if(!ftpADO.downloadFile(path+"\\"+list.getSelection()[i],text_ftpPath.getText()+list.getSelection()[i])){
							MessageDialog.openInformation(getShell(), "警告",ftpADO.getReplyString(550));return;
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				MessageDialog.openInformation(getShell(), "提示","所选文件均下载成功");
			}
		});
		
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String path="/";TreeItem treeitem=tree.getSelection()[0];
					while(treeitem!=null && !treeitem.getText(0).startsWith("ftp://")){
						path="/"+treeitem.getText(0)+path;
						treeitem=treeitem.getParentItem();
					}
					text_ftpPath.setText(path);
					listViewer.setInput(ftpADO.getFileList(text_ftpPath.getText(),".ann"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private int transport(String way){
		MessageBox messagebox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		messagebox.setText("信息提示");
		messagebox.setMessage("当前ftp下的目录为:\""+text_ftpPath.getText()+"\",是否要在该目录下进行"+way+"操作？");
		switch (messagebox.open()){
		case SWT.YES:return 1;
		case SWT.NO:return 2;
		}
		return 0;
	}
	
	private Object[] OpenFile(int choosen,int style){
		FileDialog fDialog=new FileDialog(getShell(),style);
		fDialog.setFilterExtensions(new String[]{"*.*","*.txt","*.ann"});
		fDialog.setFilterNames(new String[]{"所有文件(*.*)","文本文件(*.txt)","关系文件(*.ann)"});
		fDialog.setFilterIndex(choosen);fDialog.open();
		return new Object[]{fDialog.getFilterPath(),fDialog.getFileNames()};
	}
	
	private String SaveDir(int choosen,int style){
		DirectoryDialog dirDialog=new DirectoryDialog(getShell(),style);
		dirDialog.setMessage("请选择一个目录");
		return dirDialog.open();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Ftp\u670D\u52A1\u5668");
		setSize(480, 363);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
