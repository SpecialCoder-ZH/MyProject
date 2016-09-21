package com.augmentum.oes.util;

public class StringUtil {

    public static boolean isEmpty(Object data) {
         if (data == null || "".equals(data)) {
             return true;
         }
         return false;
    }
}
