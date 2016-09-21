package com.augmentum.oes.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.augmentum.oes.common.ActionConfig;
import com.augmentum.oes.common.ModelAndView;
import com.augmentum.oes.common.ResultConfig;
import com.augmentum.oes.common.ViewParameterConfig;
import com.augmentum.oes.util.SpringUtil;
import com.augmentum.oes.util.StringUtil;

public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String suffix = ".action";

    private Map<String,ActionConfig> actionConfigs = new HashMap<String,ActionConfig>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init ===================init  ");
        super.init(config);
        String suffixFromConf = config.getInitParameter("suffix");
        if (!StringUtil.isEmpty(suffixFromConf)) {
            suffix = suffixFromConf;
        }

        InputStream inputStream = null;
        try {

            inputStream = DispatcherServlet.class.getClassLoader().getResourceAsStream("action.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;

            builder = factory.newDocumentBuilder();

            Document document = builder.parse(inputStream);
            Element element = document.getDocumentElement();

            NodeList actionNodes = element.getElementsByTagName("action");

            if (actionNodes == null) {
                return;
            }
            int actionLength = actionNodes.getLength();
            for(int i = 0; i<actionLength; i++) {
                Element actionElement = (Element)actionNodes.item(i);
                ActionConfig actionConfig = new ActionConfig();

                String name = actionElement.getAttribute("name");
                System.out.println("name==="+name);
                actionConfig.setName(name);
                String clsName = actionElement.getAttribute("class");
                actionConfig.setClsName(clsName);
                String methodName = actionElement.getAttribute("method");
                actionConfig.setMethodName(methodName);

                String httpMethod = actionElement.getAttribute("httpMethod");
                if (StringUtil.isEmpty(httpMethod)) {
                    httpMethod = "GET";
                }

                String[] httpMethodArr = httpMethod.split(",");
                actionConfig.setHttpMethod(httpMethodArr);

                for(String httpMethodItem : httpMethodArr) {
                    System.out.println("put........");
                    actionConfigs.put(name + suffix + "#" + httpMethodItem.toUpperCase(), actionConfig);
                }

                NodeList resultNodes = actionElement.getElementsByTagName("result");
                if(resultNodes != null) {
                    int resultLength = resultNodes.getLength();
                    for(int j = 0; j<resultLength; j++) {
                        Element resultElement = (Element)resultNodes.item(j);
                        ResultConfig resultConfig = new ResultConfig();

                        String resultName = resultElement.getAttribute("name");
                        resultConfig.setName(resultName);

                        String resultView = resultElement.getAttribute("view");
                        resultConfig.setView(resultView);

                        String resultRedirect = resultElement.getAttribute("redirect");

                        if (StringUtil.isEmpty(resultRedirect)) {
                            resultRedirect = "false";
                        }

                        resultConfig.setRedirect(Boolean.parseBoolean(resultRedirect));

                        String viewParameter = resultElement.getAttribute("viewParameter");

                        if (! StringUtil.isEmpty(viewParameter)) {
                            String[] viewParameterArr = viewParameter.split(",");
                            for (String viewParameterItem : viewParameterArr) {
                                String[] viewParameterItemArr = viewParameterItem.split(":");
                                String key = viewParameterItemArr[0].trim();
                                String from = "attribute";
                                if (viewParameterItemArr.length == 2) {
                                    from = viewParameterItemArr[1].trim();
                                }
                                ViewParameterConfig viewParameterConfig = new ViewParameterConfig(key, from);
                                resultConfig.addViewParameterConfig(viewParameterConfig);
                            }
                        }
                        actionConfig.addResult(resultName, resultConfig);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public DispatcherServlet() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String requestUri = uri.substring(request.getContextPath().length()+1);
        System.out.println("requestUri======="+requestUri);
        if (request == null || "".equals(requestUri)) {
            requestUri = "LoginServlet.action";
        }

        String httpMethod = request.getMethod();

        ActionConfig actionConfig = actionConfigs.get(requestUri + "#" + httpMethod.toUpperCase());

        System.out.println("httpMethod====="+httpMethod);
        HttpSession session = request.getSession();

        if (actionConfig != null) {
            String clsName = actionConfig.getClsName();
            System.out.println("clsName====="+clsName);
            String methodName = actionConfig.getMethodName();
            System.out.println("methodName===="+methodName);
            try {
                //get class object
                Class<?>[] param = new Class[2];
                param[0] = Map.class;
                param[1] = Map.class;


                //get method

                //Object controller = BeanFactory.getInstance().getBean(clsName);
                Object controller = SpringUtil.getBean(clsName);
                Method method = controller.getClass().getMethod(methodName,param);

                //get session Map
                Map<String,Object> sessionMap = new HashMap<String,Object>();
                Enumeration<String> toSessionKeys = session.getAttributeNames();
                while (toSessionKeys.hasMoreElements()) {
                    String toKey = toSessionKeys.nextElement();
                    sessionMap.put(toKey, session.getAttribute(toKey));
                }

                //get requset Map
                Map<String,String> parameterMap = new HashMap<String,String>();
                Enumeration<String> toParameterKeys = request.getParameterNames();
                while (toParameterKeys.hasMoreElements()) {
                    String toKey = toParameterKeys.nextElement();
                    parameterMap.put(toKey, request.getParameter(toKey));
                }

                //invoke
                Object[] objs = new Object[2];
                objs[0] = parameterMap;
                objs[1] = sessionMap;

                ModelAndView  modelAndView = (ModelAndView)method.invoke(controller,objs);

                Map<String,Object> fromControllerSession = modelAndView.getSessions();
                Set<String> keys = fromControllerSession.keySet();
                for (String key : keys) {
                    session.setAttribute(key,fromControllerSession.get(key));
                }

                Map<String,Object> fromControllerRequest = modelAndView.getRequests();
                Set<String> keyRequests = fromControllerRequest.keySet();
                for (String key : keyRequests) {
                    session.setAttribute(key,fromControllerRequest.get(key));
                }

                String view = modelAndView.getView();
                System.out.println("view======="+view);
                ResultConfig resultConfig = actionConfig.getResult(view);
                //System.out.println("resultConfig===="+resultConfig.equals(null));

                if (resultConfig == null) {
                    if (modelAndView.isRedirect()) {
                        response.sendRedirect(view);
                    } else {
                        request.getRequestDispatcher(view).forward(request, response);
                    }
                } else {
                    //String resultView = request.getContextPath() + "/" + resultConfig.getView();
                    String resultView = resultConfig.getView();
                    if (resultConfig.isRedirect()) {
                        List<ViewParameterConfig> viewParameterConfigs = resultConfig.getViewParameterConfigs();
                        if (viewParameterConfigs == null || viewParameterConfigs.isEmpty()) {
                            response.sendRedirect(resultView);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            for (ViewParameterConfig viewParameterConfig : viewParameterConfigs) {
                                String name = viewParameterConfig.getName();
                                String from = viewParameterConfig.getFrom();
                                String value = "";
                                if ("attribute".equals(from)) {
                                    value = (String) request.getAttribute(name);
                                } else if ("parameter".equals(from)) {
                                    value = request.getParameter(name);
                                } else if ("session".equals(from)) {
                                    value = (String) session.getAttribute(name);
                                } else {
                                    value = (String) request.getAttribute(name);
                                }

                                if (!StringUtil.isEmpty(value)) {
                                    sb.append(name + "=" + value + "&");
                                }
                            }

                            if (resultView.indexOf("?") > 0) {
                                resultView = resultView + "&" + sb.toString();
                            } else {
                                resultView = resultView + "?" + sb.toString();
                            }
                                response.sendRedirect(resultView);
                        }
                    } else {
                        request.getRequestDispatcher(resultConfig.getView()).forward(request, response);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(500 );
            }
        } else {
            response.sendError(404);
        }
    }
}
