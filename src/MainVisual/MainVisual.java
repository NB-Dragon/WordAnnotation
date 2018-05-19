package MainVisual;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;

import DataCollection.ColorBox;
import DataCollection.MenuTranslate;
import DataCollection.MenuTranslate_null;
import DataCollection.NameEnt;
import DataCollection.NameFactory;
import DataCollection.RelEnt;
import DataCollection.RelFactory;
import FTPOperation.FTPConnection;
import MenuBar.Menu_Ent;
import MenuBar.Menu_Rel;
import MenuBar.Menu_Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

@SuppressWarnings("deprecation")
public class MainVisual {
	private static class ContentProvider_1 implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return ((RelFactory)inputElement).getBooksList().toArray();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private class TableLabelProvider_1 extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			RelEnt books_rel=(RelEnt)element;
			switch (columnIndex){
			case 0:return table_Rel.getItemCount()+"";
			case 1:return ((NameEnt)tableViewer_Ent.getElementAt(Integer.parseInt(books_rel.getMainEnt().substring(1))-1)).getEnt();
			case 2:return ((NameEnt)tableViewer_Ent.getElementAt(Integer.parseInt(books_rel.getDecoEnt().substring(1))-1)).getEnt();
			case 3:state=true;return books_rel.getRelaEnt();
			}
			return null;
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return ((NameFactory)inputElement).getBooksList().toArray();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			NameEnt books_ent=(NameEnt)element;
			switch (columnIndex){
			case 1:combo_Ent1.add(table_Ent.getItemCount()+"");combo_Ent2.add(table_Ent.getItemCount()+"");return String.valueOf(++v_ent);
			case 2:styledText.setStyleRange(new StyleRange(books_ent.getPoint().x,books_ent.getPoint().y-books_ent.getPoint().x,ColorBox.getADO().getValue(books_ent.getType()),ColorBox.getADO().getValue("白")));
					return books_ent.getEnt();
			case 3:return books_ent.getType();
			case 4:state=true;return books_ent.getType_tf();
			}
			return null;
		}
	}

	protected static Shell shell;
	private static Combo combo_Ent1;
	private static Combo combo_Ent2;
	private static Text text_Ent1;
	private static Text text_Ent2;
	private static TableViewer tableViewer_Ent;
	private static TableViewer tableViewer_Rel;
	private static Table table_Ent;
	private static Table table_Rel;
	private static StyledText styledText;
	private static int v_ent=0;
	private Menu_Text menutext;
	public static String RelDec=null;
	public static boolean state=false;
	public static Label lable_filepath;
	final MenuTranslate menutranslate=MenuTranslate.getADO();
	final MenuTranslate_null menutranslate_null=MenuTranslate_null.getADO();
	final ColorBox colorbox=ColorBox.getADO();
	final static FTPConnection ftpADO=FTPConnection.getADO();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainVisual window = new MainVisual();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(900, 744);
		shell.setLocation(Display.getCurrent().getClientArea().width/2-shell.getShell().getSize().x/2, Display.getCurrent().getClientArea().height/2-shell.getSize().y/2);
		shell.setText("\u5173\u7CFB\u5EFA\u7ACB[]");
		shell.setLayout(new GridLayout(2, false));
		
		Group group_1 = new Group(shell, SWT.NONE);
		group_1.setLayout(new GridLayout(6, false));
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		group_1.setText("\u529F\u80FD\u5217\u8868");
		
		Button button_intext = new Button(group_1, SWT.NONE);
		button_intext.setToolTipText("\u4EC5\u5BFC\u5165\u6587\u672C");
		GridData gd_button_intext = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_intext.widthHint = 80;
		button_intext.setLayoutData(gd_button_intext);
		button_intext.setText("\u5BFC\u5165\u6587\u672C");
		
		Button button_inrel = new Button(group_1, SWT.NONE);
		button_inrel.setToolTipText("\u5BFC\u5165\u5173\u7CFB\u53CA\u6587\u672C");
		GridData gd_button_inrel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_inrel.widthHint = 80;
		button_inrel.setLayoutData(gd_button_inrel);
		button_inrel.setText("\u4E00\u952E\u5BFC\u5165");
		
		Button button_intype = new Button(group_1, SWT.NONE);
		GridData gd_button_intype = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_intype.widthHint = 80;
		button_intype.setLayoutData(gd_button_intype);
		button_intype.setText("\u5BFC\u5165\u914D\u7F6E");
		
		Button button_ftpdir = new Button(group_1, SWT.NONE);
		GridData gd_button_ftpdir = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_ftpdir.widthHint = 80;
		button_ftpdir.setLayoutData(gd_button_ftpdir);
		button_ftpdir.setText("ftp\u76EE\u5F55\u67E5\u8BE2");
		
		lable_filepath = new Label(group_1, SWT.NONE);
		lable_filepath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_outann = new Button(group_1, SWT.NONE);
		GridData gd_button_outann = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_outann.widthHint = 80;
		button_outann.setLayoutData(gd_button_outann);
		button_outann.setText("\u5BFC\u51FA\u7ED3\u679C");
		
		Group group_2 = new Group(shell, SWT.NONE);
		group_2.setLayout(new GridLayout(1, false));
		GridData gd_group_2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_group_2.heightHint = 248;
		gd_group_2.widthHint = 300;
		group_2.setLayoutData(gd_group_2);
		group_2.setText("\u5B9E\u4F53\u5217\u8868");
		
		tableViewer_Ent = new TableViewer(group_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_Ent = tableViewer_Ent.getTable();
		table_Ent.setLinesVisible(true);
		table_Ent.setHeaderVisible(true);
		table_Ent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn tableViewerColumn_9 = new TableViewerColumn(tableViewer_Ent, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn_9.getColumn();
		tableColumn.setResizable(false);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_Ent, SWT.NONE);
		TableColumn tableColumn_Ent0 = tableViewerColumn.getColumn();
		tableColumn_Ent0.setToolTipText("\u8BE5\u5217\u663E\u793A\u7684ID\u503C\u7EAF\u5C5E\u53C2\u8003");
		tableColumn_Ent0.setWidth(30);
		tableColumn_Ent0.setText("ID");
		
		final TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer_Ent, SWT.NONE);
		TableColumn tableColumn_Ent1 = tableViewerColumn_1.getColumn();
		tableColumn_Ent1.setWidth(135);
		tableColumn_Ent1.setText("\u5B9E\u4F53\u540D\u79F0");
		
		final TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer_Ent, SWT.NONE);
		TableColumn tableColumn_Ent2 = tableViewerColumn_2.getColumn();
		tableColumn_Ent2.setWidth(50);
		tableColumn_Ent2.setText("\u7C7B\u578B");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer_Ent, SWT.NONE);
		TableColumn tableColumn_Ent3 = tableViewerColumn_3.getColumn();
		tableColumn_Ent3.setWidth(50);
		tableColumn_Ent3.setText("\u4FEE\u9970");
		
		Group group_3 = new Group(shell, SWT.NONE);
		group_3.setText("\u6587\u672C\u663E\u793A\u533A");
		group_3.setLayout(new GridLayout(1, false));
		GridData gd_group_3 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2);
		gd_group_3.heightHint = 310;
		group_3.setLayoutData(gd_group_3);
		
		styledText = new StyledText(group_3, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL);
		styledText.setWordWrap(true);
		styledText.setEditable(false);
		styledText.setIME(null);
		styledText.setDoubleClickEnabled(false);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Group group_4 = new Group(shell, SWT.NONE);
		GridData gd_group_4 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_group_4.widthHint = 300;
		group_4.setLayoutData(gd_group_4);
		group_4.setText("\u5173\u7CFB\u64CD\u4F5C\u53F0");
		group_4.setLayout(new GridLayout(3, false));
		
		combo_Ent1 = new Combo(group_4, SWT.READ_ONLY);
		combo_Ent1.setToolTipText("\u5B9E\u4F531_ID");
		GridData gd_combo_Ent1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_Ent1.widthHint = 120;
		combo_Ent1.setLayoutData(gd_combo_Ent1);
		
		combo_Ent2 = new Combo(group_4, SWT.READ_ONLY);
		combo_Ent2.setToolTipText("\u5B9E\u4F532_ID");
		GridData gd_combo_Ent2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_Ent2.widthHint = 120;
		combo_Ent2.setLayoutData(gd_combo_Ent2);
		
		Button button_Add = new Button(group_4, SWT.NONE);
		GridData gd_button_Add = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_button_Add.widthHint = 40;
		button_Add.setLayoutData(gd_button_Add);
		button_Add.setText("\u6DFB\u52A0");
		
		text_Ent1 = new Text(group_4, SWT.BORDER | SWT.READ_ONLY);
		text_Ent1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text_Ent2 = new Text(group_4, SWT.BORDER | SWT.READ_ONLY);
		text_Ent2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_Del = new Button(group_4, SWT.NONE);
		GridData gd_button_Del = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_button_Del.widthHint = 40;
		button_Del.setLayoutData(gd_button_Del);
		button_Del.setText("\u5220\u9664");
		
		Group group_5 = new Group(shell, SWT.NONE);
		group_5.setLayout(new GridLayout(1, false));
		group_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		group_5.setText("\u5173\u7CFB\u5217\u8868");
		
		tableViewer_Rel = new TableViewer(group_5, SWT.BORDER | SWT.FULL_SELECTION);
		table_Rel = tableViewer_Rel.getTable();
		table_Rel.setEnabled(true);
		table_Rel.setLinesVisible(true);
		table_Rel.setHeaderVisible(true);
		table_Rel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer_Rel, SWT.NONE);
		TableColumn tableColumn_Rel0 = tableViewerColumn_4.getColumn();
		tableColumn_Rel0.setWidth(30);
		tableColumn_Rel0.setText("ID");
		
		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer_Rel, SWT.NONE);
		TableColumn tableColumn_Rel1 = tableViewerColumn_5.getColumn();
		tableColumn_Rel1.setWidth(100);
		tableColumn_Rel1.setText("\u4E3B\u8981\u5BF9\u8C61");
		
		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(tableViewer_Rel, SWT.NONE);
		TableColumn tableColumn_Rel2 = tableViewerColumn_6.getColumn();
		tableColumn_Rel2.setWidth(100);
		tableColumn_Rel2.setText("\u4FEE\u9970\u8BCD");
		
		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(tableViewer_Rel, SWT.NONE);
		TableColumn tableColumn_Rel3 = tableViewerColumn_7.getColumn();
		tableColumn_Rel3.setWidth(100);
		tableColumn_Rel3.setText("\u5B9E\u4F53\u95F4\u7684\u5173\u7CFB");
		
		new Menu_Ent(styledText,tableViewer_Ent,new Text[]{text_Ent1, text_Ent2},new Combo[]{combo_Ent1,combo_Ent2});
		tableViewer_Ent.setLabelProvider(new TableLabelProvider());
		tableViewer_Ent.setContentProvider(new ContentProvider());
		tableViewer_Ent.setSorter(new MySorter_forEnt());
		
		new Menu_Rel(tableViewer_Ent,tableViewer_Rel);
		tableViewer_Rel.setLabelProvider(new TableLabelProvider_1());
		tableViewer_Rel.setContentProvider(new ContentProvider_1());
		
		menutext=new Menu_Text(styledText,tableViewer_Ent,new Combo[]{combo_Ent1,combo_Ent2});
		
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				if(state){if(message_save()==0)e.doit=false;}
			}
		});
		
		table_Ent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				styledText.setSelection(((NameEnt)tableViewer_Ent.getElementAt(table_Ent.getSelectionIndex())).getPoint());
			}
		});
		
		table_Ent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(table_Ent.getSelectionIndex()>=0)quick_time(e.character);
			}
		});
		
		table_Rel.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				table_Rel.getColumn(1).setWidth((table_Rel.getClientArea().width-30)/3-6);
				table_Rel.getColumn(2).setWidth((table_Rel.getClientArea().width-30)/3-6);
				table_Rel.getColumn(3).setWidth((table_Rel.getClientArea().width-30)/3-5);
			}
		});

		table_Rel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String str=table_Rel.getSelection()[0].getText(1);
				styledText.setSelection(new Point(Integer.parseInt(str.substring(str.indexOf("[")+1,str.lastIndexOf("-"))),Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.lastIndexOf("]")))));
			}
		});
		
		button_intext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(state){if(message_save()==0)return;}
				String filePath=OpenFile(1);if(filePath==null)return;//filePath为文件最详细的路径
				lable_filepath.setText(filePath.substring(0,filePath.lastIndexOf("\\")+1));
				lable_filepath.setToolTipText(filePath.substring(filePath.lastIndexOf("\\")+1));
				shell.setText(shell.getText().substring(0,shell.getText().lastIndexOf("[")+1)+filePath.substring(filePath.lastIndexOf("\\")+1)+"]");
				ReadFile(filePath,null,styledText);
			}
		});
		
		button_inrel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(state){if(message_save()==0)return;}
				String filePath=OpenFile(2);if(filePath==null)return;//filePath为文件最详细的路径
				lable_filepath.setText(filePath.substring(0,filePath.lastIndexOf("\\")+1));
				lable_filepath.setToolTipText(filePath.substring(filePath.lastIndexOf("\\")+1));
				shell.setText(shell.getText().substring(0,shell.getText().lastIndexOf("[")+1)+filePath.substring(filePath.lastIndexOf("\\")+1)+"]");
				ReadFile(lable_filepath.getText()+lable_filepath.getToolTipText().substring(0,lable_filepath.getToolTipText().lastIndexOf("."))+".txt",null,styledText);
				ReadFormat(lable_filepath.getText()+lable_filepath.getToolTipText(),null,styledText);
			}
		});
		
		button_intype.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				creatMenu(OpenFile(0),menutext.getMenu(1),menutranslate,menutranslate_null,colorbox);
			}
		});
		
		button_ftpdir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FtpVisual ftpVisual=new FtpVisual(null);
				ftpVisual.open();
			}
		});
		
		button_outann.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(lable_filepath.getText().startsWith("ftp")){
					try {
						int i=0;
						for(int count=0;count<3;i=lable_filepath.getText().indexOf("/",i)+1,count++);
						OutFormat(ftpADO.getFTP().storeFileStream(lable_filepath.getText().substring(i-1)+lable_filepath.getToolTipText()));
						System.out.println(ftpADO.getReplyString(ftpADO.getFTP().getReply()));
					} catch (IOException e1) {
						MessageDialog.openInformation(shell, "警告", "当前登录的用户权限不够，请联系管理员提高权限后继续操作");
						e1.printStackTrace();
					}
				}
				else{
					OutFormat(lable_filepath.getText()+lable_filepath.getToolTipText().substring(0,lable_filepath.getToolTipText().lastIndexOf("."))+".ann",null,styledText);
				}
			}
		});
		
		combo_Ent1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table_Ent.setSelection(combo_Ent1.getSelectionIndex());
				text_Ent1.setText(table_Ent.getSelection()[0].getText(2));
				styledText.setSelection(((NameEnt)tableViewer_Ent.getElementAt(table_Ent.getSelectionIndex())).getPoint());
			}
		});

		combo_Ent2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table_Ent.setSelection(combo_Ent2.getSelectionIndex());
				text_Ent2.setText(table_Ent.getSelection()[0].getText(2));
				styledText.setSelection(((NameEnt)tableViewer_Ent.getElementAt(table_Ent.getSelectionIndex())).getPoint());
			}
		});
		
		styledText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Point p=styledText.getSelection(),p_temp;
				for(int i=0;i<table_Ent.getItemCount();i++){
					p_temp=((NameEnt)tableViewer_Ent.getElementAt(i)).getPoint();
					if(p.x>=p_temp.x&&p.x<p_temp.y){table_Ent.setSelection(i);break;}
				}
			}
		});
		
		styledText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.character-'0'){
				case 1:case 2:case 3:if(table_Ent.getSelectionIndex()>=0)quick_time(e.character);break;
				case 4:if(table_Rel.getSelectionIndex()>=0)quick_time(e.character);break;
				}
			}
		});
		
		button_Add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				quick_time('3');
			}
		});
		

		button_Del.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				quick_time('4');
			}
		});
		
		tableViewerColumn_2.setEditingSupport(new EditingSupport(tableViewer_Ent) {
			protected boolean canEdit(Object element) {
				return true;
			}
			protected CellEditor getCellEditor(Object element) {
				String[] list=new String[menutext.getMenu(1).getItems().length];int i=0;
				for(MenuItem menuitem:menutext.getMenu(1).getItems()){
					list[i++]=menuitem.getText();
				}
				return new ComboBoxCellEditor(table_Ent,list,SWT.READ_ONLY);
			}
			protected Object getValue(Object element) {
				int i=-1;
				for(MenuItem menuitem:menutext.getMenu(1).getItems()){
					i++;if(((NameEnt)element).getType().equals(menuitem.getText()))return i;
				}
				return -1;
			}
			protected void setValue(Object element, Object value) {
				NameEnt nameent=(NameEnt)element;
				nameent.setType(menutext.getMenu(1).getItems()[Integer.parseInt(String.valueOf(value))].getText());
				table_Ent.getItems()[table_Ent.getSelectionIndex()].setText(3, nameent.getType());
				styledText.setStyleRange(new StyleRange(nameent.getPoint().x,nameent.getPoint().y-nameent.getPoint().x,ColorBox.getADO().getValue(nameent.getType()),ColorBox.getADO().getValue("白")));
			}
		});
	}
	
	private String OpenFile(int style){
		FileDialog fDialog=new FileDialog(shell,SWT.OPEN);
		fDialog.setFilterExtensions(new String[]{"*.ini","*.txt","*.ann"});
		fDialog.setFilterNames(new String[]{"配置文件(*.ini)","文本文件(*.txt)","关系文件(*.ann)"});
		fDialog.setFilterIndex(style);
		return fDialog.open();
	}
	
	private static void ReadFile(String FileName,InputStream inputStream,StyledText styledText){
		if(FileName==null&&inputStream==null)return;
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader((FileName==null?inputStream:new FileInputStream(FileName)),"UTF-8"));
			table_Ent.removeAll();table_Rel.removeAll();text_Ent1.setText("");text_Ent2.setText("");combo_Ent1.removeAll();combo_Ent2.removeAll();v_ent=0;
			String LineText=null;styledText.setText("");
			while((LineText=br.readLine())!=null)styledText.append(LineText+"\n");
			state=false;br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void ReadFormat(String FileName,InputStream inputStream,StyledText styledText){
		if(FileName==null&&inputStream==null)return;
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader((FileName==null?inputStream:new FileInputStream(FileName)),"UTF-8"));
			String LineText=null,textflag="";
			while((LineText=br.readLine())!=null){
				if(LineText.charAt(0)=='R')break;//ent情况下，不读关系
				textflag+=LineText+"\n";
			}
			if(textflag.equals("")){state=false;br.close();return;}
			tableViewer_Ent.setInput(new NameFactory(textflag.split("\n"),styledText.getText()));
			textflag=LineText==null?"":LineText+"\n";
			if(textflag.equals("")){state=false;br.close();return;}
			textflag=LineText+"\n";
			while((LineText=br.readLine())!=null){
				textflag+=LineText+"\n";
			}
			tableViewer_Rel.setInput(new RelFactory(textflag.split("\n"),styledText.getText()));
			state=false;br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void OutFormat(String FileName,OutputStream outputStream,StyledText styledText){
		int count=table_Ent.getItemCount(),i,an=1,n=0,f=0;
		try {
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter((FileName==null?outputStream:new FileOutputStream(FileName)), "UTF-8"));
			for(i=0;i<count;i++){
				while(n<((NameEnt)tableViewer_Ent.getElementAt(i)).getPoint().x){
					if(String.valueOf(styledText.getText().charAt(n++)).equals("\n"))f++;
				}
				bw.write(((NameEnt)tableViewer_Ent.getElementAt(i)).toString(i+1,f,an));
				if(!((NameEnt)tableViewer_Ent.getElementAt(i)).getType_tf().equals(""))an++;
			}
			count=table_Rel.getItemCount();int EntIndex[]=new int[2];
			for(i=0;i<count;i++){
				EntIndex[0]=FindEntIndex(table_Rel.getItem(i).getText(1))+1;
				EntIndex[1]=FindEntIndex(table_Rel.getItem(i).getText(2))+1;
				bw.write(((RelEnt)tableViewer_Rel.getElementAt(i)).toString(new int[]{i+1,EntIndex[0],EntIndex[1]}));
			}
			MessageDialog.openInformation(shell, "信息提示", "保存成功");
			state=false;bw.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void ReadFile(InputStream inputStream){
		ReadFile(null,inputStream,styledText);
	}
	
	public static void ReadFormat(InputStream inputStream){
		ReadFormat(null,inputStream,styledText);
	}
	
	public static void OutFormat(OutputStream outputStream) throws IOException{
		if(outputStream==null)throw new IOException();
		OutFormat(null,outputStream,styledText);
	}
	
	private static int FindEntIndex(String Ent){
		for(int i=0;i<table_Ent.getItemCount();i++){
			if(table_Ent.getItem(i).getText(2).equals(Ent))return i;
		}
		return -1;
	}
	
	private void creatMenu(String FileName,Menu menu,MenuTranslate menutranslate,MenuTranslate_null menutranslate_null,ColorBox colorbox){
		if(FileName==null)return;
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(FileName),"UTF-8"));
			String LineText=null,result[];
			removeAllMenuItem(menu);menutranslate.RemoveAllMap();menutranslate_null.RemoveAllList();colorbox.RemoveAllMap();
			while((LineText=br.readLine())!=null){
				result=LineText.split("\t");
				if(result[0].indexOf("R")>=0){RelDec=result[1];continue;}
				MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
				if(result[1].indexOf("[p]")>=0){
					menuItem.setText(result[1].substring(0, result[1].indexOf("[p]")));
					menutranslate_null.AddList(result[1].substring(0, result[1].indexOf("[p]")));
					menutranslate.AddMap(result[1].substring(0, result[1].indexOf("[p]")), result[2]);
					menutranslate.AddMap(result[2],result[1].substring(0, result[1].indexOf("[p]")));
					colorbox.AddMap(result[1].substring(0, result[1].indexOf("[p]")), new Color(shell.getDisplay(),Integer.parseInt(result[3].split(",")[0]),Integer.parseInt(result[3].split(",")[1]),Integer.parseInt(result[3].split(",")[2])));
				}else{
					menuItem.setText(result[1]);
					menutranslate.AddMap(result[1], result[2]);menutranslate.AddMap(result[2], result[1]);
					colorbox.AddMap(result[1], new Color(shell.getDisplay(),Integer.parseInt(result[3].split(",")[0]),Integer.parseInt(result[3].split(",")[1]),Integer.parseInt(result[3].split(",")[2])));
				}//创建第一级菜单
				if(result.length==5){
					Menu menuItem_Case = new Menu(menuItem);menuItem.setMenu(menuItem_Case);
					MenuItem menuItem_TypeTrue = new MenuItem(menuItem_Case, SWT.NONE);
					menuItem_TypeTrue.addSelectionListener(menutext.getmyListener());
					menuItem_TypeTrue.setText("\u5F53\u524D\u7684");
					MenuItem menuItem_TypeFalse = new MenuItem(menuItem_Case, SWT.NONE);
					menuItem_TypeFalse.addSelectionListener(menutext.getmyListener());
					menuItem_TypeFalse.setText("\u5426\u8BA4\u7684");
				}else{
					menuItem.addSelectionListener(menutext.getmyListener());
				}//创建第二级菜单
			}
			colorbox.AddMap("白",new Color(shell.getDisplay(),255,255,255));colorbox.AddMap("黑",new Color(shell.getDisplay(),0,0,0));
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void removeAllMenuItem(Menu menu){
		for(MenuItem menuitem:menu.getItems()){
			menuitem.dispose();
		}
	}
	
	private void quick_time(char c){
		if(c=='1'){
			text_Ent1.setText(table_Ent.getItem(table_Ent.getSelectionIndex()).getText(2));
			combo_Ent1.select(table_Ent.getSelectionIndex());
		}else if(c=='2'){
			text_Ent2.setText(table_Ent.getItem(table_Ent.getSelectionIndex()).getText(2));
			combo_Ent2.select(table_Ent.getSelectionIndex());
		}else if(c=='3'){
			if(combo_Ent1.getSelectionIndex()<0||combo_Ent2.getSelectionIndex()<0)return;
			RelEnt rel=new RelEnt();String dec=RelDec;
			rel.setMainEnt("T"+(combo_Ent1.getSelectionIndex()+1));
			rel.setDecoEnt("T"+(combo_Ent2.getSelectionIndex()+1));
			NameEnt[] nameent=new NameEnt[]{(NameEnt)tableViewer_Ent.getElementAt(combo_Ent1.getSelectionIndex()),(NameEnt)tableViewer_Ent.getElementAt(combo_Ent2.getSelectionIndex())};
			if((nameent[0].getType_tf().indexOf("的")>=0||nameent[1].getType_tf().indexOf("的")>=0)&&!(menutranslate_null.getList().contains(nameent[0].getType())||menutranslate_null.getList().contains(nameent[1].getType())))dec=(nameent[1].getType_tf().equals("当前的")?"":"不")+dec;
			rel.setRelaEnt(nameent[0].getType()+dec+nameent[1].getType());
			tableViewer_Rel.add(rel);
			table_Rel.setSelection(table_Rel.getItemCount()-1);
		}else if(c=='4'){
			if(table_Rel.getSelectionIndex()<0)return;state=true;
			table_Rel.remove(table_Rel.getSelectionIndex());
			table_Rel.setSelection(table_Rel.getItemCount()-1);
		}
	}
	
	public static int message_save(){
		if(state){
			MessageBox messagebox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
			messagebox.setText("信息提示");
			messagebox.setMessage("你还有部分操作未保存,是否先保存再进行下一步操作？");
			int result=messagebox.open();
			if(result==SWT.YES){
				if(lable_filepath.getText().startsWith("ftp")){
					try {
						OutFormat(ftpADO.getFTP().storeFileStream(lable_filepath.getToolTipText()));
					} catch (IOException e1) {
						MessageDialog.openInformation(shell, "警告", "当前登录的用户权限不够，请联系管理员提高权限后继续操作");
						e1.printStackTrace();
					}
				}
				else{
					OutFormat(lable_filepath.getText()+lable_filepath.getToolTipText().substring(0,lable_filepath.getToolTipText().lastIndexOf("."))+".ann",null,styledText);
				}
			}else if (result==SWT.CANCEL){
				return 0;
			}
		}
		return 1;
	}
	
	public class MySorter_forEnt extends ViewerSorter{
		public int compare(Viewer viewer,Object e1,Object e2){
			int p_1=((NameEnt)e1).getPoint().x;
			int p_2=((NameEnt)e2).getPoint().x;
			return p_1>p_2?1:(p_1==p_2?0:-1);
		}
	}
	
}