package datechapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateFormat {

	// 返回当前时间
	void getCurrentDate() {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
	}

	// 返回上一个月时间
	void getPrevMonthDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1);
		System.out.print(DateFormatUtils.format(cal, "yyyyMM"));
	}

	// 字符串转时间
	void parseStringToDate() throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt("201701".substring(1, 4)), 7, 1);
		System.out.println(cal.getMaximum(Calendar.DAY_OF_MONTH));
	}
	
	static void longToDate() {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		Date dt = new Date(1510369991813L);  
		String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
		System.out.println(sDateTime);
	}

	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm");
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str + "01 01:01");
		} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	public static void main(String[] args) throws Exception {
		longToDate();
//		new DateFormat().parseStringToDate();
//		new DateFormat().getPrevMonthDate();

	}

}
