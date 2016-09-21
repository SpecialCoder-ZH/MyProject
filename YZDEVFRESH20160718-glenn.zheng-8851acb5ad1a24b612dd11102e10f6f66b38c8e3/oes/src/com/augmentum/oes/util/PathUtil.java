package com.augmentum.oes.util;

import com.augmentum.oes.AppContext;

public class PathUtil {

    public static String getFullPath(String path) {
        if (path == null) {
            path = "";
        }

        return AppContext.getContextPath() + "/" + path;
    }

}
