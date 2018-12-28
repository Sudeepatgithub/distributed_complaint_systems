package com.services.receiverservice.services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.services.receiverservice.utils.AWSConfiguration;
import com.services.receiverservice.models.Complaint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service {


    @Autowired
    private AWSConfiguration awsConfiguration;

    private AmazonSQS amazonSQS;

    public Service() {

    }

    @PostConstruct
    private void initializeAmazon() {
        amazonSQS = AmazonSQSClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();

    }

    public void createComplaint(Complaint complaint) {
        Map<String, MessageAttributeValue> map = new HashMap<>();
        map.put("id", new MessageAttributeValue().withStringValue(complaint.getId())
                .withDataType("String"));
        map.put("userId", new MessageAttributeValue().withStringValue(complaint.getUserId())
                .withDataType("String"));
        map.put("description", new MessageAttributeValue().withStringValue(complaint.getDescription())
                .withDataType("String"));
        map.put("departmentId", new MessageAttributeValue().withStringValue(complaint.getDepartmentId())
                .withDataType("String"));
        map.put("timestamp", new MessageAttributeValue().withStringValue(complaint.getTimestamp())
                .withDataType("String"));
        SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(awsConfiguration.getQueueURL())
                .withMessageAttributes(map)
                .withMessageBody("Complaint Message")
                .withDelaySeconds(5);
        amazonSQS.sendMessage(sendMessageRequest);

    }


}
