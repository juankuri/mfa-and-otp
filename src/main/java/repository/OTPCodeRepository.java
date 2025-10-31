package repository;

import entities.OTPCode;
import entities.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPCodeRepository extends JpaRepository<OTPCode, Long> {

    Optional<OTPCode> findFirstByUserAndCodeAndUsedFalseAndExpDateAfter(User user, String code, LocalDateTime now);
}
