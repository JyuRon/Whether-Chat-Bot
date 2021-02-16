package sample;

import java.util.Calendar;

public class API_Time {
	
	int year,mon,day,hour,min,dayofweek;
	Calendar cal;
	
	API_Time(){
	cal = Calendar.getInstance(); //현재 날짜 정보 가져옴
	
	
	 year = cal.get(Calendar.YEAR);
     mon = cal.get(Calendar.MONTH)+1;
     day = cal.get(Calendar.DAY_OF_MONTH);
     hour = cal.get(Calendar.HOUR_OF_DAY);
     min = cal.get(Calendar.MINUTE);
     dayofweek = cal.get(Calendar.DAY_OF_WEEK);
	}
	
	
	//현재 시각의 날씨 정보를 불러올때  현재시각이 예보된 시간값을 불러오기
	int Module_checkTime(int hour_f, int min_f) {
		
		int resultTime;
		
		if(min_f>10) {
			
		switch(hour_f) {
		case 2:case 3:case 4:
			day=day-1; resultTime=23;break;
			
		case 5:case 6:case 7:
			 resultTime=2;break;
			
		case 8:case 9:case 10:
			 resultTime=5;break;
			
		case 11:case 12:case 13:
			 resultTime=8;break;
			
		case 14:case 15:case 16:
			resultTime=11;break;
			
		case 17:case 18:case 19:
			 resultTime=14;break;
			
		case 20:case 21:case 22:
			 resultTime=17;break;
			
		case 23:
			resultTime=20;break;
			
		case 0:case 1:
			day=day-1; resultTime=20;break;
			
		default: resultTime=50;
			
		
			}	
		
			return resultTime;
		}
		
		else
		{
			hour_f=hour_f-1;
			switch(hour_f) {
			case 2:case 3:case 4:
				day=day-1; resultTime=23;break;
				
			case 5:case 6:case 7:
				 resultTime=2;break;
				
			case 8:case 9:case 10:
				 resultTime=5;break;
				
			case 11:case 12:case 13:
				 resultTime=8;break;
				
			case 14:case 15:case 16:
				resultTime=11;break;
				
			case 17:case 18:case 19:
				 resultTime=14;break;
				
			case 20:case 21:case 22:
				 resultTime=17;break;
				
			case 23:
				resultTime=20;break;
				
			case 0:case 1:
				day=day-1; resultTime=20;break;
				
			case -1: ///0시에서 1을 뺴면 -1이기 때문에 케이스 추가
				resultTime=20;break;
				
			default: resultTime=50;
				
			
				}	
			
				return resultTime;
		}
		
	}
	
	
	int Next_checkTime(int hour_f, int min_f) {
		
		if(Module_checkTime(hour_f,min_f)+3 >=24)
		{
			day=day+1;
			return (Module_checkTime(hour_f,min_f)+3)%24;
		}
		else
			return Module_checkTime(hour_f,min_f)+3;
	}
	
	
	
	boolean TMN_Check(int hour_f, int min_f) {
		if(hour_f>=2)
		{
			if(hour_f==2 && min_f<=10)
				return false;
			else
				return true;
		}
		else
			return false;
	}
	
	
	
	boolean TMX_Check(int hour_f, int min_f) {
		if(hour_f>=11)
		{
			if(hour_f==11 && min_f==10)
				return false;
			else
				return true;
		}		
		else
			return false;
	}
	
	
	String ChangeStr(int a) {
		
		String date;
		
		date= String.format("%02d",a);
		
		return date;
	}
	
	String getWeek() {
		String week=null;
		
		switch(dayofweek) {
		
		case 1:week="일";break;
		case 2:week="월";break;
		case 3:week="화";break;
		case 4:week="수";break;
		case 5:week="목";break;
		case 6:week="금";break;
		case 7:week="토";break;
		}
		
		return week;
		
	}
	
	
	


}
