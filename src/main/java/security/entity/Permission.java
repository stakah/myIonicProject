package security.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a tabela PERMISSION
 * @generated
 */
@Entity
@Table(name = "\"PERMISSION\""


)
@XmlRootElement
public class Permission implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = 1475848671l;
	
	/**
	 * @generated
	 */
	@Column(name = "response", nullable = true, unique = false, insertable=true, updatable=true)
	private java.lang.Integer response;
	
	/**
	 * @generated
	 */
	@Column(name = "path", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String path;
	
	/**
	 * @generated
	 */
	@Column(name = "verb", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.String verb;
	
	/**
	 * @generated
	 */
	@Id
    
	@Column(name = "id", insertable=true, updatable=true)
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();
	
	/**
	 * @generated
	 */
	@Column(name = "priority", nullable = false, unique = false, insertable=true, updatable=true)
	private java.lang.Integer priority;
	
	/**
	 * @generated
	 */
	@Column(name = "exclude", nullable = true, unique = false, insertable=true, updatable=true)
	private java.lang.String exclude;
	
	/**
	 * @generated
	 */
	@Column(name = "enabled", nullable = true, unique = false, insertable=true, updatable=true)
	private boolean enabled;
	
	/**
	 * @generated
	 */
	@ManyToOne
	@JoinColumn(name="fk_role", referencedColumnName = "id", insertable=true, updatable=true)
	private Role role;
	
	
	/**
	 * Construtor
	 * @generated
	 */
	public Permission(){
	}

	
	/**
	 * Obtém response
	 * @param response response
	 * return response
	 * @generated
	 */
	public java.lang.Integer getResponse(){
		return this.response;
	}
	
	/**
	 * Define response
	 * @param response response
	 * @generated
	 */
	public Permission setResponse(java.lang.Integer response){
		this.response = response;
		return this;
	}
	
	/**
	 * Obtém path
	 * @param path path
	 * return path
	 * @generated
	 */
	public java.lang.String getPath(){
		return this.path;
	}
	
	/**
	 * Define path
	 * @param path path
	 * @generated
	 */
	public Permission setPath(java.lang.String path){
		this.path = path;
		return this;
	}
	
	/**
	 * Obtém verb
	 * @param verb verb
	 * return verb
	 * @generated
	 */
	public java.lang.String getVerb(){
		return this.verb;
	}
	
	/**
	 * Define verb
	 * @param verb verb
	 * @generated
	 */
	public Permission setVerb(java.lang.String verb){
		this.verb = verb;
		return this;
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
	public Permission setId(java.lang.String id){
		this.id = id;
		return this;
	}
	
	/**
	 * Obtém priority
	 * @param priority priority
	 * return priority
	 * @generated
	 */
	public java.lang.Integer getPriority(){
		return this.priority;
	}
	
	/**
	 * Define priority
	 * @param priority priority
	 * @generated
	 */
	public Permission setPriority(java.lang.Integer priority){
		this.priority = priority;
		return this;
	}
	
	/**
	 * Obtém exclude
	 * @param exclude exclude
	 * return exclude
	 * @generated
	 */
	public java.lang.String getExclude(){
		return this.exclude;
	}
	
	/**
	 * Define exclude
	 * @param exclude exclude
	 * @generated
	 */
	public Permission setExclude(java.lang.String exclude){
		this.exclude = exclude;
		return this;
	}
	
	/**
	 * Obtém enabled
	 * @param enabled enabled
	 * return enabled
	 * @generated
	 */
	public boolean getEnabled(){
		return this.enabled;
	}
	
	/**
	 * Define enabled
	 * @param enabled enabled
	 * @generated
	 */
	public Permission setEnabled(boolean enabled){
		this.enabled = enabled;
		return this;
	}
	
	/**
	 * Obtém role
	 * @param role role
	 * return role
	 * @generated
	 */
	public Role getRole(){
		return this.role;
	}
	
	/**
	 * Define role
	 * @param role role
	 * @generated
	 */
	public Permission setRole(Role role){
		this.role = role;
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
	    
	    if(!(obj instanceof Permission))
	      return false;
	    
	    Permission other = (Permission)obj;
	    
		if(this.id == null && other.id != null)
	    	return false;
	    else if(!this.id.equals(other.id))
	     	return false;
	

	    return true;
	    
	}
}
