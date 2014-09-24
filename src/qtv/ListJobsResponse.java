package qtv;

import java.util.*;

import com.google.gson.*;

public class ListJobsResponse {
  public List<JobValue> jobs;
  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}