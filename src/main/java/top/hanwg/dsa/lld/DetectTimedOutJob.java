package top.hanwg.dsa.lld;

import java.util.*;

public class DetectTimedOutJob {

    public record Job(int jobId, int start) { }

    int firstTimedOutJobId(List<String> logs, int timeoutThreshold) {
        Queue<Job> activeJobs = new PriorityQueue<>((job1, job2) -> {
            if (job1.start == job2.start) {
                return Integer.compare(job1.jobId, job2.jobId);
            }

            return Integer.compare(job1.start, job2.start);
        });

        for (String log : logs) {
            String[] tokens = log.split(",");
            int jobId = Integer.parseInt(tokens[0]);
            int timestamp = Integer.parseInt(tokens[1]);
            Job job = new Job(jobId, timestamp);

            // check active jobs for timeout
            Job active = activeJobs.peek();
            if (active != null && job.start - active.start >= timeoutThreshold) {
                return active.jobId;
            }

            if (Objects.equals(tokens[2], "START")) {
                activeJobs.offer(job);
            } else {
                if (active != null && active.jobId == jobId) {
                    activeJobs.poll();
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        DetectTimedOutJob solution = new DetectTimedOutJob();
        System.out.println(solution.firstTimedOutJobId(List.of("1,1,START", "2,2,START", "1,4,END", "3,8,START", "3,15,END"), 5)); // 2
        System.out.println(solution.firstTimedOutJobId(List.of("5,1,START", "6,1,START", "5,7,END", "6,7,END"), 5)); // 5
    }
}
