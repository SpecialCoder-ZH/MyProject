package com.augmentum.oes;

import java.util.HashMap;
import java.util.Map;

import com.augmentum.oes.model.User;


public class AppContext {

    private static ThreadLocal<AppContext> appContextMap = new ThreadLocal< AppContext>();

    private static Map<String, Object> objects = new HashMap<String, Object>();

    private static String contextPath;

    public static String getContextPath() {
        return contextPath;
    }

    public Map<String, Object> getObjects() {
        return objects;
    }

    public static void setContextPath(String contextPath) {
        if (AppContext.contextPath == null) {
            AppContext.contextPath = contextPath;
        }
    }

    public void setObjects(Map<String, Object> objects) {
        if (objects == null) {
            objects = new HashMap<String, Object>();
        }
        this.objects = objects;
    }

    public void addObject(String key, Object object) {
        objects.put(key, object);
    }

    private AppContext() {}

    /**
     * Get CurrentThread's AppContext
     * Every Thread has it's own AppContext;
     * @return AppContext
     */
    public static AppContext getAppContext() {
        AppContext appContext = appContextMap.get();
        if (appContext == null) {
            appContext = new AppContext();
            appContextMap.set(appContext);
        }
        return appContextMap.get();
    }

    public Object getObject(String key) {
        return objects.get(key);
    }

    public void clear() {
        objects.clear();
    }

    public User getUser() {
        return (User) objects.get(Constants.USER);
    }

    public String getUsername(){
        User user = (User) objects.get(Constants.USER);
        if (user != null) {
            return user.getUserName();
        }
        return "";
    }

    public String getUserId(){
        User user = (User) objects.get(Constants.USER);
        if (user != null) {
            return user.getId();
        }
        return "";
    }
}
