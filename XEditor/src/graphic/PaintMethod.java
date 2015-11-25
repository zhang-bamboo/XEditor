package graphic;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class PaintMethod {

    private Graphics g;

    public PaintMethod(Graphics g) {
	this.g = g;
    }

    public Method getMethod(String methodName) throws NoSuchMethodException, SecurityException {
	Method[] methods=g.getClass().getMethods();
	Method method=null;
	for(Method seekMethod:methods){
	    if(seekMethod.getName().equals(methodName)){
		method=seekMethod;
		break;
	    }
	}
	return method;
    }
    public void invokeMethod(String methodName,Object...args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	Method[] methods=g.getClass().getMethods();
	Method method=null;
	for(Method seekMethod:methods){
	    if(seekMethod.getName().equals(methodName)){
		method=seekMethod;
		break;
	    }
	}
	method.invoke(g, args);
    }
    
}
