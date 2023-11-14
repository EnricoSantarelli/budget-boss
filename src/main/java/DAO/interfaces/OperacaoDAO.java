package DAO.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Operacao;

public interface OperacaoDAO {
    List<Operacao> getAllOperacoes(int cd_conta) throws SQLException, ClassNotFoundException;

    void createOperacao(Operacao operacao, int idConta)  throws SQLException, ClassNotFoundException;
    
    public void deleteAllOperacoes(int idConta) throws SQLException, ClassNotFoundException;
}
