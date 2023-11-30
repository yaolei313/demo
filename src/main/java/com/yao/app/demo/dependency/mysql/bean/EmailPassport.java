package com.yao.app.demo.dependency.mysql.bean;

/**
 * 数据库表：email_passport 数据库注释:
 *
 * @author yao
 * @date 2023-11-29 22:55:14
 */
public class EmailPassport {

    /**
     * <pre>
     *
     * 表字段 : email_passport.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 邮箱
     * 表字段 : email_passport.email
     * </pre>
     */
    private String email;

    /**
     * <pre>
     * 用户id
     * 表字段 : email_passport.user_id
     * </pre>
     */
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}