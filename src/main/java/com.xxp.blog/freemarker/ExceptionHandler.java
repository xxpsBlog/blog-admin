package com.xxp.blog.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Writer;


public class ExceptionHandler
        implements TemplateExceptionHandler {

    private static Logger log = LogManager.getLogger(ExceptionHandler.class);

    public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
        log.error("freemarker:", te);
        try {
            TemplateExceptionHandler.HTML_DEBUG_HANDLER.handleTemplateException(te, env, out);
        } catch (Exception e) {
        }
    }
}