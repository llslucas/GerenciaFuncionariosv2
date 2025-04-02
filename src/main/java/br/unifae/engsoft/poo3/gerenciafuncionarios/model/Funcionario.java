package br.unifae.engsoft.poo3.gerenciafuncionarios.model;

import java.util.Objects;

public class Funcionario {
  public String codigo;
  public String nome;
  public double salario;

  public Funcionario(String codigo, String nome, double salario) {
    this.codigo = codigo;
    this.nome = nome;
    this.salario = salario;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double getSalario() {
    return salario;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 53 * hash + this.codigo.hashCode();
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Funcionario other = (Funcionario) obj;
    if (this.codigo.equals(other.codigo)) {
      return false;
    }
    if (Double.doubleToLongBits(this.salario) != Double.doubleToLongBits(other.salario)) {
      return false;
    }
    return Objects.equals(this.nome, other.nome);
  }

  @Override
  public String toString() {
    return "codigo: " + codigo + "\nnome: " + nome + "\nsalario: " + salario;
  }
  
}
