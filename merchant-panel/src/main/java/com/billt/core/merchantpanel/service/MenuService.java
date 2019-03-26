package com.billt.core.merchantpanel.service;

import com.billt.core.merchantpanel.Entities.MenuItemEntity;
import com.billt.core.merchantpanel.Utils.MenuUtil;
import com.billt.core.merchantpanel.model.MenuItem;
import com.billt.core.merchantpanel.repositories.read.MenuItemReadRepository;
import com.billt.core.merchantpanel.repositories.write.MenuItemWriteRepository;
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
import java.util.List;

@Service
public class MenuService {

   @Autowired
   MenuItemWriteRepository menuItemWriteRepository;

    @Autowired
    MenuItemReadRepository menuItemReadRepository;

    private static final Logger log = LoggerFactory.getLogger(MenuService.class);

    private static final String ITEM_NAME="Item Name";
    private static final String CATEGORY="Item category";
    private static final String PRICE = "Price(in Rs)";


    public static final String[] MENU_HEADER={ITEM_NAME,CATEGORY,PRICE};
    
    
    public void addNewmenuItem(MenuItem menuItem){

        //test
        if (menuItem == null  || StringUtils.isEmpty(menuItem.getName())
              ||  StringUtils.isEmpty(menuItem.getCategory())
              || StringUtils.isEmpty(menuItem.getPrice())) {
            log.info("menu item is null {} or category is null{} or price is null {}");
        }

       // Optional<MenuItemEntity> menuItemEntity;

        //menuItemEntity = menuItemReadRepository.findById(menuItem.getId());

      //  if(!menuItemEntity.isPresent()) {

            MenuItemEntity menuItemEntity1 = new MenuItemEntity();

            menuItemEntity1.setCategory(menuItem.getCategory());
            menuItemEntity1.setName(menuItem.getName());
            menuItemEntity1.setPrice(menuItem.getPrice());

            menuItemWriteRepository.save(menuItemEntity1);


        
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
                addNewmenuItem(menuItem1);
                //  }

            }
            if (!menuItem.getIsError()) {
                log.info("File uploaded successfully");
                menuItem.setMessage("File uploaded successfully");
            } else {
                log.info("file upload not successful"+ message.toString());
                menuItem.setMessage(message.toString());
            }

        }
    }

    public List<CSVRecord> csvFileReader(MultipartFile multipartFile, String[] header){

        Reader reader=null;
        InputStream ir=null;
        CSVParser csvParser =null;
        List<CSVRecord> records =null;
        try{
            ir= multipartFile.getInputStream();
            reader = new InputStreamReader(ir);
            csvParser = CSVFormat.DEFAULT.withSkipHeaderRecord(true).withIgnoreEmptyLines(true).withHeader(header).parse(reader);
            records =csvParser.getRecords();

        }catch(Exception e){
            log.error("Error In Reading Csv File",e);
        }
        finally {
            try{
                if(reader!=null){
                    reader.close();
                }
                if(ir!=null){
                    ir.close();
                }

                if(csvParser!=null){
                    csvParser.close();
                }
            }catch(Exception e){
                log.error("Unable to close stream",e);
            }


        }

        return records;

    }


    private MenuItem createNewMenuItem(CSVRecord csvRecord){
        MenuItem menuItem = new MenuItem();
        menuItem.setIsError(false);


        StringBuilder message = new StringBuilder();
        try{
            if(csvRecord==null){
                return null;
            }
            message.append("Row ").append(csvRecord.getRecordNumber()).append(":");

if(csvRecord.isSet(ITEM_NAME) && csvRecord.isSet(PRICE) && csvRecord.isSet(CATEGORY)) {
    if (MenuUtil.isNumeric(csvRecord.get(PRICE))) {
        menuItem.setPrice(Integer.parseInt(csvRecord.get(PRICE)));
    } else {
        menuItem.setIsError(true);
        message.append("Price is not valid , enter numeric values only").append("~");
    }

        menuItem.setName(csvRecord.get(ITEM_NAME));
        menuItem.setCategory(csvRecord.get(CATEGORY));

}
            else{
                menuItem.setIsError(true);
                log.error("Mandatory field are not present, please add all the fields properly");
                message.append("Mandatory field are not present, please add all the fields properly").append("~");
            }

        }catch(Exception e){
            log.error("Error in creating new issue template using csvRecord",e);
            menuItem.setIsError(true);
            menuItem.setMessage(message.append(" Some error in this row").append("~").toString());

        }
        finally{
            return menuItem;
        }

    }

}
