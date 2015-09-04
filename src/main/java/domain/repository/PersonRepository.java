
package domain.repository;

import domain.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Grinyov Vitaliy on 04.09.15.
 */

public interface PersonRepository {

    Person findById(String id);

    Page findAll(Pageable pageable);
}
