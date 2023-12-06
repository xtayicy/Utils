package harry.utils;

import com.alibaba.fastjson.JSONObject;

import harry.domain.test.Parameter;

/**
 * 
 * @author Harry
 *
 */
public final class StringUtil{
	public static void main(String[] args) {
		String jsonStr = "{\"code\":\"2\",\"key1\":\"111\",\"key2\":\"\\\"[111,222]\\\"\",\"workflow\":\"1111\",\"e_aa_data\":\"jedis(test)\",\"keepResultName\":\"key2,data,e_aa_data\"}";
		Parameter parameter = JSONObject.parseObject(jsonStr).toJavaObject(Parameter.class);
		System.out.println(parameter);
	}
	
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str)){
			return true;
		}
		
		return false;
	}
}
