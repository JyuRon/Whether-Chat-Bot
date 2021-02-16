package sample;

import javax.swing.*;

import org.json.simple.parser.ParseException;

import java.awt.*;
public class UI_TabHome extends JPanel {
	
	JPanel UI_Today,UI_Now_Whether,UI_Next_Whether;
	JLabel UI_Date,UI_Locate,UI_Max,UI_Min;
	JLabel imageLabel,imageLabel2;
	JLabel[] nowLabel,nextLabel;
	String[] now,next;
	String[] state = {"POP","T3H","REH","SKY","PTY"};
	API_Parameter UI_Parameter;
	API_Response UI_Info;
	API_Time UI_Time;
	ImageIcon[] Home_Image;
	UI_TabHome(){
		setLayout(null);
		//this.setBackground(Color.WHITE);
		UI_Parameter = new API_Parameter();
		UI_Info = new API_Response();
		UI_Time = new API_Time();
		  
		
		
		////오늘 기상정보 정리
		UI_Today = new JPanel();
		UI_Today.setSize(330, 60);
		UI_Today.setLocation(0,0);
		UI_Today.setLayout(null);
		UI_Today.setBackground(Color.WHITE);
		
		
		UI_Date= new JLabel(UI_Time.year+"년 "+UI_Time.mon+"월 "+UI_Time.day+"일 " +UI_Time.getWeek()+"요일");
		UI_Date.setLocation(10,10);
		UI_Date.setSize(200,20);
		UI_Today.add(UI_Date);
		
		///////////////////////////////////////////////////////////////////
		UI_Max = new JLabel();
		UI_Max.setLocation(230,10);
		UI_Max.setSize(200,20);
			
		if(UI_Time.TMX_Check(UI_Time.hour, UI_Time.min))
		{
			try {
				UI_Max.setText("최고기온 : "+UI_Info.information_Select(UI_Info.conAddr(UI_Parameter.TMX_Parameter()), "TMX")+"도");
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			UI_Max.setText("최고온도 : ??도");
		
		UI_Today.add(UI_Max);
		
		
		////////////////////////////////////////////////////////////////
		
		UI_Min = new JLabel();
		UI_Min.setLocation(230,30);
		UI_Min.setSize(200,20);
		
		if(UI_Time.TMN_Check(UI_Time.hour, UI_Time.min))
		{
			try {
				UI_Min.setText("최저기온 : "+UI_Info.information_Select(UI_Info.conAddr(UI_Parameter.TMN_Parameter()), "TMN")+"도");
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			UI_Min.setText("최저온도 : ??도");
		
		UI_Today.add(UI_Min);
		
		
		
		//////////////////////////////////////////////
		
		UI_Locate= new JLabel(UI_Parameter.locate[0]+" "+UI_Parameter.locate[1]+" "+UI_Parameter.locate[2]);
		UI_Locate.setLocation(10,30);
		UI_Locate.setSize(200,20);
		UI_Today.add(UI_Locate);
		
		
		add(UI_Today);
		
		
		
		Home_Image = new ImageIcon[8];
		Home_Image[0] = new ImageIcon("img/sun1.png");
		Home_Image[1] = new ImageIcon("img/sun2.png");
		Home_Image[2] = new ImageIcon("img/sun3.png");
		Home_Image[3] = new ImageIcon("img/sun4.png");
		Home_Image[4] = new ImageIcon("img/cloud1.png");
		Home_Image[5] = new ImageIcon("img/cloud2.png");
		Home_Image[6] = new ImageIcon("img/moon.png");
		Home_Image[7] = new ImageIcon("img/rain.png");
		
		
		//현재시각 기상정보 정리
		UI_Now_Whether = new JPanel();
		UI_Now_Whether.setLocation(0,73);
		UI_Now_Whether.setSize(330,200);
		UI_Now_Whether.setLayout(null);
		UI_Now_Whether.setBackground(Color.WHITE);
		imageLabel = new JLabel();
		
		
		now = new String[6];
	
		for(int i=0;i<now.length-1;i++)
		{
			try {
				now[i] =  UI_Info.information_Select(UI_Info.conAddr(UI_Parameter.endpoint), state[i]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		
		
		int difHour = UI_Time.hour;
		
		if(now[4].equals("1"))
		{
			imageLabel.setIcon(Home_Image[7]);
		}
		else
		{
			if(difHour>=20 || difHour<=3)
			{
				imageLabel.setIcon(Home_Image[6]);
			}
			else
			{
				if(now[3].equals("3"))
				{
					imageLabel.setIcon(Home_Image[4]);
				}
				else if(now[3].equals("4"))
				{
					imageLabel.setIcon(Home_Image[5]);
				}
				
				else
				{
					if(difHour == 4 || difHour == 19)
						imageLabel.setIcon(Home_Image[0]);
					else if(difHour == 5 || difHour == 18)
						imageLabel.setIcon(Home_Image[1]);
					else if(difHour == 6)
						imageLabel.setIcon(Home_Image[2]);
					else
						imageLabel.setIcon(Home_Image[3]);
				}
			}
			
				
		}
		
		
		
		imageLabel.setSize(100,100);
		imageLabel.setLocation(125,2);
		UI_Now_Whether.add(imageLabel);
		
		nowLabel = new JLabel[5];
		for(int i=0;i<nowLabel.length;i++)
		{
			nowLabel[i] = new JLabel();
			nowLabel[i].setSize(200,20);
			UI_Now_Whether.add(nowLabel[i]);
		}
		
		
		
		nowLabel[0].setText("현재 날씨");
		nowLabel[1].setText("온도 : "+now[1]+"도");
		nowLabel[2].setText("습도 : "+now[2]+"%");
		nowLabel[3].setText("강수확률 : "+now[0]+"%");
		
		if(now[3].equals("3")) 
			nowLabel[4].setText("하늘상태 : 구름많음");
		else if(now[3].equals("4"))
			nowLabel[4].setText("하늘상태 : 흐림");
		else
			nowLabel[4].setText("하늘상태 : 맑음");
	
		
		nowLabel[0].setLocation(135,100);
		nowLabel[1].setLocation(50,140);
		nowLabel[2].setLocation(50,160);
		nowLabel[3].setLocation(200,140);
		nowLabel[4].setLocation(200,160);
		
		add(UI_Now_Whether);
		
		
		
		
		
		
		
		
		
		
		
		
		
		//다음시각 기상정보 정리
		UI_Next_Whether = new JPanel();
		UI_Next_Whether.setLocation(0,286);
		UI_Next_Whether.setSize(330,200);
		UI_Next_Whether.setLayout(null);
		UI_Next_Whether.setBackground(Color.WHITE);
		imageLabel2 = new JLabel();
		imageLabel2.setIcon(Home_Image[7]);
		imageLabel2.setSize(100,100);
		imageLabel2.setLocation(125,2);
		UI_Next_Whether.add(imageLabel2);
		
		
		nextLabel = new JLabel[5];
		for(int i=0;i<nextLabel.length;i++)
		{
			nextLabel[i] = new JLabel();
			nextLabel[i].setSize(200,20);
			UI_Next_Whether.add(nextLabel[i]);
		}
		
		
		next = new String[6];
		
		for(int i=0;i<next.length-1;i++)
		{
			try {
				next[i] =  UI_Info.information_Select(UI_Info.conAddr(UI_Parameter.Next()), state[i]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		
		
		
		
		if(next[4].equals("1"))
		{
			imageLabel2.setIcon(Home_Image[7]);
		}
		else
		{
			if(difHour>=20 || difHour<=3)
			{
				imageLabel2.setIcon(Home_Image[6]);
			}
			else
			{
				if(next[3].equals("3"))
				{
					imageLabel2.setIcon(Home_Image[4]);
				}
				else if(next[3].equals("4"))
				{
					imageLabel2.setIcon(Home_Image[5]);
				}
				
				else
				{
					if(difHour == 4 || difHour == 19)
						imageLabel2.setIcon(Home_Image[0]);
					else if(difHour == 5 || difHour == 18)
						imageLabel2.setIcon(Home_Image[1]);
					else if(difHour == 6)
						imageLabel2.setIcon(Home_Image[2]);
					else
						imageLabel2.setIcon(Home_Image[3]);
				}
			}
			
				
		}
		
		
		
		nextLabel[0].setText("3시간후 날씨");
		
		nextLabel[1].setText("온도 : "+next[1]+"도");
		nextLabel[2].setText("습도 : "+next[2]+"%");
		nextLabel[3].setText("강수확률 : "+next[0]+"%");
		
		if(next[3].equals("3")) 
			nextLabel[4].setText("하늘상태 : 구름많음");
		else if(next[3].equals("4"))
			nextLabel[4].setText("하늘상태 : 흐림");
		else
			nextLabel[4].setText("하늘상태 : 맑음");
		
		nextLabel[0].setLocation(135,100);
		nextLabel[1].setLocation(50,140);
		nextLabel[2].setLocation(50,160);
		nextLabel[3].setLocation(200,140);
		nextLabel[4].setLocation(200,160);
		
		add(UI_Next_Whether);
		
		
		
		
		
		
		
	}

}

