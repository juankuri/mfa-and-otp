package repository;

import entities.OTPCode;
import org.springframework.data.repository.CrudRepository;

public interface OTPCodeRepository extends CrudRepository<OTPCode, Long> {
    
}
