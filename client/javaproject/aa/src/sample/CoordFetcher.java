package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


/* ��ǥ ���� ���(����)
	CoordFetcher a = new CoordFetcher();
		
		String locate[] = {"����Ư����","���ʱ�","����1��"};
		
		Coord b;
		b= a.fetchCoord(locate); //return Coord �ڵ��ȣ
		
		System.out.println(b.getSx());
		
		System.out.println(b.getSy());
		
 */

public class CoordFetcher {
	
	private final String sUrlDef = "http://www.kma.go.kr/DFSROOT/POINT/DATA/";
	private final String sUrlMdlHead = "mdl.";
	private final String sUrlLeafHead = "leaf.";
	private final String sUrlTail = ".json.txt";
	private Map<String, String> mapTop;
	private Map<String, String> mapMdl;
	private Map<String, Coord> mapLeaf;

	public CoordFetcher() { }

	private String getStrUrl(String s) {
		if (s.equals("top"))
			return sUrlDef + "top" + sUrlTail;
		else
			return sUrlDef;
	}

	private String getStrUrl(String s, String code) {
		String tmp = null;
		if (s.equals("mdl"))
			tmp = sUrlMdlHead;
		else if (s.equals("leaf"))
			tmp = sUrlLeafHead;
		return sUrlDef + tmp + code + sUrlTail;
	}

	public Coord fetchCoord(String[] saLocation) { // ����: {�ñ���, �õ�, ������}
		JSONArray jsonArrTop = null;
		JSONArray jsonArrMdl = null;
		JSONArray jsonArrLeaf = null;

		jsonArrTop = getRemoteJSONArray(getStrUrl("top"));
		mapTop = getJsonSubMap(jsonArrTop);
		jsonArrMdl = getRemoteJSONArray(getStrUrl("mdl", mapTop.get(saLocation[0])));
		mapMdl = getJsonSubMap(jsonArrMdl);
		jsonArrLeaf = getRemoteJSONArray(getStrUrl("leaf", mapMdl.get(saLocation[1])));
		mapLeaf = getJsonLeafMap(jsonArrLeaf);
		return mapLeaf.get(saLocation[saLocation.length - 1]);
	}

	public Map<String, String> getMapTop() {
		return mapTop;
	}

	public Map<String, String> getMapMdl() {
		return mapMdl;
	}

	public Map<String, Coord> getMapLeaf() {
		return mapLeaf;
	}
	
	public JSONArray getRemoteJSONArray(String url) {
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
		JSONArray jsonArr = (JSONArray) JSONValue.parse(jsonHtml.toString());
		return jsonArr;
	}
	
	public Map<String, String> getJsonSubMap(JSONArray jsonArrSource) {
		Map<String, String> jsonMap = new LinkedHashMap<String, String>();

		// ���û API ���� �޾ƿ��� JSON�� code:value���ĸ� ���߹Ƿ�
		// �� Algorithm���� �ϰ��ǰ� mapping�� �� ����
		// (leaf�ܿ����� x,y���� �߰��� ���Ƿ� �ٸ� method����ؾ���)
		for (int i = 0; i < jsonArrSource.size(); i++) {
			JSONObject jsonObjItem = (JSONObject) jsonArrSource.get(i); // JSONArray���� JSONObject�ϳ��� ������
			String code = (String) jsonObjItem.get("code"); // JSONObject���� key, value ������
			String value = (String) jsonObjItem.get("value");
			jsonMap.put(value, code); // �����̸����� code�� ã�� ���ϹǷ� K,V swap mapping
		}
		return jsonMap;
	}
	
	public Map<String, Coord> getJsonLeafMap(JSONArray jsonArrSource) {
		Map<String, Coord> jsonMap = new LinkedHashMap<String, Coord>();
		for (int i = 0; i < jsonArrSource.size(); i++) {
			JSONObject jsonObjItem = (JSONObject) jsonArrSource.get(i); // JSONArray���� JSONObject�ϳ��� ������
			String value = (String) jsonObjItem.get("value"); // JSONObject���� key, value ������
			String x = (String) jsonObjItem.get("x");
			String y = (String) jsonObjItem.get("y");
			jsonMap.put(value, new Coord(x, y));
		}
		return jsonMap;
	}	


}
