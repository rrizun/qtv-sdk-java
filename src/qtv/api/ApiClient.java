package qtv.api;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.*;

import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import org.glassfish.jersey.message.internal.*;

import com.google.common.base.*;
import com.google.common.io.*;
import com.google.gson.*;

/**
 * 
 * QTV API java client.
 *
 */
public class ApiClient {
  private final String url;
  private final Client client = ClientBuilder.newClient();
  /**
   * ctor
   */
  public ApiClient(final String url, final String username, final String password) {
    this.url = url;
    // http basic auth
    client.register(new ClientRequestFilter() {
      @Override
      public void filter(ClientRequestContext context) throws IOException {
        context.getHeaders().add("Authorization", Joiner.on(" ").join("Basic", BaseEncoding.base64().encode(Joiner.on(":").join(username, password).getBytes())));
      }
    });
    // json reader/writer
    client.register(new AbstractMessageReaderWriterProvider<Object>() {
      @Override
      public boolean isReadable(Class<?> cls, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
      }
      @Override
      public Object readFrom(Class<Object> cls, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> headers, InputStream in) throws IOException, WebApplicationException {
        return new Gson().fromJson(new InputStreamReader(in), cls);
      }
      @Override
      public boolean isWriteable(Class<?> cls, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
      }
      @Override
      public void writeTo(Object object, Class<?> cls, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> headers, OutputStream out) throws IOException, WebApplicationException {
        final Writer writer = new OutputStreamWriter(out);
        try {
          new Gson().toJson(object, writer);
        } finally {
          writer.flush();
        }
      }
    });
  }
  /**
   * listJobs
   * 
   * GET /app/api/v1/jobs
   * 
   * @return
   * @throws Exception
   */
  public ListJobsResponse listJobs() throws Exception {
    return client.target(url).path("jobs").request().get(ListJobsResponse.class);
  }
  /**
   * listJobs
   * 
   * GET /app/api/v1/jobs
   * 
   * @return
   * @throws Exception
   */
  public ListJobsResponse listJobs(int page) throws Exception {
    return client.target(url).path("jobs").queryParam("page", page).request().get(ListJobsResponse.class);
  }
  /**
   * getJob
   *
   * GET /app/api/v1/jobs/{job_id}
   *
   * @return
   * @throws Exception
   */
  public JobValue getJob(String job_id) throws Exception {
    return client.target(url).path("jobs").path(job_id).request().get(JobValue.class);
  }
  /**
   * newJob
   * 
   * POST /app/api/v1/jobs
   * 
   * @param newJobRequest
   * @return
   */
  public JobValue newJob(JobValue newJobRequest) {
    return client.target(url).path("jobs").request().post(Entity.json(newJobRequest), JobValue.class);
  }
}
