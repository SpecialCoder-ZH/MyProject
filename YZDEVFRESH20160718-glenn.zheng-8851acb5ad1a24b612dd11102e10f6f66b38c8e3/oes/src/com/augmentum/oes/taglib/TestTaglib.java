package com.augmentum.oes.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TestTaglib extends TagSupport {

    private static final long serialVersionUID = -4669988387345025895L;

    /**
     * SKIP_BODY means skip the content of tag
     */
    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    /**
     * EVAL_PAGE means execute the content of tag.
     */
    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
    }
}
