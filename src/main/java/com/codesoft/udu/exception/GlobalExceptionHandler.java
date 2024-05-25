package com.codesoft.udu.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_PAGE = "error";
    private static final String ERROR_INTERNAL_SERVER_PAGE = "500";
    private static final String ERROR_NOT_FOUND_PAGE = "404";

    @ExceptionHandler(value = NullEntityReferenceException.class)
    public ModelAndView handleNullEntityReferenceException(NullEntityReferenceException ex) {
        ModelAndView mav = new ModelAndView(ERROR_INTERNAL_SERVER_PAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        mav.addObject("info", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException ex) {
        ModelAndView mav = new ModelAndView(ERROR_NOT_FOUND_PAGE, HttpStatus.NOT_FOUND);
        mav.addObject("info", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ModelAndView handleRuntimeException(IllegalArgumentException ex) {
        ModelAndView mav = new ModelAndView(ERROR_INTERNAL_SERVER_PAGE, HttpStatus.FORBIDDEN);
        mav.addObject("info", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException ex) {
        ModelAndView mav = new ModelAndView(ERROR_PAGE, HttpStatus.UNPROCESSABLE_ENTITY);
        mav.addObject("info", ex.getMessage());
        return mav;
    }

    @ExceptionHandler
    public ModelAndView handleException(Exception ex) {
        ModelAndView mav = new ModelAndView(ERROR_PAGE, HttpStatus.UNPROCESSABLE_ENTITY);
        mav.addObject("info", ex.getMessage());
        return mav;
    }
}
