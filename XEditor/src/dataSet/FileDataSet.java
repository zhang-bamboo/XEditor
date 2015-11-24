package dataSet;

import java.awt.Component;
import java.awt.Frame;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import graphic.DrawingPane;
import myLib.StringFormat;
import xEditorUI.XEditorFrame;

/**
 * FielDataSet,a data center include file names,file address,component for
 * JTabbedPane has method to check file type and so on
 * 
 * @author zhang
 *
 */
public class FileDataSet {
    private XEditorFrame faFrame;
    private static LinkedList<String> fileAddrs = new LinkedList<String>();
    private static LinkedList<String> fileNames = new LinkedList<String>();
    private static LinkedList<Component> components = new LinkedList<Component>();
    private static String[] textTypes = { ".txt", ".doc" };
    private static String[] tableTypes = { ".xls", ".xlsx" };
    private static String[] graphicTypes = { ".jpg", ".bmp" };

    public FileDataSet(Frame f) {
	faFrame = (XEditorFrame) f;
    }

    // if has same name,then add (n),n:from 1
    private String sameNameTest(String fileName) {
	int sameNameMark = 0;
	String mainName = StringFormat.deletePostfix(fileName);
	String postfix = StringFormat.getPostfix(fileName);
	boolean isSameName = true;
	while (isSameName) {
	    isSameName = false;
	    for (int i = 0; i < fileNames.size(); i++) {
		if (fileNames.get(i).equals(fileName)) {
		    sameNameMark++;
		    fileName = mainName + "(" + sameNameMark + ")" + postfix;
		    isSameName = true;
		}
	    }
	}
	return fileName;
    }

    /**
     * 
     * @param fileType:
     *            file type
     * @return true if it's table type, false if it's not table type table
     *         type:".xls", ".xlsx"
     */
    public static boolean isTableType(String fileType) {
	if (fileType == null)
	    return false;
	for (int i = 0; i < tableTypes.length; i++) {
	    if (fileType.equals(tableTypes[i]))
		return true;
	}
	return false;
    }

    /**
     * 
     * @param fileType:
     *            file type
     * @return true if it's text type, false if it's not text type text type:
     *         ".txt", ".doc"
     */
    public static boolean isTextType(String fileType) {
	if (fileType == null)
	    return false;
	for (int i = 0; i < textTypes.length; i++) {
	    if (fileType.equals(textTypes[i]))
		return true;
	}
	return false;
    }

    /**
     * 
     * @param fileType:
     *            file type
     * @return true if it's graphic type, false if it's not graphic type graphic
     *         type:".jpg", ".bmp"
     */
    public static boolean isGraphicType(String fileType) {
	if (fileType == null)
	    return false;
	for (int i = 0; i < graphicTypes.length; i++) {
	    if (fileType.equals(graphicTypes[i]))
		return true;
	}
	return false;
    }

    /**
     * add file
     * 
     * @param fileName:
     *            file name
     * @param fileAddr:
     *            file address
     * 
     */
    public void addFile(String fileName, String fileAddr) {
	if (fileName == null)
	    throw new IllegalArgumentException("fileName is null");
	if (fileAddr == null)
	    fileAddr = new String();
	fileName = sameNameTest(fileName);
	fileNames.add(fileName);
	fileAddrs.add(fileAddr);
	String fileType = StringFormat.getPostfix(fileName);
	Component addComponent;
	if (isTableType(fileType)) {
	    addComponent = new JScrollPane(new JTable());
	} else if (isTextType(fileType)) {
	    addComponent = new JScrollPane(new JTextArea());

	} else if (isGraphicType(fileType)) {
	    addComponent = new DrawingPane(faFrame);
	} else {
	    return;
	}
	components.add(addComponent);
	faFrame.getTree().addTreeNode(new DefaultMutableTreeNode(fileName));
	faFrame.getTabbedPane().appendTab(fileName, addComponent);
    }

    /**
     * remove file
     * 
     * @param fileName:
     *            file name
     */
    public void removeFile(String fileName) {
	int index = fileNames.indexOf(fileName);
	if (index == -1) {
	    throw new IllegalArgumentException("fileName not find");
	}
	fileNames.remove(index);
	components.remove(index);
	fileAddrs.remove(index);
	faFrame.getTree().removeNode();
	faFrame.getTabbedPane().removeTab(fileName);
    }

    /**
     * set file address according to file name
     * 
     * @param fileName:
     *            file name
     * @param fileAddr:
     *            file address
     */
    public void setFileAddr(String fileName, String fileAddr) {
	int index = fileNames.indexOf(fileName);
	if (index == -1) {
	    throw new IllegalArgumentException("fileName not find");
	}
	fileAddrs.set(index, fileAddr);
    }

    /**
     * rename file
     * 
     * @param oldName:
     *            old name
     * @param newName:
     *            new name
     */
    public void renameFile(String oldName, String newName) {
	int index = fileNames.indexOf(oldName);
	if (index == -1) {
	    throw new IllegalArgumentException("fileName not find");
	}
	fileNames.set(index, newName);
	faFrame.getTree().renameNode(newName);
	faFrame.getTabbedPane().renameTab(oldName, newName);

    }

    /**
     * get file address according to file name
     * 
     * @param fileName:
     *            file name
     * @return file address
     */
    public String getFileAddr(String fileName) {
	int index = fileNames.indexOf(fileName);
	if (index == -1) {
	    throw new IllegalArgumentException("fileName not find ");
	}
	return fileAddrs.get(index);
    }

    /**
     * get file name according to file index don't use this method
     * 
     * @param fileIndex:
     *            file index
     * @return file name
     */
    public String getFileName(int fileIndex) {
	return fileNames.get(fileIndex);
    }

    /**
     * get the component for JTabbedPane ,according to file name
     * 
     * @param fileName:
     *            file name
     * @return component for JTabbedPane
     */
    public Component getComponent(String fileName) {
	int index = fileNames.indexOf(fileName);
	if (index == -1) {
	    throw new IllegalArgumentException("fileName not find");
	}
	return components.get(index);
    }

}
