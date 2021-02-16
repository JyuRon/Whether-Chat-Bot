import org.json.simple.parser.ParseException;

public class Filter {
	API_Time Filter_time;
	 API_Response response;
	 
	 String t3h ;
	 String reh ;
	 String pop;
	 String sky;
	 String tmn;
	 String tmx;
	 
	 String base_time;
	 String base_date;
	
	Filter(){
	 Filter_time = new API_Time();
	 response= new API_Response();
	 
	 //API_Response b = new API_Response();
	 API_Parameter c = new API_Parameter();
	 
	 try {
		response.information_DB(response.conAddr(c.endpoint));
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	
	 base_time = Filter_time.ChangeStr(Filter_time.Module_checkTime(Filter_time.hour,Filter_time.min))+"00";
	 base_date = Filter_time.year+Filter_time.ChangeStr(Filter_time.mon)+Filter_time.ChangeStr(Filter_time.day);
	 
	  t3h = response.information_DBSel("T3H", base_date, base_time);
	  reh = response.information_DBSel("REH", base_date, base_time);
	  pop = response.information_DBSel("POP", base_date, base_time);
	  sky = response.information_DBSel("SKY", base_date, base_time);
	  tmn = response.information_DBSel("TMN", base_date, base_time);
	  tmx = response.information_DBSel("TMX", base_date, base_time);
	 

	}
	
	String timeKeyword(String msg) {
		 if(msg.contains("오늘")) return base_date;
		 if(msg.contains("현재")) return Filter_time.hour+"시"+Filter_time.min+"분";
			 

		 else return "검색된 시간 없음";
		}
	
	 String total(String msg) {
			String total_msg = "";
			total_msg+=timeKeyword(msg);
			total_msg+=" "+wetherKeyword(msg);
			
			return total_msg;
		}
	 
	 
	 String wetherKeyword(String msg) {
		 if(sky.equals("1")) 
			 sky = "맑음";
			if(sky.equals("2")) 
				sky = "구름조금";
			if(sky.equals("3")) 
				sky = "구름많음";
			if(sky.equals("4")) 
				sky = "흐림";
		if(msg.contains("날씨")) {
			if(Filter_time.TMX_Check(Filter_time.hour, Filter_time.min)) {
				String final_weather = "\n최고기온 : "+tmx+ "도"+"\n최저기온 : "+tmn+"도"+"\n현재 온도 : "+t3h+"도"+
			"\n습도 : "+reh+"%"+"\n강수확률 : "+pop+"%"+"\n하늘상태 : "+sky;
				
				return final_weather;
			}
			else return "시간값 없음";
		}
		
		if(msg.contains("온도")) return "온도 : "+t3h+"도";
		if(msg.contains("습도")) return "습도 : "+reh+"%";
		if(msg.contains("강수확률")) return "강수확률 : "+pop+"%";
		if(msg.contains("하늘상태")) return sky;
		
		else return "검색된 날씨 없음";
	}
}
