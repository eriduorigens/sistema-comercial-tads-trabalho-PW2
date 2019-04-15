/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.sistemacomercial.dao;

import br.edu.ifg.sistemacomercial.entity.Categoria;
import br.edu.ifg.sistemacomercial.entity.Produtos;
import br.edu.ifg.sistemacomercial.util.FabricadeConexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ifg
 */
public class ProdutoDAO {

    public void salvar(Produtos entity) throws SQLException {

        System.out.println(" o alfabeto  grego ");
        //Ordem das colunas: senha, nome, login, id, email
        String sqlInsert = "insert into produto  values (default,'" + entity.getNome() + "','" + entity.getMarca() + "','" + entity.getCodigo_de_barra() + "', '" + entity.getUnidade_de_medida() + "'," + entity.getCategoria_id().getId() + ");";
        String sqlUpdate = "update produto set nome = '" + entity.getNome() + "', marca = '" + entity.getMarca() + "', codigo_barra = '" + entity.getCodigo_de_barra() + "', "
                + "unidade_medida = '" +1 + "', categoria_id = " + entity.getCategoria_id().getId() + " where id = " + entity.getId();
        System.out.println("URL " + sqlUpdate);
        PreparedStatement ps;
        if (entity.getId() == null) {
            ps = FabricadeConexao.getConexao().prepareStatement(sqlInsert);
        } else {
            System.out.println("entrou noupdate");
            ps = FabricadeConexao.getConexao().prepareStatement(sqlUpdate);
        }
       // ps.setString(1, entity.getNome());
        // ps.setString(2, entity.getMarca());
        //ps.setString(3, entity.getCodigo_de_barra());
        //ps.setString(4, entity.getUnidade_de_medida());
        //  ps.setLong(5, entity.getCategoria_id().getId());
        System.out.println("alfabeto deu bom " + entity.getCategoria_id().getId());
        System.out.println("URL " + sqlUpdate);
        FabricadeConexao.getConexao().setAutoCommit(false);

        ps.execute();
        FabricadeConexao.getConexao().commit();
        FabricadeConexao.fecharConexao();
    }

    public void deletar(Produtos entity) throws SQLException {
        String sqlDelete = "delete from produto where id = " + entity.getId();
        PreparedStatement ps = FabricadeConexao.getConexao().prepareStatement(sqlDelete);
        ps.execute();
        FabricadeConexao.fecharConexao();
    }

    public Categoria cat(int id) throws SQLException {

        Categoria categoria = new Categoria();

        String sqlQuery = "select * from categoria where id = " + id;
        PreparedStatement ps = FabricadeConexao.getConexao().prepareStatement(sqlQuery);
        //java.sql.ResultSet
        ResultSet rs = ps.executeQuery();
        // List<Categoria> categorias = new ArrayList<>();
        while (rs.next()) {
            //Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("id"));
            categoria.setNome(rs.getString("nome"));

        }
        FabricadeConexao.fecharConexao();

        System.out.println("nome->" + categoria.getNome());

        return categoria;
    }

    public List<Produtos> listar() throws SQLException {
        System.out.println("prod listar");
        String sqlQuery = "select * from produto";
        PreparedStatement ps = FabricadeConexao.getConexao().prepareStatement(sqlQuery);
        //java.sql.ResultSet
        ResultSet rs = ps.executeQuery();
        List<Produtos> produtos = new ArrayList<>();
        while (rs.next()) {
            Produtos prod = new Produtos();
            prod.setId(rs.getLong("id"));
            prod.setNome(rs.getString("nome"));
            prod.setMarca(rs.getString("marca"));
            prod.setCodigo_de_barra(rs.getString("codigo_barra"));
            prod.setUnidade_de_medida(rs.getString("unidade_medida"));
            long i = rs.getLong("categoria_id");
            prod.setCategoria_id(cat((int) i));
            System.out.println("aaaaaaaa->" + prod.getCategoria_id().getNome());

            produtos.add(prod);
        }
        FabricadeConexao.fecharConexao();

        System.out.println("unidade_medida " + produtos.get(0).getUnidade_de_medida());

        return produtos;
    }

}
