/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dwes.jzurro.notifications_microservice.repository;

import dwes.jzurro.notifications_microservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jabier Zurro Aduriz
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    
}
