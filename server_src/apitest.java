

import org.json.simple.parser.ParseException;
public class apitest {

	public static void main(String[] args) throws ParseException  {
		// TODO Auto-generated method stub
		
		
		API_Parameter c = new API_Parameter();
		API_Response b = new API_Response();
		
		
		//����ð��� ��������� ��� ���
		//b.information(b.conAddr(c.endpoint));
		
		
		/*
		
		//���ϴ� ī�װ����� ������ ��� ���
		System.out.println(b.information_Select(b.conAddr(c.endpoint),"POP"));
		
		
		
		
		
		//API_Time �޼ҵ� ����
		API_Time a = new API_Time();
		
		
		System.out.println(a.year+" "+a.mon+" "+a.day+" "+a.hour+" "+a.min);
		
		System.out.println(a.Module_checkTime(a.hour,a.min));
		
		System.out.println(a.Next_checkTime(a.hour, a.min));
		
		System.out.println(a.TMN_Check(a.hour,a.min));
		
		System.out.println(a.TMX_Check(a.hour,a.min));
		
		System.out.println(a.ChangeStr(a.hour));
		
		*/
		
		
		API_Time t = new API_Time();
		b.information_DB(b.conAddr(c.endpoint));
		
		if(t.TMX_Check(t.hour, t.min))
			b.information_DB(b.conAddr(c.TMX_Parameter()));
		
		if(t.TMN_Check(t.hour, t.min))
			b.information_DB(b.conAddr(c.TMN_Parameter()));
		


	}
	
	
}


