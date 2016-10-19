package controleEstoque.rest;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.*;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;

import org.springframework.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import controleEstoque.entity.*;
import controleEstoque.business.*;


/**
 * Controller para expor serviços REST de Cliente
 * 
 * @author Usu�rio de Teste
 * @version 1.0
 * @generated
 **/
@RestController
@RequestMapping(value = "/api/rest/controleEstoque/Cliente")
public class ClienteREST {

    /**
     * Classe de negócio para manipulação de dados
     * 
     * @generated
     */
    @Autowired
    @Qualifier("ClienteBusiness")
    private ClienteBusiness clienteBusiness;

  /**
   * @generated
   */
    @Autowired
    @Qualifier("VendaBusiness")
    private VendaBusiness vendaBusiness;

    /**
     * Serviço exposto para novo registro de acordo com a entidade fornecida
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.POST)
    public Cliente post(@Validated @RequestBody final Cliente entity) throws Exception {
        return clienteBusiness.post(entity);
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade fornecida
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Cliente put(@Validated @RequestBody final Cliente entity) throws Exception {
        return clienteBusiness.put(entity);
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade e id fornecidos
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Cliente put(@PathVariable("id") final java.lang.String id, @Validated @RequestBody final Cliente entity) throws Exception {
        return clienteBusiness.put(entity);
    }


    /**
     * Serviço exposto para remover a entidade de acordo com o id fornecido
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") java.lang.String id) throws Exception {
        clienteBusiness.delete(id);
    }


  /**
   * NamedQuery list
   * @generated
   */
  @RequestMapping(method = RequestMethod.GET
  )    
  public  HttpEntity<PagedResources<Cliente>> listParams (Pageable pageable, PagedResourcesAssembler assembler){
      return new ResponseEntity<>(assembler.toResource(clienteBusiness.list(pageable   )), HttpStatus.OK);    
  }

  /**
   * OneToMany Relationship GET
   * @generated
   */
  @RequestMapping(method = RequestMethod.GET
  , value="/{instanceId}/Venda")    
  public HttpEntity<PagedResources<Venda>> findVenda(@PathVariable("instanceId") java.lang.String instanceId, Pageable pageable, PagedResourcesAssembler assembler) {
    return new ResponseEntity<>(assembler.toResource(clienteBusiness.findVenda(instanceId,  pageable )), HttpStatus.OK);
  }

  /**
   * OneToMany Relationship DELETE 
   * @generated
   */  
  @RequestMapping(method = RequestMethod.DELETE
  , value="/{instanceId}/Venda/{relationId}")    
  public void deleteVenda(@PathVariable("relationId") java.lang.String relationId) throws Exception {
    this.vendaBusiness.delete(relationId);
  }
  
  /**
   * OneToMany Relationship PUT
   * @generated
   */  
  @RequestMapping(method = RequestMethod.PUT
  , value="/{instanceId}/Venda/{relationId}")
  public Venda putVenda(@Validated @RequestBody final Venda entity, @PathVariable("relationId") java.lang.String relationId) throws Exception {
	return this.vendaBusiness.put(entity);
  }  
  
  /**
   * OneToMany Relationship POST
   * @generated
   */  
  @RequestMapping(method = RequestMethod.POST
  , value="/{instanceId}/Venda")
  public Venda postVenda(@Validated @RequestBody final Venda entity, @PathVariable("instanceId") java.lang.String instanceId) throws Exception {
	Cliente cliente = this.clienteBusiness.get(instanceId);
	entity.setCliente(cliente);
	return this.vendaBusiness.post(entity);
  }   



    /**
     * Serviço exposto para recuperar a entidade de acordo com o id fornecido
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Cliente get(@PathVariable("id") java.lang.String id) throws Exception {
        return clienteBusiness.get(id);
    }
}
