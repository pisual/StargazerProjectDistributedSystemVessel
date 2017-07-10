package com.stargazerproject.messagequeue;

public class LoadCalculation {
	
	private int out = Integer.MAX_VALUE;
	
	private int in = -Integer.MAX_VALUE;

	public LoadCalculation(int maxoutArg, int maxinArg){
		out = maxoutArg;
		in = -maxinArg;
	}
	
	public static void main(String[] args) {
		LoadCalculation loadCalculation = new LoadCalculation(1000,1000);
		System.out.println("Out: "+loadCalculation.out);
		System.out.println("In: "+loadCalculation.in);
		double result = loadCalculation.in+loadCalculation.out;
		System.out.println(result);
	}
	
}
