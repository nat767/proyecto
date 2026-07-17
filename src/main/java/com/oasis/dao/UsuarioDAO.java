package com.oasis.dao;

import com.oasis.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para Usuario
 * Maneja la comunicación con la base de datos
 * Por ahora, usamos una lista en memoria (DEMO)
 * TODO: Conectar a MySQL
 */
public class UsuarioDAO {
    
    // Lista en memoria para almacenar usuarios (DEMO)
    private static List<Usuario> usuariosEnMemoria = new ArrayList<>();
    
    /**
     * Guarda un nuevo usuario
     * @param usuario El usuario a guardar
     * @return true si se guardó, false si error
     */
    public boolean guardarUsuario(Usuario usuario) {
        try {
            // TODO: Guardar en BD MySQL
            // Por ahora, solo lo agregamos a la lista
            usuario.setId(usuariosEnMemoria.size() + 1);
            usuariosEnMemoria.add(usuario);
            
            System.out.println("[DAO] Usuario guardado: " + usuario.toString());
            return true;
        } catch (Exception e) {
            System.out.println("[DAO ERROR] " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Busca un usuario por email
     * @param email El email a buscar
     * @return Usuario si existe, null si no
     */
    public Usuario buscarPorEmail(String email) {
        // TODO: Consultar en BD
        for (Usuario usuario : usuariosEnMemoria) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }
    
    /**
     * Verifica si un email ya existe
     * @param email El email a verificar
     * @return true si existe, false si no
     */
    public boolean emailExiste(String email) {
        return buscarPorEmail(email) != null;
    }
    
    /**
     * Obtiene todos los usuarios (para testing)
     * @return Lista de usuarios
     */
    public List<Usuario> obtenerTodos() {
        return usuariosEnMemoria;
    }
}