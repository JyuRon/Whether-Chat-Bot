package sample;
import java.util.Calendar;

public class API_Parameter {
	static String locate[] = {"대전광역시","서구","도마2동"};
	String endpoint = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData";
	String ServiceKey ="Ri4XU6laTcHN3xx5dxlcLZljMbVi724jfa4yJ8Lrz753odoTra9PwMmcVho3tDYovHlxvHoQFcfNfcO9hS%2FZRA%3D%3D";
	String base_date;
	String base_time;
	String nx;
	String ny;
	String _type="json";
	API_Time cal;

	public API_Parameter() {
	///좌표값 구하기
	CoordFetcher a = new CoordFetcher();
	
	
	
	Coord b;
	b= a.fetchCoord(locate); //return Coord 코드번호
	
	nx=b.getSx();
	ny=b.getSy();
	
	cal = new API_Time();
	
	base_time = cal.ChangeStr(cal.Module_checkTime(cal.hour,cal.min))+"00";
	base_date = cal.year+cal.ChangeStr(cal.mon)+cal.ChangeStr(cal.day);
	
	//System.out.println(base_time);
	//System.out.println(base_date);
	
	cal.cal = Calendar.getInstance();
	
	
	endpoint=endpoint+"?ServiceKey="+ServiceKey+"&base_date="+base_date+"&base_time="+base_time+"&nx="+nx+"&ny="+ny+"&_type="+_type;
	
	System.out.println(endpoint);
	}
	
	String TMX_Parameter() {
		String TMXpoint = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData"+"?ServiceKey="+ServiceKey+"&base_date="+base_date+"&base_time="+"1100"+"&nx="+nx+"&ny="+ny+"&_type="+_type; 
		return TMXpoint;
	}
	
	String TMN_Parameter() {
		String TMNpoint = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData"+"?ServiceKey="+ServiceKey+"&base_date="+base_date+"&base_time="+"0200"+"&nx="+nx+"&ny="+ny+"&_type="+_type; 
		return TMNpoint;
	}
	
	String Next() {
		String next_time = cal.ChangeStr(cal.Next_checkTime(cal.hour,cal.min))+"00";
		String next_date = cal.year+cal.ChangeStr(cal.mon)+cal.ChangeStr(cal.day);
		String point ="http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData" +"?ServiceKey="+ServiceKey+"&base_date="+next_date+"&base_time="+next_time+"&nx="+nx+"&ny="+ny+"&_type="+_type;
		return point;
	}
}