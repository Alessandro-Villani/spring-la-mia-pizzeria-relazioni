package org.java.pizzeria.services;

import java.util.List;
import java.util.Optional;

import org.java.pizzeria.pojo.SpecialOffer;
import org.java.pizzeria.repo.SpecialOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpecialOfferService {
	
	@Autowired
	private SpecialOfferRepo specialOfferRepo;
	
	public List<SpecialOffer> findAll(){
		
		return specialOfferRepo.findAll();
		
	}
	
	public Optional<SpecialOffer> findById(int id){
		
		return specialOfferRepo.findById(id);
		
	}
	
	public SpecialOffer save(SpecialOffer specialOffer) {
		
		return specialOfferRepo.save(specialOffer);
		
	}

}
