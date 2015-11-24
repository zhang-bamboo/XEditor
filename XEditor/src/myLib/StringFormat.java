package myLib;


public class StringFormat {
    public enum Alignment {
	LEFT, CENTER, RIGHT
    }

    public static int maxLength(StringBuilder[] stringBuilders) {
	int maxLen = 0, len;
	for (int i = 0; i < stringBuilders.length; i++) {
	    if (stringBuilders[i] == null)
		len = 4;
	    else
		len = stringBuilders[i].length();
	    if (maxLen < len)
		maxLen = len;
	}
	return maxLen;
    }

    public static void stringTrim(StringBuilder[] stringBuilders) {
	for (int i = 0; i < stringBuilders.length; i++) {
	    if (stringBuilders[i] != null)
		stringBuilders[i] = new StringBuilder(stringBuilders[i].toString().trim());
	}
    }

    // attention:nullPtr change to new StringBuilder("null"),
    // alignNum:0-left alignment,1-central alignment,2-right alignment
    public static void stringAlign(StringBuilder[] stringBuilders, Alignment alignment) {
	int maxLen = maxLength(stringBuilders);
	for (int i = 0; i < stringBuilders.length; i++) {
	    int len;
	    if (stringBuilders[i] == null) {
		stringBuilders[i] = new StringBuilder("null");
		len = 4;
	    } else
		len = stringBuilders[i].length();
	    for (int j = 0; j < maxLen - len; j++) {
		if (alignment == Alignment.LEFT)
		    stringBuilders[i].append(" ");
		else if (alignment == Alignment.RIGHT)
		    stringBuilders[i].insert(0, " ");
		else {
		    if (j < (maxLen - len) / 2.0)
			stringBuilders[i].insert(0, " ");
		    else
			stringBuilders[i].append(" ");
		}
	    }
	}
    }

    public static String deletePostfix(String string) {
	String[] strings = string.split("\\.");
	if(strings.length<2)
	    return string;
	StringBuilder stringBuilder = new StringBuilder();
	for (int i = 0; i < strings.length - 1; i++) {
	    stringBuilder.append(strings[i]);
	}
	return stringBuilder.toString();
    }
    public static String getPostfix(String string){
	String[] strings = string.split("\\.");
	if(strings.length<2)
	    return null;
	return "."+(strings[strings.length-1]);
    }

}
