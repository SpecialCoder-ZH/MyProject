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
import com.augmentum.oes.model.Question;
import com.augmentum.oes.model.User;
import com.augmentum.oes.util.PathUtil;
import com.augmentum.oes.util.SessionUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:question-mvc.xml"})
public class QuestionControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

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
    public void testSave() {
        QuestionController questionController = (QuestionController) this.applicationContext.getBean("questionController");
        Question question = new Question();
        question.setDisplayId("testSave");
        question.setQuestionContent("testSave");
        question.setOptionFour("A");
        question.setOptionOne("B");
        question.setOptionThree("C");
        question.setOptionTwo("D");
        question.setCorrectOption("B");
        question.setPreviousId("0");
        ModelAndView modelAndView = questionController.save(question);

        Assert.assertNotNull(modelAndView.getModel().get("questionList"));
        Assert.assertEquals(SessionUtil.getSession("SUCCESS_FLASH_MESSAGE"), "SUCCESS CREATE !");
        Assert.assertEquals(modelAndView.getViewName(),"/question/createQuestion");
    }

    @Test
    public void testSaveException() {
        QuestionController questionController = (QuestionController) this.applicationContext.getBean("questionController");
        Question question = new Question();
        question.setDisplayId("");
        question.setQuestionContent("");
        question.setOptionFour("");
        question.setOptionOne("");
        question.setOptionThree("");
        question.setOptionTwo("");
        question.setCorrectOption("");
        question.setPreviousId("");
        ModelAndView modelAndView = questionController.save(question);

        Assert.assertNotNull(modelAndView.getModel().get("question_error"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/createQuestion");
    }

    @Test
    public void testDelete() {
        QuestionController questionController = (QuestionController) this.applicationContext.getBean("questionController");
        ModelAndView modelAndView = questionController.delete("'Q0002','", "1");

        RedirectView redirectView = (RedirectView)modelAndView.getView();
        Assert.assertEquals(PathUtil.getFullPath("QuestionManager?forwardId=1&pagenum=1") , redirectView.getUrl());

    }

    @Test
    public void testEdit() {
        QuestionController questionController = (QuestionController) this.applicationContext.getBean("questionController");
        Question question = new Question();
        question.setDisplayId("testSave");
        question.setQuestionContent("testSave");
        question.setOptionFour("A");
        question.setOptionOne("B");
        question.setOptionThree("C");
        question.setOptionTwo("D");
        question.setCorrectOption("B");
        question.setPreviousId("0");
        ModelAndView modelAndView = questionController.edit("2420","questionList","10","10","10",question);
        Assert.assertEquals(modelAndView.getModel().get("pageSize"),"10");
        Assert.assertEquals(modelAndView.getModel().get("pagenum"),"10");
        Assert.assertNotNull(modelAndView.getModel().get("page"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/questionList");

    }

    @Test
    public void testEditElse() {
        QuestionController questionController = (QuestionController) this.applicationContext.getBean("questionController");
        Question question = new Question();
        question.setDisplayId("testSave");
        question.setQuestionContent("testSave");
        question.setOptionFour("A");
        question.setOptionOne("B");
        question.setOptionThree("C");
        question.setOptionTwo("D");
        question.setCorrectOption("B");
        question.setPreviousId("0");
        ModelAndView modelAndView = questionController.edit("2420","questionList","","","10",question);
        Assert.assertEquals(modelAndView.getViewName(),"/question/questionList");
    }

    @Test
    public void testEditException() {
        QuestionController questionController = (QuestionController) this.applicationContext.getBean("questionController");
        Question question = new Question();
        question.setDisplayId("");
        question.setQuestionContent("");
        question.setOptionFour("");
        question.setOptionOne("");
        question.setOptionThree("");
        question.setOptionTwo("");
        question.setCorrectOption("");
        question.setPreviousId("");
        ModelAndView modelAndView = questionController.edit("2420","questionList","","","10",question);
        Assert.assertNotNull(modelAndView.getModel().get("question_error"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/editQuestion");
    }

    @Test
    public void testToEdit() {
        QuestionController questionController = (QuestionController) this.applicationContext.getBean("questionController");
        ModelAndView modelAndView = questionController.toEdit("2420","");
        Assert.assertNotNull(modelAndView.getModel().get("question"));
        Assert.assertNotNull(modelAndView.getModel().get("pagenum"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/editQuestion");
    }

    @Test
    public void testList() {
        QuestionController questionController = (QuestionController) this.applicationContext.getBean("questionController");
        ModelAndView modelAndView = questionController.list("2","2","","","");
        Assert.assertNull( modelAndView.getModel().get("SUCCESS_FLASH_MESSAGE"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/createQuestion");

        modelAndView = questionController.list("1","2","","","");
        Assert.assertEquals( modelAndView.getModel().get("keywords"), "");
        Assert.assertEquals( modelAndView.getModel().get("pageSize"), "");
        Assert.assertNotNull(modelAndView.getModel().get("page"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/questionList");

        modelAndView = questionController.list("searchByKeyWord","","abc","10","");
        Assert.assertNotNull(modelAndView.getModel().get("page"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/questionList");
        Assert.assertEquals(modelAndView.getModel().get("pageSize"),"10");
        Assert.assertEquals(modelAndView.getModel().get("keywords"),"abc");

        modelAndView = questionController.list("reset","2","abc","10","");
        Assert.assertNotNull(modelAndView.getModel().get("page"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/questionList");
        Assert.assertNull(modelAndView.getModel().get("pageSize"));
        Assert.assertNull(modelAndView.getModel().get("keywords"));
        Assert.assertEquals(modelAndView.getModel().get("direction"),"ASC");

        modelAndView = questionController.list("decrese","2","abc","10","");
        Assert.assertNotNull(modelAndView.getModel().get("page"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/questionList");
        Assert.assertEquals(modelAndView.getModel().get("pageSize"),"10");
        Assert.assertEquals(modelAndView.getModel().get("keywords"),"abc");
        Assert.assertEquals(modelAndView.getModel().get("direction"), "DESC");

        modelAndView = questionController.list("1","2","","10","");
        Assert.assertNotNull(modelAndView.getModel().get("page"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/questionList");
    }

    @Test
    public void TestToList() {
        QuestionController questionController = (QuestionController) this.applicationContext.getBean("questionController");
        ModelAndView modelAndView = questionController.toList("");
        Assert.assertEquals(modelAndView.getModel().get("direction"), "ASC");
        Assert.assertNotNull(modelAndView.getModel().get("page"));
        Assert.assertEquals(modelAndView.getViewName(),"/question/questionList");
    }
}
