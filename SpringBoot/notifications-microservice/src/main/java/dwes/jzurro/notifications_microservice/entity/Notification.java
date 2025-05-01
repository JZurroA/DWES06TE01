/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dwes.jzurro.notifications_microservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "El id de reserva no puede estar vacío")
    private Integer idReserva;
    
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico debe ser válido")
    private String email;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    
    @NotBlank(message = "El título del libro no puede estar vacío")
    private String libro;
    
    @NotNull(message = "La fecha de inicio no puede ser nula")
    private String fechaInicio;
    
    @NotNull(message = "La fecha de fin no puede ser nula")
    private String fechaFin;
    
    @NotBlank(message = "El código no puede estar vacío")
    @Size(min = 8, max = 12, message = "El código debe tener entre 8 y 12 caracteres")
    private String codigo;

    public Notification() {}
    
    /**
     *
     * @param idReserva
     * @param email
     * @param nombre
     * @param apellido
     * @param libro
     * @param fechaInicio
     * @param fechaFin
     * @param codigo
     */
    public Notification(Integer idReserva, String email, String nombre, String apellido, String libro, String fechaInicio, String fechaFin, String codigo) {
        this.idReserva = idReserva;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.libro = libro;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.codigo = codigo;
    }


    // Getters y Setters

    /**
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     *
     * @return idReserva
     */
    public Integer getIdReserva() {
        return idReserva;
    }

    /**
     *
     * @param idReserva
     */
    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     *
     * @return apellido
     */
    public String getApellido() {
        return apellido;
    }
    
    /**
     *
     * @param apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     *
     * @return libro
     */
    public String getLibro() {
        return libro;
    }

    /**
     *
     * @param libro
     */
    public void setLibro(String libro) {
        this.libro = libro;
    }

    /**
     *
     * @return fechaInicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     *
     * @param fechaInicio
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     *
     * @return fechaFin
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     *
     * @param fechaFin
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     *
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
