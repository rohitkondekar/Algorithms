package General;

import java.io.IOException;

public class EnormousInputTest {
	
	
	public static void main(String[] args) throws IOException {
		
		int in,k=0,tot=0,t=0;
		
		byte [] input=new byte[65536];
		while((in=System.in.read())!=' ');//{n=n*10+in-'0';}
		while((in=System.in.read())!='\n'){k=k*10+in-'0';}
		
		while((in = System.in.read(input,0,65536))>-1){			
			
			for (int i = 0; i < in; i++) {
				if(input[i]!='\n'){
					t=t*10+input[i]-'0';
				}
				else{
					if(t%k==0)
						tot++;
					t=0;
				}
			}
		}
		System.out.println(tot);
	}

//	public static void main(String[] args) throws Exception{
//		
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		String[] tmp = in.readLine().split(" ");
//		
//		int n = Integer.parseInt(tmp[0]);
//		int k = Integer.parseInt(tmp[1]);
//		
//		int tot = 0;
//		while(n-->0){
//			if(Integer.parseInt(in.readLine())%k == 0)
//				tot++;
//		}
//		
//		System.out.println(tot);
//		in.close();
//		
//	}

}
