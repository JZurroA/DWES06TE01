/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dwes.jzurro.notifications_microservice.dto;

/**
 * DTO que representa los datos recibidos desde Laravel
 * para crear una notificación.
 * @author Jabier Zurro Aduriz
 */
public class BookDTO {
    private String title;
    
    public BookDTO() {}

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
}
