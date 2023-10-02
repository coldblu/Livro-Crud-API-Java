package br.ueg.atividade01.prog.web.dto;


import lombok.Data;

public @Data class UsuarioDTO {
    private long idUsuario;
    private String emailUsuario;
    private String senhaUsuario;
}
