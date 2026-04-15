package com.practice.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxCPULoad {

    static class Job{
        int load;
        int start;
        int end;

        public Job(int load, int start, int end) {
            this.load = load;
            this.start = start;
            this.end = end;
        }
    }

    static void main() {
        Job[] jobs = {
                new Job(1,4,3),
                new Job(2,5,4),
                new Job(7,9,6)
        };
        System.out.println(getMaxLoad(jobs));
    }

    private static int getMaxLoad(Job[] jobs) {

        //minHEap
        PriorityQueue<Job> pq = new PriorityQueue<>((a,b)-> a.end - b.end);
        Arrays.sort(jobs, (a,b)-> a.start - b.start);
        int curretLoad =0;
        int maxLoad = 0;
        for(Job job: jobs){
            while(!pq.isEmpty() && job.start >= pq.peek().end){
                curretLoad -= pq.poll().load;
            }
            pq.add(job);
            curretLoad += job.load;
            maxLoad = Math.max(curretLoad, maxLoad);

        }
        return maxLoad;
    }


}
