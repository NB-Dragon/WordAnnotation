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

import DataCollection.ColorBox;
import DataCollection.NameEnt;


public class Menu_Text {
	private myMenuListener myListener=new myMenuListener();
	private Menu menu_Text;
	private Menu menu_Add;
	private TableViewer tableViewer_Ent;
	private Table table_Ent;
	private StyledText styledText;
	private Combo combo_Ent[];
	final ColorBox colorbox=ColorBox.getADO();
	
	public Menu_Text(StyledText styledText,TableViewer tableViewer_Ent,Combo combo_Ent[]){
		this.styledText=styledText;
		this.tableViewer_Ent=tableViewer_Ent;
		this.table_Ent=tableViewer_Ent.getTable();
		this.combo_Ent=combo_Ent;
		
		menu_Text = new Menu(styledText);
		styledText.setMenu(menu_Text);
		
		MenuItem menuItem = new MenuItem(menu_Text, SWT.CASCADE);
		menuItem.setText("\u6DFB\u52A0\u5B9E\u4F53");
		
		menu_Add = new Menu(menuItem);
		menuItem.setMenu(menu_Add);
		
		MenuItem menuItem_1 = new MenuItem(menu_Text, SWT.NONE);
		menuItem_1.setText("\u5220\u9664\u5B9E\u4F53");
		menuItem_1.addSelectionListener(myListener);
	}
	
	public Menu getMenu(int index){
		switch (index){
		case 0:return this.menu_Text;
		case 1:return this.menu_Add;
		}
		return null;
	}
	
	public myMenuListener getmyListener(){
		return myListener;
	}
	
	public class myMenuListener extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			String sourceText=((MenuItem)e.getSource()).getText().trim();
			if(sourceText.equals("删除实体")){
				if(table_Ent.getSelectionIndex()<0)return;
				Point p=((NameEnt)tableViewer_Ent.getElementAt(table_Ent.getSelectionIndex())).getPoint();
				styledText.setStyleRange(new StyleRange(p.x,p.y-p.x,colorbox.getValue("黑"),colorbox.getValue("白")));
				table_Ent.remove(table_Ent.getSelectionIndex());
				combo_Ent[0].remove(combo_Ent[0].getItemCount()-1);combo_Ent[1].remove(combo_Ent[1].getItemCount()-1);
			}else{
				if(styledText.getSelection().x==styledText.getSelection().y)return;
				NameEnt nameEnt=new NameEnt();
				nameEnt.setPoint(styledText.getSelection());
				nameEnt.setEnt(styledText.getSelectionText());
				nameEnt.setType_tf("");
				String text_temp=((MenuItem)e.getSource()).getParent().getParentItem().getText();
				if(text_temp.equals("添加实体")){
					nameEnt.setType(sourceText);
				}else{
					nameEnt.setType(text_temp);
					nameEnt.setType_tf(sourceText);
				}
				tableViewer_Ent.add(nameEnt);
				table_Ent.setSelection(table_Ent.getItemCount()-1);
			}
		}
	}
}
