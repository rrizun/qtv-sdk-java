package qtv.api;

import javax.script.*;

import com.google.common.base.*;
import com.google.gson.*;

public class OutputValue {
  public String output_id;
  public String output_uri;
  
  public String format;
  public OutputValue format(String format) {
    this.format = format;
    return this;
  }
  
  public String width;
  public String height;
  
  public String video_profile;
  public boolean has_video_profile() {
    return Strings.nullToEmpty(video_profile).length()>0;
  }

  public String video_level;
  public boolean has_video_level() {
    return Strings.nullToEmpty(video_level).length()>0;
  }
  
  public String audio_codec; // aac
  public boolean has_audio_codec() {
    return Strings.nullToEmpty(audio_codec).length()>0;
  }

  /**
   * 
   */
  public String audio_profile; // aac_lc, aac_he
  public boolean has_audio_profile() {
    return Strings.nullToEmpty(audio_profile).length()>0;
  }
  
  /**
   * 
   */
  public String audio_bitrate_kbps;
  public boolean has_audio_bitrate_kbps() {
    return Strings.nullToEmpty(audio_bitrate_kbps).length()>0;
  }
  public int audio_bitrate_kbps() {
    return Integer.parseInt(audio_bitrate_kbps);
  }

  /**
   * 
   */
  public String audio_channels;
  public boolean has_audio_channels() {
    return Strings.nullToEmpty(audio_channels).length()>0;
  }
  public int audio_channels() {
    return Integer.parseInt(audio_channels);
  }
  
  /**
   * 
   */
  public String audio_sample_rate;
  public boolean has_audio_sample_rate() {
    return Strings.nullToEmpty(audio_sample_rate).length()>0;
  }
  public int audio_sample_rate() {
    return Integer.parseInt(audio_sample_rate);
  }
  
  /**
   * 
   */
  public String video_bitrate_kbps;
  public boolean has_video_bitrate_kbps() {
    return Strings.nullToEmpty(video_bitrate_kbps).length()>0;
  }
  public int video_bitrate_kbps() {
    return Integer.parseInt(video_bitrate_kbps);
  }
  
  public String video_frame_rate;
  public boolean has_video_frame_rate() {
    return Strings.nullToEmpty(video_frame_rate).length()>0;
  }
  public double video_frame_rate() throws Exception {
    return (double) new ScriptEngineManager().getEngineByName("js").eval(video_frame_rate);
  }

  /**
   * max_video_bitrate_kbps
   */
  public String max_video_bitrate_kbps;
  public int max_video_bitrate_kbps() {
    return Integer.parseInt(max_video_bitrate_kbps);
  }
  public boolean has_max_video_bitrate_kbps() {
    return Strings.nullToEmpty(max_video_bitrate_kbps).length()>0;
  }
  public void set_max_video_bitrate_kbps(int max_video_bitrate) {
    this.max_video_bitrate_kbps = Integer.toString(max_video_bitrate);
  }
  
  public String interlaced; // mbaff

  // output ffprobe result
  public FfprobeValue outprobe;
  
  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }

}