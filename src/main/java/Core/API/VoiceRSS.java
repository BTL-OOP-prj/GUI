package main.java.Core.API;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.voicerss.tts.Languages;

import java.nio.file.Path;
import com.sun.media.jfxmedia.locator.Locator;
import static java.nio.file.Files.newInputStream;

public class VoiceRSS {
    private static final String API_KEY = "d855f24940dc4b8eaab22e512b3ea899";
    private static final String API_URL = "https://api.voicerss.org/";
    public static String ACCENT = Languages.English_GreatBritain;
    private static final String[] language = { "en-us", "vi-vn", "ko-kr", "ja-jp", "ru-ru" };
    private static final String[] languageChoices = { "Tiếng Anh", "Tiếng Việt", "Tiếng Hàn",
            "Tiếng Nhật", "Tiếng Nga" };

    public static String transcript(String lang) {
        for (int i = 0; i < languageChoices.length; i++) {
            if (languageChoices[i].equals(lang)) {
                lang = language[i];
                break;
            }
        }
        return lang;
    }

    public static void audioFilePath(String text, String language) throws Exception {
        try {
            String result = "";
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            String data = "key=" + API_KEY + "&hl=" + transcript(language)
                    + "&c=MP3&f=16khz_16bit_stereo&src=" + text;
            OutputStream os = connection.getOutputStream();
            os.write(data.getBytes());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                Path outputPath = Paths.get("src/main/resources/Audio/output.mp3");
                Files.copy(is, outputPath, StandardCopyOption.REPLACE_EXISTING);
                is.close();
                connection.disconnect();
                result = outputPath.toAbsolutePath().toString();
            } else {
                System.out.println("API request failed with HTTP code: " + connection.getResponseCode());
            }
            System.out.println("Duong dan den file : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
