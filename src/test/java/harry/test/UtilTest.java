package harry.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import harry.utils.Md5Util;

/**
 * 
 * @author Harry
 *
 */
public class UtilTest {
	@Test
	public void test(){
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("abcdefghijklmnopqrstuvwxyz");
		arrayList.add("abcdefghigk");
		arrayList.add("abcdefghijklmnopqrstuvwxyz");
		arrayList.add("abcdefghigk");
		
		String a = "";
		
		Set<Character> arrs = new HashSet<Character>();
		
		for (String s : arrayList) {
			arrs.clear();
            for(char i = 'a';i <= 'z';i++){
                arrs.add(i);
            }
            
            for(int i = 0;i < s.length();i++){
                arrs.remove(s.charAt(i));
            }
            
            if(arrs.size() == 0){
               a += "1";
            }else{
            	a += "0";
            }
		}
		
		System.out.println(a);
	}
}
