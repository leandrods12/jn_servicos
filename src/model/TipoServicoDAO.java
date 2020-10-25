package model;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.TipoServico;

public class TipoServicoDAO extends GeralDAO {

	public boolean inserirTipoServico(TipoServico tipoServico) {
		String query = "INSERT INTO tipo (nome_tipo) values (?)";
		try {
            pst = con.prepareStatement(query);
          pst.setString(1, tipoServico.getNomeServico());
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
