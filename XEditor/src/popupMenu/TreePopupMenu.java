package popupMenu;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

import dataSet.Language;
import file.RenameDialog;
import myLib.StringFormat;
import xEditorUI.XEditorFrame;

@SuppressWarnings("serial")
public class TreePopupMenu extends JPopupMenu {
    private XEditorFrame faFrame;

    public TreePopupMenu(Frame frame) {
	faFrame = (XEditorFrame) frame;
	initPopupMenu();
    }

    public void addPopupMenuItem(JMenuItem menuItem) {
	add(menuItem);
    }

    private void initPopupMenu() {
	final JMenuItem hideItem = new JMenuItem(Language.getNames("hide"));
	hideItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) faFrame.getTree().getLastSelectedPathComponent();
		if (node == null)
		    return;
		if (node.isRoot()) {
		    int count = faFrame.getTabbedPane().getComponentCount();
		    for (int i = 0; i < count; i++) {
			faFrame.getTabbedPane().remove(0);
		    }
		    String splitTitle=faFrame.getSplitPane().getCurrentSplitTitle();
		    if(splitTitle!=null){
			faFrame.getTabbedPane().removeTab(splitTitle);
		    }
		    return;
		}
		String selectedName = faFrame.getTree().getSelectedName();
		faFrame.getTabbedPane().removeTab(selectedName);
	    }
	});
	add(hideItem);
	final JMenuItem showItem = new JMenuItem(Language.getNames("show"));
	showItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) faFrame.getTree().getLastSelectedPathComponent();
		if (node == null || node.isRoot())
		    return;
		String selectedName = faFrame.getTree().getSelectedName();
		if (faFrame.getTabbedPane().findTab(selectedName)==-2) {
		    Component component = faFrame.getFileDataSet().getComponent(selectedName);
		    faFrame.getTabbedPane().appendTab(selectedName, component);
		}
	    }
	});
	add(showItem);
	addSeparator();
	final JMenuItem deleteItem = new JMenuItem(Language.getNames("delete"));
	deleteItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) faFrame.getTree().getLastSelectedPathComponent();
		if (node == null || node.isRoot())
		    return;
		String selectedName = faFrame.getTree().getSelectedName();
		faFrame.getFileDataSet().removeFile(selectedName);
	    }
	});
	add(deleteItem);
	addSeparator();
	final JMenuItem renameItem = new JMenuItem(Language.getNames("rename"));
	renameItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		new RenameDialog(faFrame).setVisible(true);
	    }
	});
	add(renameItem);
	addSeparator();
	final JMenuItem openEditorItem = new JMenuItem(Language.getNames("open with Editor"));
	openEditorItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) faFrame.getTree().getLastSelectedPathComponent();
		if (node == null || node.isRoot())
		    return;
		String selectedName = faFrame.getTree().getSelectedName();
		if (StringFormat.getPostfix(selectedName).equals(".txt")) {
		    String fileAddr = faFrame.getFileDataSet().getFileAddr(selectedName);
		    try {
			if (!fileAddr.equals(""))
			    desktop(1, fileAddr + "\\" + selectedName);// 调用文本编辑器打开
		    } catch (IOException e1) {
			e1.printStackTrace();
		    }
		}
	    }
	});
	add(openEditorItem);
	final JMenuItem openOtherItem = new JMenuItem(Language.getNames("open with default program"));
	openOtherItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) faFrame.getTree().getLastSelectedPathComponent();
		if (node == null || node.isRoot())
		    return;
		String selectedName = faFrame.getTree().getSelectedName();
		String fileAddr = faFrame.getFileDataSet().getFileAddr(selectedName);
		try {
		    if (!fileAddr.equals(""))
			desktop(2, fileAddr + "\\" + selectedName);// 调用文本编辑器打开
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
	    }
	});
	add(openOtherItem);
    }

    private void desktop(int i, String str) throws IOException {
	if (Desktop.isDesktopSupported()) {
	    Desktop desktop = Desktop.getDesktop();
	    switch (i) {
	    case 1:
		if (desktop.isSupported(Desktop.Action.EDIT)) {// 调用文本编辑器打开
		    desktop.edit(new File(str));
		}
		break;
	    default:
		if (desktop.isSupported(Desktop.Action.OPEN)) {// 调用默认关联程序打开
		    desktop.open(new File(str));
		}
	    }
	}

    }
}
