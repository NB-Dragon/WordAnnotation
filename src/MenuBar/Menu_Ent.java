package MenuBar;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import DataCollection.ColorBox;
import DataCollection.NameEnt;

public class Menu_Ent{
	private myMenuListener myListener=new myMenuListener();
	private Menu menu_Ent;
	private StyledText styledText;
	private TableViewer tableViewer_Ent;
	private Table table_Ent;
	private Text text_Ent[];
	private Combo combo_Ent[];
	final ColorBox colorbox=ColorBox.getADO();
	
	public Menu_Ent(StyledText styledText,TableViewer tableViewer_Ent,Text text_Ent[],Combo combo_Ent[]){
		this.styledText=styledText;
		this.tableViewer_Ent=tableViewer_Ent;
		this.table_Ent=tableViewer_Ent.getTable();
		this.text_Ent=text_Ent;this.combo_Ent=combo_Ent;
		
		this.menu_Ent = new Menu(table_Ent);
		table_Ent.setMenu(menu_Ent);
		
		MenuItem menuItem_ChangeType = new MenuItem(menu_Ent, SWT.CASCADE);
		menuItem_ChangeType.setText("\u4FEE\u6539\u5B9E\u4F53\u4FEE\u9970");
		
		Menu menuItem_ChangeType_Case = new Menu(menuItem_ChangeType);
		menuItem_ChangeType.setMenu(menuItem_ChangeType_Case);
		
		MenuItem menuItem_TypeTrue = new MenuItem(menuItem_ChangeType_Case, SWT.NONE);
		menuItem_TypeTrue.addSelectionListener(myListener);
		menuItem_TypeTrue.setText("\u5F53\u524D\u7684");
		
		MenuItem menuItem_TypeFalse = new MenuItem(menuItem_ChangeType_Case, SWT.NONE);
		menuItem_TypeFalse.addSelectionListener(myListener);
		menuItem_TypeFalse.setText("\u5426\u8BA4\u7684");
		
		MenuItem menuItem = new MenuItem(menu_Ent, SWT.CASCADE);
		menuItem.setText("\u6DFB\u52A0\u5230\u64CD\u4F5C\u53F0");
		
		Menu menu_Place = new Menu(menuItem);
		menuItem.setMenu(menu_Place);
		
		MenuItem menuItem_1 = new MenuItem(menu_Place, SWT.NONE);
		menuItem_1.addSelectionListener(myListener);
		menuItem_1.setText("\u5B9E\u4F531");
		
		MenuItem menuItem_2 = new MenuItem(menu_Place, SWT.NONE);
		menuItem_2.addSelectionListener(myListener);
		menuItem_2.setText("\u5B9E\u4F532");
		
		MenuItem menuItem_DelEnt = new MenuItem(menu_Ent, SWT.NONE);
		menuItem_DelEnt.addSelectionListener(myListener);
		menuItem_DelEnt.setText("\u5220\u9664\u5B9E\u4F53");
	}
	
	public Menu getMenu(){
		return this.menu_Ent;
	}
	
	public class myMenuListener extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			if(table_Ent.getSelectionIndex()<0)return;
			String sourceText=((MenuItem)e.getSource()).getText().trim();
			if(sourceText.indexOf("1")>=0){
				text_Ent[0].setText(((NameEnt)tableViewer_Ent.getElementAt(table_Ent.getSelectionIndex())).getEnt());
				combo_Ent[0].select(tableViewer_Ent.getTable().getSelectionIndex());
			}else if(sourceText.indexOf("2")>=0){
				text_Ent[1].setText(((NameEnt)tableViewer_Ent.getElementAt(table_Ent.getSelectionIndex())).getEnt());
				combo_Ent[1].select(tableViewer_Ent.getTable().getSelectionIndex());
			}else if(sourceText.indexOf("µÄ")>=0){
				((NameEnt)tableViewer_Ent.getElementAt(table_Ent.getSelectionIndex())).setType_tf(sourceText);
				table_Ent.getItems()[table_Ent.getSelectionIndex()].setText(4, sourceText);
			}else if(sourceText.indexOf("É¾³ýÊµÌå")>=0){
				Point p=((NameEnt)tableViewer_Ent.getElementAt(table_Ent.getSelectionIndex())).getPoint();
				styledText.setStyleRange(new StyleRange(p.x,p.y-p.x,colorbox.getValue("ºÚ"),colorbox.getValue("°×")));
				table_Ent.remove(table_Ent.getSelectionIndex());
				combo_Ent[0].remove(combo_Ent[0].getItemCount()-1);combo_Ent[1].remove(combo_Ent[1].getItemCount()-1);
			}
		}
	}
}
