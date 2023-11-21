package main.java.Core.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class APITranslator {
    private static final String[] language = { "en", "vi", "ko", "ja", "ru" };
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

    public static String translate(String langFrom, String langTo, String text) throws IOException {
        String result = "";
        try {
            System.out.println(langFrom + ' ' + langTo + ' ' + text);
            langFrom = transcript(langFrom);
            langTo = transcript(langTo);
            String rootAPI = "https://script.google.com/macros/s/AKfycbw1qSfs1Hvfnoi3FzGuoDWijwQW69eGcMM_iGDF7p5vu1oN_CaFqIDFmCGzBuuGCk_N/exec"
                    +
                    "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + langTo + "&source=" + langFrom;
            URL url = new URL(rootAPI);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                response.append(inputLine);
            }
            inputReader.close();
            con.disconnect();
            result = response.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
