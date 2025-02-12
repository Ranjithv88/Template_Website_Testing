package com.springBoot.Template.Security;

import com.springBoot.Template.Model.OTPDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

// OTPServices Class
@Service
public class OTPServices {

    // Initialize And Declaration Variables
    private static final long OTP_VALIDITY_DURATION = TimeUnit.MINUTES.toMillis(5);
    private final Map<String, OTPDetails> otpStorage = new ConcurrentHashMap<>();

    // Logging Configuration For This Class
    private static final Logger log = LoggerFactory.getLogger(OTPServices.class);

    // Generate OTP Method
    public String generateOTP(String userId) {
        otpStorage.remove(userId);
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);
        long expiryTime = System.currentTimeMillis() + OTP_VALIDITY_DURATION;
        otpStorage.put(userId, new OTPDetails(otp, expiryTime));
        return otp;
    }

    // Validate OTP Method
    public boolean validateOTP(String userId, String otp) {
        OTPDetails details = otpStorage.get(userId);
        String otpData = otp.substring(8, 14);
        log.info(otpData);
        if (details == null) {
            return false;
        }
        if (System.currentTimeMillis() > details.getExpiryTime()) {
            otpStorage.remove(userId);
            return false;
        }
        return details.getOtp().equals(otpData);
    }

}

