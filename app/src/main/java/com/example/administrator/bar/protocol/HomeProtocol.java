package com.example.administrator.bar.protocol;

public class HomeProtocol {
	public void load(int index) {
		//请求服务器
		String json = loadLocal(index);
		if (json == null) {
			json = loadServer(index);
			if (json != null) {
				saveLocal(json, index);
			}
		}
		if (json != null) {
			paserJson(json);
		}
	}

	private String loadLocal(int index) {
		return null;
	}

	private String loadServer(int index) {

	return null;
	}

	private void saveLocal(String json, int index) {

	}

	private void paserJson(String json) {

	}
}
