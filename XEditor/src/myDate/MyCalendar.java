package myDate;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * MyCalendar is used for print calendar
 * 
 * @author zhang
 *
 */
public class MyCalendar {
    private GregorianCalendar currentCalendar;
    private String[] weekStrs = { "Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri" };

    public MyCalendar() {
	currentCalendar = new GregorianCalendar();

    }

    public int getYear() {
	return currentCalendar.get(Calendar.YEAR);
    }

    public int getMonth() {
	return currentCalendar.get(Calendar.MONTH);
    }

    /**
     * 
     * @param month:from
     *            1 to 12
     */
    public void setMonth(int month) {
	if (month >= 1 && month <= 12)
	    currentCalendar.set(Calendar.MONTH, --month);
    }

    /**
     * set the current month to last month
     */
    public void lastMonth() {
	int month = currentCalendar.get(Calendar.MONTH);
	month--;
	if (month < Calendar.JANUARY) {
	    int year = currentCalendar.get(Calendar.YEAR);
	    year--;
	    currentCalendar.set(Calendar.YEAR, year);
	    month = Calendar.DECEMBER;
	}
	currentCalendar.set(Calendar.MONTH, month);
    }

    /**
     * set the current month to next month
     */
    public void nextMonth() {
	currentCalendar.add(Calendar.MONTH, 1);
    }

    /**
     * print the current month calendar,on console
     */
    public void printCalendar() {
	String[][] calendarStrs = formatToShow();
	int firstDayOfWeek = currentCalendar.getFirstDayOfWeek();
	for (int i = 0; i < 7; i++) {
	    System.out.printf(weekStrs[firstDayOfWeek] + " ");
	    firstDayOfWeek++;
	    if (firstDayOfWeek > 6)
		firstDayOfWeek = 0;
	}
	System.out.println();
	for (String[] strs : calendarStrs) {
	    for (String str : strs) {
		System.out.print(str + "  ");
	    }
	    System.out.println();
	}

    }

    /**
     * 
     * @return the week names,the first one is the firstDayOfWeek
     */
    public String[] getWeekNames() {
	int firstDayOfWeek = currentCalendar.getFirstDayOfWeek();
	String[] weekNames = new String[7];
	for (int i = 0; i < 7; i++) {
	    if (firstDayOfWeek > 6)
		firstDayOfWeek = 0;
	    weekNames[i] = weekStrs[firstDayOfWeek];
	    firstDayOfWeek++;
	}
	return weekNames;
    }

    /**
     * 
     * @return days in current month, string[6][7],
     */
    public String[][] formatToShow() {
	int today = currentCalendar.get(Calendar.DAY_OF_MONTH);
	int thisMonth = currentCalendar.get(Calendar.MONTH);
	int thisYear = currentCalendar.get(Calendar.YEAR);
	currentCalendar.set(Calendar.DAY_OF_MONTH, 1);
	int weekday = currentCalendar.get(Calendar.DAY_OF_WEEK);
	int firstDayOfWeek = currentCalendar.getFirstDayOfWeek();
	int spaceNum = spaceNum(firstDayOfWeek, weekday);
	String[][] dayStrs = new String[6][7];
	for (int i = 0; i < spaceNum; i++)
	    dayStrs[0][i] = new String("  ");
	for (int i = spaceNum; i < 7; i++) {
	    dayStrs[0][i] = new String(" " + currentCalendar.get(Calendar.DAY_OF_MONTH));
	    currentCalendar.add(Calendar.DAY_OF_MONTH, 1);
	}
	for (int i = 1; i < dayStrs.length; i++) {
	    for (int j = 0; j < dayStrs[i].length; j++) {
		if (currentCalendar.get(Calendar.MONTH) == thisMonth) {
		    int curDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
		    if (curDay < 10)
			dayStrs[i][j] = new String(" " + curDay);
		    else
			dayStrs[i][j] = new String("" + curDay);
		} else {
		    dayStrs[i][j] = new String("  ");
		}
		currentCalendar.add(Calendar.DAY_OF_MONTH, 1);
	    }
	}
	currentCalendar.set(Calendar.YEAR, thisYear);
	currentCalendar.set(Calendar.MONTH, thisMonth);
	currentCalendar.set(Calendar.DAY_OF_MONTH, today);
	return dayStrs;
    }

    // compute the space number in first row
    private int spaceNum(int firstDayOfWeek, int weekday) {
	int count = 0;
	while (firstDayOfWeek != weekday) {
	    if (firstDayOfWeek >= Calendar.SATURDAY)
		firstDayOfWeek = Calendar.SUNDAY;
	    else
		firstDayOfWeek++;
	    count++;
	}
	return count;
    }
}
