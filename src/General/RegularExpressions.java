package General;


/**
 * Implementing 
 * ^ - start of line
 * $ - end of line
 * * - one or more of previous characters
 * . - matches any single character
 * @author rohitkondekar
 * http://www.cs.princeton.edu/courses/archive/spr09/cos333/beautiful.html
 */

public class RegularExpressions {

	boolean match(String regex, String line){
		
		if(regex.charAt(0)=='^')
			return matchRegEx(regex,line,1,0);
		else{
			int i = 0;
			while(i++<line.length())
				if(matchRegEx(regex, line, 1, i))
					return true;					
		}
		return false;
	}
	
	private boolean matchRegEx(String regex, String line, int rIndex, int lIndex){

		if(rIndex == regex.length())
			return true;
		
		if(regex.charAt(rIndex)=='$' && lIndex == line.length())
			return true;
		
		if(rIndex<regex.length()-1 && regex.charAt(rIndex+1)=='*')
			return matchStar(regex,line,rIndex+2,lIndex,regex.charAt(rIndex));
		
		if(lIndex == line.length())
			return false;
		
		if(regex.charAt(rIndex)=='.' || regex.charAt(rIndex)==line.charAt(lIndex))
			return matchRegEx(regex, line, rIndex+1, lIndex+1);
		
		return false;
	}
	
	
	private boolean matchStar(String regex, String line, int rIndex, int lIndex, char lastCharacter){
		do{
			if(matchRegEx(regex,line,rIndex,lIndex))
				return true;
		}while(lIndex<line.length() && (line.charAt(lIndex++)==lastCharacter || lastCharacter=='.'));
		return false;
	}
	
	public static void main(String[] args) {
		RegularExpressions rx = new RegularExpressions();
		System.out.println(rx.match("^a*bb$", "aaaabb"));
		System.out.println(rx.match("a.*c$", "aaaabb"));
		System.out.println(rx.match("a.*c", "aaaabb"));
		System.out.println(rx.match("a.*c$", "aaaabbzc"));
	}

}
