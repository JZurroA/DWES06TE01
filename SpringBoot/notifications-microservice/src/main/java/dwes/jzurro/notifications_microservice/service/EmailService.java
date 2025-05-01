/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dwes.jzurro.notifications_microservice.service;

import dwes.jzurro.notifications_microservice.entity.Notification;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jabier Zurro Aduriz
 */
@Service
public class EmailService {

    /**
     * Método para simular el envío de un email
     * @param notification
     */
    public void sendEmail(Notification notification) {
        System.out.println("==========================================");
        System.out.println("Simulando envío de email a: " + notification.getEmail());
        System.out.println("Estimado/a " + notification.getNombre() + " " + notification.getApellido() + ",");
        System.out.println("Su reserva del libro '" + notification.getLibro() + "' está confirmada.");
        System.out.println("Fecha inicio: " + notification.getFechaInicio());
        System.out.println("Fecha fin: " + notification.getFechaFin());
        System.out.println("Código de confirmación: " + notification.getCodigo());
        System.out.println("==========================================");
    }
}
