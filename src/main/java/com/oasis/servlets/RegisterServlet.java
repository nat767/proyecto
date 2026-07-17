package com.oasis.servlets;

import com.oasis.model.Usuario;
import com.oasis.dao.UsuarioDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet para manejar el registro de nuevos usuarios
 * GET  /register  → Muestra el formulario de registro
 * POST /register  → Procesa el formulario de registro
 */
public class RegisterServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Método GET: Muestra la página de registro
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }

    /**
     * Método POST: Procesa el registro de nuevo usuario
     * Recibe: nombres, apellidos, email, password, confirmPassword
     * Retorna: JSON con éxito o error
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // ==================== PASO 1: OBTENER DATOS DEL FORMULARIO ====================
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        System.out.println("[REGISTRO] Intento de registro:");
        System.out.println("  - Nombres: " + nombres);
        System.out.println("  - Apellidos: " + apellidos);
        System.out.println("  - Email: " + email);
        
        // ==================== PASO 2: VALIDAR CAMPOS VACÍOS ====================
        if (nombres == null || nombres.trim().isEmpty() ||
            apellidos == null || apellidos.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty()) {
            
            System.out.println("[ERROR] Campos vacíos");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\":\"Todos los campos son requeridos\"}");
            return;
        }
        
        // ==================== PASO 3: VALIDAR LONGITUD DE NOMBRES ====================
        if (nombres.trim().length() < 2) {
            System.out.println("[ERROR] Nombre muy corto");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\":\"El nombre debe tener al menos 2 caracteres\"}");
            return;
        }
        
        if (apellidos.trim().length() < 2) {
            System.out.println("[ERROR] Apellido muy corto");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\":\"El apellido debe tener al menos 2 caracteres\"}");
            return;
        }
        
        // ==================== PASO 4: VALIDAR EMAIL ====================
        if (!validarEmail(email.trim())) {
            System.out.println("[ERROR] Email inválido: " + email);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\":\"El correo electrónico no es válido\"}");
            return;
        }
        
        // ==================== PASO 5: VALIDAR LONGITUD DE CONTRASEÑA ====================
        if (password.length() < 6) {
            System.out.println("[ERROR] Contraseña muy corta");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\":\"La contraseña debe tener al menos 6 caracteres\"}");
            return;
        }
        
        // ==================== PASO 6: VALIDAR QUE LAS CONTRASEÑAS COINCIDAN ====================
        if (!password.equals(confirmPassword)) {
            System.out.println("[ERROR] Las contraseñas no coinciden");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\":\"Las contraseñas no coinciden\"}");
            return;
        }
        
        try {
            // ==================== PASO 7: VERIFICAR SI YA EXISTE ====================
            if (usuarioDAO.emailExiste(email.trim())) {
                System.out.println("[ERROR] Email ya registrado: " + email);
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                out.println("{\"error\":\"El correo ya está registrado\"}");
                return;
            }
            
            // ==================== PASO 8: CREAR NUEVO USUARIO ====================
            Usuario nuevoUsuario = new Usuario(
                nombres.trim(),
                apellidos.trim(),
                email.trim(),
                password  // TODO: Encriptar con BCrypt en próximas versiones
            );
            
            // ==================== PASO 9: GUARDAR EN BASE DE DATOS ====================
            boolean guardado = usuarioDAO.guardarUsuario(nuevoUsuario);
            
            if (guardado) {
                System.out.println("[ÉXITO] Usuario registrado: " + email);
                response.setStatus(HttpServletResponse.SC_CREATED);
                out.println("{\"success\":true, \"mensaje\":\"Usuario registrado exitosamente. Redirigiendo al login...\"}");
            } else {
                throw new Exception("Error al guardar el usuario en la base de datos");
            }
            
        } catch (Exception e) {
            System.out.println("[ERROR SERVIDOR] " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\":\"Error en el servidor: " + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
    
    /**
     * Valida que el email tenga un formato correcto
     * Utiliza expresión regular (regex)
     * 
     * @param email El email a validar
     * @return true si el formato es válido, false si no
     */
    private boolean validarEmail(String email) {
        // Patrón regex para validar emails
        // Explicación:
        // ^[A-Za-z0-9+_.-]+     = Comienza con letras, números, +, _, . o -
        // @                      = Debe tener un @
        // [A-Za-z0-9.-]+        = Dominio con letras, números, . o -
        // \\.                    = Debe tener un punto (.)
        // [A-Za-z]{2,}$         = Termina con 2+ letras (com, es, co, etc)
        
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}