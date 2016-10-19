package controleEstoque.business;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;


import controleEstoque.dao.*;
import controleEstoque.entity.*;
import java.util.*;



/**
 * Classe que representa a camada de negócios de EstoqueBusiness
 * 
 * @generated
 **/
@Service("EstoqueBusiness")
public class EstoqueBusiness {

    /**
     * Instância da classe EstoqueDAO que faz o acesso ao banco de dados
     * 
     * @generated
     */
    @Autowired
    @Qualifier("EstoqueDAO")
    protected EstoqueDAO repository;

    // CRUD

    /**
     * Serviço exposto para novo registro de acordo com a entidade fornecida
     * 
     * @generated
     */
    public Estoque post(final Estoque entity) throws Exception {
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
    public Estoque get(java.lang.String id) throws Exception {
      // begin-user-code  
      // end-user-code        
       Estoque result = repository.findOne(id);
      // begin-user-code  
      // end-user-code        
      return result;
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade fornecida
     * 
     * @generated
     */
    public Estoque put(final Estoque entity) throws Exception {
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
  public Page<Estoque> list ( Pageable pageable ){
    // begin-user-code  
    // end-user-code        
    Page<Estoque> result = repository.list (  pageable );
    // begin-user-code  
    // end-user-code        
    return result;
  }
    
    

  /**
   * @generated modifiable
   * OneToMany Relation
   */  
  public Page<VendaItem> findVendaItem(java.lang.String id,  Pageable pageable) {
      // begin-user-code
      // end-user-code  
      Page<VendaItem> result = repository.findVendaItem(id,  pageable );
      // begin-user-code  
      // end-user-code        
      return result;	  
  }



  /**
   * @generated modifiable
   * ManyToMany Relation
   */  
  public Page<Venda> listVenda(java.lang.String id,  Pageable pageable ) {
      // begin-user-code
      // end-user-code  
      Page<Venda> result = repository.listVenda(id,  pageable );
      // begin-user-code
      // end-user-code
      return result;        	  
  }
  
  /**
   * @generated modifiable
   * ManyToMany Relation
   */    
  public int deleteVenda(java.lang.String instanceId, java.lang.String relationId) {
      // begin-user-code
      // end-user-code  
      int result = repository.deleteVenda(instanceId, relationId);
      // begin-user-code
      // end-user-code  
      return result;  
  }
}

