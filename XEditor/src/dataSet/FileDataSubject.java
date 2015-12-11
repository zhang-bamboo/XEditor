package dataSet;

import java.awt.Component;

import dataSet.FileDataObserver.Type;

public interface FileDataSubject {

    void registerObserver(FileDataObserver observer);
    void removeObserver(FileDataObserver observer);
    void notifyObserver(Type type,String fileName,String newFileName,Component component);
}
