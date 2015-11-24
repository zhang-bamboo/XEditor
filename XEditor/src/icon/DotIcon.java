package icon;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

public class DotIcon implements Icon{
	private int width;
	private int height;
	public DotIcon(int width,int height){
		this.width=width;
		this.height=height;
	}
	public int getIconHeight(){
		return height;
	}
	public int getIconWidth(){
		return width;
	}
	public void paintIcon(Component arg0,Graphics arg1,int x,int y){
		arg1.fillOval(x, y, width, height);
	}
}