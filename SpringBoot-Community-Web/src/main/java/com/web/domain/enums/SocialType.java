package com.web.domain.enums;

/* Information about Social Medias */
public enum SocialType {
    FACEBOOK("facebook"),
    GOOGLE("google"),
    KAKAO("kakao");

    private final String ROLE_PREFIX = "ROLE_";
    private String name;

    SocialType(String name) {
        this.name = name;
    }

    // getRoleType : Generate a String like 'ROLE_*'
    public String getRoleType() {
        return ROLE_PREFIX + name.toUpperCase();
    }

    public String getValue() {
        return name;
    }

    public boolean isEquals(String authroity) {
        return this.getRoleType().equals(authroity);
    }
}
