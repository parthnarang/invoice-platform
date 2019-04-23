package com.billt.core.merchantpanel.model;

import com.billt.core.merchantpanel.Entities.MenuItemEntity;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class MenuWrapper {

    HashMap<String,List<MenuItemEntity>> menuMap;
}
