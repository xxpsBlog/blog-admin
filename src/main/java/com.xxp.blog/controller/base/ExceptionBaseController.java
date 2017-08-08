package com.xxp.blog.controller.base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class ExceptionBaseController {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionBaseController.class);

    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public String handleTypeMismatchException(TypeMismatchException ex) {
        LOGGER.error("数据库异常：", ex);
        return ex.getMessage();
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseBody
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        LOGGER.error("数据库异常：", ex);
        return ex.getMessage();
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        LOGGER.error("handleException", ex);
        return ex.getMessage();
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public String handleException(Exception ex) {
        LOGGER.error("handleException", ex);
        return ex.getMessage();
    }
}