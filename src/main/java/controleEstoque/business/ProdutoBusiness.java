package controleEstoque.business;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;


import controleEstoque.dao.*;
import controleEstoque.entity.*;
import java.util.*;



/**
 * Classe que representa a camada de negócios de ProdutoBusiness
 * 
 * @generated
 **/
@Service("ProdutoBusiness")
public class ProdutoBusiness {

    /**
     * Instância da classe ProdutoDAO que faz o acesso ao banco de dados
     * 
     * @generated
     */
    @Autowired
    @Qualifier("ProdutoDAO")
    protected ProdutoDAO repository;

    // CRUD

    /**
     * Serviço exposto para novo registro de acordo com a entidade fornecida
     * 
     * @generated
     */
    public Produto post(final Produto entity) throws Exception {
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
    public Produto get(java.lang.String id) throws Exception {
      // begin-user-code  
      // end-user-code        
       Produto result = repository.findOne(id);
      // begin-user-code  
      // end-user-code        
      return result;
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade fornecida
     * 
     * @generated
     */
    public Produto put(final Produto entity) throws Exception {
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
  public Page<Produto> list ( Pageable pageable ){
    // begin-user-code  
    // end-user-code        
    Page<Produto> result = repository.list (  pageable );
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
  public Page<Fornecedor> listFornecedor(java.lang.String id,  Pageable pageable ) {
      // begin-user-code
      // end-user-code  
      Page<Fornecedor> result = repository.listFornecedor(id,  pageable );
      // begin-user-code
      // end-user-code
      return result;        	  
  }
  
  /**
   * @generated modifiable
   * ManyToMany Relation
   */    
  public int deleteFornecedor(java.lang.String instanceId, java.lang.String relationId) {
      // begin-user-code
      // end-user-code  
      int result = repository.deleteFornecedor(instanceId, relationId);
      // begin-user-code
      // end-user-code  
      return result;  
  }
}

