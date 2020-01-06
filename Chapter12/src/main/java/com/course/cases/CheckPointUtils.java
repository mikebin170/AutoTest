package com.course.cases;

import com.googlecode.aviator.AviatorEvaluator;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CheckPointUtils {
	
	public static boolean checkbyJsonPath(String json, String params) {
		if (params != null && !"".equals(params)) {
			    //提取表达式 给jsonpath 提取数据使用
				String[] values = params.split("=|>|<|>=|<=|==");
				Object value = JsonPath.read(json, values[0]);
				//提取数据如果是特殊类型
				if(value instanceof String) {
					System.out.println("----------"+ values[1]);
					params = params.replace(values[values.length-1], covertToAviatorString(values[values.length-1]));
					if(params.contains("=")&&!params.contains(">=")&&!params.contains("<=")&&!params.contains("==")) {
						params = params.replace("=", "==");
					}
					
				}
				//替换$. 成data
				params = params.replace(values[0], "data");
				//构造aviator参数
				Map<String, Object> env = new HashMap<String, Object>();
				env.put("data", value);
				System.out.println(" totest " + params + " value " + value);
				Boolean result = (Boolean) AviatorEvaluator.execute(params, env);
				return result;
		}
		return true;
	}
	
//	public static boolean checkbyJsonPath(String json, String params) {
//		if (params != null && !"".equals(params)) {
//			    //提取表达式 给jsonpath 提取数据使用
//				String[] values = params.split("=|>|<|>=|<=|==");
//				Object value = JsonPath.read(json, values[0]);
//				//提取数据如果是特殊类型
//				if(value instanceof String) {
//					System.out.println("----------"+ values[1]);
//					params = params.replace(values[values.length-1], covertToAviatorString(values[values.length-1]));
//				}
//				
//				if(params.contains("=")&&!params.contains(">=")&&!params.contains("<=")&&!params.contains("==")) {
//					params = params.replace("=", "==");
//				}
//				
//				//替换$. 成data
//				params = params.replace(values[0], "data");
//				//构造aviator参数
//				Map<String, Object> env = new HashMap<String, Object>();
//				env.put("data", value);
//				System.out.println(" totest " + params + " value " + value);
//				Boolean result = (Boolean) AviatorEvaluator.execute(params, env);
//				return result;
//		}
//		return true;
//	}
	
	
	
	private static String makeQueryStringAllRegExp(String str) {
	        if(StringUtils.isBlank(str)){
	            return str;
	        }
	        return str.replace("\\", "\\\\").replace("*", "\\*")
	                .replace("+", "\\+").replace("|", "\\|")
	                .replace("{", "\\{").replace("}", "\\}")
	                .replace("(", "\\(").replace(")", "\\)")
	                .replace("^", "\\^").replace("$", "\\$")
	                .replace("[", "\\[").replace("]", "\\]")
	                .replace("?", "\\?").replace(",", "\\,")
	                .replace(".", "\\.").replace("&", "\\&");
	    }
	
	
	
	
	
	/**
	 * 字符串特殊处理
	 * @param value
	 * @return
	 */
	private static String covertToAviatorString(String value) {
		return "'"+value+"'";
	}
	
	public static void main(String[] args) {
		String test ="{\"msg\":\"登录成功\",\"uid\":\"DAD3483647A94DBDB174C4C036CA8A80\",\"code\":\"1\",\"code2\":\"2\",\"code3\":\"3\"}";
		String expression2="$.code=1";
		checkbyJsonPath(test,expression2);
		    
		//复杂检查点问题
		String expression="$.code=1&&$.code2>=4&&$.code3>2";
	    checkbyJsonPath(test,expression);
	}

}
