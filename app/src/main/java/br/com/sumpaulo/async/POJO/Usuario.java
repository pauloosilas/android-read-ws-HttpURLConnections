package br.com.sumpaulo.async.POJO;

import java.util.List;

/**
 * Created by paulo on 16/07/17.
 */

public class Usuario {
    private int usuarioId;
    private String nome;
    private String twitter;

    List<Usuario> usuarioList;

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
