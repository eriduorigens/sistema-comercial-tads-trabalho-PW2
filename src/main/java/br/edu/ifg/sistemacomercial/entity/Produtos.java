package br.edu.ifg.sistemacomercial.entity;

import java.io.Serializable;
import java.util.Objects;

public class Produtos implements Serializable {

    private Long id;
    private String nome;
    private String marca;
    private String codigo_de_barra;
    private String unidade_de_medida;
    private Categoria categoria_id; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigo_de_barra() {
        return codigo_de_barra;
    }

    public void setCodigo_de_barra(String codigo_de_barra) {
        this.codigo_de_barra = codigo_de_barra;
    }

    public String getUnidade_de_medida() {
        return unidade_de_medida;
    }

    public void setUnidade_de_medida(String unidade_de_medida) {
        this.unidade_de_medida = unidade_de_medida;
    }

    public Categoria getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Categoria categoria_id) {
        this.categoria_id = categoria_id;
    }



    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produtos other = (Produtos) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void setCategoria_id(long i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
