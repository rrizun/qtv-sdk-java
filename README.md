qtv-sdk-java
============

Qtv-sdk-java is a helper library used to help integrate the QuickFire.TV transcoder service using java.

Libraries used:

 - google-gson
 - google-guava
 - java-jersey

Usage
=====

Create api client:

```java
ApiClient api = new ApiClient("http://api.quickfire.tv/app/api/v1", "demo", "demo");
```

List jobs:

```java
ListJobsResponse listJobsResponse = api.listJobs();
```

Get specific job:

```java
String job_id = ...;
JobValue job = api.getJob(job_id);
```

Submit a new job:

```java
JobValue newJobRequest = new JobValue("s3://quickfire-public/big_buck_bunny_1080p_h264.mov");

OutputValue output = new OutputValue();
outputValue.format = "mp4";
newJobRequest.outputs.add(output);

JobValue newJobResponse = api.newJob(newJobRequest);
```
