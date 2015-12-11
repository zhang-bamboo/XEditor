package dataSet;

import java.awt.Component;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public interface FileDataObserver {
   
    enum Type{ADD,REOMVE,CHANGE};
    void update(Type type,String fileName,String newFileName,Component component);
}
