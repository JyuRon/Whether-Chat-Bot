package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
public class UI_TabSetting extends JPanel {
	JLabel Setting_name,Setting_location,Setting_time;
	Container c;
	JTextField mod_name;
	JComboBox citybox,districtbox,areabox;
	JButton but_name,but_location;
	City_Array cityObj;
	
			
	UI_TabSetting(){
		Calendar cal = Calendar.getInstance();
		setLayout(null);
		this.setBackground(Color.WHITE);
		
		//닉네임 setting
		Setting_name = new JLabel("현재 닉네임 : "+UI_JFrame.User);
		Setting_name.setLocation(10,70);
		Setting_name.setSize(200,20);
		add(Setting_name);
		
		mod_name = new JTextField();
		mod_name.setLocation(30,90);
		mod_name.setSize(200,20);
		add(mod_name);
		
		but_name = new JButton("변경");
		but_name.setLocation(250,90);
		but_name.setSize(60,20);
		add(but_name);
		
		
		
		
		//위치 setting
		Setting_location = new JLabel("현재 위치 : "+API_Parameter.locate[0]+" "+API_Parameter.locate[1]+" "+API_Parameter.locate[2]);
		Setting_location.setLocation(10,160);
		Setting_location.setSize(250,20);
		add(Setting_location);
		
		cityObj = new City_Array();
		
		citybox = new JComboBox(cityObj.city);
		citybox.setLocation(10,190);
		citybox.setSize(100,20);
		add(citybox);
		
		/////////////////////////////////////////////////////////////////////////////////////
		
		districtbox = new JComboBox();
		
		
		String districtname = (String)cityObj.CtoD.get(citybox.getSelectedItem());
	
		
		for(int i=0;i<cityObj.getDistrict(districtname).length;i++) {
			districtbox.addItem(cityObj.district[i]);
		}
		
		
		districtbox.setLocation(120,190);
		districtbox.setSize(100,20);
		add(districtbox);
		
		
		districtbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JComboBox cb = (JComboBox)e.getSource();
				int index = cb.getSelectedIndex();
				areabox.removeAllItems();
				for(int i=0;i<cityObj.area[index].length;i++) {
					areabox.addItem(cityObj.area[index][i]);
				}
				
			}
			
		});
		
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		areabox = new JComboBox();
		
		
		
		areabox.setLocation(225,190);
		areabox.setSize(100,20);
		add(areabox);
		
		
		/////////////////////////////////////////////////////////////////////////////////////////
		
		
		but_location= new JButton("위치 변경");
		but_location.setLocation(225,220);
		but_location.setSize(100,20);
		add(but_location);
		
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		
		///현재 시간 setting
		
		
		
		Setting_time = new JLabel("설정 시간 : "+cal.get(Calendar.YEAR)+"년 "+(cal.get(Calendar.MONTH)+1)+"월 "+cal.get(Calendar.DAY_OF_MONTH)+"일 "+cal.get(Calendar.HOUR_OF_DAY)+"시" );
		
		Setting_time.setLocation(10,400);
		Setting_time.setSize(380,20);
		add(Setting_time);
		
		
	}

}

