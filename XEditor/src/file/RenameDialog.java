package file;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

import dataSet.Language;
import xEditorUI.XEditorFrame;

@SuppressWarnings("serial")
public class RenameDialog extends JDialog {
    private XEditorFrame faFrame;
    private JTextField newNameTextfield;
    private JButton sureButn;
    private JButton quitButn;

    public RenameDialog(Frame frame) {
	super(frame, Language.getNames("rename"));
	faFrame = (XEditorFrame) frame;
	setBounds(400, 300, 300, 100);
	setLayout(new GridLayout(2, 2));
	add(new JLabel( Language.getNames("New name:")));
	newNameTextfield = new JTextField(15);
	add(newNameTextfield);
	initSureButn();
	add(sureButn);
	initQuitButn();
	add(quitButn);
    }

    private void initSureButn() {
	sureButn = new JButton(Language.getNames("ok"));
	sureButn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String newName = newNameTextfield.getText();
		if (newName.equals(""))
		    return;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) faFrame.getTree().getLastSelectedPathComponent();
		if (node == null)
		    return;
		else if (node.isRoot()) {
		    faFrame.getTree().renameRoot(newName);
		    faFrame.setVisible(true);
		} else {
		    String oldName = faFrame.getTree().getSelectedName();
		    faFrame.getFileDataSet().renameFile(oldName, newName);
		    faFrame.getTree().renameNode(oldName, newName);
		    faFrame.getTabbedPane().renameTab(oldName, newName);
		    faFrame.setVisible(true);
		}
	    }
	});
    }

    private void initQuitButn() {
	quitButn = new JButton(Language.getNames("quit"));
	quitButn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		dispose();
	    }
	});
    }

}
