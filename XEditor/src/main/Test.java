package main;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import graphic.PaintMethod;

public class Test extends JFrame {

    public Test() {
	super();
	initialize();// 调用初始化方法
    }

    // 初始化方法
    private void initialize() {
	this.setSize(300, 200);// 设置窗体大小
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗体关闭模式
	setContentPane(new DrawPanel());// 设置窗体面板为绘图面板对象
	this.setTitle("绘图实例1");// 设置窗体标题
    }

    public static void main(String[] args) {
	new Test().setVisible(true);
    }

    // 创建绘图面板
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
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	    } catch (IllegalArgumentException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	    } catch (InvocationTargetException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	    }

	}
    }
}