/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.uv.mfa.service;

import org.uv.mfa.entities.OTPCode;
import org.uv.mfa.entities.User;

/**
 *
 * @author kuri
 */
public interface OTPService {
    OTPCode generateCode(User user, String machine);
    boolean validateOTP(User user, String code);
}
