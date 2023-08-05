package com.example.strategy.impl;

import com.example.entity.Oauth2ThirdAccount;
import com.example.strategy.Oauth2UserConverterStrategy;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.example.constant.SecurityConstants.THIRD_LOGIN_GITEE;

/**
 * 转换通过码云登录的用户信息
 *
 * @author vains
 */
@Component(THIRD_LOGIN_GITEE)
public class GiteeUserConverter implements Oauth2UserConverterStrategy {

    @Override
    public Oauth2ThirdAccount convert(OAuth2User oAuth2User) {
        // 获取三方用户信息
        Map<String, Object> attributes = oAuth2User.getAttributes();
        // 转换至Oauth2ThirdAccount
        Oauth2ThirdAccount thirdAccount = new Oauth2ThirdAccount();
        thirdAccount.setUniqueId(String.valueOf(attributes.get("id")));
        thirdAccount.setThirdUsername(oAuth2User.getName());
        thirdAccount.setType(THIRD_LOGIN_GITEE);
        thirdAccount.setBlog(String.valueOf(attributes.get("blog")));
        // 设置基础用户信息
        thirdAccount.setName(oAuth2User.getName());
        thirdAccount.setAvatarUrl(String.valueOf(attributes.get("avatar_url")));
        return thirdAccount;
    }
}
