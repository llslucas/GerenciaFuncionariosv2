package br.unifae.engsoft.poo3.gerenciafuncionarios.controller;

import br.unifae.engsoft.poo3.gerenciafuncionarios.model.Funcionario;
import br.unifae.engsoft.poo3.gerenciafuncionarios.dao.FuncionarioArquivoDAO;
import br.unifae.engsoft.poo3.gerenciafuncionarios.dao.FuncionarioMemoriaDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FuncionarioController {

  //Abre o arquivo e salva seus dados na memória.
  public void abrirArquivo() {
    FuncionarioArquivoDAO arquivoDao = new FuncionarioArquivoDAO("funcionarios.txt");
    FuncionarioMemoriaDAO memoriaDao = new FuncionarioMemoriaDAO();
    
    List<Funcionario> funcionarios = new ArrayList<>();

    try {
      funcionarios = arquivoDao.recuperaTodos();
    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Falha ao abrir o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
      Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
    }

    memoriaDao.salvaTodos(funcionarios);
  }

  public void preencherTabela(JTable tabela) {
    DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
    tabela.setModel(tableModel);
    tabela.selectAll();
    tableModel.setRowCount(0);

    FuncionarioMemoriaDAO dao = new FuncionarioMemoriaDAO();

    List<Funcionario> funcionarios = dao.recuperaTodos();

    for (int i = 0; i < funcionarios.size(); i++) {
      Funcionario funcionario = funcionarios.get(i);

      Object[] data = {
	funcionario.getCodigo(),
	funcionario.getNome(),
	funcionario.getSalario()
      };

      tableModel.addRow(data);
    }
  }

  public void buscar(String codigo) {
    FuncionarioMemoriaDAO dao = new FuncionarioMemoriaDAO();

    Funcionario funcionario = dao.recupera(codigo);

    JOptionPane.showMessageDialog(null, funcionario != null ? funcionario.toString() : "Funcionario não encontrado.");
  }

  public boolean cadastrar(String codigo, String nome, float salario) {
    Funcionario novoFuncionario = new Funcionario(codigo, nome, salario);
    FuncionarioMemoriaDAO dao = new FuncionarioMemoriaDAO();

    try {
      dao.salva(novoFuncionario);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  // Método para salvar no arquivo quando o programa for fechado
  public static void salvarTudoNoArquivo() {
    FuncionarioArquivoDAO arquivoDao = new FuncionarioArquivoDAO("funcionarios.txt");
    FuncionarioMemoriaDAO memoriaDao = new FuncionarioMemoriaDAO();

    List<Funcionario> funcionarios = memoriaDao.recuperaTodos();

    try {
      arquivoDao.salvaTodos(funcionarios);
      JOptionPane.showMessageDialog(null, "Funcionário(s) salvo(s) como sucesso!");
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "Erro ao salvar o(s) funcionário(s)!");
    }
  }

}
