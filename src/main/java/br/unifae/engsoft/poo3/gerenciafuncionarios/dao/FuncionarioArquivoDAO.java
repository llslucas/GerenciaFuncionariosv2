package br.unifae.engsoft.poo3.gerenciafuncionarios.dao;

import br.unifae.engsoft.poo3.gerenciafuncionarios.model.Funcionario;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionarioArquivoDAO implements DAO<Funcionario> {

  private String nomeArq = null;

  public FuncionarioArquivoDAO(String nomeArq) {
    this.nomeArq = nomeArq;
  }

  @Override
  public Funcionario recupera(String codigo) throws FileNotFoundException {
    FileReader arquivo = new FileReader(nomeArq);

    Scanner scanner = new Scanner(arquivo);

    while (scanner.hasNext()) {
      String codigoArquivo = scanner.nextLine();
      String nome = scanner.nextLine();
      double salario = Double.parseDouble(scanner.nextLine());

      if (codigo.equals(codigoArquivo)) {
	return new Funcionario(codigoArquivo, nome, salario);
      }
    }

    return null;
  }

  @Override
  public List<Funcionario> recuperaTodos() throws FileNotFoundException {
    FileReader arquivo = new FileReader(nomeArq);

    Scanner scanner = new Scanner(arquivo);

    List<Funcionario> funcionarios = new ArrayList<>();

    while (scanner.hasNext()) {
      String codigo = scanner.nextLine();
      String nome = scanner.nextLine();
      double salario = Double.parseDouble(scanner.nextLine());

      funcionarios.add(new Funcionario(codigo, nome, salario));
    }

    return funcionarios;
  }

  @Override
  public void salva(Funcionario item) throws IOException {
    PrintWriter writer;
    writer = new PrintWriter(new FileWriter(nomeArq, true));
    
    writer.println(item.getCodigo());
    writer.println(item.getNome());
    writer.println(item.getSalario());    
    
    writer.close();
  }

  //Salvar os funcionários no arquivo
  @Override
  public void salvaTodos(List<Funcionario> funcionarios) throws IOException {
    PrintWriter writer;
    writer = new PrintWriter(new FileWriter(nomeArq, false));
	    
    for (Funcionario f : funcionarios) {
      writer.println(f.getCodigo());
      writer.println(f.getNome());
      writer.println(f.getSalario());
    }    
    
    writer.close();
  }
}
