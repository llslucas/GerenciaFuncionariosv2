package br.unifae.engsoft.poo3.gerenciafuncionarios.dao;

import java.util.List;

interface DAO<T> {
  public T recupera(String codigo) throws Exception;  
  public List<T> recuperaTodos() throws Exception;
  public void salva(T item) throws Exception;
  public void salvaTodos(List<T> itens) throws Exception;
}