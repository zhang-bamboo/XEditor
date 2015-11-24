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
    private String drawType[]={"drawOval","drawRect"};
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
	    String selectedItem = ((JRadioButtonMenuItem) e.getSource()).getText();
	    if (selectedItem.equals("drawOval")) {
		currentDrawType="drawOval";
	    } else if (selectedItem.equals("drawRect")) {
		currentDrawType="drawRect";
	    }

	}
    };

    private void initPopupMenu() {
	JRadioButtonMenuItem drawOval = new JRadioButtonMenuItem("drawOval");
	buttonGroup.add(drawOval);
	drawOval.addActionListener(actionListener);
	add(drawOval);
	JRadioButtonMenuItem drawRect = new JRadioButtonMenuItem("drawRect");
	buttonGroup.add(drawRect);
	drawRect.addActionListener(actionListener);
	add(drawRect);

    }

}
