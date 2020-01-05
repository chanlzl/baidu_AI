package com.baidu.ai.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "config_item", schema = "ai", catalog = "")
public class ConfigItem {
    private int id;
    private String itemName;
    private String itemValue;
    private String itemDesc;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "item_name")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "item_value")
    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    @Basic
    @Column(name = "item_desc")
    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigItem that = (ConfigItem) o;
        return id == that.id &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(itemValue, that.itemValue) &&
                Objects.equals(itemDesc, that.itemDesc) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    public ConfigItem(String itemName, String itemValue, String itemDesc) {
        this.itemName = itemName;
        this.itemValue = itemValue;
        this.itemDesc = itemDesc;
    }

    public ConfigItem() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, itemValue, itemDesc, createTime, updateTime);
    }
}
