package com.archetype.news.clients.ads;

import com.archetype.news.clients.ads.dto.AdsRequest;
import com.archetype.news.clients.ads.dto.AdsResponse;

public interface AdsClient {

    AdsResponse getAds();

    AdsResponse putAds(AdsRequest request);
}
