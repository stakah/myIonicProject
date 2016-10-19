package controleEstoque.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a tabela VENDAITEM
 * @generated
 */
@Entity
@Table(name = "\"VENDAITEM\""


)
@XmlRootElement
public class VendaItem implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = 903868761l;
	
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
	@JoinColumn(name="fk_estoque", referencedColumnName = "id", insertable=true, updatable=true)
	private Estoque estoque;
	
	/**
	 * @generated
	 */
	@Column(name = "quantidade", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.Long quantidade;
	
	/**
	 * @modified
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_venda", referencedColumnName = "id", insertable=true, updatable=true)
	private Venda venda;
	
	
	/**
	 * Construtor
	 * @generated
	 */
	public VendaItem(){
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
	public VendaItem setId(java.lang.String id){
		this.id = id;
		return this;
	}
	
	/**
	 * Obtém estoque
	 * @param estoque estoque
	 * return estoque
	 * @generated
	 */
	public Estoque getEstoque(){
		return this.estoque;
	}
	
	/**
	 * Define estoque
	 * @param estoque estoque
	 * @generated
	 */
	public VendaItem setEstoque(Estoque estoque){
		this.estoque = estoque;
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
	public VendaItem setQuantidade(java.lang.Long quantidade){
		this.quantidade = quantidade;
		return this;
	}
	
	/**
	 * Obtém venda
	 * @param venda venda
	 * return venda
	 * @generated
	 */
	public Venda getVenda(){
		return this.venda;
	}
	
	/**
	 * Define venda
	 * @param venda venda
	 * @generated
	 */
	public VendaItem setVenda(Venda venda){
		this.venda = venda;
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
	    
	    if(!(obj instanceof VendaItem))
	      return false;
	    
	    VendaItem other = (VendaItem)obj;
	    
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	

	    return true;
	    
	}
}
