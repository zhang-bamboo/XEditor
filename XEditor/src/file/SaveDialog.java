package file;

import java.awt.*;
import javax.swing.*;

import dataSet.Language;
import myLib.StringFormat;
import xEditorUI.XEditorFrame;

import java.awt.event.*;
import java.io.File;

public class SaveDialog extends JDialog {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private XEditorFrame faFrame;
    private JTextField readTextField;
    private JTextField addrTextField;
    private JTextField nameTextField;
    private JComboBox<String> ComboBox1;
    private JButton butn1;
    private JButton butn2;

    private void initButn1() {
	butn1 = new JButton(Language.getNames("save"));
	butn1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String inputFileName;
		File tempFile=new File(getAddrStr());
		if(!tempFile.exists())
		    return;
		if (((String) ComboBox1.getSelectedItem()).equals("txt")) {
		    // 将文本域内容写入文件，getText2()-文件路径，getText3()-文件名
		    // getFaFrame().getTextAreaStr()-文本域内容字符串
		    inputFileName=getNameStr() + ".txt";
		    String saveAddr = getAddrStr()+"\\"+ getNameStr() + ".txt";
		    MyFile file = new MyFile(saveAddr);
		    file.write(faFrame.getTabbedPane().getCurrentText());
		    file.closeWrite();
		} else if(((String) ComboBox1.getSelectedItem()).equals("zip")){
		    // 将文本域内容写入压缩文件，getText2()-文件路径，getText3()-文件名
		    // getFaFrame().getTextAreaStr()-文本域内容字符串
		    inputFileName=getNameStr() + ".zip";
		    String saveAddr = getAddrStr()+"\\"+ getNameStr() + ".zip";
		    MyFile file = new MyFile(saveAddr);
		    file.putNextEntry(getNameStr() + ".txt");
		    file.zipIn(faFrame.getTabbedPane().getCurrentText());
		    file.closeZip();
		}else{
		    inputFileName=getNameStr() + ".doc";
		    String saveAddr = getAddrStr()+"\\"+ getNameStr() + ".doc";
		    MyFile file = new MyFile(saveAddr);
		    file.write(faFrame.getTabbedPane().getCurrentText());
		    file.closeWrite();
		}
		String oldName=faFrame.getTabbedPane().getCurrentTitle();
		faFrame.getFileDataSet().renameFile(oldName, inputFileName);
		faFrame.getFileDataSet().setFileAddr(faFrame.getTabbedPane().getCurrentTitle(), getAddrStr());
	        faFrame.getTabbedPane().renameTab(oldName, inputFileName);
	        faFrame.getTree().renameNode(oldName, inputFileName);
	        faFrame.setVisible(true);
	    }
	});
    }

    private void initButn2() {
	butn2 = new JButton(Language.getNames("fileChooser"));
	butn2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int i = fileChooser.showOpenDialog(faFrame);
		if (i == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    addrTextField.setText(selectedFile.getPath());
		}
	    }
	});
    }

    public String getTextStr() {
	return readTextField.getText();
    }

    public String getAddrStr() {
	return addrTextField.getText();
    }

    public String getNameStr() {
	return nameTextField.getText();
    }

    public SaveDialog(Frame f) {
	super(f, Language.getNames("save"));
	faFrame = (XEditorFrame) f;
	Container container = getContentPane();
	JLabel jlb1 = new JLabel(Language.getNames("Read from:  "));
	jlb1.setHorizontalAlignment(SwingConstants.RIGHT);
	container.add(jlb1);
	readTextField = new JTextField(Language.getNames("from text area"));
	container.add(readTextField);
	JLabel jlb2 = new JLabel(Language.getNames("Save to:  "));
	jlb2.setHorizontalAlignment(SwingConstants.RIGHT);
	container.add(jlb2);
	String selectedName = faFrame.getTabbedPane().getCurrentTitle();
	String fileAddr = faFrame.getFileDataSet().getFileAddr(selectedName);
	addrTextField = new JTextField(fileAddr);
	container.add(addrTextField);
	JLabel jlb3 = new JLabel(Language.getNames("Filename:  "));
	jlb3.setHorizontalAlignment(SwingConstants.RIGHT);
	container.add(jlb3);
	nameTextField = new JTextField(StringFormat.deletePostfix(selectedName));
	container.add(nameTextField);
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
	setBounds(410, 300, 400, 200);
	setLayout(new GridLayout(5, 2, 5, 5));
    }
}