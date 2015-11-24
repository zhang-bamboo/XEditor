package dataSet;


public class Language {
    private static String[] type= {"file", "edit","help","New file","open","save","exit","cut","copy","paste","font","attribute","helpitem","copyright","project","database","tools","localhost","show","hide","delete","rename","open with Editor","open with default program","New name:","ok","quit","fileChooser","Read from:  ","from text area","Save to:  ","Filename:  ","format:  ","clear","Local host：","Local IP：","execute","Password","userName: ","password: ","exit","HorizontalView","VerticalView","calendar","lastMonth","nextMonth"};

    //private static String[] names = { "File", "Edit", "Help","New file", "Open","Save", "Exit", "Cut" ,"Copy","Paste","Font","Attribute","help","CopyRight","project","database","Tools","LocalHost","show","hide","delete","rename","open with editor","open with default program","New name:","OK","quit","fileChooser","Read from:  ","from text area","Save to:  ","Filename:  ","format:  ","clear","Local host：","Local IP：","execute","Password","userName: ","password: ","exit","HorizontalView","VerticalView",,"calendar","lastMonth","nextMonth"};
    
    private static String[] names={ "文件", "编辑", "帮助","新建文件", "打开","保存", "退出", "剪切" ,"复制","粘贴","字体","属性","帮助","版权","工程","数据库","工具","本地主机","显示","隐藏","删除","重命名","用记事本打开","用默认程序打开","新名字:","确定","取消","文件选择器","读自:  ","文本域","保存到:  ","文件名:  ","格式:  ","清除","本机名：","本机IP：","执行","密码","用户名: ","密码: ","退出","水平分割视图","垂直分割视图","日历","上月","下月"};
        
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
