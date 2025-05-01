/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dwes.jzurro.notifications_microservice.service;

import dwes.jzurro.notifications_microservice.entity.Notification;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jabier Zurro Aduriz
 */
public interface NotificationService {
    
    /**
     * Recupera todas las notificaciones guardadas.
     * @return Lista de notificaciones
     */
    List<Notification> getAllNotifications();
    
    /**
     * Guarda una nueva notificación en la base de datos.
     * @param notification Notificación a guardar
     * @return La notificación guardada (puede incluir ID generado, etc.)
     */
    Notification saveNotification(Notification notification);
    
    /**
     * Recupera una notificación a partir de su identificador único.
     * 
     * @param id Identificador de la notificación
     * @return Un Optional con la notificación si existe, o vacío si no se encuentra
     */
    Optional<Notification> getNotificationById(Integer id);

}