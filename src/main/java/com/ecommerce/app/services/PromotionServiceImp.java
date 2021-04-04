package com.ecommerce.app.services;


import com.ecommerce.app.entity.Promotion;
import com.ecommerce.app.paging.PromotionPaging;
import com.ecommerce.app.repository.PromotionCriteriaRepository;
import com.ecommerce.app.repository.PromotionRepository;
import com.ecommerce.app.search.PromotionCriteriaSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImp implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
	private PromotionCriteriaRepository promotionCriteriaRepository;

	@Override
	public Page<Promotion> findAll(PromotionPaging promotionPaging , PromotionCriteriaSearch promotionCriteriaSearch) {
		return promotionCriteriaRepository.findAllWithFilters(promotionPaging , promotionCriteriaSearch);
	}

	@Override
	public List<Promotion> listAll() {
		return promotionRepository.findAll();
	}

	@Override
	public Promotion save(Promotion promotion) {
	    promotionRepository.findByPromotionName(promotion.getPromotionName()).ifPresent(
                (p) -> {
                    throw new RuntimeException("this is promotion with name PromotionName " + p.getPromotionName()
                     + " is already exist");
                }
        );
		return promotionRepository.save(promotion);
	}

	@Override
	public Promotion get(Long promotionId) {
		Promotion maybePromotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> {
                    throw new RuntimeException("this promotion is not found");
                });
		return maybePromotion;
	}

	@Override
	public Promotion update(Promotion promotion, Long promotionId) {
		Promotion maybePromotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> {
                    throw new RuntimeException("this promotion is not found");
                });
        Optional<Promotion> checkPromotionName = promotionRepository.findByPromotionName(promotion.getPromotionName());
        Optional<Promotion> checkIfPromotionExist = promotionRepository.findById(promotionId);
        if(checkPromotionName.isPresent() && checkPromotionName.get().getPromotionId() != checkIfPromotionExist.get().getPromotionId()) {
            throw new RuntimeException("this promotion is already token in database");
        }
        maybePromotion.setPromotionName(promotion.getPromotionName());
        maybePromotion.setPromotionType(promotion.getPromotionType());
        maybePromotion.setBeginDate(promotion.getBeginDate());
        maybePromotion.setEndDate(promotion.getEndDate());
        return promotionRepository.save(maybePromotion);
	}

	@Override
	public void delete(Long promotionId) {

	    promotionRepository.findById(promotionId)
                .orElseThrow(() -> {
                    throw new RuntimeException("this promotion is not found");
                });
	    promotionRepository.deleteById(promotionId);
	}

   
}
