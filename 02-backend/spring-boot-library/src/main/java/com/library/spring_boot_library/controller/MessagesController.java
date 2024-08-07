package com.library.spring_boot_library.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.spring_boot_library.entity.Message;
import com.library.spring_boot_library.requestmodels.AdminQuestionRequest;
import com.library.spring_boot_library.service.MessagesService;
import com.library.spring_boot_library.utils.ExtractJWT;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/messages")
public class MessagesController {

    private MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;

    }

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value = "Authorization") String token,
            @RequestBody Message messageRequest) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        messagesService.postMessage(messageRequest, userEmail);
    }

    @PutMapping("/secure/admin/message")
    public void putMessage(@RequestHeader(value = "Authorization") String token,
            @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only.");
        }
        messagesService.putMessage(adminQuestionRequest, userEmail);
    }
}
