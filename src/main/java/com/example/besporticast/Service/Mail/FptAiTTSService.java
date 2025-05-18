package com.example.besporticast.Service.Mail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class FptAiTTSService {

    private static final String API_URL = "https://api.fpt.ai/hmi/tts/v5";
    private static final String API_KEY = "Ps4NHRxyjx3HJRIfbNE9QNzw30Gce9Q1";

    private final OkHttpClient httpClient = new OkHttpClient();

    public void convertTextToSpeech(String text, String outputFilePath) throws IOException, InterruptedException {
        // Tách các số thành từng chữ số để dễ đọc (chỉ trong TTS)
        String processedText = formatTextForTTS(text);

        RequestBody body = RequestBody.create(processedText, MediaType.parse("text/plain"));

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("api-key", API_KEY)
                .addHeader("voice", "banmai")
                .addHeader("speed", "-1")
                .build();


        Response response = httpClient.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Lỗi gọi API TTS: " + response);
        }

        String jsonResponse = response.body().string();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);
        int error = root.path("error").asInt();
        if (error != 0) {
            throw new IOException("API trả lỗi: " + root.path("message").asText());
        }

        String asyncUrl = root.path("async").asText();

        Thread.sleep(3000); // Chờ file mp3 được xử lý

        downloadMp3File(asyncUrl, outputFilePath);
    }

    private void downloadMp3File(String fileUrl, String savePath) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        }
    }

    /**
     * Hàm tách từng chữ số trong chuỗi để đọc từng số riêng biệt trong TTS.
     * Ví dụ: "Mã OTP là 123456" -> "Mã OTP là 1 2 3 4 5 6"
     */
    public String formatTextForTTS(String originalText) {
        return originalText.replaceAll("\\d", "$0 ").replaceAll("\\s+", " ").trim();
    }
}

