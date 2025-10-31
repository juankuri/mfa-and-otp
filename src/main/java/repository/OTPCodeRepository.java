/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package repository;

import entities.OTPCode;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author kuri
 */
public interface OTPCodeRepository extends CrudRepository<OTPCode, Long> {
    
}
