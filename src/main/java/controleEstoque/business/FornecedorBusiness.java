package controleEstoque.business;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;


import controleEstoque.dao.*;
import controleEstoque.entity.*;
import java.util.*;



/**
 * Classe que representa a camada de negócios de FornecedorBusiness
 * 
 * @generated
 **/
@Service("FornecedorBusiness")
public class FornecedorBusiness {

    /**
     * Instância da classe FornecedorDAO que faz o acesso ao banco de dados
     * 
     * @generated
     */
    @Autowired
    @Qualifier("FornecedorDAO")
    protected FornecedorDAO repository;

    // CRUD

    /**
     * Serviço exposto para novo registro de acordo com a entidade fornecida
     * 
     * @generated
     */
    public Fornecedor post(final Fornecedor entity) throws Exception {
      // begin-user-code  
      // end-user-code  
      repository.save(entity);
      // begin-user-code  
      // end-user-code  
      return entity;
    }

    /**
     * Serviço exposto para recuperar a entidade de acordo com o id fornecido
     * 
     * @generated
     */
    public Fornecedor get(java.lang.String id) throws Exception {
      // begin-user-code  
      // end-user-code        
       Fornecedor result = repository.findOne(id);
      // begin-user-code  
      // end-user-code        
      return result;
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade fornecida
     * 
     * @generated
     */
    public Fornecedor put(final Fornecedor entity) throws Exception {
      // begin-user-code  
      // end-user-code        
      repository.saveAndFlush(entity);
      // begin-user-code  
      // end-user-code        
      return entity;
    }

    /**
     * Serviço exposto para remover a entidade de acordo com o id fornecido
     * 
     * @generated
     */
    public void delete( java.lang.String id) throws Exception {
      // begin-user-code  
      // end-user-code        
      repository.delete(id);
      // begin-user-code  
      // end-user-code        
    }

    // CRUD
    
  /**
   * Lista com paginação de acordo com a NamedQuery
   * 
   * @generated
   */
  public Page<Fornecedor> list ( Pageable pageable ){
    // begin-user-code  
    // end-user-code        
    Page<Fornecedor> result = repository.list (  pageable );
    // begin-user-code  
    // end-user-code        
    return result;
  }
    
    

  /**
   * @generated modifiable
   * OneToMany Relation
   */  
  public Page<Estoque> findEstoque(java.lang.String id,  Pageable pageable) {
      // begin-user-code
      // end-user-code  
      Page<Estoque> result = repository.findEstoque(id,  pageable );
      // begin-user-code  
      // end-user-code        
      return result;	  
  }



  /**
   * @generated modifiable
   * ManyToMany Relation
   */  
  public Page<Produto> listProduto(java.lang.String id,  Pageable pageable ) {
      // begin-user-code
      // end-user-code  
      Page<Produto> result = repository.listProduto(id,  pageable );
      // begin-user-code
      // end-user-code
      return result;        	  
  }
  
  /**
   * @generated modifiable
   * ManyToMany Relation
   */    
  public int deleteProduto(java.lang.String instanceId, java.lang.String relationId) {
      // begin-user-code
      // end-user-code  
      int result = repository.deleteProduto(instanceId, relationId);
      // begin-user-code
      // end-user-code  
      return result;  
  }
}

