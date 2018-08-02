package com.zhjiang.entity;


/**
 * @Description ����ʵ��
 * @author Thales
 *
 */
public class Blogger {

    private Integer id;
    private String username; //����
    private String password; //����
    private String profile; //������Ϣ
    private String nickname; //�����ǳ�
    private String sign; //����ǩ��
    private String imagename; //ͷ��·��


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getImagename() {
        return imagename;
    }
    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

}
