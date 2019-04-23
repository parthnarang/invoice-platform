package com.billt.core.merchantpanel.controller;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import com.billt.core.merchantpanel.Entities.CategoryEntity;
import com.billt.core.merchantpanel.Entities.MenuItemEntity;
import com.billt.core.merchantpanel.Utils.MenuUtil;
import com.billt.core.merchantpanel.model.Category;
import com.billt.core.merchantpanel.model.MenuItem;
import com.billt.core.merchantpanel.model.MenuWrapper;
import com.billt.core.merchantpanel.service.Impl.MenuCategoryService;
import com.billt.core.merchantpanel.service.Impl.SecurityServiceImpl;
import com.billt.core.merchantpanel.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    SecurityServiceImpl securityService;

    @Autowired
    private MerchantReadRepository merchantReadRepository;

    @Autowired
    MenuCategoryService menuCategoryService;


    @PostMapping("menu/addMenuIteminBulk")
    public String addMenuIteminBulk(@ModelAttribute MenuItem menuItem) {
        menuService.menuFileRead(menuItem);
        return "AlertSystem/add";
    }

    @PostMapping("menu/addMenuItem")
    public String addMenuItem(Model model,@ModelAttribute MenuItem menuItem) {

        menuItem.setIsError(false);
        menuItem.setMessage("uploaded successfully");
        log.info("Receiving request to create menu item");

        if (MenuUtil.isNumeric(menuItem.getPrice())){
            menuService.addNewmenuItem(menuItem,findLoggedInMerchant());
        } else {
            menuItem.setIsError(true);
            menuItem.setMessage("Price is not valid , enter numeric values only");
        }


        model.addAttribute("con", "single-upload");
        return "layout";
    }

   @GetMapping("menu/view-menu")
    public String viewMenu(Model model) {

       HashMap<String,List<MenuItemEntity>> map = menuService.getMenu();
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

        Merchant merchant = findLoggedInMerchant();
        List<CategoryEntity> menuItemEntities = menuCategoryService.findCategoriesByMerchant(merchant);
        MenuItem menuItem = new MenuItem();

        model.addAttribute("con", "single-upload");
        model.addAttribute("menuItem", menuItem);
        model.addAttribute("categoryList", menuItemEntities);
        return "layout";
    }

    @GetMapping("menu/edit-menu")
    public String editMenu(Model model) {

        HashMap<String,List<MenuItemEntity>> map = menuService.getMenu();
        MenuWrapper menuWrapper = new MenuWrapper();
        menuWrapper.setMenuMap(map);

        model.addAttribute("con", "edit");
        model.addAttribute("map", menuWrapper);
        return "layout";
    }

    public Merchant findLoggedInMerchant() {

        String username = null;
        Merchant merchant= null;

        username =securityService.getLoggedInUser();
        if(username !=null) {
            merchant= merchantReadRepository.findByEmail(username);
        }
        return merchant;
    }







}



