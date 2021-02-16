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
		 if(msg.contains("����")) return base_date;
		 if(msg.contains("����")) return Filter_time.hour+"��"+Filter_time.min+"��";
			 

		 else return "�˻��� �ð� ����";
		}
	
	 String total(String msg) {
			String total_msg = "";
			total_msg+=timeKeyword(msg);
			total_msg+=" "+wetherKeyword(msg);
			
			return total_msg;
		}
	 
	 
	 String wetherKeyword(String msg) {
		 if(sky.equals("1")) 
			 sky = "����";
			if(sky.equals("2")) 
				sky = "��������";
			if(sky.equals("3")) 
				sky = "��������";
			if(sky.equals("4")) 
				sky = "�帲";
		if(msg.contains("����")) {
			if(Filter_time.TMX_Check(Filter_time.hour, Filter_time.min)) {
				String final_weather = "\n�ְ��� : "+tmx+ "��"+"\n������� : "+tmn+"��"+"\n���� �µ� : "+t3h+"��"+
			"\n���� : "+reh+"%"+"\n����Ȯ�� : "+pop+"%"+"\n�ϴû��� : "+sky;
				
				return final_weather;
			}
			else return "�ð��� ����";
		}
		
		if(msg.contains("�µ�")) return "�µ� : "+t3h+"��";
		if(msg.contains("����")) return "���� : "+reh+"%";
		if(msg.contains("����Ȯ��")) return "����Ȯ�� : "+pop+"%";
		if(msg.contains("�ϴû���")) return sky;
		
		else return "�˻��� ���� ����";
	}
}
