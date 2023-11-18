package main.java.Core.API;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import java.nio.file.Path;
import java.nio.file.Paths;


public class VoiceRSS {
    private static final String API_KEY = "d855f24940dc4b8eaab22e512b3ea899";
    private static final String API_URL = "https://api.voicerss.org/";
    public static String ACCENT = Languages.English_GreatBritain;

    public static String playVoice(String text, String language) throws Exception {
        String result = "";
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            String data = "key=" + API_KEY + "&hl=" + language 
            + "&c=MP3&f=16khz_16bit_stereo&src=" + text; 
            OutputStream os = connection.getOutputStream();
            os.write(data.getBytes());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                //Path outputPath = Paths.get("/src/main/resources/Audio/output.mp3");
                Path outputPath = Paths.get("src/main/resources/Audio/output.mp3");
                Files.copy(is, outputPath, StandardCopyOption.REPLACE_EXISTING);
                is.close();
                connection.disconnect();
                result = outputPath.toAbsolutePath().toString();
            } else {
                System.out.println("API request failed with HTTP code: " + connection.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String textToSpeech = "bạn gầy";
        String audioFilePath = playVoice(textToSpeech, "vi-vn");
        if (audioFilePath == null) {
            System.out.println("Không thể tạo tệp âm thanh.");
        }
    }
}


