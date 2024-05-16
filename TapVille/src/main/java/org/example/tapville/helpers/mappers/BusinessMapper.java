package org.example.tapville.helpers.mappers;

import org.example.tapville.models.Business;
import org.example.tapville.models.dtos.BusinessDto;
import org.example.tapville.services.contracts.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessMapper {
    private final BusinessService businessService;
    @Autowired
    public BusinessMapper(BusinessService businessService) {
        this.businessService = businessService;
    }
    public Business dtoBusinessCreate(BusinessDto businessDto){
        Business business = new Business();
        business.setBusinessName(businessDto.getBusinessName());
        business.setBusinessCode(businessDto.getBusinessCode());
        return business;
    }

    public BusinessDto businessToDto(Business business){
        BusinessDto businessDto = new BusinessDto();
        businessDto.setBusinessName(business.getBusinessName());
        businessDto.setBusinessCode(business.getBusinessCode());
        return businessDto;
    }
}
