package com.chenshun.eshopproductservice.service.impl;

import com.chenshun.eshopproductservice.mapper.ProductIntroMapper;
import com.chenshun.eshopproductservice.model.ProductIntro;
import com.chenshun.eshopproductservice.rabbitmq.RabbitMQSender;
import com.chenshun.eshopproductservice.rabbitmq.RabbitQueue;
import com.chenshun.eshopproductservice.service.ProductIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductIntroServiceImpl implements ProductIntroService {

    @Autowired
    private ProductIntroMapper productIntroMapper;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void add(ProductIntro productIntro, String operationType) {
        productIntroMapper.add(productIntro);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"product_intro\", \"id\": " + productIntro.getId() + ", " +
                "\"product_id\": " + productIntro.getProductId() + "}");
    }

    @Override
    public void update(ProductIntro productIntro, String operationType) {
        productIntroMapper.update(productIntro);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"product_intro\", \"id\": " + productIntro.getId() + ", " +
                "\"product_id\": " + productIntro.getProductId() + "}");
    }

    @Override
    public void delete(Long id, String operationType) {
        ProductIntro productIntro = findById(id);
        productIntroMapper.delete(id);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"product_intro\", \"id\": " + id + ", \"product_id\": " +
                productIntro.getProductId() + "}");
    }

    @Override
    public ProductIntro findById(Long id) {
        return productIntroMapper.findById(id);
    }

}
