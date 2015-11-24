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
	    file.mkdirs();// 创建父路径文件夹
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
	    // 读入1024个字符
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
    
    // 读文件file，maxCharNum：最大读入字符数，返回字符串，发生异常或到文件末尾，返回null
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

    // 结束读文件file
    public void closeRead() {
	try {
	    fRead.close();
	    isReading = false;
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // 写入文件file，writeStr：写入内容字符串
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

    // 结束写入文件file
    public void closeWrite() {
	try {
	    fWrite.close();
	    isWriting = false;
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // 解压缩文件file时，取得下一个Entry，成功返回true，失败返回false
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

    // 解压缩文件file，maxByteSize：最大读入字节数，返回：解压缩结果字符串
    public String disZip(int maxByteSize) {
	try {
	    if (entry != null) {
		byte[] byArr = new byte[maxByteSize];
		int len = zipIn.read(byArr, 0, byArr.length);// 读入maxByteSize个字符??????
		if (len == -1)// ?????
		    return null;
		return new String(byArr, 0, len);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    // 结束解压缩
    public void closeDisZip() {
	try {
	    zipIn.close();
	    entry = null;
	    isDisZiping = false;
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // 将字符串内容压缩入文件file，zipStr：要压缩的内容字符串，entryName：压缩条目名字
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

    // 压缩时，设置下一压缩Entry，entryName：Entry名字
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

    // 结束压缩
    public void closeZip() {
	try {
	    zipOut.close();
	    isZiping = false;
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
