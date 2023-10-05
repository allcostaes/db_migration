package com.example.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTopicConfig kafkaTopicConfig;

    @PostMapping("/create")
    public boolean createProduct(@RequestBody ProductRequest request) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            String reqJson = mapper.writeValueAsString(request);
            kafkaTemplate.send(kafkaTopicConfig.productTopic().name(), reqJson);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}