package br.com.fadergs.cadastramentodecliente;

public class Cliente {

    private int id;
    private String nome;
    private String cep;
    private String estado;

    public Cliente(String nome, String cep, String estado) {
        this.nome = nome;
        this.cep = cep;
        this.estado = estado;
    }
    public Cliente() {

    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cep='" + cep + '\'' +
                ", estado='" + estado + '\'' +
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
