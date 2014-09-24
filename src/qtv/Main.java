package qtv;

import java.io.*;

public class Main {
  static ApiClient api = new ApiClient("http://api.quickfire.tv/app/api/v1", "test", "test");
  public static void main(String[] args) throws Exception {
    // list all jobs
    ListJobsResponse listJobsResponse = api.listJobs();
    out.println(listJobsResponse);
    // get a specific job
    JobValue job = api.getJob(listJobsResponse.jobs.get(0).job_id);
    out.println(job);
  }
  static PrintStream out = System.out;
}
