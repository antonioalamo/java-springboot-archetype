package com.archetype.news.clients.ads;

import com.archetype.news.clients.ads.mapper.AdsMapper;
import com.archetype.news.domain.model.Ad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdsApiAdaptorImpl {

    private final AdsMapper mapper;
//    private final AdsClient client;


    public Ad getAd(){
//        var response = client.getAds();
//        return mapper.toDomain(response);
        return null;
    }
}
