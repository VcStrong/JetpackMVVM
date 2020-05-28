package com.vc.wd.common.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * desc
 * author VcStrong
 * github VcStrong
 * date 2020/5/28 1:42 PM
 */
@Entity
public class UserInfo {
    //as-signable:允许使用其他地方分配的ID，默认false代表objectbox自动生成
    @Id(assignable = true)
    long userId;
    String headPic;
    String nickName;
    String phone;
    String sessionId;
    int sex;

    int status;//记录本地用户登录状态，用于直接登录和退出,1:登录，0：未登录或退出

    public UserInfo(long userId, String headPic, String nickName, String phone,
                    String sessionId, int sex, int status) {
        this.userId = userId;
        this.headPic = headPic;
        this.nickName = nickName;
        this.phone = phone;
        this.sessionId = sessionId;
        this.sex = sex;
        this.status = status;
    }

    public UserInfo() {
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getHeadPic() {
        return this.headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
