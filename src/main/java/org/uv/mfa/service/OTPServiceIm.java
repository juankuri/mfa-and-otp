package org.uv.mfa.service;

import org.uv.mfa.entities.OTPCode;
import org.uv.mfa.entities.User;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.uv.mfa.repository.OTPCodeRepository;

@Service
public class OTPServiceIm implements OTPService {

    private final OTPCodeRepository otpCodeRepository;
    private final MailService mailService;

    public OTPServiceIm(OTPCodeRepository otpCodeRepository, MailService mailService) {
        this.otpCodeRepository = otpCodeRepository;
        this.mailService = mailService;
    }

    @Override
    public OTPCode generateCode(User user, String machine) {
        String code = String.valueOf((int) (Math.random() * 900000) + 100000);

        OTPCode otp = new OTPCode();
        otp.setUser(user);
        otp.setCode(code);
        otp.setCreation_date(LocalDateTime.now());
        otp.setExp_date(LocalDateTime.now().plusMinutes(5));
        otp.setUsed(false);
        otp.setMachine(machine);

        OTPCode saved = otpCodeRepository.save(otp);

        mailService.sendEmail(code, user.getEmail());

        return saved;
    }

    @Override
    public boolean validateOTP(User user, String code) {
        Optional<OTPCode> otpOpt = otpCodeRepository
                .findFirstByUserAndCodeAndUsedFalseAndExpDateAfter(user, code, LocalDateTime.now());

        if (otpOpt.isPresent()) {
            OTPCode otp = otpOpt.get();
            otp.setUsed(true);
            otpCodeRepository.save(otp);
            return true;
        }

        return false;
    }
}
