package database;

import java.security.KeyStore.PrivateKeyEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import myLib.StringFormat;
import myLib.StringFormat.Alignment;
import net.InetAddr;

public class Database {

    public enum DatabaseType {
	ORACLE, MYSQL
    }

    private static HashMap<DatabaseType, String> forNames = new HashMap<>();
    private static HashMap<DatabaseType, String> connectStrs = new HashMap<>();
    private static HashMap<DatabaseType, String> userNames = new HashMap<>();
    private static HashMap<DatabaseType, String> passwords = new HashMap<>();
    private Connection connect; // ����Connection����

    public Database() {
	forNames.put(DatabaseType.ORACLE, "oracle.jdbc.driver.OracleDriver");
	connectStrs.put(DatabaseType.ORACLE, "jdbc:oracle:thin:@" + InetAddr.getLocalIp() + ":1521");
	userNames.put(DatabaseType.ORACLE, "system");
	passwords.put(DatabaseType.ORACLE, "430423");
	forNames.put(DatabaseType.MYSQL, "com.mysql.jdbc.Driver");
	connectStrs.put(DatabaseType.MYSQL, "jdbc:mysql://" + InetAddr.getLocalIp() + ":3306/" + "test_database");
	userNames.put(DatabaseType.MYSQL, "root");
	passwords.put(DatabaseType.MYSQL, "430423");
    }

    // �Է��������������ݿ�����
    public Connection getConnection(String driverName, String connectStr, String userName, String password) {
	try {// �������ݿ�������
	    Class.forName(driverName);
	    System.out.println("���ݿ��������سɹ�");
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	try {// ͨ���������ݿ��URL��ȡ���ݿ����Ӷ���
	    connect = DriverManager.getConnection(connectStr, userName, password);
	    System.out.println("���ݿ����ӳɹ�");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return connect; // ������Ҫ�󷵻�һ��Connection����
    }

    // ����OracleĬ�����ӣ�����ֵΪConnection
    public Connection getConnection(String databaseStr) {
	DatabaseType databaseType=DatabaseType.valueOf(databaseStr);
	String driverName = forNames.get(databaseType);
	String connectStr = connectStrs.get(databaseType);
	String userName=userNames.get(databaseType);
	String password = passwords.get(databaseType);
	if (driverName == null||connectStr==null||userName==null||password==null)
	    return null;
	try {// �������ݿ�������
	    Class.forName(driverName);
	    System.out.println("���ݿ��������سɹ�");
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	try {// ͨ���������ݿ��URL��ȡ���ݿ����Ӷ���
	    connect = DriverManager.getConnection(connectStr, userName, password);
	    System.out.println("���ݿ����ӳɹ�");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return connect; // ������Ҫ�󷵻�һ��Connection����
    }

    // ���ұ����������
    public ResultSet selectFrom(String tableName) {
	try {
	    return connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
		    .executeQuery("select * from " + tableName);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    // ��ɸѡֵ����ѯ��
    public ResultSet selectFrom(String tableName, String filterName, String filterValue) {
	try {
	    PreparedStatement sql = connect.prepareStatement(
		    "select * from " + tableName + " where " + filterName + "=?", ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY);
	    sql.setString(1, filterValue);
	    return sql.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    // Ԥ�����������
    public void insertInto(String tableName, String[] insertValues) throws SQLException {
	StringBuilder sqlStr = new StringBuilder("insert into " + tableName + " values(");
	for (int i = 0; i < insertValues.length - 1; i++) {
	    sqlStr.append("'" + insertValues[i] + "',");
	}
	sqlStr.append("'" + insertValues[insertValues.length - 1] + "')");
	PreparedStatement sql = connect.prepareStatement(sqlStr.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
		ResultSet.CONCUR_UPDATABLE);
	sql.executeUpdate();
    }

    // ��ɸѡֵ��������
    public void update(String tableName, String updateName, String updateValue, String filterName, String filterValue) {
	String sqlStr = "update " + tableName + " set " + updateName + "=? where " + filterName + "=?";
	try {
	    PreparedStatement sql = connect.prepareStatement(sqlStr, ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_UPDATABLE);
	    sql.setString(1, updateValue);
	    sql.setString(2, filterValue);
	    sql.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    // ��ɸѡֵɾ������
    public void deleteFrom(String tableName, String filterName, String filterValue) {
	String sqlStr = "delete from " + tableName + " where " + filterName + " like ?";
	try {
	    PreparedStatement sql = connect.prepareStatement(sqlStr, ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_UPDATABLE);
	    sql.setString(1, filterValue);
	    sql.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    // �ҳ�һ�������ֵ,bug�����п������ݿⲢ��
    public int maxValue(String tableName, String columnName) throws SQLException {
	String sqlStr = "select max(" + columnName + ") from " + tableName;
	PreparedStatement sql = connect.prepareStatement(sqlStr, ResultSet.TYPE_FORWARD_ONLY,
		ResultSet.CONCUR_READ_ONLY);
	ResultSet res = sql.executeQuery();
	res.next();
	if (res.getObject(1) == null)
	    return 0;
	else
	    return res.getInt(1);
    }

    // bug�����п��ǲ���������getGeneratedKeys����
    // �����У��Զ����ɲ��ظ�����,ǰ������/bug:Ĭ�ϵ�һ��Ϊ����,����ֵ��˳��ֱ�Ӳ���
    public void insertRow(String tableName, String primaryKey, String[] insertValues) {
	try {
	    int maxId = maxValue(tableName, primaryKey);
	    maxId++;
	    StringBuilder sqlStr = new StringBuilder("insert into " + tableName + " values(");
	    sqlStr.append("'" + maxId + "',");
	    for (int i = 0; i < insertValues.length - 1; i++) {
		sqlStr.append("'" + insertValues[i] + "',");
	    }
	    sqlStr.append("'" + insertValues[insertValues.length - 1] + "')");
	    PreparedStatement sql = connect.prepareStatement(sqlStr.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_UPDATABLE);
	    sql.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    // ��ѯ��ȥ���˿ո��Ȳ�������������ʹ��ltrim(rtrim(columnName))�������в�ѯ
    // bug�������������������ݣ�Ӧ�Ľ�
    public ResultSet[] selectTrimFrom(String tableName) {
	try {
	    ResultSet resultSet = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
		    .executeQuery("select * from " + tableName);
	    int columns = resultSet.getMetaData().getColumnCount();
	    String[] columnName = new String[columns];
	    for (int i = 0; i < columns; i++)
		columnName[i] = resultSet.getMetaData().getColumnName(i + 1);
	    ResultSet[] resultSets = new ResultSet[columns];
	    for (int i = 0; i < columns; i++)
		resultSets[i] = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
			.executeQuery("select ltrim(rtrim(" + columnName[i] + ")) from " + tableName);
	    return resultSets;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    // һ��ֻ�е���Ԫ�صĽ������תΪStringBuilder[]
    public static StringBuilder[] toStringBuilders(ResultSet resultSet) throws SQLException {
	resultSet.last();
	int rows = resultSet.getRow();
	StringBuilder[] stringBuilders = new StringBuilder[rows];
	resultSet.first();
	for (int i = 0; i < rows; i++) {
	    if (resultSet.getString(1) == null)
		stringBuilders[i] = null;
	    else
		stringBuilders[i] = new StringBuilder(resultSet.getString(1));
	    resultSet.next();
	}
	return stringBuilders;
    }

    // �������תΪStringBuilder[][]
    public StringBuilder[][] splitToColumn(ResultSet resultSet) throws SQLException {
	int columns;
	columns = resultSet.getMetaData().getColumnCount();
	resultSet.last();
	int rows = resultSet.getRow();
	resultSet.beforeFirst();
	StringBuilder[][] stringBuilders = new StringBuilder[columns][rows];
	for (int i = 0; i < rows; i++) {
	    resultSet.next();
	    for (int j = 0; j < columns; j++) {
		if (resultSet.getString(j + 1) == null)
		    stringBuilders[j][i] = null;
		else
		    stringBuilders[j][i] = new StringBuilder(resultSet.getString(j + 1));
	    }
	}
	return stringBuilders;
    }

    // �������������ݣ�����ֵ���ж���
    public void printAllFrom(String tableName) {
	try {
	    ResultSet resultSet = selectFrom(tableName);// ��DB��������
	    int columns = resultSet.getMetaData().getColumnCount();
	    resultSet.last();
	    int rows = resultSet.getRow();
	    resultSet.beforeFirst();
	    StringBuilder[][] stringBuilders = new StringBuilder[columns][rows];
	    stringBuilders = splitToColumn(resultSet);// �������תΪStringBuilder[][]
	    for (int i = 0; i < columns; i++)
		StringFormat.stringTrim(stringBuilders[i]);// ȥ�ո�
	    for (int i = 0; i < columns; i++)
		StringFormat.stringAlign(stringBuilders[i], Alignment.LEFT);// ��ո���ж���
	    for (int i = 0; i < rows; i++) {
		for (int j = 0; j < columns; j++) {
		    System.out.print(stringBuilders[j][i] + "  ");// �������
		}
		System.out.println("");// �������
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public ResultSet querySql(String sql) {
	try {
	    PreparedStatement preSql = connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY);
	    return preSql.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public void updateSql(String sql) {
	try {
	    PreparedStatement preSql = connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_UPDATABLE);
	    preSql.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void executeSql(String sql) {
	String[] sqlStrs = sql.split(" +");
	if (sqlStrs.length < 3) {
	    System.out.println("sql is invalid");
	    return;
	}
	if (sqlStrs[0].toLowerCase() == "select" || (sqlStrs[0] == "" && sqlStrs[1].toLowerCase() == "select")) {

	}
    }

    public static String[] getColumnNames(ResultSet resultSet) throws SQLException {
	int columnCount = resultSet.getMetaData().getColumnCount();
	String[] columnNames = new String[columnCount];
	for (int i = 0; i < columnCount; i++)
	    columnNames[i] = resultSet.getMetaData().getColumnName(i + 1);
	return columnNames;
    }

    public static Object[][] splitResult(ResultSet resultSet) throws SQLException {
	int columns = resultSet.getMetaData().getColumnCount();
	resultSet.last();
	int rows = resultSet.getRow();
	resultSet.beforeFirst();
	Object[][] rowData = new Object[rows][columns];
	for (int i = 0; i < rows; i++) {
	    resultSet.next();
	    for (int j = 0; j < columns; j++) {
		rowData[i][j] = resultSet.getObject(j + 1);
	    }
	}
	return rowData;
    }

    public static void main(String[] args) {
	new Database().getConnection("MYSQL");
    }
}
