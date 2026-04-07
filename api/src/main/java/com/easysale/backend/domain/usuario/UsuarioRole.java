package com.easysale.backend.domain.usuario;

public enum UsuarioRole {

    ADMIN("administrador"),

    USER("vendedor");

    private String role;

    UsuarioRole(String role){
        this.role=role;
    }

    public String getRole() {
        return role;
    }
}
