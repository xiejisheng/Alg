package com.xjs.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HandleJson {
	public static List<String> findValsOfAssginedKey(String data, Set<String> matchs){
		Map<String, List<String>> map = parse(data);
	    List<String> result = new ArrayList<String>();
	    for (Map.Entry<String, List<String>> each : map.entrySet()) {
	    	for (String match : matchs) {
	    		if (each.getKey().contains(match)) {
	    			result.addAll(each.getValue());
	    		}
	    	}
	    }
		return result;
	}
	// 前提：data是合法的json数组
	public static Map<String, List<String>> parse(String data) {
		Map<String, List<String>> result = new HashMap<>();
		if (null == data || data.length() == 0)
	    	return result;
		
		String trim = data.replace("[", "").replace("]", "").trim();
	    String[] substrs = trim.split("}");
	    for (String substr : substrs) {
	    	String[] maps = substr.split(",");
	        for (String aMap : maps) {
	        	System.out.println(aMap);
	            String replace2 = aMap.replace("{", "").replace("\"", "");
	            String[] keyVal = replace2.split(":");
	            
	            if (result.containsKey(keyVal[0])) {
	            	result.get(keyVal[0]).add(keyVal[0].trim());
	            } else {
	            	List<String> list = new ArrayList<>();
	                list.add(keyVal[1].trim());
	                result.put(keyVal[0], list);
	            }
			}
	    }
	    return result;
	}

//	@Test
	public static  void testFindValsOfAssginedKey() {
		Set<String> matchs = new HashSet<>();
	    matchs.add("ccd");
	    matchs.add("dcc");
	    String data = "[{1:\"a\",2:\"b\",cd: \"ccd\",ccd: \"ccd\",dcc: \"dcc\",ccdcc: \"dcc\"}]";
		List<String> result = findValsOfAssginedKey(data, matchs);
	    System.out.println(result);
	}
	public static void main(String[] args) {
		testFindValsOfAssginedKey();
	}
}
