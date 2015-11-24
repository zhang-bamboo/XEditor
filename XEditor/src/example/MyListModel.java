package example;
import javax.swing.*;

public class MyListModel extends AbstractListModel<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] contents={"white","black","blue"};
	public String getElementAt(int x){
		if(x<contents.length)
			return contents[x++];
		else
			return null;
	}
	public int getSize(){
		return contents.length;
	}

}
