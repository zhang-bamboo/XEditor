package xEditorUI;

import java.awt.Component;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MySplitPane extends JSplitPane {
    private XEditorFrame faFrame;
    private JSplitPane horizontalViewPane;
    private JSplitPane verticalViewPane;
    private JTabbedPane curViewTabbedpane;

    public MySplitPane(Frame frame) throws MalformedURLException {
	faFrame = (XEditorFrame) frame;
	setOneTouchExpandable(true);
	setLeftComponent(faFrame.getTreePane());
	setRightComponent(faFrame.getTabbedPane());
	faFrame.getTabbedPane().setOpaque(false);
	setOpaque(false);
    }

    public String getCurrentSplitTitle() {
	if (curViewTabbedpane != null && curViewTabbedpane.getComponentCount() > 0)
	    return curViewTabbedpane.getTitleAt(0);
	else
	    return null;
    }

    public Component getCurSplitComponent() {
	if (curViewTabbedpane != null && curViewTabbedpane.getComponentCount() > 0)
	    return curViewTabbedpane.getComponentAt(0);
	else
	    return null;
    }

    public void setCurrentSplitTitle(String newName) {
	if (curViewTabbedpane != null && curViewTabbedpane.getComponentCount() > 0)
	    curViewTabbedpane.setTitleAt(0, newName);
    }

    public void destroyView() {
	setRightComponent(faFrame.getTabbedPane());
	horizontalViewPane = null;
	verticalViewPane = null;
	curViewTabbedpane = null;
	faFrame.getTabbedPane().getPopupMenu().initCheckBox();
    }

    public void setHorizontalView(boolean isTrue) {
	if (isTrue) {
	    horizontalViewPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    horizontalViewPane.setDividerLocation(0.5);
	    horizontalViewPane.setLeftComponent(faFrame.getTabbedPane());
	    String currentTitle = faFrame.getTabbedPane().getCurrentTitle();
	    Component curViewComponent = faFrame.getTabbedPane().getSelectedComponent();
	    curViewTabbedpane = new JTabbedPane();
	    curViewTabbedpane.add(currentTitle, curViewComponent);
	    horizontalViewPane.setRightComponent(curViewTabbedpane);
	    setRightComponent(horizontalViewPane);
	} else {
	    removeView();
	}
    }

    public void setVerticalView(boolean isTrue) {
	if (isTrue) {
	    verticalViewPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	    verticalViewPane.setDividerLocation(0.5);
	    verticalViewPane.setLeftComponent(faFrame.getTabbedPane());
	    String currentTitle = faFrame.getTabbedPane().getCurrentTitle();
	    Component curViewComponent = faFrame.getTabbedPane().getSelectedComponent();
	    curViewTabbedpane = new JTabbedPane();
	    curViewTabbedpane.add(currentTitle, curViewComponent);
	    verticalViewPane.setRightComponent(curViewTabbedpane);
	    setRightComponent(verticalViewPane);
	} else {
	    removeView();
	}
    }

    private void removeView() {
	String currentTitle = getCurrentSplitTitle();
	Component curViewComponent =getCurSplitComponent();
	if (currentTitle != null && curViewComponent != null) {
	    faFrame.getTabbedPane().appendTab(currentTitle, curViewComponent);
	    setRightComponent(faFrame.getTabbedPane());
	}
	horizontalViewPane = null;
	verticalViewPane = null;
	curViewTabbedpane = null;
    }

}
