package org.uv.mfa.repository;

import org.uv.mfa.entities.OTPCode;
import org.uv.mfa.entities.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPCodeRepository extends JpaRepository<OTPCode, Long> {

    Optional<OTPCode> findFirstByUserAndCodeAndUsedFalseAndExpDateAfter(User user, String code, LocalDateTime now);
}
