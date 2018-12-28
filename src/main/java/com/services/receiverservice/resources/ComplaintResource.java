package com.services.receiverservice.resources;

import com.services.receiverservice.models.Complaint;
import com.services.receiverservice.services.Service;
import com.services.receiverservice.utils.AWSConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplaintResource {

    private static final Logger logger = LoggerFactory.getLogger(ComplaintResource.class);

    @Autowired
    private Service service;

    @Autowired
    private AWSConfiguration awsConfiguration;

    @RequestMapping(value = "/publish/{issueId}",method = RequestMethod.POST)
    public ResponseEntity raiseComplaint(@RequestBody Complaint complaint){
        service.createComplaint(complaint);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
