package com.billt.core.merchantpanel.model;

import lombok.Data;

@Data
public class Category {

  long id;
  String categoryname;

  public Category(long id , String categoryname){
      this.id = id;
      this.categoryname = categoryname;
  }


}
