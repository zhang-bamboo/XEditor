package myDate;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import dataSet.Language;
import database.Database;
import net.InetAddr;

/**
 * CalendarTable ,print calendar in table ,user can choose next or last month
 * 
 * @author zhang
 *
 */
public class CalendarTable extends JFrame {
    Container container;
    private MyCalendar calendar;
    private JScrollPane showPane;
    private JTable dbTable;
    private DefaultTableModel tableModel;
    private JButton lastMonthButn;
    private JButton nextMonthButn;
    private JLabel monthLabel;
    private JPanel ctrPanel;

    public CalendarTable() {
	super(Language.getNames("calendar"));
	container = getContentPane();
	calendar = new MyCalendar();
	String[] weekNames = calendar.getWeekNames();
	String[][] days = calendar.formatToShow();
	tableModel = new DefaultTableModel(days, weekNames);
	dbTable = new JTable(tableModel);
	showPane = new JScrollPane(dbTable);
	container.add(showPane, BorderLayout.CENTER);
	initLastMonthButn();
	initNextMonthButn();
	monthLabel = new JLabel(calendar.getYear() + "年" + (calendar.getMonth() + 1) + "月");
	ctrPanel = new JPanel();
	ctrPanel.add(lastMonthButn, BorderLayout.WEST);
	ctrPanel.add(monthLabel, BorderLayout.CENTER);
	ctrPanel.add(nextMonthButn, BorderLayout.EAST);
	container.add(ctrPanel, BorderLayout.SOUTH);
	setBounds(250, 210, 800, 400);
    }

    private void initLastMonthButn() {
	lastMonthButn = new JButton(Language.getNames("lastMonth"));
	lastMonthButn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		calendar.lastMonth();
		monthLabel.setText(calendar.getYear() + "年" + (calendar.getMonth() + 1) + "月");
		String[] weekNames = calendar.getWeekNames();
		String[][] days = calendar.formatToShow();
		dbTable.setModel(new DefaultTableModel(days, weekNames));
		setVisible(true);
	    }
	});
    }

    private void initNextMonthButn() {
	nextMonthButn = new JButton(Language.getNames("nextMonth"));
	nextMonthButn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		calendar.nextMonth();
		monthLabel.setText(calendar.getYear() + "年" + (calendar.getMonth() + 1) + "月");
		String[] weekNames = calendar.getWeekNames();
		String[][] days = calendar.formatToShow();
		dbTable.setModel(new DefaultTableModel(days, weekNames));
		setVisible(true);
	    }
	});
    }
}
