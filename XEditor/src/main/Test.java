package main;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import graphic.PaintMethod;

public class Test extends JFrame {

    public Test() {
	super();
	initialize();// ���ó�ʼ������
    }

    // ��ʼ������
    private void initialize() {
	this.setSize(300, 200);// ���ô����С
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô���ر�ģʽ
	setContentPane(new DrawPanel());// ���ô������Ϊ��ͼ������
	this.setTitle("��ͼʵ��1");// ���ô������
    }

    public static void main(String[] args) {
	new Test().setVisible(true);
    }

    // ������ͼ���
    class DrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
	    super.paint(g);
	    PaintMethod method = new PaintMethod(g);
	    Object[] args = new Object[4];
	   args[0]=10;
	   args[1]=10;
	   args[2]=30;
	   args[3]=30;
	    try {
		method.invokeMethod("drawOval", args);
	    } catch (IllegalAccessException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	    } catch (IllegalArgumentException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	    } catch (InvocationTargetException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	    }

	}
    }
}