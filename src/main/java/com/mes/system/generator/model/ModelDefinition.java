package com.mes.system.generator.model;

import java.util.List;

/**
 * 仅用于生成器内部的数据结构描述，不会生成到业务代码目录
 */
public class ModelDefinition {
    private String tableName;
    private List<ModelField> fields;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ModelField> getFields() {
        return fields;
    }

    public void setFields(List<ModelField> fields) {
        this.fields = fields;
    }
}