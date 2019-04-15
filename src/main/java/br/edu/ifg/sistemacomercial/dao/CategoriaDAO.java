/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.sistemacomercial.dao;

import br.edu.ifg.sistemacomercial.entity.Categoria;
import br.edu.ifg.sistemacomercial.util.FabricadeConexao;
import br.edu.ifg.sistemacomercial.util.JsfUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ifg
 */
public class CategoriaDAO extends JsfUtil {

    public void salvar(Categoria entity) throws SQLException {
        //Ordem das colunas: senha, nome, login, id, email
        String sqlInsert = "insert into categoria (nome, id"
                + ") values (?,default)";
        String sqlUpdate = "update categoria set nome = ? where id = ?";

        PreparedStatement ps;
        if (entity.getId() == null) {
            ps = FabricadeConexao.getConexao().prepareStatement(sqlInsert);
        } else {
            ps = FabricadeConexao.getConexao().prepareStatement(sqlUpdate);
            ps.setLong(2, entity.getId());
        }
        ps.setString(1, entity.getNome());

        FabricadeConexao.getConexao().setAutoCommit(false);
        ps.execute();
        FabricadeConexao.getConexao().commit();
        FabricadeConexao.fecharConexao();
    }
private boolean aprovacao;
    public void deletar(Categoria entity) throws SQLException {
        long quant = 0;
        String sqlDeleteTest = "select count(*) as asd from produto where categoria_id =" + entity.getId();
        PreparedStatement ps1 = FabricadeConexao.getConexao().prepareStatement(sqlDeleteTest);
        System.out.println("URL do delete test  = " + sqlDeleteTest);
        //java.sql.ResultSet
        ResultSet rs = ps1.executeQuery();
        while (rs.next()) {
            quant = rs.getLong("asd");
        }
        
        FabricadeConexao.fecharConexao();
        if (quant == 0) {
            aprovacao = true;
            addMensagem("Produto inserido com sucesso!");

            String sqlDelete = "delete from categoria where id = " + entity.getId();
            PreparedStatement ps = FabricadeConexao.getConexao().prepareStatement(sqlDelete);
            System.out.println("URL do delet = " + sqlDelete);

            ps.execute();
            FabricadeConexao.fecharConexao();
        } else {
             aprovacao = false;
            addMensagem("NAO E possivel apagar pois "+quant+" produto estao relacionados com essa categoria");

        }

    }

    public boolean isAprovacao() {
        return aprovacao;
    }

    public List<Categoria> listar() throws SQLException {
        System.out.println("categoria listar");
        String sqlQuery = "select * from categoria";
        PreparedStatement ps = FabricadeConexao.getConexao().prepareStatement(sqlQuery);
        //java.sql.ResultSet
        ResultSet rs = ps.executeQuery();
        List<Categoria> categorias = new ArrayList<>();
        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("id"));
            categoria.setNome(rs.getString("nome"));
            categorias.add(categoria);
        }
        FabricadeConexao.fecharConexao();

        System.out.println("nome cat" + categorias.get(0).getNome());

        return categorias;
    }

}
