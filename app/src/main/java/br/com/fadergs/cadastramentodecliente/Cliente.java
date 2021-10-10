package br.com.fadergs.cadastramentodecliente;

import androidx.annotation.NonNull;

public class Cliente {

    private int id;
    private String nome;
    private String telefone;
    private String estado;

    public Cliente(String nome, String telefone, String estado) {
        this.nome = nome;
        this.telefone = telefone;
        this.estado = estado;
    }
    public Cliente() {

    }

    @NonNull
    @Override
    public String toString() {
        return "Cliente{" +
                "nome: '" + nome + '\'' +
                ", telefone: '" + telefone + '\'' +
                ", estado: '" + estado + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
