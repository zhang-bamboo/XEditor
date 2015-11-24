package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dataSet.Language;

@SuppressWarnings("serial")
public class DbFrame extends JFrame {
    private Container container;
    private Database myDatabase;
    private JScrollPane showPane;
    private JTable fixTable;
    private DefaultTableModel fixTableModel;
    private JTable dbTable;
    private DefaultTableModel tableModel;
    private JPanel ctrlPanel;
    private JTextArea sqlText;
    private JButton executeButn;

    public DbFrame() {
	super(Language.getNames("database"));
	container = getContentPane();
	myDatabase = new Database();
	myDatabase.getConnection();
	dbTable = new JTable();
	showPane = new JScrollPane(dbTable);
	container.add(showPane, BorderLayout.CENTER);
	initCtrlPane();
	container.add(ctrlPanel, BorderLayout.SOUTH);
	setBounds(250, 210, 800, 400);
    }

    private void initCtrlPane() {
	ctrlPanel = new JPanel();
	sqlText = new JTextArea(3, 20);
	sqlText.addKeyListener(new KeyListener() {
	    public void keyTyped(KeyEvent e) {
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    try {
			executeSql(sqlText.getText());
		    } catch (SQLException e1) {
			e1.printStackTrace();
		    }
		}
	    }

	    public void keyPressed(KeyEvent e) {
	    }
	});
	ctrlPanel.add(new JScrollPane(sqlText), BorderLayout.CENTER);
	executeButn = new JButton(Language.getNames("execute"));
	executeButn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    executeSql(sqlText.getText());
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
	    }
	});
	ctrlPanel.add(executeButn, BorderLayout.EAST);
    }

    private void executeSql(String sqlStr) throws SQLException {
	String[] sqlStrs = sqlStr.split(" +");
	if (sqlStrs[0] == "" && sqlStrs.length < 2) {
	    System.out.println("sql is invalid");
	    return;
	}
	if (sqlStrs[0].toLowerCase().equals("select")
		|| (sqlStrs[0].equals("") && sqlStrs[1].toLowerCase().equals("select"))) {
	    ResultSet resultSet = myDatabase.querySql(sqlStr);
	    String[] columnNames = Database.getColumnNames(resultSet);
	    Object[][] rowData = Database.splitResult(resultSet);
	    setTable(rowData, columnNames);
	} else {
	    myDatabase.updateSql(sqlStr);
	}
    }

    private void setTable(Object[][] rowData, String[] columnNames) {
	Object[][] fixRowData = new Object[rowData.length][1];
	for (int i = 0; i < rowData.length; i++) {
	    fixRowData[i][0] = rowData[i][0];
	}
	String[] fixColumnNames = { columnNames[0] };
	fixTableModel = new DefaultTableModel(fixRowData, fixColumnNames);
	fixTable = new JTable(fixTableModel);
	tableModel = new DefaultTableModel(rowData, columnNames);
	dbTable = new JTable() {
	    private static final long serialVersionUID = 1L;

	    public boolean isCellEditable(int row, int column) {// 设置为不可编辑
		return false;
	    }
	};
	dbTable.setSelectionForeground(Color.RED);
	dbTable.setRowHeight(30);
	dbTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	dbTable.setModel(tableModel);
	dbTable.setRowSorter(new TableRowSorter<>(tableModel));// 添加排序器
	fixTable.setRowHeight(30);
	showPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixTable.getTableHeader());
	JViewport viewport = new JViewport();
	viewport.setView(fixTable);
	viewport.setPreferredSize(fixTable.getPreferredSize());
	showPane.setRowHeaderView(viewport);
	showPane.setViewportView(dbTable);
    }
}
