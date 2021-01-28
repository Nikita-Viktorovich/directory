package org.example.Entity;

public enum Type{
   STRING("VARCHAR(45)"),
   INTEGER("INTEGER"),
   DOUBLE("DOUBLE"),
   BOOLEAN("BOOLEAN" ),
   DATE("DATE");
   public String sql;

   Type(String sqlType){
      this.sql=sqlType;
   }

   public String getSql() {
      return sql;
   }
}
