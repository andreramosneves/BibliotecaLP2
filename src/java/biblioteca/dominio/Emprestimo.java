/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 41445368
 */
public class Emprestimo {

    private int idEmprestimo;
    private Usuario Usuario;
    private List<ItemEmprestimo> item = new ArrayList<>();
    private Recibo recibo;

    private Double valor;
    private Date dataRetirada;
    private int dias;
    private Date dataEntrada;

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    
    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    
    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    public Emprestimo() {
    }

    public List<ItemEmprestimo> getItem() {
        return item;
    }

    public void setItem(List<ItemEmprestimo> item) {
        this.item = item;
    }


    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    
}
