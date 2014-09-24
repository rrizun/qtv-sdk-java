package qtv;

import java.io.*;
import java.net.*;

import com.google.common.base.*;
import com.google.common.io.*;
import com.google.gson.*;

/**
 * 
 * QTV API client.
 *
 */
public class ApiClient {
  private final String url;
  private final String username;
  private final String password;

  /**
   * ctor
   * 
   * @param authProvider
   */
  public ApiClient(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  /**
   * listJobs
   * 
   * @return
   * @throws Exception
   */
  public ListJobsResponse listJobs() throws Exception {
    URLConnection urlConnection = new URL(url + "/jobs").openConnection();
    urlConnection.setRequestProperty("Authorization", Joiner.on(" ").join("Basic", BaseEncoding.base64().encode(Joiner.on(":").join(username, password).getBytes())));
    final InputStream in = urlConnection.getInputStream();
    try {
      return new Gson().fromJson(new InputStreamReader(in), ListJobsResponse.class);
    } finally {
      in.close();
    }
  }

  /**
   * getJob
   * 
   * @return
   * @throws Exception
   */
  public JobValue getJob(String job_id) throws Exception {
    URLConnection urlConnection = new URL(url + "/jobs/" + job_id).openConnection();
    urlConnection.setRequestProperty("Authorization", Joiner.on(" ").join("Basic", BaseEncoding.base64().encode(Joiner.on(":").join(username, password).getBytes())));
    final InputStream in = urlConnection.getInputStream();
    try {
      return new Gson().fromJson(new InputStreamReader(in), JobValue.class);
    } finally {
      in.close();
    }
  }

}
