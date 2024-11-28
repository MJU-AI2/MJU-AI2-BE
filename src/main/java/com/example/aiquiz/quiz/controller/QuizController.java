package com.example.aiquiz.quiz.controller;

//import com.example.aiquiz.chatgpt.service.GPTService;
import com.example.aiquiz.quiz.dto.response.GetQuizResponse;
import com.example.aiquiz.quiz.dto.response.PageResponse;
import com.example.aiquiz.util.dto.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Quiz API", description = "문제 관련 API")
public interface QuizController {

    @Operation(summary = "퀴즈 전체 조회", description = "페이지에 맞게 퀴즈 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"
                    , description = "성공적으로 조회")
    })
    @GetMapping("/all")
    PageResponse<GetQuizResponse> getAllQuiz(
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page );

    @Operation( summary = "퀴즈 개수 조회", description = "현재 존재하는 퀴즈 개수 조회" )
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200"
                    , description = "성공적으로 조회" )
    })
    @GetMapping( "/getCount" )
    ResponseEntity<SuccessResponse<Integer>> getQuizCount();
}
