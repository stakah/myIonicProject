package controleEstoque.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a tabela CLIENTE
 * @generated
 */
@Entity
@Table(name = "\"CLIENTE\""


)
@XmlRootElement
public class Cliente implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = -1769724432l;
	
	/**
	 * @generated
	 */
	@Id
    
	@Column(name = "id", insertable=true, updatable=true)
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	/**
	 * @generated
	 */
	@Column(name = "nomecompleto", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String nomeCompleto;
	
	/**
	 * @generated
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "datanascimento", nullable = false, unique = false, insertable=true, updatable=true)
	private java.util.Date dataNascimento;
	
	/**
	 * @generated
	 */
	@Column(name = "cpf", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String cpf;
	
	/**
	 * @generated
	 */
	@Column(name = "endereco", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String endereco;
	
	/**
	 * @generated
	 */
	@Column(name = "cidade", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String cidade;
	
	/**
	 * @generated
	 */
	@Column(name = "estado", nullable =false, unique = false, insertable=true, updatable=true)
	private java.lang.String estado;
	
	
	/**
	 * Construtor
	 * @generated
	 */
	public Cliente(){
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
	public Cliente setId(java.lang.String id){
		this.id = id;
		return this;
	}
	
	/**
	 * Obtém nomeCompleto
	 * @param nomeCompleto nomeCompleto
	 * return nomeCompleto
	 * @generated
	 */
	public java.lang.String getNomeCompleto(){
		return this.nomeCompleto;
	}
	
	/**
	 * Define nomeCompleto
	 * @param nomeCompleto nomeCompleto
	 * @generated
	 */
	public Cliente setNomeCompleto(java.lang.String nomeCompleto){
		this.nomeCompleto = nomeCompleto;
		return this;
	}
	
	/**
	 * Obtém dataNascimento
	 * @param dataNascimento dataNascimento
	 * return dataNascimento
	 * @generated
	 */
	public java.util.Date getDataNascimento(){
		return this.dataNascimento;
	}
	
	/**
	 * Define dataNascimento
	 * @param dataNascimento dataNascimento
	 * @generated
	 */
	public Cliente setDataNascimento(java.util.Date dataNascimento){
		this.dataNascimento = dataNascimento;
		return this;
	}
	
	/**
	 * Obtém cpf
	 * @param cpf cpf
	 * return cpf
	 * @generated
	 */
	public java.lang.String getCpf(){
		return this.cpf;
	}
	
	/**
	 * Define cpf
	 * @param cpf cpf
	 * @generated
	 */
	public Cliente setCpf(java.lang.String cpf){
		this.cpf = cpf;
		return this;
	}
	
	/**
	 * Obtém endereco
	 * @param endereco endereco
	 * return endereco
	 * @generated
	 */
	public java.lang.String getEndereco(){
		return this.endereco;
	}
	
	/**
	 * Define endereco
	 * @param endereco endereco
	 * @generated
	 */
	public Cliente setEndereco(java.lang.String endereco){
		this.endereco = endereco;
		return this;
	}
	
	/**
	 * Obtém cidade
	 * @param cidade cidade
	 * return cidade
	 * @generated
	 */
	public java.lang.String getCidade(){
		return this.cidade;
	}
	
	/**
	 * Define cidade
	 * @param cidade cidade
	 * @generated
	 */
	public Cliente setCidade(java.lang.String cidade){
		this.cidade = cidade;
		return this;
	}
	
	/**
	 * Obtém estado
	 * @param estado estado
	 * return estado
	 * @generated
	 */
	public java.lang.String getEstado(){
		return this.estado;
	}
	
	/**
	 * Define estado
	 * @param estado estado
	 * @generated
	 */
	public Cliente setEstado(java.lang.String estado){
		this.estado = estado;
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
	    
	    if(!(obj instanceof Cliente))
	      return false;
	    
	    Cliente other = (Cliente)obj;
	    
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	

	    return true;
	    
	}
}
