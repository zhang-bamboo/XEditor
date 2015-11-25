package graphic;

import java.io.ObjectInputStream.GetField;
import java.util.LinkedList;

public class DrawDataSet {
    private LinkedList<Object> drawObjects;

    public DrawDataSet() {
	drawObjects = new LinkedList<Object>();
    }

    public void addDrawObject(Object... object) {
	drawObjects.add(object);
    }

    public int getObjectCount() {
	return drawObjects.size();
    }

    public Object[] getDrawObject(int index) {
	return (Object[]) drawObjects.get(index);
    }
    public Object[] getArgs(int index){
	Object[] objects=(Object[]) drawObjects.get(index);
	Object[] args=new Object[objects.length-1];
	for(int i=0;i<args.length;i++){
	    args[i]=objects[i+1];
	}
	return args;
    }
    public String getType(int index){
	return (String) ((Object[]) drawObjects.get(index))[0];
    }
    public void removeObject(int index) {
	drawObjects.remove(index);
    }

}
