package graphic;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import xEditorUI.XEditorFrame;

@SuppressWarnings("serial")
public class DrawingPane extends JPanel {
    private XEditorFrame faFrame;
    private boolean isDrawing;
    private boolean isChoosing;
    private DrawingPanePopupMenu popupMenu;
    private DrawDataSet drawDataSet;

    private static final int TYPE = 0;
    private static final int X = 1;
    private static final int Y = 2;
    LinkedList<Object> drawDots = new LinkedList<>();

    public void enableDrawing(boolean bool) {
	isDrawing = bool;
    }

    public DrawingPane(Frame frame) {
	// faFrame = (XEditorFrame) frame;
	isDrawing = false;
	isChoosing = false;
	popupMenu = new DrawingPanePopupMenu(faFrame);
	drawDataSet = new DrawDataSet();
	addMouseMotionListener(new MouseMotionListener() {

	    @Override
	    public void mouseMoved(MouseEvent e) {
		if (drawDots.size() != 0) {
		    drawDots.set(3, Math.abs(e.getX() - (int) drawDots.get(X)));
		    drawDots.set(4, Math.abs(e.getY() - (int) drawDots.get(Y)));
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

		if (isDrawing) {
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
	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
		    isDrawing = !isDrawing;
		}

	    }
	});
    }

    public void paint(Graphics g) {
	super.paint(g);
	int count = drawDataSet.getObjectCount();
	for (int i = 0; i < count; i++) {
	    String drawType = (String) (drawDataSet.getDrawObject(i)[0]);
	    if (drawType.equals("drawOval")) {
		g.drawOval((int) drawDataSet.getDrawObject(i)[1], (int) drawDataSet.getDrawObject(i)[2],
			(int) drawDataSet.getDrawObject(i)[3], (int) drawDataSet.getDrawObject(i)[4]);
	    } else if (drawType.equals("drawRect")) {
		g.drawRect((int) drawDataSet.getDrawObject(i)[1], (int) drawDataSet.getDrawObject(i)[2],
			(int) drawDataSet.getDrawObject(i)[3], (int) drawDataSet.getDrawObject(i)[4]);
	    }
	}
	if (isChoosing)
	    choosingPaint(g);
    }

    private void choosingPaint(Graphics g) {
	String drawType = popupMenu.getSelectItem();
	if (drawType == null)
	    return;
	if (drawType.equals("drawOval")) {
	    g.drawOval((int) drawDots.get(X), (int) drawDots.get(Y), (int) drawDots.get(3), (int) drawDots.get(4));
	} else if (drawType.equals("drawRect")) {
	    g.drawRect((int) drawDots.get(X), (int) drawDots.get(Y), (int) drawDots.get(3), (int) drawDots.get(4));
	}
    }

    public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setBounds(300, 300, 300, 300);
	frame.getContentPane().add(new DrawingPane(frame), BorderLayout.CENTER);
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }
}
