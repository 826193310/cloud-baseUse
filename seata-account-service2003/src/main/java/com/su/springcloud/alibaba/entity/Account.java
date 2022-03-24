package com.su.springcloud.alibaba.entity;

/**
 * @Classname Storage
 * @Description TODO
 * @Date 2020/11/30 22:10
 * @Created by SGZ
 */
public class Account {
    private Long id;

    // 产品ID
    private Long userId;

    // 总额度
    private Integer total;

    // 已用额度
    private Integer used;

    // 剩余额度
    private Integer residue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getResidue() {
        return residue;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
    }
}
