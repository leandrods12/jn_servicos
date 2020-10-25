/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class ClienteDAO extends GeralDAO {

    public boolean inserirNovoCliente(Cliente novoCliente) {

        connectToDb();

        String query = "INSERT INTO Cliente (nome_cliente, cpf, endereco, telefone) values (?,?,?,?)";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, novoCliente.getNome());
            pst.setLong(2, novoCliente.getCpf());
            pst.setString(3, novoCliente.getEndereco());
            pst.setLong(4, novoCliente.getTelefone());

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

    public ArrayList<Cliente> listaClientes() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        connectToDb();
        Cliente clienteTemp;
        long cpf, telefone;
        String nome, endereco;
        String sql = "SELECT * FROM Cliente";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            while (rs.next()) {
                nome = rs.getString("nome_cliente");
                cpf = rs.getLong("cpf");
                telefone = rs.getLong("telefone");
                endereco = rs.getString("endereco");
                clienteTemp = new Cliente(cpf, telefone, nome, endereco);
                listaClientes.add(clienteTemp);
            }
            sucesso = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro = " + ex.getMessage());
            }
        }
        return listaClientes;
    }

    public int getIdCliente(String nomeCliente) {

        connectToDb();

        int idCliente = 0;

        String query = "SELECT id_cliente FROM Cliente WHERE nome_cliente = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, nomeCliente);

            rs = pst.executeQuery();

            while (rs.next()) {
                idCliente = rs.getInt("id_cliente");
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
        return idCliente;
    }

    public long getTelefoneCliente(String nomeCliente) {

        connectToDb();

        long telefoneCliente = 0;

        String query = "SELECT telefone FROM Cliente WHERE nome_cliente = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, nomeCliente);

            rs = pst.executeQuery();

            while (rs.next()) {
                telefoneCliente = rs.getLong("telefone");
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
        return telefoneCliente;
    }

    public long getCpfCliente(String nomeCliente) {

        connectToDb();

        long cpfCliente = 0;

        String query = "SELECT cpf FROM Cliente WHERE nome_cliente = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, nomeCliente);

            rs = pst.executeQuery();

            while (rs.next()) {
                cpfCliente = rs.getLong("cpf");
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
        return cpfCliente;
    }

    public String getEnderecoCliente(String nomeCliente) {

        connectToDb();

        String enderecoCliente = null;

        String query = "SELECT endereco FROM Cliente WHERE nome_cliente = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, nomeCliente);

            rs = pst.executeQuery();

            while (rs.next()) {
                enderecoCliente = rs.getString("endereco");
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
        return enderecoCliente;
    }

    public Cliente getInfoCliente(String nomeCliente) {
        String endereco = this.getEnderecoCliente(nomeCliente);
        long cpf = this.getCpfCliente(nomeCliente);
        long telefone = this.getTelefoneCliente(nomeCliente);

        Cliente cliente = new Cliente(cpf, telefone, nomeCliente, endereco);

        return cliente;
    }
}
