package tools;


public abstract class ProjectTools {

	public static boolean isNumeric(String s) {
		boolean b = true;
		
		try {
			Integer.parseInt(s);
		} catch(Exception e) {
			b = false;
		}
		return b;
	}
	
	public static String SecToString(String s) {
		String string = "";

		int sInt = Integer.parseInt(s);
		
		int hours = sInt / 3600;
		sInt = sInt - hours * 3600;
		
		int minutes = sInt / 60;
		sInt = sInt - minutes * 60;
		
		int seconds = sInt;
		
		String hoursS = hours+"";
		if(hours < 10)
			hoursS = "0"+hours;
		
		String minutesS = minutes+"";
		if(minutes < 10)
			minutesS = "0"+minutes;
		
		String secondsS = seconds+"";
		if(seconds < 10)
			secondsS = "0"+seconds;
		
		string = hoursS+":"+minutesS+":"+secondsS;
		
		return string;
	}
	
}
