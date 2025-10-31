/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package repository;

import entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends  JpaRepository<User, Long> {
}
