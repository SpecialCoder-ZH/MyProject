package com.augmentum.oes.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.RedirectView;

import com.augmentum.oes.AppContext;
import com.augmentum.oes.Constants;
import com.augmentum.oes.exception.ParameterException;
import com.augmentum.oes.exception.ServiceException;
import com.augmentum.oes.model.User;
import com.augmentum.oes.service.UserService;
import com.augmentum.oes.service.impl.UserServiceImpl;
import com.augmentum.oes.util.StringUtil;

@Controller
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    private final String LOGIN_JSP = "login";
    private final String LOGOUT_JSP = "logout";
    private final String SHOW_USER_JSP = "/user/showUser";
    private final String CHANGE_PASSWORD_JSP = "/user/changePassword";
    private final String SHOW_USER_PAGE = "showUser";
    private final String QUESTION_LIST_PAGE = "ToQuestionManager";
    private  final Logger logger = Logger.getLogger(UserServiceImpl.class);

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="ToLogin", method=RequestMethod.GET)
    public  ModelAndView toLogin(@RequestParam(value="go", defaultValue="")String go) {

        User user = AppContext.getAppContext().getUser();

        ModelAndView modelView = new ModelAndView();
        if (user != null) {
            modelView.setView(new RedirectView(AppContext.getContextPath()+ "/" + QUESTION_LIST_PAGE));
        } else {
            modelView.addObject("go", go);
            modelView.setViewName(LOGIN_JSP);
        }
        return modelView;
    }

    @RequestMapping(value="SaveLogin", method=RequestMethod.POST)
    public ModelAndView saveLogin(
                                  @RequestParam(value="userName", defaultValue="")String userName,
                                  @RequestParam(value="password", defaultValue="")String password,
                                  @RequestParam(value="go", defaultValue="")String go,
                                  @RequestParam(value="queryString", defaultValue="")String queryString,
                                  @RequestParam(value="rememberMe", defaultValue="")String rememberMe
                                  ) {
        ModelAndView modelView = new ModelAndView();
        try {
            User user = null;
            user = userService.login(userName, password);
            user.setUserName(userName);
            user.setPassword(password);
            user.setPassword(null);
            this.addSession("user", user);
            this.addSession("langType", "chinese");
            if (rememberMe.equals("remember")) {
                Cookie cookie = new Cookie("userNameAndPassword", userName+"="+ password);
                cookie.setMaxAge(24*60*60);
                HttpServletResponse response = (HttpServletResponse) AppContext.getAppContext().getObject(Constants.APP_CONTEXT_RESPONSE);
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("userNameAndPassword", null);
                cookie.setMaxAge(0);
            }

            if (!StringUtil.isEmpty(queryString)) {
                if (queryString.startsWith("#")) {
                    queryString = queryString.substring(1);
                }
                go = go + "?" + queryString;
            }
            if (StringUtil.isEmpty(go)) {
                modelView.setView(new RedirectView(AppContext.getContextPath() + "/" + QUESTION_LIST_PAGE));
            } else {
                modelView.setView(new RedirectView(AppContext.getContextPath() + "/" + go));
            }
            logger.info("userName->" + user.getUserName()+" :login the system");
        } catch (ParameterException parameterException) {
            Map<String,String> errorMessage = parameterException.getErrorFields();
            modelView.addObject(Constants.ERROR_MESSAGE, errorMessage);
            modelView.setViewName(LOGIN_JSP);
            logger.info("username or password is null",parameterException);
        }catch (ServiceException e) {
            modelView.addObject(Constants.MESSAGE, e.getMessage());
            modelView.setViewName(LOGIN_JSP);
            logger.info("username or password doesn't exist!",e);
        }
        return modelView;
    }

    @RequestMapping(value="Logout", method=RequestMethod.GET)
    public  ModelAndView logout () {
        ModelAndView modelView = new ModelAndView();
        modelView.addObject("exitMessage", "SUCCESS EXIT");
        this.removeSession("user");
        this.invalidate();
        modelView.setViewName(LOGOUT_JSP);
        return modelView;
    }

    @RequestMapping(value="showUser", method=RequestMethod.GET)
    public  ModelAndView showUser () {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName(SHOW_USER_JSP);
        return modelView;
    }

    @RequestMapping(value="toChangePassword", method=RequestMethod.GET)
    public  ModelAndView toChangePassword () {
        ModelAndView modelView = new ModelAndView();
        String userName = this.getUserName();
        modelView.addObject("userName", userName);
        modelView.setViewName(CHANGE_PASSWORD_JSP);
        return modelView;
    }

    @RequestMapping(value="changePassword", method=RequestMethod.POST)
    public  ModelAndView changePassword (
            @RequestParam(value="id", defaultValue="")String id,
            @RequestParam(value="password", defaultValue="")String password
                                         ) {
        ModelAndView modelView = new ModelAndView();
        try {
            userService.changePassword(id, password);
            this.addSession("SUCCESS", "SUCCESS CHANGE PASSWORD");
            modelView.setView(this.getRedirectView(SHOW_USER_PAGE));
        } catch (ParameterException parameterException) {
            Map<String,String> errorMessage = parameterException.getErrorFields();
            modelView.addObject(Constants.ERROR_MESSAGE, errorMessage);
            modelView.setViewName(SHOW_USER_JSP);
            logger.info(" password can't be null",parameterException);
        }
        return modelView;
    }

    @RequestMapping(value="changeLangType", method = {RequestMethod.GET})
    public ModelAndView changeLangType(
                                       @RequestParam(value="langType", defaultValue="english") String langType,
                                       HttpServletRequest request
                                       ){
            ModelAndView modelView = new ModelAndView();
            System.out.println("requestURL--->" + request.getRequestURL());
            System.out.println("requestURI--->" + request.getRequestURI());
            System.out.println("requestURI--->" + request.getQueryString());
            if (langType.equals("chinese")) {
                this.addSession("langType", "chinese");
                Locale locale = new Locale("zh", "CN");
                this.addSession(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            } else if (langType.equals("english")){
                this.addSession("langType", "english");
                Locale locale = new Locale("en", "US");
                this.addSession(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            } else {
                this.addSession("langType", "english");
                Locale locale = new Locale("en", "US");
                this.addSession(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            }
            modelView.setView(new RedirectView(AppContext.getContextPath()+ "/" + QUESTION_LIST_PAGE));
            return modelView;
    }
}
