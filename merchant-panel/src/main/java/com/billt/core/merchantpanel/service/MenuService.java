package com.billt.core.merchantpanel.service;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import com.billt.core.merchantpanel.Entities.CategoryEntity;
import com.billt.core.merchantpanel.Entities.MenuItemEntity;
import com.billt.core.merchantpanel.Utils.MenuUtil;
import com.billt.core.merchantpanel.model.Category;
import com.billt.core.merchantpanel.model.MenuItem;
import com.billt.core.merchantpanel.repositories.read.MenuCategoryReadRepository;
import com.billt.core.merchantpanel.repositories.read.MenuItemReadRepository;
import com.billt.core.merchantpanel.repositories.write.MenuItemWriteRepository;
import com.billt.core.merchantpanel.service.Impl.SecurityServiceImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuItemWriteRepository menuItemWriteRepository;

    @Autowired
    MenuItemReadRepository menuItemReadRepository;

    @Autowired
    MenuCategoryReadRepository menuCategoryReadRepository;

    @Autowired
    MerchantReadRepository merchantReadRepository;


    @Autowired
    SecurityServiceImpl securityService;

    private static final Logger log = LoggerFactory.getLogger(MenuService.class);

    private static final String ITEM_NAME = "Item Name";
    private static final String CATEGORY = "Item category";
    private static final String PRICE = "Price(in Rs)";


    public static final String[] MENU_HEADER = {ITEM_NAME, CATEGORY, PRICE};


    public void addNewmenuItem(MenuItem menuItem, Merchant merchant) {

        if (menuItem == null || StringUtils.isEmpty(menuItem.getName())
                || StringUtils.isEmpty(menuItem.getCategory())
                || StringUtils.isEmpty(menuItem.getPrice())) {
            log.info("menu item is null {} or category is null{} or price is null {}");
            menuItem.setIsError(true);
            menuItem.setMessage("category or price not proper");
            return;
        }

        CategoryEntity categoryEntity = menuCategoryReadRepository.findFirstByMerchantIdAndCategoryname(merchant.getId(), menuItem.getCategory());

        if(categoryEntity==null) {
            categoryEntity = new CategoryEntity();
            categoryEntity.setCategoryname(menuItem.getCategory());
            categoryEntity.setMerchant(merchant);
            categoryEntity.setCreatedOn(new Date());
            categoryEntity.setUpdatedOn(new Date());
            menuCategoryReadRepository.save(categoryEntity);

        }

        MenuItemEntity menuItemEntity1 = new MenuItemEntity();

        menuItemEntity1.setCategory(menuItem.getCategory());
        menuItemEntity1.setName(menuItem.getName());
        menuItemEntity1.setCategoryEntity(categoryEntity);
        menuItemEntity1.setMerchant(merchant);
        menuItemEntity1.setPrice(Integer.parseInt(menuItem.getPrice()));
        menuItemEntity1.setCreatedOn(new Date());
        menuItemEntity1.setUpdatedOn(new Date());


        menuItemWriteRepository.save(menuItemEntity1);
        log.info("inserted successfully");


    }

    public void addCategory(Category category) {

    }


    public void menuFileRead(MenuItem menuItem) {

        StringBuilder message = new StringBuilder();
        menuItem.setIsError(false);

        List<CSVRecord> records = csvFileReader(menuItem.getFile(), MENU_HEADER);
        if (records == null || records.isEmpty()) {
            log.info("Csv menuItem Records is blank");
            menuItem.setIsError(true);
            menuItem.setMessage("Csv menuItem File is Empty or Incorrect");
            return;
        }
        MenuItem menuItem1;

        for (CSVRecord record : records) {
            menuItem1 = createNewMenuItem(record);
            if (menuItem1.getIsError()) {
                menuItem.setIsError(true);
                message.append(menuItem1.getMessage()).append("\n");
            } else {
                addNewmenuItem(menuItem1, findLoggedInMerchant());
                //  }

            }
            if (!menuItem.getIsError()) {
                log.info("File uploaded successfully");
                menuItem.setMessage("File uploaded successfully");
            } else {
                menuItem.setIsError(true);
                log.info("file upload not successful" + message.toString());
                menuItem.setMessage(message.toString());
            }

        }
    }

    public List<CSVRecord> csvFileReader(MultipartFile multipartFile, String[] header) {

        Reader reader = null;
        InputStream ir = null;
        CSVParser csvParser = null;
        List<CSVRecord> records = null;
        try {
            ir = multipartFile.getInputStream();
            reader = new InputStreamReader(ir);
            csvParser = CSVFormat.DEFAULT.withSkipHeaderRecord(true).withIgnoreEmptyLines(true).withHeader(header).parse(reader);
            records = csvParser.getRecords();

        } catch (Exception e) {
            log.error("Error In Reading Csv File", e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (ir != null) {
                    ir.close();
                }

                if (csvParser != null) {
                    csvParser.close();
                }
            } catch (Exception e) {
                log.error("Unable to close stream", e);
            }


        }

        return records;

    }


    private MenuItem createNewMenuItem(CSVRecord csvRecord) {
        MenuItem menuItem = new MenuItem();
        menuItem.setIsError(false);


        StringBuilder message = new StringBuilder();
        try {
            if (csvRecord == null) {
                return null;
            }
            message.append("Row ").append(csvRecord.getRecordNumber()).append(":");

            if (csvRecord.isSet(ITEM_NAME) && csvRecord.isSet(PRICE) && csvRecord.isSet(CATEGORY)) {
                if (MenuUtil.isNumeric(csvRecord.get(PRICE))) {
                    menuItem.setPrice(csvRecord.get(PRICE));
                } else {
                    menuItem.setIsError(true);
                    message.append("Price is not valid , enter numeric values only").append("~");
                }

                menuItem.setName(csvRecord.get(ITEM_NAME));
                menuItem.setCategory(csvRecord.get(CATEGORY));

            } else {
                menuItem.setIsError(true);
                log.error("Mandatory field are not present, please add all the fields properly");
                message.append("Mandatory field are not present, please add all the fields properly").append("~");
            }

        } catch (Exception e) {
            log.error("Error in creating new issue template using csvRecord", e);
            menuItem.setIsError(true);
            message.append(" Some error in this row").append("~").toString();

        } finally {
            menuItem.setMessage(message.toString());
            return menuItem;
        }

    }


    public Merchant findLoggedInMerchant() {

        String username = null;
        Merchant merchant = null;

        username = securityService.getLoggedInUser();
        if (username != null) {
            merchant = merchantReadRepository.findByEmail(username);
        }
        return merchant;
    }
    public HashMap<String,List<MenuItemEntity>> getMenu(){

        HashMap<String,List<MenuItemEntity>> menuMap = new HashMap<>();
Merchant merchant = findLoggedInMerchant();
final List<CategoryEntity> categoryEntityList = menuCategoryReadRepository.findAllByMerchant(merchant);
for(CategoryEntity categoryEntity : categoryEntityList){
    final List<MenuItemEntity> menuItemEntityList = menuItemReadRepository.findAllByMerchantAndCategoryEntity(merchant,categoryEntity);
    menuMap.put(categoryEntity.getCategoryname(),menuItemEntityList);
}

return menuMap;


    }
}
