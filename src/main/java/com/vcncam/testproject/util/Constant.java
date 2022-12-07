package com.vcncam.testproject.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class Constant {
    
    public static class Roles{
        public static String ADMIN = "ADMIN";
        public static String EDITOR = "EDITOR";
        public static String USER = "USER";
    }
    
    public static class ResponseCode {
        public static final String SUCCESS = "000";
        public static final String ERROR = "99";
    }
    
    public static class ResponseMessage {
        public static final String SUCCESS = "SUCCESS";
        public static final String ERROR = "FAILED";
    }
}
