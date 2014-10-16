package qtv.api;

import java.util.*;

import javax.script.*;

import com.google.common.collect.*;
import com.google.gson.*;

// https://github.com/FFmpeg/FFmpeg/blob/master/doc/ffprobe.xsd
public class FfprobeValue {
  public static class FormatValue {
    public String duration; // e.g., "25.000000"
    public String bit_rate; // bps
  }
  public static class StreamValue {
    public int index;
    public String codec_name; // "h264"
    public String profile;
    public String codec_type; // "audio", "video"

    // audio
    public String sample_fmt;
    public String sample_rate;
    public String channels;
    public String channel_layout;
    public String bits_per_sample;
    
    // video
    public String width;
    public String height;
    public String level;
    
    public String avg_frame_rate; // e.g., "24/1"
    public double avg_frame_rate() throws Exception {
      return (double) new ScriptEngineManager().getEngineByName("js").eval(avg_frame_rate);
    }
    public String duration;
    public String bit_rate; // bps
    public String nb_frames;

    // accessors
    
    public int sample_rate() {
      return Integer.parseInt(sample_rate);
    }
    public int channels() {
      return Integer.parseInt(channels);
    }
    public int bits_per_sample() {
      return Integer.parseInt(bits_per_sample);
    }

    public int width() {
      return Integer.parseInt(width);
    }
    public int height() {
      return Integer.parseInt(height);
    }
    public double bit_rate() {
      return (double) Double.parseDouble(bit_rate);
    }
  }
  public FormatValue format;
  public final List<StreamValue> streams = Lists.newArrayList();
  
  /**
   * Returns the first audio stream, if any.
   * 
   * @return
   */
  public List<StreamValue> audio_streams() {
    List<StreamValue> result = Lists.newArrayList();
    for (StreamValue stream : streams) {
      if ("audio".equals(stream.codec_type))
        result.add(stream);
    }
    return result;
  }
  
  /**
   * Returns the first video stream, if any.
   * 
   * @return
   */
  public List<StreamValue> video_streams() {
    List<StreamValue> result = Lists.newArrayList();
    for (StreamValue stream : streams) {
      if ("video".equals(stream.codec_type))
        result.add(stream);
    }
    return result;
  }
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}
