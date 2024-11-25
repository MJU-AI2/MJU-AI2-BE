package com.example.aiquiz.quiz.controller;

//import com.example.aiquiz.chatgpt.service.GPTService;
import com.example.aiquiz.quiz.dto.response.GetQuizResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Quiz API", description = "문제 관련 API")
public interface QuizController {

    @Operation(summary = "의류 전체 조회", description = "Page에 맞게 의류 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"
                    , description = "성공적으로 조회")
    })
    @GetMapping("/all")
    ResponseEntity<GetQuizResponse> getAllQuiz(
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "sort", required = false, defaultValue = "LATEST") String sortOptions);

}
