package dataSet;

import java.awt.event.KeyEvent;

public class KeySet {
    private static String[] keyTypes={"fileKey","editKey","helpKey","toolsKey","newFileKey","openFileKey","saveKey","exitKey"};
    private static int[] keyValue={KeyEvent.VK_F,KeyEvent.VK_E,KeyEvent.VK_H,KeyEvent.VK_T,KeyEvent.VK_N,KeyEvent.VK_O,KeyEvent.VK_S,KeyEvent.VK_E};

    public static int getKeyValue(String keyType){
	for(int i=0;i<keyTypes.length;i++){
	    if(keyTypes[i].equals(keyType)){
		return keyValue[i];
	    }
	}
	return -1;
    }
}
