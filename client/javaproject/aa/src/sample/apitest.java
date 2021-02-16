package sample;

import org.json.simple.parser.ParseException;

public class apitest {

	public static void main(String[] args) throws ParseException  {
		// TODO Auto-generated method stub
		
		
		API_Parameter c = new API_Parameter();
		API_Response b = new API_Response();
		
		
		//현재시간의 기상정보를 모두 출력
		b.information(b.conAddr(c.endpoint));
		
		//원하는 카테고리의 정보를 얻는 방법
		System.out.println(b.information_Select(b.conAddr(c.endpoint),"POP"));
		
		
		
		
		
		//API_Time 메소드 사용법
		API_Time a = new API_Time();
		
		
		System.out.println(a.year+" "+a.mon+" "+a.day+" "+a.hour+" "+a.min);
		
		System.out.println(a.Module_checkTime(a.hour,a.min));
		
		System.out.println(a.Next_checkTime(a.hour, a.min));
		
		System.out.println(a.TMN_Check(a.hour,a.min));     
		
		System.out.println(a.TMX_Check(a.hour,a.min));
		
		System.out.println(a.ChangeStr(a.hour));
		
		
		
		//DB에서 원하는 값 불러오기  ----- 현재 기상정보
		
		String base_time = a.ChangeStr(a.Module_checkTime(a.hour,a.min))+"00";
		//String sql = "select POP"+" from village_weather where base_date = '"+baseDate_result+"' and base_time = '"+baseTime_result+"' ;";
        //System.out.println(sql);
		
		//String base_time = a.ChangeStr(a.Next_checkTime(a.hour,a.min))+"00";
		//3시간후 기상정보 불러오기 위해선 이거 사용
		//String base_time = "1100"       //최고기온 발표된 시간
		//String base_time = "0200"       //최저기온 발표된 시간
		
		String base_date = a.year+a.ChangeStr(a.mon)+a.ChangeStr(a.day);
		
		API_Response sample = new API_Response();
		
		
		System.out.println(sample.information_DBSel("POP", base_date, base_time));
		
		
		
		
		
		
			

	}
	
	
}



