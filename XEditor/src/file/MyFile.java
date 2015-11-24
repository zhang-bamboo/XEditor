package file;

import java.io.*;
import java.util.zip.*;

import myLib.StringFormat;

public class MyFile {
    private File file;
    private FileReader fRead;
    private FileWriter fWrite;
    private ZipInputStream zipIn;
    private ZipOutputStream zipOut;
    private ZipEntry entry = null;
    private boolean isReading = false;
    private boolean isWriting = false;
    private boolean isDisZiping = false;
    private boolean isZiping = false;

    private void makeParentDir() {
	if (!file.exists()) {
	    String fileAddr = file.getParent();
	    String filePath = file.getAbsolutePath();
	    file = new File(fileAddr);
	    file.mkdirs();// ������·���ļ���
	    file = new File(filePath);
	}
    }

    public MyFile(String filePath) {
	file = new File(filePath);
    }

    public MyFile(File file) {
	this.file = file;
    }

    private String readAll(File file) throws IOException {
	char[] byArr = new char[1024];
	int len;
	StringBuilder stringBuilder = new StringBuilder();
	fRead = new FileReader(file);
	while ((len = fRead.read(byArr)) != -1) {
	    stringBuilder.append(new String(byArr, 0, len));
	}
	fRead.close();
	return stringBuilder.toString();
    }

    private String disZipAll(File file) throws IOException {
	zipIn = new ZipInputStream(new FileInputStream(file));
	entry = zipIn.getNextEntry();
	if (entry != null) {
	    byte[] byArr = new byte[1024];
	    int len;
	    StringBuilder stringBuilder = new StringBuilder();
	    // ����1024���ַ�
	    while ((len = zipIn.read(byArr, 0, byArr.length)) != -1) {
		stringBuilder.append(new String(byArr, 0, len));
	    }
	    zipIn.close();
	    return stringBuilder.toString();
	}
	return "";
    }

    public String readFile() throws IOException {
	if (!file.exists()) {
	    throw new IllegalArgumentException("file cannot find");
	}
	if (StringFormat.getPostfix(file.getName()).equals(".txt")) {
	    return readAll(file);
	} else if (StringFormat.getPostfix(file.getName()).equals(".zip")) {
	    return disZipAll(file);
	} else {
	    throw new IllegalArgumentException("err");
	}
    }

    public void writeFile(String writeStr) throws IOException {
	if (!file.exists()) {
	    throw new IllegalArgumentException("cannot find");
	}
	if (StringFormat.getPostfix(file.getName()).equals(".txt")) {
	    fWrite = new FileWriter(file);
	    fWrite.write(writeStr);
	    fWrite.close();
	} else if (StringFormat.getPostfix(file.getName()).equals(".zip")) {
	    zipOut = new ZipOutputStream(new FileOutputStream(file));
	    zipOut.putNextEntry(new ZipEntry(file.getName()));
	    byte[] byArr = writeStr.getBytes();
	    zipOut.write(byArr, 0, byArr.length);
	    zipOut.close();
	} else {
	    throw new IllegalArgumentException("err");
	}
    }
    
    // ���ļ�file��maxCharNum���������ַ����������ַ����������쳣���ļ�ĩβ������null
    public String read(int maxCharNum) {
	try {
	    if (!file.exists()) {
		throw new IllegalArgumentException("cannot find");
	    }
	    if (!isReading) {
		fRead = new FileReader(file);
		isReading = true;
	    }
	    char[] byArr = new char[maxCharNum];
	    int len = fRead.read(byArr);
	    if (len == -1)
		return null;
	    return new String(byArr, 0, len);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    // �������ļ�file
    public void closeRead() {
	try {
	    fRead.close();
	    isReading = false;
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // д���ļ�file��writeStr��д�������ַ���
    public void write(String writeStr) {
	try {
	    if (!file.exists())
		makeParentDir();
	    if (!isWriting) {
		fWrite = new FileWriter(file);
		isWriting = true;
	    }
	    fWrite.write(writeStr);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return;
    }

    // ����д���ļ�file
    public void closeWrite() {
	try {
	    fWrite.close();
	    isWriting = false;
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // ��ѹ���ļ�fileʱ��ȡ����һ��Entry���ɹ�����true��ʧ�ܷ���false
    public boolean getNextEntry() {
	try {
	    if (!file.exists()) {
		throw new IllegalArgumentException("file cannot find");
	    }
	    if (!isDisZiping) {
		zipIn = new ZipInputStream(new FileInputStream(file));
		isDisZiping = true;
	    }
	    entry = zipIn.getNextEntry();
	    if (entry != null)
		return true;
	    return false;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return false;
    }

    // ��ѹ���ļ�file��maxByteSize���������ֽ��������أ���ѹ������ַ���
    public String disZip(int maxByteSize) {
	try {
	    if (entry != null) {
		byte[] byArr = new byte[maxByteSize];
		int len = zipIn.read(byArr, 0, byArr.length);// ����maxByteSize���ַ�??????
		if (len == -1)// ?????
		    return null;
		return new String(byArr, 0, len);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    // ������ѹ��
    public void closeDisZip() {
	try {
	    zipIn.close();
	    entry = null;
	    isDisZiping = false;
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // ���ַ�������ѹ�����ļ�file��zipStr��Ҫѹ���������ַ�����entryName��ѹ����Ŀ����
    public void zipIn(String zipStr) {
	try {
	    if (isZiping) {
		byte[] byArr = zipStr.getBytes();
		zipOut.write(byArr, 0, byArr.length);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // ѹ��ʱ��������һѹ��Entry��entryName��Entry����
    public void putNextEntry(String entryName) {
	try {
	    if (!file.exists())
		makeParentDir();
	    if (!isZiping) {
		zipOut = new ZipOutputStream(new FileOutputStream(file));
		isZiping = true;
	    }
	    zipOut.putNextEntry(new ZipEntry(entryName));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // ����ѹ��
    public void closeZip() {
	try {
	    zipOut.close();
	    isZiping = false;
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
