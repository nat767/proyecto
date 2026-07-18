package com.oasis.util;

import java.util.regex.Pattern;

/**
 * Clase utilitaria para validaciones de datos
 * Valida: nombres, apellidos, emails, contraseñas
 * También sanitiza datos para evitar inyecciones de código
 */
public class Validador {
    
    // Patrones regex
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    private static final Pattern NOMBRE_PATTERN = 
        Pattern.compile("^[a-zA-ZáéíóúñÁÉÍÓÚÑ\\s]{2,}$");
    
    /**
     * Valida un registro completo de usuario
     * @param nombres Nombres del usuario
     * @param apellidos Apellidos del usuario
     * @param email Email del usuario
     * @param password Contraseña del usuario
     * @return null si todo es válido, String con el error si no
     */
    public static String validarRegistro(String nombres, String apellidos, 
                                         String email, String password) {
        
        // Validar nombres
        if (nombres == null || nombres.trim().isEmpty()) {
            return "El nombre es obligatorio";
        }
        if (nombres.trim().length() < 2) {
            return "El nombre debe tener al menos 2 caracteres";
        }
        if (nombres.trim().length() > 100) {
            return "El nombre no puede exceder 100 caracteres";
        }
        if (!NOMBRE_PATTERN.matcher(nombres.trim()).matches()) {
            return "El nombre solo puede contener letras y espacios";
        }
        
        // Validar apellidos
        if (apellidos == null || apellidos.trim().isEmpty()) {
            return "El apellido es obligatorio";
        }
        if (apellidos.trim().length() < 2) {
            return "El apellido debe tener al menos 2 caracteres";
        }
        if (apellidos.trim().length() > 100) {
            return "El apellido no puede exceder 100 caracteres";
        }
        if (!NOMBRE_PATTERN.matcher(apellidos.trim()).matches()) {
            return "El apellido solo puede contener letras y espacios";
        }
        
        // Validar email
        if (email == null || email.trim().isEmpty()) {
            return "El email es obligatorio";
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            return "El email no es válido";
        }
        if (email.trim().length() > 100) {
            return "El email no puede exceder 100 caracteres";
        }
        
        // Validar password
        if (password == null || password.trim().isEmpty()) {
            return "La contraseña es obligatoria";
        }
        if (password.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres";
        }
        if (password.length() > 100) {
            return "La contraseña no puede exceder 100 caracteres";
        }
        
        return null; // Todo válido
    }
    
    /**
     * Sanitiza una cadena para evitar inyección de código
     * Elimina caracteres especiales y espacios extras
     * @param texto Texto a sanitizar
     * @return Texto sanitizado
     */
    public static String sanitizar(String texto) {
        if (texto == null) {
            return "";
        }
        
        // Trim y eliminar caracteres peligrosos
        String sanitizado = texto.trim()
                                .replaceAll("[<>\"'%;()&+]", "")
                                .replaceAll("\\s+", " "); // Espacios múltiples -> un espacio
        
        // Limitar longitud
        if (sanitizado.length() > 100) {
            sanitizado = sanitizado.substring(0, 100);
        }
        
        return sanitizado;
    }
    
    /**
     * Valida específicamente un email
     * @param email Email a validar
     * @return true si es válido, false si no
     */
    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }
    
    /**
     * Valida específicamente una contraseña
     * @param password Contraseña a validar
     * @return true si es válida, false si no
     */
    public static boolean validarPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.length() >= 6 && password.length() <= 100;
    }
    
    /**
     * Valida específicamente un nombre
     * @param nombre Nombre a validar
     * @return true si es válido, false si no
     */
    public static boolean validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        if (nombre.trim().length() < 2 || nombre.trim().length() > 100) {
            return false;
        }
        return NOMBRE_PATTERN.matcher(nombre.trim()).matches();
    }
}

