package popupMenu;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import dataSet.Language;
import xEditorUI.XEditorFrame;

@SuppressWarnings("serial")
public class TabbedPanePopupMenu extends JPopupMenu {
    private XEditorFrame faFrame;
    private JCheckBoxMenuItem hCheckBox;
    private JCheckBoxMenuItem vCheckBox;

    public TabbedPanePopupMenu(Frame frame) {
	faFrame = (XEditorFrame) frame;
	initPopupMenu();
    }
    public void initCheckBox(){
	hCheckBox.setSelected(false);
	vCheckBox.setSelected(false);
    }
    public boolean isSplitViewing(){
	if((!hCheckBox.isSelected())&&(!vCheckBox.isSelected()))
	    return false;
	else
	    return true;
    }
    public void addMenuItem(JMenuItem menuItem) {
	add(menuItem);
    }

    private void initPopupMenu() {
	hCheckBox = new JCheckBoxMenuItem(Language.getNames("HorizontalView"));
	hCheckBox.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		if(vCheckBox.isSelected())
		    hCheckBox.setSelected(false);
		if (hCheckBox.isSelected() && !(vCheckBox.isSelected()))
		    faFrame.getSplitPane().setHorizontalView(true);
		else if (!hCheckBox.isSelected() && !(vCheckBox.isSelected()))
		    faFrame.getSplitPane().setHorizontalView(false);
	    }
	});
	add(hCheckBox);
	vCheckBox = new JCheckBoxMenuItem(Language.getNames("VerticalView"));
	vCheckBox.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		if(hCheckBox.isSelected())
		    vCheckBox.setSelected(false);
		if (vCheckBox.isSelected() && !(hCheckBox.isSelected()))
		    faFrame.getSplitPane().setVerticalView(true);
		else if (!vCheckBox.isSelected() && !(hCheckBox.isSelected()))
		    faFrame.getSplitPane().setVerticalView(false);
	    }
	});
	add(vCheckBox);
    }

}
