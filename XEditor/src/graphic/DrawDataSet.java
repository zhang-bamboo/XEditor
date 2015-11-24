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

    public void removeObject(int index) {
	drawObjects.remove(index);
    }

}
