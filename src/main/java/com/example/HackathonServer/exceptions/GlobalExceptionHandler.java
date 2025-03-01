package com.example.HackathonServer.exceptions;


import com.example.HackathonServer.reponses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleUserNotFound(UserNotFoundException e) {
        return ApiResponse.onError(e.getMessage());
    }
    @ExceptionHandler(PostNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object>handlePostNotFound(PostNotFoundException e){
        return ApiResponse.onError(e.getMessage());
    }
    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object>handleCommentNotFound(CommentNotFoundException e){
        return ApiResponse.onError(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleAllErrors(Exception ex) {
        return ApiResponse.onError(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
