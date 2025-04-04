package br.unifae.engsoft.poo3.gerenciafuncionarios.controller;

import br.unifae.engsoft.poo3.gerenciafuncionarios.model.Funcionario;
import br.unifae.engsoft.poo3.gerenciafuncionarios.dao.FuncionarioArquivoDAO;
import br.unifae.engsoft.poo3.gerenciafuncionarios.dao.FuncionarioMemoriaDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FuncionarioController {

  //Abre o arquivo e salva seus dados na memória.
  public void abrirArquivo() throws FileNotFoundException {
    FuncionarioArquivoDAO arquivoDao = new FuncionarioArquivoDAO("funcionarios.txt");
    FuncionarioMemoriaDAO memoriaDao = new FuncionarioMemoriaDAO();

    List<Funcionario> funcionarios;

    funcionarios = arquivoDao.recuperaTodos();

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

  public Funcionario buscar(String codigo) {
    FuncionarioMemoriaDAO dao = new FuncionarioMemoriaDAO();

    Funcionario funcionario = dao.recupera(codigo);

    return funcionario;
  }

  public void cadastrar(String codigo, String nome, float salario) {
    Funcionario novoFuncionario = new Funcionario(codigo, nome, salario);
    FuncionarioMemoriaDAO dao = new FuncionarioMemoriaDAO();

    dao.salva(novoFuncionario);
  }

  public void salvarTudoNoArquivo() throws IOException {
    FuncionarioArquivoDAO arquivoDao = new FuncionarioArquivoDAO("funcionarios.txt");
    FuncionarioMemoriaDAO memoriaDao = new FuncionarioMemoriaDAO();

    List<Funcionario> funcionarios = memoriaDao.recuperaTodos();

    arquivoDao.salvaTodos(funcionarios);
  }
}
