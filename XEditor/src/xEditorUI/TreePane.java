package xEditorUI;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import popupMenu.TreePopupMenu;

@SuppressWarnings("serial")
public class TreePane extends JScrollPane {
    private XEditorFrame faFrame;

    public TreePane(Frame frame, JTree tree) {
	super(tree);
	this.faFrame = (XEditorFrame) frame;
	tree.addMouseListener(new MouseAdapter() {
	    public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
		    new TreePopupMenu(faFrame).show(e.getComponent(), e.getX(), e.getY());
		} else if (e.getClickCount() == 2) {
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode) faFrame.getTree()
			    .getLastSelectedPathComponent();
		    if (node == null || node.isRoot())
			return;
		    String selectedName = faFrame.getTree().getSelectedName();
		    int findMark=faFrame.getTabbedPane().findTab(selectedName);
		    if (findMark==-2) {//cannot find
			Component component = faFrame.getFileDataSet().getComponent(selectedName);
			faFrame.getTabbedPane().appendTab(selectedName, component);
		    }else if(findMark>=0){
			faFrame.getTabbedPane().setSelectedIndex(findMark);
		    }
		}
	    }
	});
    }

}
