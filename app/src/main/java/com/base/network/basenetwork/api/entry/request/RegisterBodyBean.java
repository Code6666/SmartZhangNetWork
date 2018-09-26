package com.base.network.basenetwork.api.entry.request;


public class RegisterBodyBean {
    /**
     * phone(String)*：注册手机号
     * password(String)*：密码
     * invitationCode(String):邀请码
     * appMsgId(String)*:app推送标识
     * curMid(String)*:当前设备唯一标识
     * sourceFrom(int)*:用户来源: 1 ANDROID-APP;2 IOS-APP 3 微信公众号; 4 微信小程序 ; 5 PC-WEB
     * lang(string)：语言:中文简体(zh_CN)、中文繁体(zh_TW)、英文(en_US)
     */

    String phone;
    String areaCode;
    String password;
    String appMsgId;
    String curMid;
    int sourceFrom;
    String lang;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppMsgId() {
        return appMsgId;
    }

    public void setAppMsgId(String appMsgId) {
        this.appMsgId = appMsgId;
    }

    public String getCurMid() {
        return curMid;
    }

    public void setCurMid(String curMid) {
        this.curMid = curMid;
    }

    public int getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(int sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


}
