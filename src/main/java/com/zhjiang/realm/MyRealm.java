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
 * @Description �Զ���realm
 * @author Thales
 *
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private BloggerService bloggerService;

    /**
     * Ϊ��ǰ��½���û������ɫ��Ȩ��
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        // ������˲�����Ŀ��û����һ��ģ���Ϊ��һ���û�
        return null;
    }

    /**
     * ��ǰ��½���û����������֤
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal(); // ��ȡ�û���
        System.out.println("密码:" + token.getCredentials());
        Blogger blogger = bloggerService.getByUsername(username); // �����û��������ݿ��в�ѯ��������Ϣ

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
