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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/panel/")
@Controller
public class MenuController {

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    MenuService menuService;


    @PostMapping("menu/addMenuIteminBulk")
    public String addMenuIteminBulk(@ModelAttribute MenuItem menuItem) {
        menuService.menuFileRead(menuItem);
        return "AlertSystem/addNewIssue";
    }

    @PostMapping("menu/addMenuItem")
    public String addMenuItem(@ModelAttribute MenuItem menuItem) {

        log.info("Receiving request to create menu item");
        menuService.addNewmenuItem(menuItem);
        log.info("Creation for request is done");
        return "success";
    }

   @GetMapping("menu/view-menu")
    public String viewMenu(Model model) {
       HashMap<String, List<String>> map = new HashMap<>();

       ArrayList<String> arrayList1 = new ArrayList<>();
       arrayList1.add("Aloo and Dal ki Tikki");
       arrayList1.add("Cheese Balls");
       arrayList1.add("Veg Crispy");
       arrayList1.add("Aloo and Dal ki Tikki");

       ArrayList<String> arrayList2 = new ArrayList<>();
       arrayList2.add("Aloo and Dal ki Tikki");
       arrayList2.add("Cheese Balls");
       arrayList2.add("Veg Crispy");
       arrayList2.add("Aloo and Dal ki Tikki");

       ArrayList<String> arrayList3 = new ArrayList<>();
       arrayList3.add("Aloo and Dal ki Tikki");
       arrayList3.add("Cheese Balls");
       arrayList3.add("Veg Crispy");
       arrayList3.add("Aloo and Dal ki Tikki");

       ArrayList<String> arrayList4 = new ArrayList<>();
       arrayList4.add("Aloo and Dal ki Tikki");
       arrayList4.add("Cheese Balls");
       arrayList4.add("Veg Crispy");
       arrayList4.add("Aloo and Dal ki Tikki");

       ArrayList<String> arrayList5 = new ArrayList<>();
       arrayList5.add("Aloo and Dal ki Tikki");
       arrayList5.add("Cheese Balls");
       arrayList5.add("Veg Crispy");
       arrayList5.add("Aloo and Dal ki Tikki");

       map.put("Starters",arrayList1);
       map.put("Main course",arrayList2);
       map.put("Beverages",arrayList3);
       map.put("Salads",arrayList4);
       map.put("Desserts",arrayList5);



       MenuWrapper menuWrapper = new MenuWrapper();
       menuWrapper.setMenuMap(map);

       model.addAttribute("con", "menu");
       model.addAttribute("map", menuWrapper);
       return "layout";
    }

    @GetMapping("menu/bulk-upload")
    public String bulkupload(Model model) {
        model.addAttribute("con", "bulk-upload");
        model.addAttribute("menuItem", new MenuItem());
        return "layout";
    }
    @GetMapping("menu/single-upload")
    public String singleupload(Model model) {
        MenuItem menuItem = new MenuItem();
        model.addAttribute("con", "single-upload");
        model.addAttribute("menuItem", menuItem);
        return "layout";
    }

    @GetMapping("menu/edit-menu")
    public String editMenu(Model model) {
        model.addAttribute("con", "edit");
        return "layout";
    }




}



