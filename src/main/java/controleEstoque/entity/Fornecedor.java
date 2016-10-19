package controleEstoque.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a tabela FORNECEDOR
 * @generated
 */
@Entity
@Table(name = "\"FORNECEDOR\""


)
@XmlRootElement
public class Fornecedor implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = -990870125l;
	
	/**
	 * @generated
	 */
	@Id
    
	@Column(name = "id", insertable=true, updatable=true)
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	/**
	 * @generated
	 */
	@Column(name = "nomefantasia", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String nomeFantasia;
	
	/**
	 * @generated
	 */
	@Column(name = "razaosocial", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String razaoSocial;
	
	/**
	 * @generated
	 */
	@Column(name = "cnpj", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String cnpj;
	
	/**
	 * @generated
	 */
	@Column(name = "nomeresponsavel", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String nomeResponsavel;
	
	
	/**
	 * Construtor
	 * @generated
	 */
	public Fornecedor(){
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
	public Fornecedor setId(java.lang.String id){
		this.id = id;
		return this;
	}
	
	/**
	 * Obtém nomeFantasia
	 * @param nomeFantasia nomeFantasia
	 * return nomeFantasia
	 * @generated
	 */
	public java.lang.String getNomeFantasia(){
		return this.nomeFantasia;
	}
	
	/**
	 * Define nomeFantasia
	 * @param nomeFantasia nomeFantasia
	 * @generated
	 */
	public Fornecedor setNomeFantasia(java.lang.String nomeFantasia){
		this.nomeFantasia = nomeFantasia;
		return this;
	}
	
	/**
	 * Obtém razaoSocial
	 * @param razaoSocial razaoSocial
	 * return razaoSocial
	 * @generated
	 */
	public java.lang.String getRazaoSocial(){
		return this.razaoSocial;
	}
	
	/**
	 * Define razaoSocial
	 * @param razaoSocial razaoSocial
	 * @generated
	 */
	public Fornecedor setRazaoSocial(java.lang.String razaoSocial){
		this.razaoSocial = razaoSocial;
		return this;
	}
	
	/**
	 * Obtém cnpj
	 * @param cnpj cnpj
	 * return cnpj
	 * @generated
	 */
	public java.lang.String getCnpj(){
		return this.cnpj;
	}
	
	/**
	 * Define cnpj
	 * @param cnpj cnpj
	 * @generated
	 */
	public Fornecedor setCnpj(java.lang.String cnpj){
		this.cnpj = cnpj;
		return this;
	}
	
	/**
	 * Obtém nomeResponsavel
	 * @param nomeResponsavel nomeResponsavel
	 * return nomeResponsavel
	 * @generated
	 */
	public java.lang.String getNomeResponsavel(){
		return this.nomeResponsavel;
	}
	
	/**
	 * Define nomeResponsavel
	 * @param nomeResponsavel nomeResponsavel
	 * @generated
	 */
	public Fornecedor setNomeResponsavel(java.lang.String nomeResponsavel){
		this.nomeResponsavel = nomeResponsavel;
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
	    
	    if(!(obj instanceof Fornecedor))
	      return false;
	    
	    Fornecedor other = (Fornecedor)obj;
	    
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	

	    return true;
	    
	}
}
