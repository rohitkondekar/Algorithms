package General;

import java.nio.ByteBuffer;

public class ReverseByteInt {
	
	//Java Specific way to handle bits
	static int reverseBytesJava(int num){
		
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(num);
		
		//reverse
		byte b1_1 = buffer.get(3);
		byte b1_2 = buffer.get(0);
		buffer.put(3, b1_2);
		buffer.put(0, b1_1);
		
		b1_1 = buffer.get(2);
		b1_2 = buffer.get(1);
		buffer.put(2, b1_2);
		buffer.put(1, b1_1);
		
		return buffer.getInt(0);
	}
	
	static int reverseBytesNonJava(int num){
		
		byte[] byteArray = IntToByteArray(num);
		
		//reverse byteArray;
		byte b1_1 = byteArray[0];
		byte b1_2 = byteArray[3];
		byteArray[0] = b1_2;
		byteArray[3] = b1_1;
		
		b1_1 = byteArray[1];
		b1_2 = byteArray[2];
		byteArray[1] = b1_2;
		byteArray[2] = b1_1;
		
		return ByteArrayInt(byteArray);
		
	}
	
	static byte[] IntToByteArray(int num){
		byte[] byteArray = new byte[4];
		byteArray[0] = (byte)(num>>>24);
		byteArray[1] = (byte)(num>>>16);
		byteArray[2] = (byte)(num>>>8);
		byteArray[3] = (byte)(num);
		
		return byteArray;
	}
	
	static int ByteArrayInt(byte[] array){
		int num = 0;
		num |= array[0]<<24;
		num |= array[1]<<16;
		num |= array[2]<<8;
		num |= array[3];
		
		return num;
	}


	public static void main(String[] args) {
		
		System.out.println(123457+" -- "+reverseBytesJava(123457));
		System.out.println(65536+" -- "+reverseBytesJava(65536));
		System.out.println(6123213+" -- "+reverseBytesJava(6123213));
		
		System.out.println(65536+" -- "+reverseBytesNonJava(65536));
		System.out.println(123457+" -- "+reverseBytesNonJava(123457));
		System.out.println(6123213+" -- "+reverseBytesNonJava(6123213));
	}

}
