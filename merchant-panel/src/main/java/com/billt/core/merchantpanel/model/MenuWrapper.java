package com.billt.core.merchantpanel.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class MenuWrapper {

    HashMap<String, List<String>> menuMap;
}
