package com.senac.springWebPi4.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    public Produto produto;

    private String img;

    @JoinColumn
    private long fk_prodId;

    public Imagem(String img, long fk_prodId) {
        this.img = img;
        this.fk_prodId = fk_prodId;
    }

    public Imagem() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
    
    public long getId() {
        return id;
    }

    public void setId(long idImg) {
        this.id = idImg;
    }

    public long getFk_prodId() {
        return fk_prodId;
    }

    public void setFk_prodId(long fk_prodId) {
        this.fk_prodId = fk_prodId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
