package com.server.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liqiangpeng on 2017/11/29.
 */
public class Company implements Serializable {

    private String id;

    private String name;

    private String simpleName;

    private String code;

    private Integer sort;

    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", simpleName='" + simpleName + '\'' +
                ", code='" + code + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
