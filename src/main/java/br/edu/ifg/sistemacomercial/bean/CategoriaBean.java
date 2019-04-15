/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.sistemacomercial.bean;

import br.edu.ifg.sistemacomercial.util.JsfUtil;

import br.edu.ifg.sistemacomercial.dao.CategoriaDAO;
import br.edu.ifg.sistemacomercial.entity.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ifg
 */
@ManagedBean
@SessionScoped
public class CategoriaBean extends JsfUtil {

    private Categoria categoria;
    private List<Categoria> categorias;
    private Status statusTela;
    int i;
    private CategoriaDAO categoriaDAO;

    private enum Status {

        INSERINDO,
        EDITANDO,
        PESQUISANDO
    }

    @PostConstruct
    public void init() {
        categoria = new Categoria();
        categorias = new ArrayList<>();
        statusTela = CategoriaBean.Status.PESQUISANDO;
        categoriaDAO = new CategoriaDAO();
    }

    public void novo() {
        this.i = 0;
        statusTela = Status.INSERINDO;

        categoria = new Categoria();
    }
// adicionar produto ----------------------------------------------

    public void adicionarCategoria() {
        try {
            categoriaDAO.salvar(categoria);
            categoria = new Categoria();

            FacesContext context = FacesContext.getCurrentInstance();
            System.out.println("o calor de ddee");
            if (this.i == 0) {
                addMensagem("Categoria inserida com sucesso!");

            } else {
                addMensagem("Categoria alterada com sucesso!");

            }
            //pesquisar();
        } catch (SQLException ex) {
            addMensagemErro(ex.getMessage());
        }
    }

    //remover  ----------------------------------------------
    public void remover(Categoria cateroria) {
        System.out.println("deletar aaa1 " + cateroria.getNome());

        try {
            categoriaDAO.deletar(cateroria);

            if (categoriaDAO.isAprovacao() == true){
                 categorias.remove(cateroria);
            }       
           
        } catch (SQLException ex) {
            addMensagemErro(ex.getMessage());
        }
    }

    //editar  ----------------------------------------------
    public void editar(Categoria categoria) {
        //remover(usuario);
        this.i = 1;
        this.categoria = categoria;
        statusTela = Status.EDITANDO;
    }

    //-------------------- para produtos--------
    public List<Categoria> produto_Categoria() {
        List<Categoria> listCategoria = new ArrayList<>();

        try {
            listCategoria = categoriaDAO.listar();

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategoria;
    }

    //pesquisar ----------------------------------------------------
    public void pesquisar() {
        System.out.println("Pesquisar categoria");
        try {
            if (!statusTela.equals(Status.PESQUISANDO)) {
                statusTela = Status.PESQUISANDO;
                return;
            }
            categorias = categoriaDAO.listar();
            if (categorias == null || categorias.isEmpty()) {
                addMensagemAviso("Nenhum produto cadastrado.");
            }
        } catch (SQLException ex) {
            addMensagemErro(ex.getMessage());
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public String getStatusTela() {
        return statusTela.name();
    }

}
