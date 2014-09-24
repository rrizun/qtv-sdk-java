package qtv.api;

import java.util.*;

import com.google.common.collect.*;
import com.google.gson.*;

/**
 * JobValue
 */
public class JobValue {
  public String job_id;
  public String input_uri;
  public String status; // PENDING, SUCCESS, FAILURE, CANCELED
  public int progress; // 0..100
  public FfprobeValue inprobe;
  public final List<OutputValue> outputs = Lists.newArrayList();
  /**
   * ctor
   */
  public JobValue() {
  }
  /**
   * ctor
   * 
   * @param input_uri
   */
  public JobValue(String input_uri) {
    this.input_uri = input_uri;
  }
  /**
   * ctor
   * 
   * @param input_uri
   * @param output
   */
  public JobValue(String input_uri, OutputValue output) {
    this.input_uri = input_uri;
    outputs.add(output);
  }
  /**
   * Returns true if job is closed.
   * 
   * @return
   */
  public boolean isClosed() {
    return ImmutableSet.of("SUCCESS", "FAILURE", "CANCELED").contains(status);
  }
  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}