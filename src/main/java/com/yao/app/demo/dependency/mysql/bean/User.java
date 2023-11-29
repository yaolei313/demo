package com.yao.app.demo.dependency.mysql.bean;

/**
 * 数据库表：user
 * 数据库注释: 
 * 
 * @author yao
 * @date 2023-11-28 20:01:16
 *
 */
public class User {
    /**
     * <pre>
     * 
     * 表字段 : user.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 昵称
     * 表字段 : user.nickname
     * </pre>
     */
    private String nickname;

    /**
     * <pre>
     * 邮箱
     * 表字段 : user.email
     * </pre>
     */
    private String email;

    /**
     * <pre>
     * 状态
     * 表字段 : user.status
     * </pre>
     */
    private Byte status;

    /**
     * <pre>
     * 创建时间
     * 表字段 : user.create_time
     * </pre>
     */
    private Long createTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : user.update_time
     * </pre>
     */
    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}