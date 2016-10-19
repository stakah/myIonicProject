package security.business;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;


import security.dao.*;
import security.entity.*;
import java.util.*;



/**
 * Classe que representa a camada de negócios de PermissionBusiness
 * 
 * @generated
 **/
@Service("PermissionBusiness")
public class PermissionBusiness {

    /**
     * Instância da classe PermissionDAO que faz o acesso ao banco de dados
     * 
     * @generated
     */
    @Autowired
    @Qualifier("PermissionDAO")
    protected PermissionDAO repository;

    // CRUD

    /**
     * Serviço exposto para novo registro de acordo com a entidade fornecida
     * 
     * @generated
     */
    public Permission post(final Permission entity) throws Exception {
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
    public Permission get(java.lang.String id) throws Exception {
      // begin-user-code  
      // end-user-code        
       Permission result = repository.findOne(id);
      // begin-user-code  
      // end-user-code        
      return result;
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade fornecida
     * 
     * @generated
     */
    public Permission put(final Permission entity) throws Exception {
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
  public Page<Permission> list ( Pageable pageable ){
    // begin-user-code  
    // end-user-code        
    Page<Permission> result = repository.list (  pageable );
    // begin-user-code  
    // end-user-code        
    return result;
  }
    
    



}

