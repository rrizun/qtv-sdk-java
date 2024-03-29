qtv-sdk-java
============

Qtv-sdk-java is a helper library used to help integrate the QuickFire.TV transcoder service using java.

Usage
=====

Create api client:

```java
ApiClient api = new ApiClient("http://api.quickfire.tv/app/api/v1", "myusername", "mypassword");
```

List jobs:

```java
ListJobsResponse listJobsResponse = api.listJobs();
for (JobValue job : listJobsResponse.jobs)
  out.println(job);
```

Get specific job:

```java
String job_id = ...;
JobValue job = api.getJob(job_id);
```

Submit a new job:

```java
JobValue newJobRequest = new JobValue("s3://quickfire-public/big_buck_bunny_1080p_h264.mov");
newJobRequest.outputs.add(new OutputValue().format("mp4"));
JobValue newJobResponse = api.newJob(newJobRequest);
```
