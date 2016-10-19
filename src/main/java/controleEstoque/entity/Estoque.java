package controleEstoque.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a tabela ESTOQUE
 * @generated
 */
@Entity
@Table(name = "\"ESTOQUE\""


)
@XmlRootElement
public class Estoque implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = 216146462l;
	
	/**
	 * @generated
	 */
	@Id
    
	@Column(name = "id", insertable=true, updatable=true)
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	/**
	 * @generated
	 */
	@ManyToOne
	@JoinColumn(name="fk_produto", referencedColumnName = "id", insertable=true, updatable=true)
	private Produto produto;
	
	/**
	 * @generated
	 */
	@ManyToOne
	@JoinColumn(name="fk_fornecedor", referencedColumnName = "id", insertable=true, updatable=true)
	private Fornecedor fornecedor;
	
	/**
	 * @generated
	 */
	@Column(name = "quantidade", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.Long quantidade;
	
	/**
	 * @generated
	 */
	@Column(name = "un", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String un;
	
	/**
	 * @generated
	 */
	@Column(name = "valorcompra", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.Double valorCompra;
	
	/**
	 * @generated
	 */
	@Column(name = "valorvenda", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.Double valorVenda;
	
	
	/**
	 * Construtor
	 * @generated
	 */
	public Estoque(){
	}

	
	/**
	 * Obtém id
	 * @param id id
	 * return id
	 * @generated
	 */
	public java.lang.String getId(){
		return this.id;
	}
	
	/**
	 * Define id
	 * @param id id
	 * @generated
	 */
	public Estoque setId(java.lang.String id){
		this.id = id;
		return this;
	}
	
	/**
	 * Obtém produto
	 * @param produto produto
	 * return produto
	 * @generated
	 */
	public Produto getProduto(){
		return this.produto;
	}
	
	/**
	 * Define produto
	 * @param produto produto
	 * @generated
	 */
	public Estoque setProduto(Produto produto){
		this.produto = produto;
		return this;
	}
	
	/**
	 * Obtém fornecedor
	 * @param fornecedor fornecedor
	 * return fornecedor
	 * @generated
	 */
	public Fornecedor getFornecedor(){
		return this.fornecedor;
	}
	
	/**
	 * Define fornecedor
	 * @param fornecedor fornecedor
	 * @generated
	 */
	public Estoque setFornecedor(Fornecedor fornecedor){
		this.fornecedor = fornecedor;
		return this;
	}
	
	/**
	 * Obtém quantidade
	 * @param quantidade quantidade
	 * return quantidade
	 * @generated
	 */
	public java.lang.Long getQuantidade(){
		return this.quantidade;
	}
	
	/**
	 * Define quantidade
	 * @param quantidade quantidade
	 * @generated
	 */
	public Estoque setQuantidade(java.lang.Long quantidade){
		this.quantidade = quantidade;
		return this;
	}
	
	/**
	 * Obtém un
	 * @param un un
	 * return un
	 * @generated
	 */
	public java.lang.String getUn(){
		return this.un;
	}
	
	/**
	 * Define un
	 * @param un un
	 * @generated
	 */
	public Estoque setUn(java.lang.String un){
		this.un = un;
		return this;
	}
	
	/**
	 * Obtém valorCompra
	 * @param valorCompra valorCompra
	 * return valorCompra
	 * @generated
	 */
	public java.lang.Double getValorCompra(){
		return this.valorCompra;
	}
	
	/**
	 * Define valorCompra
	 * @param valorCompra valorCompra
	 * @generated
	 */
	public Estoque setValorCompra(java.lang.Double valorCompra){
		this.valorCompra = valorCompra;
		return this;
	}
	
	/**
	 * Obtém valorVenda
	 * @param valorVenda valorVenda
	 * return valorVenda
	 * @generated
	 */
	public java.lang.Double getValorVenda(){
		return this.valorVenda;
	}
	
	/**
	 * Define valorVenda
	 * @param valorVenda valorVenda
	 * @generated
	 */
	public Estoque setValorVenda(java.lang.Double valorVenda){
		this.valorVenda = valorVenda;
		return this;
	}
	
	/**
	 * @generated
	 */
	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }
	
	/**
	 * @generated
	 */	
	@Override
  	public boolean equals(Object obj) {
    
	    if(this == obj)
	      return true;
	    
	    if(obj == null)
	      return false;
	    
	    if(!(obj instanceof Estoque))
	      return false;
	    
	    Estoque other = (Estoque)obj;
	    
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	

	    return true;
	    
	}
}
