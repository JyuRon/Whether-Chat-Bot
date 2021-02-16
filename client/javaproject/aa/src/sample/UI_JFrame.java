package sample;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


public class UI_JFrame extends JFrame {
	
	JFrame UI_Main;
	JTabbedPane  UI_TapBar;
	ImageIcon[] Tab_Image;
	
	static String User="GUEST";
	
	//ui ������
	public UI_JFrame() {
		
		//��ä ���� ����
		UI_Main = new JFrame();
		UI_TapBar = createTabbedPane();
		
		//contentpane �߰�����
		Container UI_ContentPane = UI_Main.getContentPane();
		UI_ContentPane.add(UI_TapBar,BorderLayout.CENTER);
		
		
		
		//JFrame ���� ����
		UI_Main.setTitle("��������ʼ�");
		UI_Main.setSize(350,600);
		UI_Main.setVisible(true);;
		UI_Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	//�ǹ� ���� �޼ҵ�
	JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		pane.setBackground(Color.white);
		
		Tab_Image = new ImageIcon[3];
		Tab_Image[0] = new ImageIcon("img/home2.png");
		Tab_Image[1] = new ImageIcon("img/chat2.png");
		Tab_Image[2] = new ImageIcon("img/setting2.png");
		
		pane.addTab("",Tab_Image[0], new UI_TabHome());
		pane.addTab("",Tab_Image[1], new UI_TabChat());
		pane.addTab("",Tab_Image[2], new UI_TabSetting());
		
		return pane;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new UI_JFrame();
		
		

	}

}
