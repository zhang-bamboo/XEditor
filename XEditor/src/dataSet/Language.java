package dataSet;


public class Language {
    private static String[] type= {"file", "edit","help","New file","open","save","exit","cut","copy","paste","font","attribute","helpitem","copyright","project","database","tools","localhost","show","hide","delete","rename","open with Editor","open with default program","New name:","ok","quit","fileChooser","Read from:  ","from text area","Save to:  ","Filename:  ","format:  ","clear","Local host��","Local IP��","execute","Password","userName: ","password: ","exit","HorizontalView","VerticalView","calendar","lastMonth","nextMonth"};

    //private static String[] names = { "File", "Edit", "Help","New file", "Open","Save", "Exit", "Cut" ,"Copy","Paste","Font","Attribute","help","CopyRight","project","database","Tools","LocalHost","show","hide","delete","rename","open with editor","open with default program","New name:","OK","quit","fileChooser","Read from:  ","from text area","Save to:  ","Filename:  ","format:  ","clear","Local host��","Local IP��","execute","Password","userName: ","password: ","exit","HorizontalView","VerticalView",,"calendar","lastMonth","nextMonth"};
    
    private static String[] names={ "�ļ�", "�༭", "����","�½��ļ�", "��","����", "�˳�", "����" ,"����","ճ��","����","����","����","��Ȩ","����","���ݿ�","����","��������","��ʾ","����","ɾ��","������","�ü��±���","��Ĭ�ϳ����","������:","ȷ��","ȡ��","�ļ�ѡ����","����:  ","�ı���","���浽:  ","�ļ���:  ","��ʽ:  ","���","��������","����IP��","ִ��","����","�û���: ","����: ","�˳�","ˮƽ�ָ���ͼ","��ֱ�ָ���ͼ","����","����","����"};
        
    public static String getNames(String name){
	for(int i=0;i<type.length;i++){
	    if(type[i].equals(name)){
		return names[i];
	    }
	}
	return null;
    }
    public static String[] getFileMenuNames(){
	String[] Names=new String[4]; 
	for(int i=3;i<7;i++)
	    Names[i-3]=names[i];
	return Names;	
    }
    public static String[] getEditMenuNames(){
	String[] Names=new String[5]; 
	for(int i=7;i<12;i++)
	    Names[i-7]=names[i];
	return Names;	
    }
    public static String[] getHelpMenuNames(){
	String[] Names=new String[2]; 
	for(int i=12;i<14;i++)
	    Names[i-12]=names[i];
	return Names;	
    }
    public static String[] getType(){
	return type;
    }
    public static String[] getNames(){
	return names;
    }
    
}
