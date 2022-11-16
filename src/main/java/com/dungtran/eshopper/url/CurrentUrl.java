package com.dungtran.eshopper.url;

public class CurrentUrl {

	
	public static String addFirstParamString(String u, String paramName, String paramValue) {

		StringBuffer url = new StringBuffer("/" + u);
		if (paramName != "") {
			url.append("?" + paramName + "=" + paramValue );
		}

		return url.toString();
	}

	public static String addParamsString(String u, String paramName, String paramValue) {

		StringBuffer url = new StringBuffer(u);
		url.append("&" + paramName + "=" + paramValue);

		return url.toString();
	}
	
	public static String getUrlWithoutParam(String u) {
		StringBuffer url = new StringBuffer("/" + u + "?flag=f");
		return url.toString();
	}

}

























/*rainsama7 Rainsama7
 * yasuo Rainsama123456
 * tvdunggit git10109811
 * Gl5
 * ud(gm) Rainsama123456
 * minh12453 Dungsama123456
 * 
 * 
 * 
 */