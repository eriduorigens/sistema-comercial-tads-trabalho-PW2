package br.edu.ifg.sistemacomercial.bean;

import br.edu.ifg.sistemacomercial.dao.ProdutoDAO;
import br.edu.ifg.sistemacomercial.dao.CategoriaDAO;

import br.edu.ifg.sistemacomercial.util.JsfUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.ifg.sistemacomercial.entity.Produtos;
import br.edu.ifg.sistemacomercial.entity.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class ProdutoBean extends JsfUtil {

    private Produtos produto;
    private List<Categoria> categorias;
    private List<Produtos> produtos;
    int i;
    private Status statusTela;
    private ProdutoDAO produtoDAO;
    private CategoriaBean categoriaBean;

    private enum Status {

        INSERINDO,
        EDITANDO,
        PESQUISANDO
    }

    @PostConstruct
    public void init() {
        produto = new Produtos();
        produtos = new ArrayList<>();
        statusTela = ProdutoBean.Status.PESQUISANDO;
        produtoDAO = new ProdutoDAO();
    }

//construtor    
    public ProdutoBean() throws SQLException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categorias = categoriaDAO.listar();

    }

    public void novo() {
        this.i = 0;
        statusTela = Status.INSERINDO;
        produto = new Produtos();

    }
// adicionar produto ----------------------------------------------

    public void adicionarProduto() {
        try {
            produtoDAO.salvar(produto);
            produto = new Produtos();

            FacesContext context = FacesContext.getCurrentInstance();
            if (this.i == 0) {
                addMensagem("Produto inserido com sucesso!");
            } else {
                addMensagem("Produto alterado com sucesso!");

            }
//            context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));            //pesquisar();
        } catch (SQLException ex) {
            addMensagemErro(ex.getMessage());
        }
    }
    //remover  ----------------------------------------------

    public void remover(Produtos produto) {
        try {
            addMensagem("Produto deletado com sucesso!");

            produtoDAO.deletar(produto);
            produtos.remove(produto);

        } catch (SQLException ex) {
            addMensagemErro(ex.getMessage());
        }
    }

    //editar  ----------------------------------------------
    public void editar(Produtos produto) {
        //remover(usuario);
        this.i = 1;
        this.produto = produto;
        statusTela = Status.EDITANDO;
    }

    //pesquisar ----------------------------------------------------
    public void pesquisar() {
        System.out.println("Pesquisar produto");
        try {
            if (!statusTela.equals(Status.PESQUISANDO)) {
                statusTela = Status.PESQUISANDO;
                return;
            }
            produtos = produtoDAO.listar();
            if (produtos == null || produtos.isEmpty()) {
                addMensagemAviso("Nenhum produto cadastrado.");
            }
        } catch (SQLException ex) {
            addMensagemErro(ex.getMessage());
        }
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public List<Produtos> getProdutos() {

        return produtos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public String getStatusTela() {
        return statusTela.name();
    }

    //
    //
}
