package com.zalinius.darzalcommon.math;

public class GCD {
	//prevent instantiation of this class
	private GCD() {}
	
	public static int gcd(int x, int y){
		x = Math.abs(x);
		y = Math.abs(y);
		if(x>=y)
			return GCDRecursive(x,y);
		else
			return GCDRecursive(y,x);
			
	}
	
	private static int GCDRecursive(int x, int y){
		if(y==0)
			return x;
		return (GCDRecursive(y, x%y));
	}
}
