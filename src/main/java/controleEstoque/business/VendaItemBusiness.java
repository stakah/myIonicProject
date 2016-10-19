package controleEstoque.business;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;


import controleEstoque.dao.*;
import controleEstoque.entity.*;
import java.util.*;



/**
 * Classe que representa a camada de negócios de VendaItemBusiness
 * 
 * @generated
 **/
@Service("VendaItemBusiness")
public class VendaItemBusiness {

    /**
     * Instância da classe VendaItemDAO que faz o acesso ao banco de dados
     * 
     * @generated
     */
    @Autowired
    @Qualifier("VendaItemDAO")
    protected VendaItemDAO repository;

    // CRUD

    /**
     * Serviço exposto para novo registro de acordo com a entidade fornecida
     * 
     * @generated
     */
    public VendaItem post(final VendaItem entity) throws Exception {
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
    public VendaItem get(java.lang.String id) throws Exception {
      // begin-user-code  
      // end-user-code        
       VendaItem result = repository.findOne(id);
      // begin-user-code  
      // end-user-code        
      return result;
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade fornecida
     * 
     * @generated
     */
    public VendaItem put(final VendaItem entity) throws Exception {
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
    
    public void deleteVendaItemFromVendaId( java.lang.String id) throws Exception {
      // begin-user-code  
      // end-user-code        
      repository.deleteVendaItemFromVendaId(id);
      // begin-user-code  
      // end-user-code        
    }

    // CRUD
    
  /**
   * Lista com paginação de acordo com a NamedQuery
   * 
   * @generated
   */
  public Page<VendaItem> list ( Pageable pageable ){
    // begin-user-code  
    // end-user-code        
    Page<VendaItem> result = repository.list (  pageable );
    // begin-user-code  
    // end-user-code        
    return result;
  }
    
    



}

