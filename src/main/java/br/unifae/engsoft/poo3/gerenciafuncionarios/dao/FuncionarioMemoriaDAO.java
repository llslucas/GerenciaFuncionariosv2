/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unifae.engsoft.poo3.gerenciafuncionarios.dao;

import br.unifae.engsoft.poo3.gerenciafuncionarios.model.Funcionario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class FuncionarioMemoriaDAO implements DAO<Funcionario>{
  private static final List<Funcionario> funcionarios = new ArrayList<>();  

  @Override
  public Funcionario recupera(String codigo) {
    for(Funcionario funcionario: funcionarios){
      if(funcionario.codigo.equals(codigo)){
	return funcionario;
      }
    }    
    return null;
  }

  @Override
  public List<Funcionario> recuperaTodos() {
    return funcionarios;
  }

  @Override
  public void salva(Funcionario item) {
    funcionarios.add(item);
  }

  @Override
  public void salvaTodos(List<Funcionario> itens){
    funcionarios.clear();    
    funcionarios.addAll(itens);
  } 
}
