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
 * Controller para expor serviços REST de Estoque
 * 
 * @author Usu�rio de Teste
 * @version 1.0
 * @generated
 **/
@RestController
@RequestMapping(value = "/api/rest/controleEstoque/Estoque")
public class EstoqueREST {

    /**
     * Classe de negócio para manipulação de dados
     * 
     * @generated
     */
    @Autowired
    @Qualifier("EstoqueBusiness")
    private EstoqueBusiness estoqueBusiness;

  /**
   * @generated
   */
    @Autowired
    @Qualifier("VendaBusiness")
    private VendaBusiness vendaBusiness;
  /**
   * @generated
   */
    @Autowired
    @Qualifier("VendaItemBusiness")
    private VendaItemBusiness vendaItemBusiness;

    /**
     * Serviço exposto para novo registro de acordo com a entidade fornecida
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.POST)
    public Estoque post(@Validated @RequestBody final Estoque entity) throws Exception {
        return estoqueBusiness.post(entity);
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade fornecida
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Estoque put(@Validated @RequestBody final Estoque entity) throws Exception {
        return estoqueBusiness.put(entity);
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade e id fornecidos
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Estoque put(@PathVariable("id") final java.lang.String id, @Validated @RequestBody final Estoque entity) throws Exception {
        return estoqueBusiness.put(entity);
    }


    /**
     * Serviço exposto para remover a entidade de acordo com o id fornecido
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") java.lang.String id) throws Exception {
        estoqueBusiness.delete(id);
    }


  /**
   * NamedQuery list
   * @generated
   */
  @RequestMapping(method = RequestMethod.GET
  )    
  public  HttpEntity<PagedResources<Estoque>> listParams (Pageable pageable, PagedResourcesAssembler assembler){
      return new ResponseEntity<>(assembler.toResource(estoqueBusiness.list(pageable   )), HttpStatus.OK);    
  }

  /**
   * OneToMany Relationship GET
   * @generated
   */
  @RequestMapping(method = RequestMethod.GET
  , value="/{instanceId}/VendaItem")    
  public HttpEntity<PagedResources<VendaItem>> findVendaItem(@PathVariable("instanceId") java.lang.String instanceId, Pageable pageable, PagedResourcesAssembler assembler) {
    return new ResponseEntity<>(assembler.toResource(estoqueBusiness.findVendaItem(instanceId,  pageable )), HttpStatus.OK);
  }

  /**
   * OneToMany Relationship DELETE 
   * @generated
   */  
  @RequestMapping(method = RequestMethod.DELETE
  , value="/{instanceId}/VendaItem/{relationId}")    
  public void deleteVendaItem(@PathVariable("relationId") java.lang.String relationId) throws Exception {
    this.vendaItemBusiness.delete(relationId);
  }
  
  /**
   * OneToMany Relationship PUT
   * @generated
   */  
  @RequestMapping(method = RequestMethod.PUT
  , value="/{instanceId}/VendaItem/{relationId}")
  public VendaItem putVendaItem(@Validated @RequestBody final VendaItem entity, @PathVariable("relationId") java.lang.String relationId) throws Exception {
	return this.vendaItemBusiness.put(entity);
  }  
  
  /**
   * OneToMany Relationship POST
   * @generated
   */  
  @RequestMapping(method = RequestMethod.POST
  , value="/{instanceId}/VendaItem")
  public VendaItem postVendaItem(@Validated @RequestBody final VendaItem entity, @PathVariable("instanceId") java.lang.String instanceId) throws Exception {
	Estoque estoque = this.estoqueBusiness.get(instanceId);
	entity.setEstoque(estoque);
	return this.vendaItemBusiness.post(entity);
  }   


  /**
   * ManyToMany Relationship GET
   * @generated
   */
  @RequestMapping(method = RequestMethod.GET
  ,value="/{instanceId}/Venda")
  public HttpEntity<PagedResources<Venda>> listVenda(@PathVariable("instanceId") java.lang.String instanceId,  Pageable pageable, PagedResourcesAssembler assembler ) {
    return new ResponseEntity<>(assembler.toResource(estoqueBusiness.listVenda(instanceId,  pageable )), HttpStatus.OK); 
  }

  /**
   * ManyToMany Relationship POST
   * @generated
   */  
  @RequestMapping(method = RequestMethod.POST
  ,value="/{instanceId}/Venda")
  public Estoque postVenda(@Validated @RequestBody final Venda entity, @PathVariable("instanceId") java.lang.String instanceId) throws Exception {
      VendaItem newVendaItem = new VendaItem();

      Estoque instance = this.estoqueBusiness.get(instanceId);

      newVendaItem.setVenda(entity);
      newVendaItem.setEstoque(instance);
      
      this.vendaItemBusiness.post(newVendaItem);

      return newVendaItem.getEstoque();
  }   

  /**
   * ManyToMany Relationship DELETE
   * @generated
   */  
  @RequestMapping(method = RequestMethod.DELETE
  ,value="/{instanceId}/Venda/{relationId}")
  public void deleteVenda(@PathVariable("instanceId") java.lang.String instanceId, @PathVariable("relationId") java.lang.String relationId) {
	  this.estoqueBusiness.deleteVenda(instanceId, relationId);
  }  



    /**
     * Serviço exposto para recuperar a entidade de acordo com o id fornecido
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Estoque get(@PathVariable("id") java.lang.String id) throws Exception {
        return estoqueBusiness.get(id);
    }
}
