package MenuBar;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;

import DataCollection.NameEnt;
import DataCollection.RelEnt;
import MainVisual.MainVisual;

public class Menu_Rel {
	private Menu menu_Rel;
	private Menu menuItem_ChangeType_Case;
	private TableViewer tableViewer_Ent;
	private TableViewer tableViewer_Rel;
	private Table table_Rel;
	
	private myMenuListener myListener=new myMenuListener();

	public Menu_Rel(TableViewer tableViewer_Ent,TableViewer tableViewer_Rel){
		this.tableViewer_Ent=tableViewer_Ent;
		this.tableViewer_Rel=tableViewer_Rel;
		this.table_Rel=tableViewer_Rel.getTable();
		
		this.menu_Rel = new Menu(this.table_Rel);
		this.table_Rel.setMenu(menu_Rel);
		
		MenuItem menuItem_ChangeType = new MenuItem(menu_Rel, SWT.CASCADE);
		menuItem_ChangeType.setText("\u4FEE\u6539\u5173\u7CFB\u4FEE\u9970");
		
		this.menuItem_ChangeType_Case = new Menu(menuItem_ChangeType);
		menuItem_ChangeType.setMenu(menuItem_ChangeType_Case);
		
		MenuItem menuItem_TypeTrue = new MenuItem(menuItem_ChangeType_Case, SWT.NONE);
		menuItem_TypeTrue.addSelectionListener(myListener);
		menuItem_TypeTrue.setText("\u5F53\u524D\u7684");
		
		MenuItem menuItem_TypeFalse = new MenuItem(menuItem_ChangeType_Case, SWT.NONE);
		menuItem_TypeFalse.addSelectionListener(myListener);
		menuItem_TypeFalse.setText("\u5426\u8BA4\u7684");
		
		MenuItem menuItem_DelEnt = new MenuItem(menu_Rel, SWT.NONE);
		menuItem_DelEnt.addSelectionListener(myListener);
		menuItem_DelEnt.setText("\u5220\u9664\u5173\u7CFB");
	}
	
	public Menu getMenu(int index){
		switch (index){
		case 0:return this.menu_Rel;
		case 1:return this.menuItem_ChangeType_Case;
		}
		return null;
	}
	
	public class myMenuListener extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			String sourceText=((MenuItem)e.getSource()).getText().trim();
			if(sourceText.equals("删除关系")){
				if(table_Rel.getSelectionIndex()<0)return;
				table_Rel.remove(table_Rel.getSelectionIndex());
			}else{
				String text_ent1=((NameEnt)tableViewer_Ent.getElementAt(Integer.parseInt(((RelEnt)tableViewer_Rel.getElementAt(tableViewer_Rel.getTable().getSelectionIndex())).getMainEnt().substring(1))-1)).getType();
				String text_ent2=((NameEnt)tableViewer_Ent.getElementAt(Integer.parseInt(((RelEnt)tableViewer_Rel.getElementAt(tableViewer_Rel.getTable().getSelectionIndex())).getDecoEnt().substring(1))-1)).getType();
				tableViewer_Rel.getTable().getItems()[tableViewer_Rel.getTable().getSelectionIndex()].setText(3, text_ent1+(sourceText.equals("当前的")?"":"不")+MainVisual.RelDec+text_ent2);
				((RelEnt)tableViewer_Rel.getElementAt(tableViewer_Rel.getTable().getSelectionIndex())).setRelaEnt(text_ent1+(sourceText.equals("当前的")?"":"不")+MainVisual.RelDec+text_ent2);
			}
		}
	}
}
