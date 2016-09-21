package com.augmentum.oes.controller;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.augmentum.oes.AppContext;
import com.augmentum.oes.Constants;
import com.augmentum.oes.model.Exam;
import com.augmentum.oes.model.PageInfo;
import com.augmentum.oes.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:question-mvc.xml"})
public class ExamControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Before
    public void setUp() throws Exception {
        AppContext appContext = AppContext.getAppContext();
        User user = new User();
        user.setId("1");
        user.setUserName("Glenn.Zheng");
        HttpSession session = new MockHttpSession();
        session.setAttribute("user", user);
        appContext.addObject(Constants.APP_CONTEXT_SESSION, session);
        appContext.addObject("user", user);
    }

    @After
    public void tearDown() throws Exception {
        AppContext appContext= AppContext.getAppContext();
        appContext.clear();
    }

    @Test
    public void testCreate() throws Exception {
        ExamController examController = (ExamController)this.applicationContext.getBean("examController");
       // ExamController examController = (ExamController)SpringUtil.getApplicationContext().getBean("examController");
        Exam exam = new Exam();
        exam.setCreator("glenn");
        exam.setDescription("this is test exam");
        exam.setDuration("60");

        exam.setExamName("Exam name");
        exam.setFullScore("100");
        exam.setPassCriteria("60");
        exam.setQuestionPoints("5");
        exam.setQuestionQuantity("20");
        for (int i = 0; i < 200; i++){
            exam.setExamId("E000"+i);
            ModelAndView modelAndView = examController.save("2016-08-20", "5", "22", exam);
        }
    }

    @Test
    public void testExamList() throws Exception {
        //ExamController examController = (ExamController)this.applicationContext.getBean("examController");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPagenum("1");
        pageInfo.setPagesize("10");
        pageInfo.setKeywords("This");
        //examController.examList(pageInfo);
    }
}
