package file;

import javax.swing.*;
public class MyComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String>{
	
	private static final long serialVersionUID = 1L;
	String selecteditem=null;
	String[] text={"txt","doc","zip","jpg"};
	public String getElementAt(int index){
		return text[index];
	}
	public int getSize(){
		return text.length;
	}
	public void setSelectedItem(Object item){
		selecteditem=(String)item;
	}
	public Object getSelectedItem(){
		return selecteditem;
	}

}
