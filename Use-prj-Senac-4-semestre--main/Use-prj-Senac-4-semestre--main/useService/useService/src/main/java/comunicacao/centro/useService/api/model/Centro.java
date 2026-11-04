package comunicacao.centro.useService.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CENTRO")
public class Centro {

    @Id
    @Column(name = "id_centro", length = 50)
    private String idCentro;

    @Column(name = "id_usuario", length = 50, nullable = false)
    private String idUsuario;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "endereco", length = 255, nullable = false)
    private String endereco;

    @Column(name = "telefone", length = 20)
    private String telefone;

    public Centro() {
    }

    public String getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(String idCentro) {
        this.idCentro = idCentro;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}