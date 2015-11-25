package xEditorUI;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import dataSet.KeySet;
import dataSet.Language;
import database.DbFrame;
import file.MyFile;
import file.SaveDialog;
import music.MusicDialog;
import myDate.CalendarTable;
import net.InetAddr;

@SuppressWarnings("serial")
public class MyMenuBar extends JMenuBar {
    private XEditorFrame faFrame;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu helpMenu;
    private JMenu toolMenu;

    public MyMenuBar(Frame f) {
	faFrame = (XEditorFrame) f;
	initFileMenu();
	add(fileMenu);
	initEditMenu();
	add(editMenu);
	initHelpMenu();
	add(helpMenu);
	initToolMenu();
	add(toolMenu);
    }

    private void initFileMenu() {
	fileMenu = new JMenu(Language.getNames("file"));
	fileMenu.setMnemonic(KeySet.getKeyValue("fileKey"));
	for (int i = 0; i < Language.getFileMenuNames().length; i++)
	    fileMenu.add(new JMenuItem(Language.getFileMenuNames()[i]));
	fileMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke(KeySet.getKeyValue("newFileKey"),InputEvent.CTRL_MASK));
	fileMenu.getItem(0).addActionListener(new ActionListener() {// New file
	    public void actionPerformed(ActionEvent e) {
		new NewFileDialog(faFrame).setVisible(true);
	    }
	});
	fileMenu.getItem(1).setAccelerator(KeyStroke.getKeyStroke(KeySet.getKeyValue("openFileKey"),InputEvent.CTRL_MASK));
	fileMenu.getItem(1).addActionListener(new ActionListener() {// open file
	    public void actionPerformed(ActionEvent e) {
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("txt/zip", "txt", "zip");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(fileFilter);
		int i = fileChooser.showOpenDialog(faFrame);
		if (i == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    MyFile myFile = new MyFile(selectedFile);
		    try {
			String readStr = myFile.readFile();
			faFrame.getFileDataSet().addFile(selectedFile.getName(), selectedFile.getParent());
			faFrame.getTabbedPane().setCurrentText(readStr);// 写入文本域
		    } catch (IOException e1) {
			e1.printStackTrace();
		    }

		}
	    }
	});
	fileMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke(KeySet.getKeyValue("saveKey"),InputEvent.CTRL_MASK));
	fileMenu.getItem(2).addActionListener(new ActionListener() {// save file
	    public void actionPerformed(ActionEvent e) {
		if (faFrame.getTabbedPane().getTabCount() < 1)// tabbedPane没有选项文本域
		    return;
		new SaveDialog(faFrame).setVisible(true);
	    }
	});
	fileMenu.getItem(3).setAccelerator(KeyStroke.getKeyStroke(KeySet.getKeyValue("exitKey"),InputEvent.CTRL_MASK));
	fileMenu.getItem(3).addActionListener(new ActionListener() {// exit
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});

    }

    private void initEditMenu() {
	editMenu = new JMenu(Language.getNames("edit"));
	editMenu.setMnemonic(KeySet.getKeyValue("editKey"));
	for (int i = 0; i < Language.getEditMenuNames().length; i++)
	    editMenu.add(new JMenuItem(Language.getEditMenuNames()[i]));

    }

    private void initHelpMenu() {
	helpMenu = new JMenu(Language.getNames("help"));
	helpMenu.setMnemonic(KeySet.getKeyValue("helpKey"));
	for (int i = 0; i < Language.getHelpMenuNames().length; i++)
	    helpMenu.add(new JMenuItem(Language.getHelpMenuNames()[i]));
	helpMenu.getItem(0).addActionListener(new ActionListener() {// call help
	    public void actionPerformed(ActionEvent e) {

	    }
	});
	helpMenu.getItem(1).addActionListener(new ActionListener() {// copyRight
	    public void actionPerformed(ActionEvent e) {
		JDialog jd1 = new JDialog(faFrame);
		Container ct1 = jd1.getContentPane();
		ct1.add(new JLabel("Write by：bin zhang"));
		ct1.add(new JLabel("Reserve all right"));
		jd1.setLayout(new GridLayout(2, 1, 15, 15));
		jd1.setBounds(500, 300, 200, 100);
		jd1.setVisible(true);
	    }
	});

    }

    private void initToolMenu() {
	toolMenu = new JMenu(Language.getNames("tools"));
	toolMenu.setMnemonic(KeySet.getKeyValue("toolsKey"));
	toolMenu.add(new JMenuItem(Language.getNames("database")));
	toolMenu.getItem(0).addActionListener(new ActionListener() {// database
	    public void actionPerformed(ActionEvent e) {
		new DbFrame().setVisible(true);
	    }
	});
	toolMenu.add(new JMenuItem(Language.getNames("localhost")));
	toolMenu.getItem(1).addActionListener(new ActionListener() {// localhost
	    public void actionPerformed(ActionEvent e) {
		JDialog jd1 = new JDialog(faFrame);
		Container ct1 = jd1.getContentPane();
		ct1.add(new JLabel(Language.getNames("Local host：")));
		ct1.add(new JLabel(InetAddr.getHostName()));
		ct1.add(new JLabel(Language.getNames("Local IP：")));
		ct1.add(new JLabel(InetAddr.getLocalIp()));
		jd1.setLayout(new GridLayout(2, 2, 15, 15));
		jd1.setBounds(500, 300, 200, 100);
		jd1.setVisible(true);
	    }
	});
	toolMenu.add(new JMenuItem(Language.getNames("calendar")));
	toolMenu.getItem(2).addActionListener(new ActionListener() {// 
	    public void actionPerformed(ActionEvent e) {
		new CalendarTable().setVisible(true);
	    }
	});
	toolMenu.add(new JMenuItem("music"));
	toolMenu.getItem(3).addActionListener(new ActionListener() {//
	    public void actionPerformed(ActionEvent e) {
		try {
		    new MusicDialog(faFrame).setVisible(true);
		} catch (MalformedURLException e1) {
		    e1.printStackTrace();
		}
	    }
	});

    }
    public void setSaveEnable(boolean enable){
	fileMenu.getItem(2).setEnabled(enable);//禁用/开启保存菜单项
    }
    public void addTool(JMenuItem toolItem) {
	toolMenu.add(toolItem);
    }
}
