package org.cloud.hikvision.common.concurrent;

import java.util.concurrent.ForkJoinPool;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		int[] array = new int[] {1,2,2,3,4,54,5,6,7,7,8};
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		SumTask sumTask = new SumTask(0,array.length-1,array);
		long start = System.currentTimeMillis();
		forkJoinPool.invoke(sumTask);
		System.out.println("The count is "+sumTask.join()
        +" spend time:"+(System.currentTimeMillis()-start)+"ms");
		Thread.sleep(30000);
	}
}































