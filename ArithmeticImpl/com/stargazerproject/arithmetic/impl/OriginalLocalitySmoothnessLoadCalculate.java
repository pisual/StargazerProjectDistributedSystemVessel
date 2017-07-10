package com.stargazerproject.arithmetic.impl;

import java.util.Random;


/** 
*  @name 近周期推断型平滑及趋势算法 -Almost periodic type inference smoothing algorithm and trends-
*  @illustrate 实现缓存的基础功能
*  @param <K> 缓存的Key值类型
*  @param <V> 缓存的Value类型
*  @author Felixerio
*  **/


public class OriginalLocalitySmoothnessLoadCalculate {
	
	
	private float presentDataWeight = 0.8F;
	private float formerlyDataWeight = 0.2F;
	private int queueTotalNumber = 20000;
	private float equilibrium = 0.1F;

	/***/
	
	public int queueNowNumber;
	public float presentData;
	public float formerlyData[];
	
	
	private float s5(float formerlyData){
		return (presentData*presentDataWeight)+(formerlyData*formerlyDataWeight);
	}
	
	public float s4(){
		float formerlyDataSum = 0;
		for(int i=0;i<formerlyData.length;i++){
			formerlyDataSum = formerlyDataSum + formerlyData[i];
		}
		return s5(formerlyDataSum/formerlyData.length);
	}
	
	public float s3(){
		return (((queueTotalNumber*equilibrium) - queueNowNumber) + s4())/2;
	}
	
	
	
	public static void main(String[] args) {
		
		OriginalLocalitySmoothnessLoadCalculate o = new OriginalLocalitySmoothnessLoadCalculate();
		
		int queueNowNumber = 0 ;
		float presentData = 0;
		float formerlyData[] = new float[20];
		
		String queueNowNumberArray [] = new String [1000];
		String presentDataArray [] = new String[1000];
		String nextNeedDataArray [] = new String[1000];
		
		
		Random random = new Random();
		
		int totalNeed[] = new int[5];
		
		for(int i=0;i<1000;i++){
			
			
			if(i<20){
				while(true){
					presentData = random.nextInt(500);
					if(presentData>100) break;
				}
				

				if((queueNowNumber-presentData)<0){
					queueNowNumber = 0;
				}
				else{
					queueNowNumber = (int) (queueNowNumber-presentData);
				}
				


				formerlyData[i%19] = presentData;
				
				o.presentData = presentData;
				o.formerlyData = formerlyData;
				o.queueNowNumber = queueNowNumber;
				
				queueNowNumber = queueNowNumber + 200;

				queueNowNumberArray[i] = queueNowNumber+"	";
				presentDataArray [i] = presentData + "	";
				nextNeedDataArray [i] = 200 + "	";
				
				
				continue;
			}
			
			else{
//				System.out.print(queueNowNumber+"	");
//				System.out.println("这一轮的消费数目：" + presentData);
//				System.out.println("需要获取的消息数目：" + o.s3());
//				System.out.println();
				
				
				if(i>200){

						presentData = random.nextInt(10);

				}
				else
				{
//					while(true){
//						presentData = random.nextInt(500);
//						if(presentData>100) break;
//					}
					presentData = random.nextInt(300);
					
				}


				if((queueNowNumber-presentData)<0){
					queueNowNumber = 0;
				}
				else{
					queueNowNumber = (int) (queueNowNumber-presentData);
				}
				formerlyData[i%19] = presentData;
				
				o.presentData = presentData;
				o.formerlyData = formerlyData;
				o.queueNowNumber = queueNowNumber;
				

				
				if(i%5 == 0){
					int sum = totalNeed[0] + totalNeed[1]+ totalNeed[2]+totalNeed[3]+totalNeed[4];
					if(queueNowNumber + sum > 3000)
					{
						queueNowNumber = queueNowNumber + (2000-queueNowNumber);
					}
					else{
						queueNowNumber = queueNowNumber + sum;
					}

				}
				else{
					if((int)o.s3()>0){
						totalNeed[i%4] = (int)o.s3();
					}
					else{
						totalNeed[i%4] = 0;
					}

				}

				queueNowNumberArray[i] = queueNowNumber+"	";
				presentDataArray [i] = presentData + "	";

				if((int)o.s3()>0){
					nextNeedDataArray [i] = o.s3() + "	";
				}
				else{
					nextNeedDataArray [i] = 0 + "	";
				}
				
			}
			
		}
		
		for(int i=0;i<queueNowNumberArray.length;i++){
			System.out.print(queueNowNumberArray[i]);
		}
		
		System.out.println();
		
		for(int i=0;i<presentDataArray.length;i++){
			System.out.print(presentDataArray[i]);
		}
		
		System.out.println();
		
		for(int i=0;i<nextNeedDataArray.length;i++){
			System.out.print(nextNeedDataArray[i]);
		}
		
		
	}
	
	
}
