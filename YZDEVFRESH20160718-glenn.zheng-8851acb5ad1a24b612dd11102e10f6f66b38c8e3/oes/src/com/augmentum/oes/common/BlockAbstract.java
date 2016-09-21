package com.augmentum.oes.common;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import com.augmentum.oes.util.StringUtil;

public abstract class BlockAbstract {

    private static Logger logger = Logger.getLogger(BlockAbstract.class);

    public String template;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String displayBlock(PageContext pageContext) {
        execute(pageContext);
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Writer body = new StringWriter();
        try {
            if (!StringUtil.isEmpty(template)) {
                pageContext.pushBody(body);
                pageContext.include(template);
                pageContext.popBody();
                return body.toString();
            }
        } catch (Exception e) {
            logger.error("block error",e);
        } finally {
            try {
                body.close();
            } catch (IOException e) {
                logger.error("body colse error",e);
            }
        }
        return "";
      }
    abstract protected void execute(PageContext pageContext);
}
