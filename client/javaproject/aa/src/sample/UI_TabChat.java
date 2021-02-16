package sample;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
public class UI_TabChat extends JPanel {
	
	JPanel TabChat;
	JTextArea inputText,outputText;
	String res_message,req_message;
	
	Socket socket = null;            //Server�� ����ϱ� ���� Socket
    DataInputStream in = null;        //Server�κ��� �����͸� �о���̱� ���� �Է½�Ʈ��
    DataOutputStream out = null;   
	String ip="1.209.180.149";
	int port =5000;
	//SoftBevelBorder inputText_Border;
	LineBorder inputText_Border;
	UI_TabChat(){
		
		 try {
			socket = new Socket(ip,port);
			in = new DataInputStream(socket.getInputStream());            
	         
	         out = new DataOutputStream(socket.getOutputStream());
	         
	         out.writeUTF(UI_JFrame.User); //���� �г��� ������ ����
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		
			
			
			
		} //��������
		 
		//chat tap ����
		TabChat = new JPanel();
		this.setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		
		
		
		//ä���Է�â ����
		inputText = new JTextArea("",5,20);
		inputText_Border = new LineBorder(Color.black,2);
		inputText.setBorder(inputText_Border);
		inputText.setLineWrap(true);//�ڵ��ٹٲ�
		inputText.addKeyListener(new ChatKeyListener());
		add(inputText,BorderLayout.SOUTH);
		
		
		
		
		//ä�ó��� Ȯ�� , static���� ����� socket method �ʿ�!!
		outputText = new JTextArea();
		outputText.setEditable(false);
		add(new JScrollPane(outputText),BorderLayout.CENTER);
		
	
		
	}
	
	
	//keylistener Ŭ���� ���� 
	class ChatKeyListener extends KeyAdapter {
		
		

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				req_message = inputText.getText();
				outputText.append(UI_JFrame.User+" : ");
				outputText.append(req_message+"\n");
				inputText.setText("");
				
				try {
					out.writeUTF(req_message);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					outputText.append("Bot : �������� ������ �������� �ʽ��ϴ�.\n");
				}
				
				
				
				//�����ε� ���� request�޽��� ���� Ŭ���� �ʿ�
				
				try {
					res_message = in.readUTF();
					outputText.append("Bot : ");
					outputText.append(res_message+"\n");
					inputText.setText("");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					outputText.append("Bot : ������ �߻��Ͽ����ϴ�.");
				}
				
				
				
			}
		}
	}
	
	
	
	
	
	
}
	







