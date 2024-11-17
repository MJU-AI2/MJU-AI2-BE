package com.example.aiquiz.quiz.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Quiz API", description = "문제 관련 API")
public interface QuizController {
    private final GPTService gptService;

    public QuizController(GPTService gptService) {
        this.gptService = gptService;
    }

    /**
     *
     * @param topic QuizTopic or Language
     * @return generated quiz by gpt
     */
    @GetMapping("/generate")
    public ResponseEntity<String> generateQuiz(@RequestParam String topic) {
        try {
            String prompt = "Provide a coding quiz question about " + topic + ".";
            String quiz = gptService.generateQuiz(prompt);
            return ResponseEntity.ok(quiz);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating quiz: " + e.getMessage());
        }
    }
}
