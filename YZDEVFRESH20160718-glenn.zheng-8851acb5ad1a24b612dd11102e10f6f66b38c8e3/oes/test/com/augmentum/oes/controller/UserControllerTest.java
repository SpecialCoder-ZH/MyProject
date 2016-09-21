package com.augmentum.oes.controller;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.augmentum.oes.AppContext;
import com.augmentum.oes.Constants;
import com.augmentum.oes.model.User;
import com.augmentum.oes.util.PathUtil;
import com.augmentum.oes.util.SessionUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:question-mvc.xml"})
public class UserControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Before
    public void setUp() throws Exception {
        AppContext appContext = AppContext.getAppContext();
        appContext.addObject(Constants.APP_CONTEXT_SESSION, new MockHttpSession());
    }

    @After
    public void tearDown() throws Exception {
        AppContext appContext= AppContext.getAppContext();
        appContext.clear();
    }

    @Test
    public void testSaveLogin() {
        UserController userController = (UserController) this.applicationContext.getBean("userController");
        ModelAndView modelAndView = userController.saveLogin("glenn", "000", "", "", "");
        RedirectView redirectView = (RedirectView)modelAndView.getView();
        Assert.assertEquals(PathUtil.getFullPath("ToQuestionManager") , redirectView.getUrl());
        Assert.assertNotNull(SessionUtil.getSession("user"));

        modelAndView = userController.saveLogin("glenn", "000", "QuestionManager", "#forwardId=2", "unRemember");

        modelAndView = userController.saveLogin("glenn", "123", "QuestionManager", "#forwardId=2", "");
        Assert.assertEquals(modelAndView.getViewName(),"login");
    }

    @Test
    public void testSaveLoginException() {
        UserController userController = (UserController) this.applicationContext.getBean("userController");
        ModelAndView modelAndView = userController.saveLogin("glenn", "", "QuestionManager", "#forwardId=2", "");
        Assert.assertEquals(modelAndView.getViewName(),"login");
    }

    @Test
    public void testSaveLoginUserNameNullException() {
        UserController userController = (UserController) this.applicationContext.getBean("userController");
        ModelAndView modelAndView = userController.saveLogin("", "", "QuestionManager", "#forwardId=2", "remember");
        Assert.assertEquals(modelAndView.getViewName(),"login");
    }

    @Test
    public void testToLogin() {
        UserController userController = (UserController) this.applicationContext.getBean("userController");
        ModelAndView modelAndView = userController.toLogin("");
        Assert.assertEquals(modelAndView.getModel().get("go"),"");
        Assert.assertEquals(modelAndView.getViewName(),"login");

        AppContext appContext = AppContext.getAppContext();
        User user = new User();
        user.setId("1");
        user.setUserName("Glenn.Zheng");
        HttpSession session = new MockHttpSession();
        session.setAttribute("user", user);
        appContext.addObject(Constants.APP_CONTEXT_SESSION, session);
        appContext.addObject("user", user);
        modelAndView = userController.toLogin("");
        //Assert.assertEquals(modelAndView.getView(), new RedirectView(AppContext.getContextPath()+ "/ToQuestionManager"));
    }

    @Test
    public void testLogout() {
        UserController userController = (UserController) this.applicationContext.getBean("userController");
        ModelAndView modelAndView = userController.logout();
        Assert.assertEquals(modelAndView.getModel().get("exitMessage"),"SUCCESS EXIT");
        Assert.assertNull(SessionUtil.getSession("user"));
        Assert.assertEquals(modelAndView.getViewName(),"logout");
    }
}
