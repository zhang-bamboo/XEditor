package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;

import xEditorUI.XEditorFrame;

public class DrawToolBar extends JToolBar{
    
    private ButtonGroup buttonGroup;
    private String[] drawTypes = { "drawOval", "drawRect","fillOval","fillRect","drawLine"};
    private String currentDrawType; 
    private ActionListener actionListener = new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	    currentDrawType = ((JRadioButton) e.getSource()).getActionCommand();
	}
    };
    public DrawToolBar() {
	buttonGroup = new ButtonGroup();
	JRadioButton button;
	for (String drawType : drawTypes) {
	    button= new JRadioButton(drawType);
	    button.setActionCommand(drawType);
	    button.addActionListener(actionListener);
	    buttonGroup.add(button);
	    add(button);
	}
    }
    public String getSelectItem() {
	return currentDrawType;
    }
    

}
