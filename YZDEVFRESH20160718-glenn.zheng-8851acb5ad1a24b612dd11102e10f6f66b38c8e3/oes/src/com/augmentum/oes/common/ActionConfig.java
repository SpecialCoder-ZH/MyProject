package com.augmentum.oes.common;

import java.util.HashMap;
import java.util.Map;

public class ActionConfig {

    private String name;
    private String clsName;
    private String methodName;
    private String[] httpMethod;

    private Map<String, ResultConfig> results = new HashMap<String, ResultConfig>();

    public ActionConfig() {
    }

    public ResultConfig getResult(String resultKey) {
        return results.get(resultKey);
    }

    public Map<String, ResultConfig> getResults() {
        return results;
    }

    public void setResults(Map<String, ResultConfig> results) {
        this.results = results;
    }

    public ActionConfig(String clsName, String methodName) {
        this.clsName = clsName;
        this.methodName = methodName;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String[] getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String[] httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addResult(String resultName, ResultConfig resultConfig) {
        results.put(resultName, resultConfig);
    }
}
