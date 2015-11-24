package example;
import javax.swing.*;
import java.awt.*;
public class MyDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	public MyDialog(Frame f,String title){
		super(f,title,true);
		setLayout(new FlowLayout());
		Container container=getContentPane();
		container.add(new JLabel("Never give up"));
		setBounds(120,120,100,100);
	}
	MyDialog(Frame f,String title,boolean model){
		super(f,title,model);
		Container container=getContentPane();
		container.add(new JLabel("Never give up",SwingConstants.CENTER));
		setBounds(120,120,100,100);
	}	
}
