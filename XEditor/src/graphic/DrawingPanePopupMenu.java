package graphic;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import xEditorUI.XEditorFrame;

@SuppressWarnings("serial")
public class DrawingPanePopupMenu extends JPopupMenu {
    private XEditorFrame faFrame;
    private ButtonGroup buttonGroup;
    private String[] drawTypes = { "drawOval", "drawRect","fillOval","fillRect","drawLine"};
    private String currentDrawType;

    public DrawingPanePopupMenu(Frame frame) {
	faFrame = (XEditorFrame) frame;
	buttonGroup = new ButtonGroup();
	initPopupMenu();
    }

    public String getSelectItem() {
	return currentDrawType;
    }

    public void addMenuItem(JRadioButtonMenuItem menuItem) {
	buttonGroup.add(menuItem);
	add(menuItem);
    }

    private ActionListener actionListener = new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	    currentDrawType = ((JRadioButtonMenuItem) e.getSource()).getText();
	}
    };

    private void initPopupMenu() {
	JRadioButtonMenuItem menuItem;
	for (String drawType : drawTypes) {
	    menuItem = new JRadioButtonMenuItem(drawType);
	    menuItem.addActionListener(actionListener);
	    buttonGroup.add(menuItem);
	    add(menuItem);
	}
    }

}
