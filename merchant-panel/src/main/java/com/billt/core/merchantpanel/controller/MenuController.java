package com.billt.core.merchantpanel.controller;

import com.billt.core.merchantpanel.model.MenuItem;
import com.billt.core.merchantpanel.model.MenuWrapper;
import com.billt.core.merchantpanel.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/panel/")
@Controller
public class MenuController {

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    MenuService menuService;


    @PostMapping("/addMenuIteminBulk")
    public String addMenuIteminBulk(@ModelAttribute MenuItem menuItem) {
        menuService.menuFileRead(menuItem);
        return "AlertSystem/addNewIssue";
    }

    @PostMapping("/addMenuItem")
    public String addMenuItem(@ModelAttribute MenuItem menuItem) {
        log.info("Receiving request to create menu item");
        menuService.addNewmenuItem(menuItem);
        log.info("Creation for request is done");
        return "success";
    }
    @GetMapping("/getMenu")
    public String getMenu(HttpSession session, Model model) {
        HashMap<String, List<String>> map = new HashMap<>();

        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("abc");
        arrayList1.add("abc");
        arrayList1.add("abc");
        arrayList1.add("abc");

        map.put("efg",arrayList1);

        MenuWrapper menuWrapper = new MenuWrapper();
        menuWrapper.setMenuMap(map);

        model.addAttribute("con", "menu");
        model.addAttribute("map", menuWrapper);
        return "layout";
    }



}



