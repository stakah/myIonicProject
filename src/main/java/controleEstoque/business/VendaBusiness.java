package controleEstoque.business;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;


import controleEstoque.dao.*;
import controleEstoque.entity.*;
import java.util.*;



/**
 * Classe que representa a camada de negócios de VendaBusiness
 * 
 * @generated
 **/
@Service("VendaBusiness")
public class VendaBusiness {

    /**
     * Instância da classe VendaDAO que faz o acesso ao banco de dados
     * 
     * @generated
     */
    @Autowired
    @Qualifier("VendaDAO")
    protected VendaDAO repository;

    // CRUD

    /**
     * Serviço exposto para novo registro de acordo com a entidade fornecida
     * 
     * @generated
     */
    public Venda post(final Venda entity) throws Exception {
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
    public Venda get(java.lang.String id) throws Exception {
      // begin-user-code  
      // end-user-code        
       Venda result = repository.findOne(id);
      // begin-user-code  
      // end-user-code        
      return result;
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade fornecida
     * 
     * @generated
     */
    public Venda put(final Venda entity) throws Exception {
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
  public Page<Venda> list ( Pageable pageable ){
    // begin-user-code  
    // end-user-code        
    Page<Venda> result = repository.list (  pageable );
    // begin-user-code  
    // end-user-code        
    return result;
  }
  /**
   * Lista com paginação de acordo com a NamedQuery
   * 
   * @generated
   */
  public Page<Venda> getTotal ( Pageable pageable ){
    // begin-user-code  
    // end-user-code        
    Page<Venda> result = repository.getTotal (  pageable );
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
  public Page<Estoque> listEstoque(java.lang.String id,  Pageable pageable ) {
      // begin-user-code
      // end-user-code  
      Page<Estoque> result = repository.listEstoque(id,  pageable );
      // begin-user-code
      // end-user-code
      return result;        	  
  }
  
  /**
   * @generated modifiable
   * ManyToMany Relation
   */    
  public int deleteEstoque(java.lang.String instanceId, java.lang.String relationId) {
      // begin-user-code
      // end-user-code  
      int result = repository.deleteEstoque(instanceId, relationId);
      // begin-user-code
      // end-user-code  
      return result;  
  }
}

