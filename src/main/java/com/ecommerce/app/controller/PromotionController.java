package com.ecommerce.app.controller;

import com.ecommerce.app.entity.Promotion;
import com.ecommerce.app.paging.PromotionPaging;
import com.ecommerce.app.search.PromotionCriteriaSearch;
import com.ecommerce.app.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @RequestMapping("/allPromotions")
    public ResponseEntity<Page<Promotion>> findAllWithPaging(PromotionPaging promotionPaging
            , PromotionCriteriaSearch promotionCriteriaSearch) {
        return new ResponseEntity<>(promotionService.findAll(promotionPaging , promotionCriteriaSearch) , HttpStatus.OK);
    }


    @RequestMapping("/listPromotions")
    public ResponseEntity<List<Promotion>> listPromotion(){
        return new ResponseEntity<>(promotionService.listAll() , HttpStatus.OK);
    }

    @RequestMapping("/save")
    public ResponseEntity<Promotion> savePromotion(@ModelAttribute Promotion promotion) {
        Promotion createPromotion = promotionService.save(promotion);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(createPromotion);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Promotion> getPromotion(@PathVariable Long id) {
        return new ResponseEntity<>(promotionService.get(id) , HttpStatus.OK);
    }

    @RequestMapping("/update/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable Long id , @ModelAttribute Promotion promotion) {
        Promotion updatePromotion = promotionService.update(promotion ,id);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(updatePromotion);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable Long id) {
        promotionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
