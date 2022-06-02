package com.senac.springWebPi4.model;

import com.senac.springWebPi4.Utils.Status;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomeProduto;
    private long qtd;
    private String status;
    @Column(length = 1000)
    private String descricao;
    private double valor;
    private String categoria;

    @OneToMany(mappedBy = "produto")
    List<Imagem> imagens;

    @OneToMany(mappedBy = "prod")
    List<Carrinho> carrinho;

    @OneToMany(mappedBy = "ItemProduto")
    List<ItemPedido> itemPedido;

    public List<ItemPedido> getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(List<ItemPedido> itemPedido) {
        this.itemPedido = itemPedido;
    }

    public List<Carrinho> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Carrinho> carrinho) {
        this.carrinho = carrinho;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Produto() {
    }

    public Produto(String nomeProduto, long qtd, String status, String descricao) {
        this.nomeProduto = nomeProduto;
        this.qtd = qtd;
        this.status = status;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public long getQtd() {
        return qtd;
    }

    public void setQtd(long qtd) {
        this.qtd = qtd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nomeProduto=" + nomeProduto + ", qtd=" + qtd + ", status=" + status + ", descricao=" + descricao + '}';
    }

}
