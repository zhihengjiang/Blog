package com.zhjiang.realm;

import javax.annotation.Resource;

import com.zhjiang.entity.Blogger;
import com.zhjiang.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 认证
 * @author Thales
 *
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private BloggerService bloggerService;

    /**
     * Ϊ
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        return null;
    }

    /**
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        System.out.println("密码:" + token.getCredentials());
        Blogger blogger = bloggerService.getByUsername(username);

        System.out.println(blogger.getUsername()+":"+blogger.getPassword());

        if (blogger != null) {
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
                    blogger.getUsername(), blogger.getPassword(), "MyRealm");
            return authcInfo;
        } else {
            return null;
        }

    }



}
