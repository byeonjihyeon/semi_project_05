package kr.or.iei.gym.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PortOneUtil {
    private static final String IMP_KEY = "4682862534767316";
    private static final String IMP_SECRET = "kYEFogmdTSurns3YAwvGlorA6NaWhbIQ0ncOJ0CL8hvwpbPklSVT7jWtntITN3xekeF3a4mCcJ5R4xVf";
    private static final Gson gson = new Gson();

    // 1. 액세스 토큰 요청
    public static String getAccessToken() throws IOException {
        URL url = new URL("https://api.iamport.kr/users/getToken");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        Map<String, String> credentials = new HashMap<>();
        credentials.put("imp_key", IMP_KEY);
        credentials.put("imp_secret", IMP_SECRET);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(gson.toJson(credentials).getBytes());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        Map<String, Object> responseMap = gson.fromJson(reader, Map.class);
        Map<String, Object> responseData = (Map<String, Object>) responseMap.get("response");

        return (String) responseData.get("access_token");
    }

    // 2. imp_uid로 결제 정보 조회
    public static Map<String, Object> getPaymentInfo(String impUid, String token) throws IOException {
        URL url = new URL("https://api.iamport.kr/payments/" + impUid);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", token);

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        Map<String, Object> result = gson.fromJson(reader, new TypeToken<Map<String, Object>>() {}.getType());
        return (Map<String, Object>) result.get("response");
    }
}
