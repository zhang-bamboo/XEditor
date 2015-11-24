package xEditorUI;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import dataSet.FileDataSet;

/**
 * the frame for XEditor
 * 
 * @author zhang
 *
 */
public class XEditorFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private FileDataSet fileDataSet;
    private MyMenuBar menubar;
    private NorthPanel northPanel;
    private MyTree tree;
    private TreePane treeScrlPane;
    private MySplitPane mainSplitPane;
    private MyTabbedPane tabbedPane;
    private SouthPanel southPanel;

    public XEditorFrame(String title) throws MalformedURLException {
	super(title);
	fileDataSet = new FileDataSet(this);
	Container container = getContentPane();
	setLayout(new BorderLayout());// 布局设置

	((JPanel) getContentPane()).setOpaque(false);// 设置背景图片
	URL imageUrl = new URL("file:///d:/IMG.jpg");
	ImageIcon imageIcon = new ImageIcon(imageUrl);
	JLabel label = new JLabel(imageIcon);
	label.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
	getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

	menubar = new MyMenuBar(this);
	setJMenuBar(menubar);
	northPanel = new NorthPanel(this);
	container.add(northPanel, BorderLayout.NORTH);// 添加面板
	tree = new MyTree(this);
	treeScrlPane = new TreePane(this, tree);
	treeScrlPane.setPreferredSize(new Dimension(100, 300));
	tabbedPane = new MyTabbedPane(this);
	mainSplitPane = new MySplitPane(this);
	container.add(mainSplitPane, BorderLayout.CENTER);
	if (tabbedPane.getComponentCount() == 0)
	    menubar.setSaveEnable(false);
	southPanel = new SouthPanel(this);
	container.add(southPanel, BorderLayout.SOUTH);
	setBounds(200, 100, 900, 600);
	setDefaultCloseOperation(3);
    }

    /**
     * get project tree
     * 
     * @return project tree
     */
    public MyTree getTree() {
	return tree;
    }

    /**
     * get tabbedPane
     * 
     * @return tabbedPane
     */
    public MyTabbedPane getTabbedPane() {
	return tabbedPane;
    }

    /**
     * get tree panel
     * 
     * @return tree panel
     */
    public TreePane getTreePane() {
	return treeScrlPane;
    }

    /**
     * get split panel
     * 
     * @return main split panel
     */
    public MySplitPane getSplitPane() {
	return mainSplitPane;
    }

    /**
     * set save menu item
     * 
     * @param enable:
     *            true to enable,false to collapse
     */
    public void setSaveEnable(boolean enable) {
	menubar.setSaveEnable(enable);
    }

    /**
     * get fileDataSet(file data center)
     * 
     * @return fileDataSet
     */
    public FileDataSet getFileDataSet() {
	return fileDataSet;
    }

}
