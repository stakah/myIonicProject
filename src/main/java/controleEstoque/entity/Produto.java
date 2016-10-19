package controleEstoque.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a tabela PRODUTO
 * @generated
 */
@Entity
@Table(name = "\"PRODUTO\""


)
@XmlRootElement
public class Produto implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = 1355181790l;
	
	/**
	 * @generated
	 */
	@Id
    
	@Column(name = "id", insertable=true, updatable=true)
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	/**
	 * @generated
	 */
	@Column(name = "descricao", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String descricao;
	
	/**
	 * @generated
	 */
	@Column(name = "marca", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String marca;
	
	
	/**
	 * Construtor
	 * @generated
	 */
	public Produto(){
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
	public Produto setId(java.lang.String id){
		this.id = id;
		return this;
	}
	
	/**
	 * Obtém descricao
	 * @param descricao descricao
	 * return descricao
	 * @generated
	 */
	public java.lang.String getDescricao(){
		return this.descricao;
	}
	
	/**
	 * Define descricao
	 * @param descricao descricao
	 * @generated
	 */
	public Produto setDescricao(java.lang.String descricao){
		this.descricao = descricao;
		return this;
	}
	
	/**
	 * Obtém marca
	 * @param marca marca
	 * return marca
	 * @generated
	 */
	public java.lang.String getMarca(){
		return this.marca;
	}
	
	/**
	 * Define marca
	 * @param marca marca
	 * @generated
	 */
	public Produto setMarca(java.lang.String marca){
		this.marca = marca;
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
	    
	    if(!(obj instanceof Produto))
	      return false;
	    
	    Produto other = (Produto)obj;
	    
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	

	    return true;
	    
	}
}
