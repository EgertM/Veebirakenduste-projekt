package com.veebirakendus.Attempt1.services;

import com.veebirakendus.Attempt1.entity.AdObject;

public interface AdService {
    public Iterable<AdObject> findAll();
}
