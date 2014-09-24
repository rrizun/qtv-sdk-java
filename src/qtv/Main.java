package qtv;

import java.io.*;

import qtv.api.*;

public class Main {
  static ApiClient api = new ApiClient("http://api.quickfire.tv/app/api/v1", "demo", "demo");
  public static void main(String[] args) throws Exception {
    listJobs();
    getJob();
//    submitNewJob();
  }
  static void listJobs() throws Exception {
    out.println(api.listJobs());
  }
  static void getJob() throws Exception {
    ListJobsResponse listJobsResponse = api.listJobs(-1); // list most recent jobs (page=-1)
    if (listJobsResponse.jobs.size() > 0)
      out.println(api.getJob(listJobsResponse.jobs.get(0).job_id));
  }
  static void submitNewJob() throws Exception {
    JobValue newJobRequest = new JobValue("s3://quickfire-input/sintel-1024-surround.mp4");
    JobValue newJobResponse = api.newJob(newJobRequest);
    out.println(newJobResponse);
  }
  static PrintStream out = System.out;
}
