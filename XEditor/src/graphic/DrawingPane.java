package graphic;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import xEditorUI.XEditorFrame;

@SuppressWarnings("serial")
public class DrawingPane extends JPanel {
    private XEditorFrame faFrame;
    private boolean isChoosing;
    private DrawingPanePopupMenu popupMenu;
    private DrawDataSet drawDataSet;
    private DrawToolBar toolBar;
    private static final int TYPE = 0;
    private static final int X = 1;
    private static final int Y = 2;
    LinkedList<Object> drawDots = new LinkedList<>();

    public DrawingPane(Frame frame) {
	faFrame = (XEditorFrame) frame;
	toolBar=new DrawToolBar();
	add(toolBar, BorderLayout.SOUTH);
	isChoosing = false;
	popupMenu = new DrawingPanePopupMenu(faFrame);
	drawDataSet = new DrawDataSet();
	addMouseMotionListener(new MouseMotionListener() {

	    @Override
	    public void mouseMoved(MouseEvent e) {
		if (drawDots.size() != 0) {
		    if (drawDots.get(TYPE).equals("drawLine")) {
			drawDots.set(3, e.getX());
			drawDots.set(4, e.getY());
		    } else {
			drawDots.set(3, Math.abs(e.getX() - (int) drawDots.get(X)));
			drawDots.set(4, Math.abs(e.getY() - (int) drawDots.get(Y)));
		    }
		    isChoosing = true;
		    repaint();
		}
	    }

	    @Override
	    public void mouseDragged(MouseEvent e) {

	    }
	});
	addMouseListener(new MouseAdapter() {

	    public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
		    popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}

	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3)
		    return;
		String selectedDrawType = popupMenu.getSelectItem();
		if (selectedDrawType == null)
		    return;
		if (drawDots.size() == 0) {
		    drawDots.add(TYPE, selectedDrawType);
		    drawDots.add(X, e.getX());
		    drawDots.add(Y, e.getY());
		    drawDots.add(0);
		    drawDots.add(0);
		} else {
		    drawDataSet.addDrawObject(drawDots.toArray());
		    drawDots = new LinkedList<>();// reset size
		    isChoosing = false;
		    repaint();
		}
	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {

	    }
	});
    }

    public void paint(Graphics g) {
	try {
	    super.paint(g);
	    int count = drawDataSet.getObjectCount();
	    for (int i = 0; i < count; i++) {
		String drawType = drawDataSet.getType(i);
		Object[] args = drawDataSet.getArgs(i);
		new PaintMethod(g).invokeMethod(drawType, args);
	    }
	    if (isChoosing)
		choosingPaint(g);
	} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	    e.printStackTrace();
	}
    }

    private void choosingPaint(Graphics g)
	    throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	String drawType = (String) drawDots.get(TYPE);
	if (drawType == null)
	    return;
	PaintMethod method = new PaintMethod(g);
	method.invokeMethod(drawType, toArgs());
    }

    @SuppressWarnings("unchecked")
    private Object[] toArgs() {
	LinkedList<Object> copyDrawDot = (LinkedList<Object>) drawDots.clone();
	copyDrawDot.remove(0);
	return copyDrawDot.toArray();
    }

    public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setBounds(300, 300, 300, 300);
	frame.getContentPane().add(new DrawingPane(frame), BorderLayout.CENTER);
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }
}
