
package com.veebirakendus.Attempt1.repositories;

import com.veebirakendus.Attempt1.entity.AdObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Repository
public interface AdRepository extends CrudRepository<AdObject, Long> {

}

