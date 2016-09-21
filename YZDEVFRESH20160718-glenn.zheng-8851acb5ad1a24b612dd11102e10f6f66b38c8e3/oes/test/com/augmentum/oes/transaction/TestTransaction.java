package com.augmentum.oes.transaction;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.augmentum.oes.AppContext;
import com.augmentum.oes.Constants;
import com.augmentum.oes.controller.QuestionController;
import com.augmentum.oes.model.Question;
import com.augmentum.oes.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:question-mvc.xml"})
public class TestTransaction extends AbstractTransactionalJUnit4SpringContextTests {

    @Before
    public void setUp() throws Exception {
        AppContext appContext = AppContext.getAppContext();

        User user = new User();
        user.setId("123");
        user.setUserName("Glenn");

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
    public void testSave() {
        QuestionController questionController = (QuestionController) this.applicationContext.getBean("questionController");
        Question question = new Question();
        question.setDisplayId("testTransaction");
        question.setQuestionContent("testTransaction");
        question.setOptionFour("A");
        question.setOptionOne("B");
        question.setOptionThree("C");
        question.setOptionTwo("D");
        question.setCorrectOption("B");
        question.setPreviousId("0");
        questionController.save(question);
    }
}
