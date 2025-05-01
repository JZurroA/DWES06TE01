/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dwes.jzurro.notifications_microservice.service;

import dwes.jzurro.notifications_microservice.entity.Notification;
import dwes.jzurro.notifications_microservice.repository.NotificationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jabier Zurro Aduriz
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    
    /**
     *
     * @param notificationRepository
     */
    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    
    /**
     *
     * @param notification
     * @return
     */
    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Notification> getNotificationById(Integer id) {
        return notificationRepository.findById(id);
    }
}
