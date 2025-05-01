/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dwes.jzurro.notifications_microservice.controller;

import dwes.jzurro.notifications_microservice.dto.BookDTO;
import dwes.jzurro.notifications_microservice.dto.UserDTO;
import dwes.jzurro.notifications_microservice.dto.ReservationDTO;
import dwes.jzurro.notifications_microservice.entity.Notification;
import dwes.jzurro.notifications_microservice.service.EmailService;
import dwes.jzurro.notifications_microservice.service.NotificationService;
import dwes.jzurro.notifications_microservice.util.CodeGenerator;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Controlador REST para gestionar notificaciones.
 * Recibe las peticiones de Laravel y registra la notificación en la base de datos.
 * 
 * Autor: Jabier Zurro Aduriz
 */
@RestController
@RequestMapping("/api/notificaciones")
public class NotificationController {

    @Autowired
    private EmailService emailService;
    private final NotificationService notificationService;
    private static final String API_BASE_URL = "http://localhost/Zurro_Aduriz_Jabier_DWES05TE01/public/api/";
    
    /**
     *
     * @param notificationService
     */
    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    /**
     * Devuelve todas las notificaciones registradas en la base de datos.
     * @return Lista de notificaciones y código HTTP adecuado
     */
    @GetMapping
    public ResponseEntity<?> getAllNotifications() {
        try {
            return new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Unexpected error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Devuelve una notificación específica a partir de su ID.
     * @param id ID numérico de la notificación a recuperar
     * @return Notificación encontrada o mensaje de error con el estado HTTP adecuado
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getNotificationById(@PathVariable Integer id) {
    return notificationService.getNotificationById(id)
        .map(notification -> new ResponseEntity<Object>(notification, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<Object>(
            Map.of("error", "Notification with ID " + id + " not found."),
            HttpStatus.NOT_FOUND
        ));
    }

    /**
     * Registra una nueva notificación en la base de datos.
     * @param reservationDTO
     * @return Respuesta HTTP con la notificación registrada y el estado HTTP adecuado
     */
    @PostMapping
    public ResponseEntity<?> createNotification(@Valid @RequestBody ReservationDTO reservationDTO) {
        try {

            UserDTO user = getUserById(reservationDTO.getUser_id());
            BookDTO book = getBookById(reservationDTO.getBook_id());

            if (user == null) {
                return new ResponseEntity<>(Map.of("error", "User with ID " + reservationDTO.getUser_id() + " not found."), HttpStatus.NOT_FOUND);

            }
            if (book == null) {
                return new ResponseEntity<>(Map.of("error", "Book with ID " + reservationDTO.getBook_id() + " not found."), HttpStatus.NOT_FOUND);
            }

            String codigoConfirmacion = CodeGenerator.generateCode();

            Notification notification = new Notification();
            notification.setIdReserva(reservationDTO.getId());
            notification.setNombre(user.getName());
            notification.setApellido(user.getSurname());
            notification.setEmail(user.getEmail());
            notification.setLibro(book.getTitle());
            notification.setFechaInicio(reservationDTO.getStart_date());
            notification.setFechaFin(reservationDTO.getEnd_date());
            notification.setCodigo(codigoConfirmacion);

            Notification savedNotification = notificationService.saveNotification(notification);
            emailService.sendEmail(savedNotification);
            return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);

        } catch (Exception e) {
            // Captura de error genérico
            return new ResponseEntity<>(Map.of("error", "Unexpected error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Recupera los datos del usuario desde la API de Laravel.
     * @param userId ID del usuario
     * @return Objeto UserDTO si se encuentra; null si ocurre un error
     */
    private UserDTO getUserById(int userId) {
        RestTemplate restTemplate = new RestTemplate();
        String userUrl = API_BASE_URL + "users/" + userId;
        try {
            return restTemplate.getForObject(userUrl, UserDTO.class);
        } catch (Exception e) {
            // Puedes loguearlo si quieres o simplemente devolver null
            return null;
        }
    }
    
    /**
     * Recupera los datos del libro desde la API de Laravel.
     * @param bookId ID del libro
     * @return Objeto BookDTO si se encuentra; null si ocurre un error
     */
    private BookDTO getBookById(int bookId) {
        RestTemplate restTemplate = new RestTemplate();
        String bookUrl = API_BASE_URL + "books/" + bookId;
        try {
            return restTemplate.getForObject(bookUrl, BookDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
}