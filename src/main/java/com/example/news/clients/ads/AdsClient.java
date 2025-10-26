package com.example.news.clients.ads;

import com.example.news.clients.ads.dto.AdsRequest;
import com.example.news.clients.ads.dto.AdsResponse;

public interface AdsClient {

    AdsResponse getAds();

    AdsResponse putAds(AdsRequest request);
}
