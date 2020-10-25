/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Servico;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class ServicoDAO extends GeralDAO {

    public boolean inserirServico(Servico novoServico) {

        connectToDb();
        String query = "INSERT INTO Cliente (data_inicio, data_fim, valor) values (?,?,?)";
        try {
            pst = con.prepareStatement(query);
            pst.setDate(1, novoServico.getDataInicio());
            pst.setDate(2, novoServico.getDataFinal());
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
}
