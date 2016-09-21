package com.augmentum.oes.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;

import com.augmentum.oes.common.BlockAbstract;
import com.augmentum.oes.util.SpringUtil;

public class BlockTaglib extends TagSupport {

     private static final long serialVersionUID = 9104506056700300305L;

     private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        ApplicationContext  ctx = SpringUtil.getApplicationContext();
        BlockAbstract block = (BlockAbstract)ctx.getBean(name);
        String content = block.displayBlock(pageContext);
        JspWriter out = pageContext.getOut();
        try {
            out.println(content);
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
