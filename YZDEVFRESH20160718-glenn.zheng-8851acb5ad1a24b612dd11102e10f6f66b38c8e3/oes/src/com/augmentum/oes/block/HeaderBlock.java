package com.augmentum.oes.block;

import javax.servlet.jsp.PageContext;

import com.augmentum.oes.common.BlockAbstract;

public class HeaderBlock extends BlockAbstract {

    @Override
    protected void execute(PageContext pageContext) {
        /*HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = AppContext.getAppContext().getUser();
        user.setUserName(userName);
        user.setPassword(password);
        user.setPassword(null);
        pageContext.getSession().setAttribute("user", user);*/
    }
}

