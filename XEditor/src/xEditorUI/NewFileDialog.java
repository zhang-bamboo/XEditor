package xEditorUI;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import dataSet.FileDataSet;
import dataSet.Language;
import file.MyComboBoxModel;

@SuppressWarnings("serial")
public class NewFileDialog extends JDialog {

    private XEditorFrame faFrame;
    private JTextField jtf3;
    private JComboBox<String> ComboBox1;
    private JButton butn1;
    private JButton butn2;

    public NewFileDialog(Frame f) {
	super(f, Language.getNames("New file"));
	faFrame = (XEditorFrame) f;
	Container container = getContentPane();
	JLabel jlb3 = new JLabel(Language.getNames("Filename:  "));
	jlb3.setHorizontalAlignment(SwingConstants.RIGHT);
	container.add(jlb3);
	jtf3 = new JTextField(Language.getNames("file"));
	container.add(jtf3);
	JLabel jlb4 = new JLabel(Language.getNames("format:  "));
	jlb4.setHorizontalAlignment(SwingConstants.RIGHT);
	container.add(jlb4);
	ComboBox1 = new JComboBox<>(new MyComboBoxModel());
	container.add(ComboBox1);
	ComboBox1.setSelectedItem("txt");
	initButn1();
	container.add(butn1);
	initButn2();
	container.add(butn2);
	setBounds(400, 300, 400, 200);
	setLayout(new GridLayout(3, 2, 5, 5));
    }

    private void initButn1() {
	butn1 = new JButton(Language.getNames("New file"));
	butn1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String fileName;
		String selectedFormat = (String) ComboBox1.getSelectedItem();
		switch (selectedFormat) {
		case "zip":
		    fileName = getText3() + ".zip";
		    break;
		case "doc":
		    fileName = getText3() + ".doc";
		    break;
		case "jpg":
		    fileName = getText3() + ".jpg";
		    break;

		default:
		    fileName = getText3() + ".txt";
		    break;
		}
		FileDataSet fileDataSet =  faFrame.getFileDataSet();
		fileDataSet.addFile(fileName, "");
	    }
	});
    }

    private void initButn2() {
	butn2 = new JButton(Language.getNames("clear"));
	butn2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		jtf3.setText("");
	    }
	});
    }

    public String getText3() {
	return jtf3.getText();
    }

}
