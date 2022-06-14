package com.zalinius.darzalcommon.math;

public class GCD {
	//prevent instantiation of this class
	private GCD() {}
	
	public static int gcd(int x, int y){
		x = Math.abs(x);
		y = Math.abs(y);
		if(x<y) {
			int temp = y;
			y = x;
			x = temp;
		}
			
		return gcdRecursive(x,y);
	}
	
	private static int gcdRecursive(int x, int y){
		if(y==0)
			return x;
		return (gcdRecursive(y, x%y));
	}
}
