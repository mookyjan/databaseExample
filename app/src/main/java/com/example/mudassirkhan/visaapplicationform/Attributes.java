package com.example.mudassirkhan.visaapplicationform;

/**
 * Created by waqas on 1/10/17.
 */

public class Attributes {
   public static enum Type {TEXT, INTEGER , Boolean ,LONG, Decimal , Float ,BLOB, PRIMARY_KEY};
   private String columnName;
   private Type columnType;

   public Attributes(String columnName, Type columnType) {
       this.columnName = columnName;
       this.columnType = columnType;
   }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Type getColumnType() {
        return columnType;
    }

    public void setColumnType(Type columnType) {
        this.columnType = columnType;
    }
}
