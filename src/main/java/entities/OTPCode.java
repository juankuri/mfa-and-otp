/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author kuri
 */
@Table
@Entity(name = "otp_codes")
public class OTPCode implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    private String code;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column
    private LocalDateTime creation_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column
    private LocalDateTime exp_date;

    @Column
    private boolean used;

    @Column
    private String machine;

}
