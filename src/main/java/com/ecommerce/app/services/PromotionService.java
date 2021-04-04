package com.ecommerce.app.services;

import com.ecommerce.app.entity.Promotion;
import com.ecommerce.app.paging.PromotionPaging;
import com.ecommerce.app.search.PromotionCriteriaSearch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PromotionService {

    public Page<Promotion> findAll(PromotionPaging promotionPaging , PromotionCriteriaSearch promotionCriteriaSearch);

    public List<Promotion> listAll();

    public Promotion save(Promotion promotion);

    public Promotion get(Long promotionId);

    public Promotion update(Promotion promotion , Long promotionId);

    public void delete(Long promotionId);

}
