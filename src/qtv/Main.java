package qtv;

import java.io.*;
import java.net.*;

import com.google.common.base.*;
import com.google.common.io.*;
import com.google.gson.*;

public class Main {
  public static ListJobsResponse listJobs() throws Exception {
    URLConnection urlConnection = new URL("http://localhost/app/api/v1/jobs").openConnection();
    urlConnection.setRequestProperty("Authorization", Joiner.on(" ").join("Basic", BaseEncoding.base64().encode("test:test".getBytes())));
    final InputStream in = urlConnection.getInputStream();
    try {
      return new Gson().fromJson(new InputStreamReader(in), ListJobsResponse.class);
    } finally {
      in.close();
    }
  }
  public static void main(String[] args) throws Exception {
    for (JobValue job : listJobs().jobs)
      out.println(job);
  }
  static PrintStream out = System.out;
}
