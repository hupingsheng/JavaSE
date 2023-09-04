package com.hps.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 计算1到100的和
 */
public class CountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2;

    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1, 100);

        ForkJoinTask<Integer> result = forkJoinPool.submit(task);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute){
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }else {
            int middle = (start + end) / 2;
            CountTask left = new CountTask(start, middle);
            CountTask right = new CountTask(middle + 1, end);

            //执行子任务
            left.fork();
            right.fork();

            //等待子任务执行完，并得到其结果
            int leftResult = left.join();
            int rightResult = right.join();

            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;

    }
}
