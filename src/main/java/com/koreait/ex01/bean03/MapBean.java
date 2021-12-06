package com.koreait.ex01.bean03;

import java.util.Map;

public class MapBean {

	private Map<String, String> map;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public void mapInfo() {
		for(Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey()+ ":"+ entry.getValue());
		}
	}
}
