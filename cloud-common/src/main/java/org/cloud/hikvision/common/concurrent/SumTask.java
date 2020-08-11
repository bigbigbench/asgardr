package org.cloud.hikvision.common.concurrent;

import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class SumTask extends RecursiveTask<Integer> {

	private final int threshold;
	private static final int segmentation = 10;
	
	private int[] src;
	
	private int fromIndex;
	private int toIndex;
	
	public SumTask(int fromIndex, int toIndex, int[] src) {
		this.fromIndex = fromIndex;
		this.toIndex = toIndex;
		this.src = src;
		this.threshold = src.length/segmentation;
	}
	
	@Override
	protected Integer compute() {
		if((toIndex-fromIndex) < threshold) {
			int count = 0;
			System.out.println("from index = "+fromIndex + "  toIndex="+toIndex);
			for(int i = fromIndex;i<=toIndex;i++) {
				count+=src[i];
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return count;
		}else {
			int mid = (fromIndex+toIndex)/2;
			SumTask left = new SumTask(fromIndex,mid,src);
			SumTask right = new SumTask(mid+1,toIndex,src);
			invokeAll(left,right);
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return left.join() + right.join();
		}
	}
	
	public static void main(String[] args) {
		
	}

}























