package com.chenshun.eshopproductservice.controller;

import com.chenshun.eshopproductservice.model.ProductIntro;
import com.chenshun.eshopproductservice.service.ProductIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-intro")
public class ProductIntroController {

    @Autowired
    private ProductIntroService productIntroService;

    @RequestMapping("/add")
    @ResponseBody
    public String add(ProductIntro productIntro, String operationType) {
        try {
            productIntroService.add(productIntro, operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(ProductIntro productIntro, String operationType) {
        try {
            productIntroService.update(productIntro, operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Long id, String operationType) {
        try {
            productIntroService.delete(id, operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ProductIntro findById(Long id) {
        try {
            return productIntroService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductIntro();
    }

}
