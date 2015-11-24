package xEditorUI;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import dataSet.Language;

@SuppressWarnings("serial")
public class PasswordDialog extends JDialog {
    private Frame faFrame;
    private int identifier;
    private String userName;
    private String password;
    private int inputCount;
    private static final int MAXCOUNT = 3;
    private JTextField inputNameText;
    private JPasswordField inputPswordText;
    private JButton sureButn;
    private JButton exitButn;

    public PasswordDialog(Frame f, int identifier) {
	super(f, Language.getNames("Password"), true);
	faFrame = f;
	this.identifier = identifier;
	if (identifier == 0) {
	    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}
	userName = "sa";
	password = "123";
	inputCount = 0;
	Container ct = getContentPane();
	setLayout(new GridLayout(3, 2, 5, 5));
	ct.add(new JLabel(Language.getNames("userName: ")));
	inputNameText = new JTextField(20);
	ct.add(inputNameText);
	ct.add(new JLabel(Language.getNames("password: ")));
	inputPswordText = new JPasswordField(20);
	ct.add(inputPswordText);
	initSureButn();
	ct.add(sureButn);
	initExitButn();
	ct.add(exitButn);
	setBounds(500, 300, 300, 100);

    }

    private void initSureButn() {
	sureButn = new JButton(Language.getNames("ok"));
	sureButn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (identifier == 0) {
		    faFrame.setVisible(true);
		    dispose();
		} else if (identifier == 1) {
		    String inputName = inputNameText.getText();
		    char[] pswordArr = inputPswordText.getPassword();
		    String inputPsword = new String(pswordArr, 0, pswordArr.length);
		    if ((++inputCount) < MAXCOUNT && inputName.equals(userName) && inputPsword.equals(password)) {
			((XEditorFrame) faFrame).getTree().setIsOpenAgain(true);
			((XEditorFrame) faFrame).getTree().openTree();
		    }
		    dispose();
		}
	    }
	});
    }

    private void initExitButn() {
	exitButn = new JButton(Language.getNames("exit"));
	exitButn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (identifier == 0) {
		    System.exit(0);
		}
		else {
		    dispose();
		}
	    }
	});
    }
}
