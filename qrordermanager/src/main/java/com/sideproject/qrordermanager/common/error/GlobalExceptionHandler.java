package com.sideproject.qrordermanager.common.error;

import com.sideproject.qrordermanager.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ViewCustomException.class)
    protected ModelAndView handleViewCustomException(ViewCustomException ex) {
        return createViewErrorResponse(ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(value = ApiCustomException.class)
    protected ResponseEntity<ApiResponse<?>> handleApiCustomException(ApiCustomException ex) {
        return createApiErrorResponse(ex.getErrorCode());
    }

    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class}) // 404 Not Found 처리
    protected Object handleNotFoundException(HttpServletRequest request) {
        return handleCustomError(ErrorCode.NOT_FOUND, request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class) // 405 Method Not Allowed
    protected Object handleMethodNotSupported(HttpServletRequest request) {
        return handleCustomError(ErrorCode.NOT_SUPPORTED, request);
    }

    @ExceptionHandler(Exception.class) // 그 외 모든 예외 처리
    protected Object handleAllExceptions(Exception ex, HttpServletRequest request) {
        return handleCustomError(ErrorCode.ECT_ERROR, request); // 기본적으로 500 응답
    }

    private Object handleCustomError(ErrorCode errorCode, HttpServletRequest request) {
        if (request.getRequestURI().contains("api") || request.getRequestURI().contains("replace")) {
            return createApiErrorResponse(errorCode);
        } else {
            return createViewErrorResponse(errorCode.getHttpStatus());
        }
    }


    private ModelAndView createViewErrorResponse(int httpStatus) {
        ModelAndView mav = new ModelAndView("error"); // "error.html" 또는 "error.jsp"
        mav.setStatus(HttpStatusCode.valueOf(httpStatus));
        mav.addObject("httpStatus", httpStatus);
        return mav;
    }

    private ResponseEntity<ApiResponse<?>> createApiErrorResponse(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.error(errorCode));
    }
}
