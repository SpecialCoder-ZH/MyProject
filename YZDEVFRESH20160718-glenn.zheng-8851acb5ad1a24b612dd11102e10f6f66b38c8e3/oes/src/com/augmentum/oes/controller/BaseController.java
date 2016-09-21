package com.augmentum.oes.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.augmentum.oes.AppContext;
import com.augmentum.oes.model.User;
import com.augmentum.oes.util.PathUtil;
import com.augmentum.oes.util.SessionUtil;

public abstract class BaseController {

    private final Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        logger.error(e.getMessage(), e);
        ModelAndView modelAndview = new ModelAndView(new RedirectView(AppContext.getContextPath() + "/static/500.html"));
        return modelAndview;
    }

    protected User getUser() {
        return AppContext.getAppContext().getUser();
    }

    public String getUserName() {
        User user = getUser();
        if (user != null) {
            return user.getUserName();
        }
        return "";
    }

    public String getUserId() {
        User user = getUser();
        if (user != null) {
            return user.getId();
        }
        return "";
    }

    protected RedirectView getRedirectView(String path) {
        return new RedirectView(PathUtil.getFullPath(path));
    }

    protected void addSession(String key, Object object) {
        SessionUtil.addSession(key, object);
    }

    protected void getSession(String key) {
        SessionUtil.getSession(key);
    }

    protected void removeSession(String key) {
        SessionUtil.removeSession(key);
    }

    protected void invalidate() {
        SessionUtil.invalidate();
    }
}
