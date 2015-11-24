package xEditorUI;

import java.awt.Frame;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import dataSet.Language;


@SuppressWarnings("serial")
public class MyTree extends JTree {
    private XEditorFrame faFrame;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private TreePath selectedPath;
    private boolean isOpenAgain = false;

    public MyTree(Frame faFrame) {
	this.faFrame = (XEditorFrame) faFrame;
	root = new DefaultMutableTreeNode(Language.getNames("project"));
	treeModel = new DefaultTreeModel(root);
	setModel(treeModel);
	addTreeExpansionListener(new TreeExpansionListener() {
	    public void treeExpanded(TreeExpansionEvent event) {
		if (!isOpenAgain) {
//		    selectedPath = event.getPath();
//		    collapsePath(selectedPath);
//		    new PasswordDialog(faFrame,1).setVisible(true);
		} else {
//		    isOpenAgain = false;
		}
	    }

	    public void treeCollapsed(TreeExpansionEvent event) {
	    }
	});
    }

    public void setIsOpenAgain(boolean isOpenAgain) {
	this.isOpenAgain = isOpenAgain;
    }

    public void addTreeNode(DefaultMutableTreeNode insertNode) {
	treeModel.insertNodeInto(insertNode, root, root.getChildCount());
    }

    public void removeNode() {
	DefaultMutableTreeNode node = (DefaultMutableTreeNode)getLastSelectedPathComponent();
	if (node==null||!node.isRoot()) {
	    DefaultMutableTreeNode nextSelectNode = node.getNextSibling();
	    if (nextSelectNode == null)
		nextSelectNode = (DefaultMutableTreeNode) node.getParent();
	    treeModel.removeNodeFromParent(node);
	    setSelectionPath(new TreePath(nextSelectNode.getPath()));
	}
    }

    public void renameNode(String newName) {
	DefaultMutableTreeNode node = (DefaultMutableTreeNode)getLastSelectedPathComponent();
	if (node==null||!node.isRoot()) {
	    node.setUserObject(newName);
	}
    }
    public void renameNode(String oldName,String newName) {
	int index=findNode(oldName);
	if(index!=-1){
	    DefaultMutableTreeNode node=((DefaultMutableTreeNode)treeModel.getChild(root, index));
	    node.setUserObject(newName);
	}
    }
    public void renameRoot(String newName){
	root.setUserObject(newName);
    }
    public int findNode(String name){
	int count=root.getChildCount();
	for(int i=0;i<count;i++){
	    String curName=(String) ((DefaultMutableTreeNode)treeModel.getChild(root, i)).getUserObject();
	    if(curName.equals(name)){
		return i;
	    }
	}
	return -1;
    }
    public void openTree(){
	expandPath(selectedPath);
    }
    public void collapseTree(){
	collapsePath(selectedPath);
    }
    public String getSelectedName(){
	return getLastSelectedPathComponent().toString();
    }
}
