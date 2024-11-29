package com.example.aiquiz.chatgpt.service;

import com.example.aiquiz.chatgpt.dto.GPTRequest;
import com.example.aiquiz.chatgpt.dto.GPTResponse;
import com.example.aiquiz.quiz.constants.QuizType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GPTService {
    private static final String API_KEY = System.getenv("OPENAI_API_KEY");
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public GPTResponse generateQuiz(String basePrompt, QuizType quizType) throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        // QuizType에 따라 프롬프트 생성
        String fullPrompt = basePrompt + "\n" + quizType.getPromptText() + "\n\n" +
                "Respond in JSON format with the following structure:\n" +
                "{\n" +
                "  \"title\": \"\",  // 문제 제목\n" +
                "  \"question\": \"\",  // 문제 내용\n" +
                "  \"choices\": [\"\", \"\", \"\", \"\"],  // 객관식 선택지\n" +
                "  \"answer\": \"\"  // 정답\n" +
                "}\n\n";

        GPTRequest request = new GPTRequest(fullPrompt);
        String requestBody = objectMapper.writeValueAsString(request);

        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));
        Request requestObject = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(requestObject).execute()) {
            if (response.isSuccessful()) {
                // 응답 내용을 변수에 저장
                String responseBody = response.body().string();

                // JSON 파싱
                return parseGPTResponse(responseBody, objectMapper);
            } else {
                String errorBody = response.body().string(); // 오류 응답 내용 저장
                System.err.println("GPT Error Response: " + errorBody);
                throw new Exception("Failed to generate quiz: " + errorBody);
            }
        }


    }

    private GPTResponse parseGPTResponse(String responseBody, ObjectMapper objectMapper) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode choiceNode = rootNode.get("choices").get(0).get("message").get("content");

            // JSON 데이터에서 필드 추출
            JsonNode contentNode = objectMapper.readTree(choiceNode.asText());
            GPTResponse gptResponse = new GPTResponse();
            gptResponse.setTitle(contentNode.get("title").asText());
            gptResponse.setQuestion(contentNode.get("question").asText());
            gptResponse.setAnswer(contentNode.get("answer").asText());

            if (contentNode.has("choices")) {
                gptResponse.setChoices(objectMapper.convertValue(contentNode.get("choices"), List.class));
            }

            return gptResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse GPT response: " + e.getMessage());
        }
    }
}
