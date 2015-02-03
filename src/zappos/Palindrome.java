package zappos;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Palindrome {

    static int palindrome(String str) {
    
        if(str==null || str.equals(""))
        	return 0;
        
        if(str.length()==1)
        	return 1;
        
        
        StringBuilder buf = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
        	buf.append(str.charAt(i));
        	buf.append("#");
		}
        buf.deleteCharAt(buf.length()-1);
        
        System.out.println(buf);
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < buf.length(); i++) {
        	
			int f=i,b=i;
        	
        	while(f>=0 && b<buf.length()){
        		if(buf.charAt(f)!=buf.charAt(b))
        			break;
        		
        		if(buf.charAt(f)!='#')
        			set.add(buf.substring(f, b+1).replace("#", ""));
        		f--;
        		b++;
        	}
        	
		}
        
        return set.size();
    }
    

    public static void main(String[] args) {
    	System.out.println(palindrome("aabaa"));
    	System.out.println(palindrome("abc"));
    	System.out.println(palindrome("abcc"));
    	System.out.println(palindrome("abcbcd"));
	}
    
//    public static void main(String[] args) throws IOException{
//        Scanner in = new Scanner(System.in);
//        final String fileName = System.getenv("OUTPUT_PATH");
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
//        int res;
//        String _str;
//        try {
//            _str = in.nextLine();
//        } catch (Exception e) {
//            _str = null;
//        }
//        
//        res = palindrome(_str);
//        bw.write(String.valueOf(res));
//        bw.newLine();
//        
//        bw.close();
//    }
    
    
}