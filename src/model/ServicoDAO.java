/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Cliente;
import controller.Servico;
import controller.TipoServico;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class ServicoDAO extends GeralDAO {

    public boolean inserirServico(Servico novoServico, String nomeCliente, String tipoServico) {

        ClienteDAO clienteDAO = new ClienteDAO();
        connectToDb();
        String query = "INSERT INTO Cliente (Cliente_id_cliente, tipo_id_tipo, valor) values (?,?,?)";
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, clienteDAO.getIdCliente(nomeCliente));
            pst.setInt(2, novoServico.getDataFinal());
            pst.setDouble(3, novoServico.getValor());

            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) { //Código de erro para entradas duplicadas
                JOptionPane.showMessageDialog(null, "Cliente com este nome ja cadastrado!\nTente novamente com um nome diferente!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            }
        } finally {
            try {   //Encerra a conexão
                con.close();
                pst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }

    public int getIdServico(int idCliente) {
        int idServico = 0;

        String query = "SELECT * FROM Servico WHERE Cliente_id_cliente = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, idCliente);

            rs = pst.executeQuery();

            while (rs.next()) {
                idServico = rs.getInt("id_servico");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {   //Encerra a conexão
                con.close();
                pst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            }
        }

        return idServico;
    }

    public int getIdTipoServico(int id) {
        int idTipo = 0;
        String query = "SELECT tipo_id_tipo FROM Servico WHERE id_servico = ?";

        try {
            pst = con.prepareStatement(query);
            pst.setLong(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                idTipo = rs.getInt("tipo_id_tipo");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {   //Encerra a conexão
                con.close();
                pst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            }
        }

        return idTipo;
    }

    public TipoServico getTipoServico(int idServico) {
        TipoServico tipoServico = null;
        int idTipo = getIdTipoServico(idServico);
        String query = "SELECT * FROM tipo WHERE id_tipo = ?";

        try {
            pst = con.prepareStatement(query);
            pst.setLong(1, idTipo);
            rs = pst.executeQuery();

            while (rs.next()) {
                tipoServico = new TipoServico(rs.getString("nome_tipo"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {   //Encerra a conexão
                con.close();
                pst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            }
        }

        return tipoServico;
    }

    public ArrayList<Servico> getServicoCliente(Cliente cliente) {
        ArrayList<Servico> listaServicos = new ArrayList<>();

        ClienteDAO clienteDAO = new ClienteDAO();
        int idCliente = clienteDAO.getIdCliente(cliente.getNome());

        Servico servicoTemp;
        int idServico = getIdServico(idCliente);
        double valorServico;
        java.sql.Date dataInicio = null;
        java.sql.Date dataFim = null;
        TipoServico tipoServico = getTipoServico(idServico);
        connectToDb();

        String query = "SELECT * FROM Servico WHERE Cliente_id_cliente = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, idCliente);

            rs = pst.executeQuery();

            while (rs.next()) {
                idServico = rs.getInt("id_servico");
                valorServico = rs.getDouble("valor");
                dataInicio = rs.getDate("data_inicio");
                dataFim = rs.getDate("data_fim");
                servicoTemp = new Servico(tipoServico, valorServico, cliente);
                listaServicos.add(servicoTemp);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {   //Encerra a conexão
                con.close();
                pst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            }
        }
        return listaServicos;

    }
}
