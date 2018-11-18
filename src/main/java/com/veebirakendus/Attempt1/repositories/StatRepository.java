package com.veebirakendus.Attempt1.repositories;

import com.veebirakendus.Attempt1.entity.RequestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatRepository extends CrudRepository<RequestData, Long> {

    RequestData findBySessionId(String sessionId);

    @Query(nativeQuery = true,
            value = "select distinct browser, count(browser) from request_data group by browser order by count(browser) desc limit 5")
    List<Object> getDistinctTopByBrowser();

    @Query(nativeQuery = true,
            value = "select distinct os, count(os) from request_data group by os order by count(os) desc limit 5")
    List<Object> getDistinctTopByOs();

    @Query(nativeQuery = true,
            value = "select count(*) from request_data where hour(time_of_request) = :hour")
    Integer getRequestsByHour(@Param("hour") Integer hour);
}