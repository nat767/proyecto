package com.oasis.model;

/**
 * Clase que representa un Usuario del sistema
 * Contiene los datos básicos de un usuario registrado
 */
public class Usuario {
    
    // ==================== ATRIBUTOS ====================
    private int id;
    private String nombres;
    private String apellidos;
    private String email;
    private String password;
    private String fechaRegistro;
    
    // ==================== CONSTRUCTORES ====================
    
    /**
     * Constructor vacío
     */
    public Usuario() {
    }
    
    /**
     * Constructor con parámetros principales
     * @param nombres Nombres del usuario
     * @param apellidos Apellidos del usuario
     * @param email Email del usuario
     * @param password Contraseña del usuario
     */
    public Usuario(String nombres, String apellidos, String email, String password) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }
    
    // ==================== GETTERS Y SETTERS ====================
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombres() {
        return nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    // ==================== MÉTODOS ====================
    
    /**
     * Retorna una representación en string del usuario
     * Útil para debugging
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                '}';
    }
}
