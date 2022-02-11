package com.web.controller;

import com.web.annotation.SocialUser;
import com.web.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /*
    There are some problems with this loginComplete()
    First, there are some unnecessary logics
    Second, this can be used for only facebook authentication
    So, We'll make an annotation and use the AOP

    // Set redirecting URL after authentication succeeds
    @GetMapping(value = "/{facebook|google|kakao|}/complete")
    public String loginComplete(HttpSession session) {
        // Get authenticated information from SecurityContextHolder for OAuth2Authentication type
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        // Get personal information by Map type with getDetails()
        Map<String, String> map = (HashMap<String, String>) authentication.getUserAuthentication().getDetails();
        // Generate user information with User builder
        session.setAttribute("user", User.builder()
                .name(map.get("name"))
                .email(map.get("email"))
                .principal(map.get("id"))
                .socialType(SocialType.FACEBOOK)
                .createdDate(LocalDateTime.now())
                .build());

        return "redirect:/board/list";
    }
     */

    @GetMapping(value = "/{facebook-google-kakao}/complete")
    // @SocialUser : An annotation to be created, it uses HandlerMethodArgumentResolver
    public String loginComplete(@SocialUser User user) {
        return "redirect:/board/list";
    }
}
