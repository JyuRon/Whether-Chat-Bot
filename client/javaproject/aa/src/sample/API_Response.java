package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class API_Response {
	
	JSONArray conAddr(String endpoint) throws ParseException {
		
		
		String url = endpoint;
		
		StringBuffer jsonHtml = new StringBuffer();
		try {
			URL u = new URL(url);
			InputStream uis = u.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(uis, "UTF-8"));

			String line = null;
			while ((line = br.readLine()) != null) {
				jsonHtml.append(line + "\r\n");
			}
			br.close();
			uis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		JSONParser paser = new JSONParser();
		
		
		//문자열 데이터를 json데이터로 변환
		JSONObject obj = (JSONObject) paser.parse(jsonHtml.toString());
		
		System.out.println(jsonHtml.toString());
		
		//response가져오기
		JSONObject parse_response = (JSONObject) obj.get("response");
		
		//response로부터 body가져오기
		JSONObject parse_body = (JSONObject) parse_response.get("body");
		
		//body로 부터 items 받아오기
		JSONObject parse_items = (JSONObject) parse_body.get("items");
		
		//items로 부터 itemlist 받아오기
		JSONArray parse_item = (JSONArray) parse_items.get("item");
		
		return parse_item;
		
	
		
	}
	
	//데이터 가공처리 메소드
	
	void information(JSONArray parse_item) {
		
		for(int i=0;i<parse_item.size();i++) {
			JSONObject imsi = (JSONObject)parse_item.get(i);
			String baseDate_result = imsi.get("baseDate").toString();
			String baseTime_result = (String) imsi.get("baseTime").toString();
			String category_result = (String) imsi.get("category").toString();
			String fcstDate_result = (String) imsi.get("fcstDate").toString();
			String fcstTime_result = (String) imsi.get("fcstTime").toString();
			String fcstValue_result = (String) imsi.get("fcstValue").toString();
			String nx_result = (String) imsi.get("nx").toString();
			String ny_result = (String) imsi.get("ny").toString();
			
			System.out.println(baseDate_result + "   " +baseTime_result+"   "+category_result+ "   "+fcstDate_result+ "   "+fcstTime_result+ "   "+fcstValue_result+ "   "+nx_result+"  "+ny_result+"\n");
		}
		
	}
	
	
	String information_Select(JSONArray parse_item,String value) {
		
		String fcstValue_result = null;
		String category_result = null;
		for(int i=0;i<parse_item.size();i++) {
			
			JSONObject imsi = (JSONObject)parse_item.get(i);
			
			
			category_result = (String) imsi.get("category").toString();
			
			if(category_result.equals(value)) {
				fcstValue_result = (String) imsi.get("fcstValue").toString();
				
				return fcstValue_result; 
			}
			
			
		}
		
		return "결과없음";
		
		
	}
	
	
	void information_DB(JSONArray parse_item) {
	      
	     
	      Statement stmt = null;
	      
	      String driver = "org.mariadb.jdbc.Driver";    //드라이버 (maria[2.4.1])
	       Connection con = null;   
	       
	       PreparedStatement pstmt = null;
	       ResultSet rs;
	       
	       try {
	            Class.forName(driver);                    //JDBC 드라이버 로딩
	            con = DriverManager.getConnection(
	                    "jdbc:mariadb://localhost/test123", //IP:PORT/DB이름
	                    "root",                       //ID
	                    "1234");                       //PW
	            
	            if( con != null ) {
	                System.out.println("DB 접속 성공");

	           
	            }
	            
	            
	          String baseDate_result=null;
	          String baseTime_result=null;
	          String category_result=null;
	          String fcstDate_result=null;
	          String fcstTime_result=null;
	          String fcstValue_result=null;
	          String nx_result=null;
	          String ny_result=null;
	          
	          for(int i=0;i<parse_item.size();i++) {
	          
	             JSONObject imsi = (JSONObject)parse_item.get(i);
	             baseDate_result = imsi.get("baseDate").toString();
	             baseTime_result = (String) imsi.get("baseTime").toString();
	             category_result = (String) imsi.get("category").toString();
	             fcstDate_result = (String) imsi.get("fcstDate").toString();
	             fcstTime_result = (String) imsi.get("fcstTime").toString();
	             fcstValue_result = (String) imsi.get("fcstValue").toString();
	             nx_result = (String) imsi.get("nx").toString();
	             ny_result = (String) imsi.get("ny").toString();
	             
	            // System.out.println(baseDate_result + "   " +baseTime_result+"   "+category_result+ "   "+fcstDate_result+ "   "+fcstTime_result+ "   "+fcstValue_result+ "   "+nx_result+"  "+ny_result+"\n");
	             
	             
	             String insert = "insert into village_weather(base_date, base_time, nx, ny) values("+"\""+ baseDate_result +"\""+","+"\""+ baseTime_result +"\""+","+"\""+nx_result+"\""+","+"\""+ny_result+"\""+");";
	             String update = "update village_weather set "+category_result+" = "+"\""+fcstValue_result+"\""+" where base_date = "+"\""+baseDate_result+"\""+" and base_time = "+"\""+baseTime_result+"\""+" and nx = "+"\""+nx_result+"\""+" and ny = "+"\""+ny_result+"\""+";";
	             
	        
	             
	             String Q_Date = "select base_date from village_weather where "+baseDate_result+";"; //검증용
	             String Q_Time = "select base_time from village_weather where "+baseTime_result+";"; //검증용
	             try {
	                if(i==0) {
	                System.out.println(insert);
	                stmt = con.createStatement();
	                if (Q_Date == baseDate_result && Q_Time == baseTime_result) { //날자,시간이 같을시 그냥 넘어감
	                   continue;
	                }
	                else {
	                   stmt.executeUpdate(insert);                          //없을시 한번 insert 함
	                }
	                /*pstmt = con.prepareStatement("insert into Village_weather(base_date, base_time, nx, ny) values(?, ?, ?, ?)");
	                pstmt.setString(1, baseDate_result);
	                pstmt.setString(2, baseTime_result);
	                pstmt.setString(3, nx_result);
	                pstmt.setString(4, ny_result);*/
	                }
	                
	                System.out.println(update);
	                stmt.executeUpdate(update);
	                
	               
	 
	             } catch (SQLException e) {
	                e.printStackTrace();
	             }
	             
	          }
	            
	            
	            
	        } catch (ClassNotFoundException e) {            //드라이버 연결 실패(라이브러리 추가)
	            System.out.println("드라이버 로드 실패");
	        } catch (SQLException e) {
	            System.out.println("DB 접속 실패");              //ID, PW확인 / 서버상태확인
	            e.printStackTrace();
	        }
	         finally {                                //close 순서 pstmt -> con
	            if (pstmt != null) {
	               try {
	                  pstmt.close();
	               }
	               catch (SQLException e){
	                  e.printStackTrace();
	               }
	            }
	            if (con != null) {
	               try {
	                  con.close();
	               }
	               catch (SQLException e) {
	                  e.printStackTrace();
	               }
	            }
	         }
	       
	      
	   
	   }
	
	String information_DBSel(String category, String baseDate_result, String baseTime_result)
	{
		
		Statement stmt = null;
	      String result = null;
	      String driver = "org.mariadb.jdbc.Driver";    //드라이버 (maria[2.4.1])
	       Connection con = null;   
	       
	       PreparedStatement pstmt = null;
	       ResultSet rs;
	       
	       try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(
	                   "jdbc:mariadb://localhost/test123", //IP:PORT/DB이름
	                   "root",                       //ID
	                   "1234");                       //PW
	           
	           if( con != null ) 
	               System.out.println("DB 접속 성공");
	           
	           String sql = "select '"+category+"' from village_weather where base_date = '"+baseDate_result+"' and base_time = '"+baseTime_result+"' ;";
	           System.out.println(sql);
	           

	           
	           rs = stmt.executeQuery(sql); //POP값을 불러옴
	           
	           while(rs.next()) {
	        	   result = rs.getString(category);
	           }
	           	
	           
	           return result;
	           
	           
	           
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "결과없음";
		}catch (SQLException e) {
            System.out.println("DB 접속 실패");              //ID, PW확인 / 서버상태확인
            e.printStackTrace();
            return "결과없음";
        }
         finally {                                //close 순서 pstmt -> con
            if (pstmt != null) {
               try {
                  pstmt.close();
               }
               catch (SQLException e){
                  e.printStackTrace();
               }
            }
            if (con != null) {
               try {
                  con.close();
               }
               catch (SQLException e) {
                  e.printStackTrace();
               }
            }
         }                    //JDBC 드라이버 로딩
           
	       
		
	}


}
