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
    /**
     * °ë½Ç×ªÈ«½Ç
     * @param input String.
     * @return È«½Ç×Ö·û´®.
     */
    public static String ToSBC(String input) {
             char c[] = input.toCharArray();
             for (int i = 0; i < c.length; i++) {
               if (c[i] == ' ') {
                 c[i] = '\u3000';
               } else if (c[i] < '\177') {
                 c[i] = (char) (c[i] + 65248);

               }
             }
             return new String(c);
    }

    /**
     * È«½Ç×ª°ë½Ç
     * @param input String.
     * @return °ë½Ç×Ö·û´®
     */
    public static String ToDBC(String input) {
        

             char c[] = input.toCharArray();
             for (int i = 0; i < c.length; i++) {
               if (c[i] == '\u3000') {
                 c[i] = ' ';
               } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                 c[i] = (char) (c[i] - 65248);

               }
             }
        String returnString = new String(c);
        
             return returnString;
    }
}
