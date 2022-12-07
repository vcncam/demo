package com.vcncam.testproject.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class Common {
    public static String getCurrentUser() {
        try {
            return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
