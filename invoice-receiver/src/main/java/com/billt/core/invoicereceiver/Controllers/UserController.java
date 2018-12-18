package com.billt.core.invoicereceiver.Controllers;


import com.billt.core.invoicereceiver.Model.User;
import com.billt.core.invoicereceiver.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Mobile/")
public class UserController {
    @Autowired
    UserRepository userRepository;


    @PostMapping(value = "/AddNewUser")
    @ResponseBody
    public String AddNewUser(@RequestBody User u) {
        System.out.println(u);
        User user = userRepository.findByEmail(u.getEmail());
        try {
            if (user == null) {
                userRepository.save(u);
                return "new ResponseEntity<String>(HttpStatus.CREATED)";
            } else {
                return "new ResponseEntity<String>(Email not valid, HttpStatus.BAD_REQUEST)";
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return "new ResponseEntity<String>(some error occured, HttpStatus.INTERNAL_SERVER_ERROR)";
        }
    }

    @PostMapping("CheckPhoneNo")
    public ResponseEntity<Boolean> CheckPhoneNo(@RequestParam("phoneno") String phoneNo) {
        System.out.println(phoneNo);
        User user = userRepository.findByMobile(phoneNo);
        Boolean status = false;
        try {
            if (user != null) {
                status = true;
            }
            return new ResponseEntity<Boolean>(status, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("")
    public void Check() {
        System.out.println("abc");
    }


}
