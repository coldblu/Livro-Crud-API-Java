package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.UsuarioDTO;
import br.ueg.atividade01.prog.web.model.Usuario;

public class UsuarioMapperImpl implements UsuarioMapper{
    @Override
    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmailUsuario(usuario.getEmailUsuario());
        usuarioDTO.setSenhaUsuario(usuario.getSenhaUsuario());
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        return usuarioDTO;
    }

    @Override
    public Usuario toUsuarioModel(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmailUsuario(usuarioDTO.getEmailUsuario());
        usuario.setSenhaUsuario(usuarioDTO.getSenhaUsuario());
        usuario.setIdUsuario(usuarioDTO.getIdUsuario());
        return usuario;
    }
}
