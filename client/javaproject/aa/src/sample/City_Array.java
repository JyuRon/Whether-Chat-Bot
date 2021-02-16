package sample;

import java.util.HashMap;

public class City_Array {
	
	
	String city[]= {"서울특별시","부산광역시", "대구광역시","인천광역시","광주광역시","대전광역시","울산광역시",
			"세종특별자치시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주특별자치도"};
	
	//대전 구
		String district[] = {"동구","중구","서구","유성구","대덕구"};
		
		//대전 동
		String area[][] = {{"중앙동","효동","신인동","판암1동","판암2동","용운동","대동","자양동","가양1동","가양2동","용전동","성남동",
			"홍도동","삼성동","대청동","산내동"},{"은행선화동","목동","중촌동","대흥동","문창동","석교동","대사동","부사동","용두동","오류동","태평1동","태평2동",
				"유천1동","유천2동","문화1동","문화2동","산성동"},{"복수동","도마1동","도마2동","정림동","변동","용문동","탄방동","괴정동","가장동","내동","갈마1동","갈마2동",
					"월평1동","월평2동","월평3동","가수원동","관저1동","관저2동","기성동","둔산1동","둔산2동","만년동","둔산3동"},
				{"진잠동","온천1동","온천2동","노은1동","노은2동","노은3동","신성동","전민동","구즉동","관평동","원신흥동"},
				{"오정동","대화동","회덕동","비래동","송촌동","중리동","신탄진동","석봉동","덕암동","목상동","법1동","법2동"}}; 
		
	
	HashMap<String, String> CtoD ;
	HashMap<String, Integer> DtoA;
	
	City_Array(){
	CtoD = new HashMap<String, String>();
	DtoA = new HashMap<String, Integer>();
	int i;
	
	
	
	
		
	for(i=0;i<city.length;i++)
		CtoD.put(city[i], "district");
	
	for(i=0;i<district.length;i++)
		DtoA.put(district[i],i);
		
	
	
	}

	public String[] getDistrict(String name) {
		return district;
	}

	public String[][] getArea() {
		return area;
	}
	
	
	
	
			

	


}
