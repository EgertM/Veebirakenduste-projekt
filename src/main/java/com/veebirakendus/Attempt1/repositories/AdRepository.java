
package com.veebirakendus.Attempt1.repositories;

import com.veebirakendus.Attempt1.entity.AdObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends CrudRepository<AdObject, Integer> {

    @Query(value="SELECT pic FROM ad_objects LIMIT 12",nativeQuery = true)
    List<byte[]> getAllPics();

    @Query(value = "SELECT * FROM ad_objects", nativeQuery = true)
    List<AdObject> getAllAds();

    @Query(value="SELECT * FROM ad_objects WHERE google_uid=(:googleUid)",nativeQuery = true)
    List<AdObject> findByGoogleId(@Param("googleUid") String googleId);

    /*@Query(value="SELECT pic FROM ad_objects WHERE google_uid=(:googleUid)",nativeQuery = true)
    List<byte[]> getAllPicsByGoogleUid(@Param("googleUid") String googleId);*/

    @Modifying
    @Transactional
    @Query(value="delete from ad_objects where id=(:id)", nativeQuery = true)
    void deleteAd(@Param("id")Long id);
}

