package xEditorUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import dataSet.FileDataSet;
import myLib.StringFormat;
import popupMenu.TabbedPanePopupMenu;

public class MyTabbedPane extends JTabbedPane {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private XEditorFrame faFrame;
    private TabbedPanePopupMenu popupMenu;

    public MyTabbedPane(Frame frame) {
	this.faFrame = (XEditorFrame) frame;
	setPreferredSize(new Dimension(280, 280));
	popupMenu = new TabbedPanePopupMenu(faFrame);
	addMouseListener(new MouseAdapter() {
	    public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
		    if (faFrame.getTabbedPane().getComponentCount() > 0)
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	    }
	});
    }

    public TabbedPanePopupMenu getPopupMenu() {
	return popupMenu;
    }

    public void appendTab(String name, Component component) {
	super.addTab(name, component);
	setSelectedComponent(component);
	if (faFrame.getTabbedPane().getComponentCount() > 0)
	    faFrame.setSaveEnable(true);
    }

    public void setAfterAddTab(JScrollPane newPane) {
	setSelectedComponent(newPane);
	if (faFrame.getTabbedPane().getComponentCount() > 0)
	    faFrame.setSaveEnable(true);
    }

    public void removeTab(String fileName) {
	int index = findTab(fileName);
	if (index >= 0) {
	    remove(index);
	} else if (index == -1) {
	    faFrame.getSplitPane().destroyView();
	}
	if (faFrame.getTabbedPane().getComponentCount() <= 0)
	    faFrame.setSaveEnable(false);
    }

    public void renameTab(String oldName, String newName) {
	int index = findTab(oldName);
	if (index >= 0) {
	    setTitleAt(index, newName);
	} else if (index == -1) {
	    faFrame.getSplitPane().setCurrentSplitTitle(newName);
	}
    }

    // -2:find nothing,-1:find at splitView,>0:find at tabbedPane
    public int findTab(String fileName) {
	if (popupMenu.isSplitViewing()) {
	    String splitName = faFrame.getSplitPane().getCurrentSplitTitle();
	    if (splitName != null && splitName.equals(fileName))
		return -1;
	}
	int tabCount = getTabCount();
	for (int i = 0; i < tabCount; i++) {
	    if (getTitleAt(i).equals(fileName)) {
		return i;
	    }
	}
	return -2;
    }

    public String getCurrentTitle() {
	return getTitleAt(getSelectedIndex());
    }

    public String getCurrentText() {
	String selectedName = getTitleAt(getSelectedIndex());
	String fileType = StringFormat.getPostfix(selectedName);
	Component textArea;
	if (FileDataSet.isTextType(fileType)) {
	    textArea = ((JScrollPane) getSelectedComponent()).getViewport().getView();
	    return ((JTextArea) textArea).getText();
	}
	return null;
    }

    public void setCurrentText(String string) {
	String selectedName = getTitleAt(getSelectedIndex());
	String fileType = StringFormat.getPostfix(selectedName);
	Component textArea;
	if (FileDataSet.isTextType(fileType)) {
	    textArea = ((JScrollPane) getSelectedComponent()).getViewport().getView();
	    ((JTextArea) textArea).setText(string);
	}
    }
}
