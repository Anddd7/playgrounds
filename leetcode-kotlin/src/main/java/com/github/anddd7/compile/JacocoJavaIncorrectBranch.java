package com.github.anddd7.compile;

public class JacocoJavaIncorrectBranch {
    public String double_safe_call_operator(String str) {
        if (str != null) {
            String result = str.trim();
            if (result != null) {
                return result.toLowerCase();
            }
        }

        return null;
    }

    public String one_safe_call_operator(String str) {
        if (str == null)
            return null;

        return str.trim().toLowerCase();
    }
}
