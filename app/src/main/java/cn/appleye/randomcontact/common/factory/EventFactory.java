package cn.appleye.randomcontact.common.factory;

import java.util.Calendar;

public class EventFactory implements IFactory{
	private static Calendar sCalendar = Calendar.getInstance();
	
	private static int sNonLeapYearDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	private int mMinCount;
	private int mMaxCount;

	public EventFactory() {
		this(1, 1);
	}

	public EventFactory(int minCount, int maxCount) {
		mMinCount = minCount;
		mMaxCount = maxCount;
	}
	
	public static int getRandomYear() {
		int section = sCalendar.get(Calendar.YEAR) - 1980;
		
		return 1980 + (int)(Math.random()*(section+1));
	}
	
	public static int getRandomMonth() {
		return (int)(Math.random()*12) + 1;
	}
	
	public static int getRandomDay(int year, int month) {
		int totalMonthDay = 0;
		if (month == 2) {
			if (year%400==0 || year%4==0&&year%100!=0) {
				totalMonthDay = 29;
			} else {
				totalMonthDay = 28;
			}
		} else {
			totalMonthDay = sNonLeapYearDays[month-1];
		}
		
		return (int)(Math.random()*totalMonthDay) + 1;
	}

	@Override
	public String createFirstRandomData() {
		int year  = getRandomYear();
		int month = getRandomMonth();
		int day = getRandomDay(year, month);
		
		boolean isYearShow = ((int)(Math.random()*2) == 0) ? false : true;
		
		if (isYearShow) {
			return year + "-"  + (month<10?"0"+month:month) + "-" + (day<10?"0"+day:day);
		} else {
			if (month == 2 && day == 29) {
				day = 28;
			}
			return "--"  + (month<10?"0"+month:month) + "-" + (day<10?"0"+day:day);
		}
	}

	@Override
	public String[] createFirstRandomData(boolean repeatAllowed) {
		int count = mMinCount + (int)((mMaxCount - mMinCount + 1)*Math.random());

		String[] dates = new String[count];
		
		for(int i=0; i<count; i++) {
			String date= createFirstRandomData();
			
			dates[i] = date;
			boolean willRepeat = false;
			if(repeatAllowed && i<count-1) {
				willRepeat = (int)(Math.random()*2)==1;
			}

			if (willRepeat) {
				i++;
				dates[i] = date;
			}
		}
		
		return dates;
	}

	@Override
	public String createSecondRandomData() {
		return null;
	}

}
