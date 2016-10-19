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
 * Controller para expor serviços REST de Fornecedor
 * 
 * @author Usu�rio de Teste
 * @version 1.0
 * @generated
 **/
@RestController
@RequestMapping(value = "/api/rest/controleEstoque/Fornecedor")
public class FornecedorREST {

    /**
     * Classe de negócio para manipulação de dados
     * 
     * @generated
     */
    @Autowired
    @Qualifier("FornecedorBusiness")
    private FornecedorBusiness fornecedorBusiness;

  /**
   * @generated
   */
    @Autowired
    @Qualifier("ProdutoBusiness")
    private ProdutoBusiness produtoBusiness;
  /**
   * @generated
   */
    @Autowired
    @Qualifier("EstoqueBusiness")
    private EstoqueBusiness estoqueBusiness;

    /**
     * Serviço exposto para novo registro de acordo com a entidade fornecida
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.POST)
    public Fornecedor post(@Validated @RequestBody final Fornecedor entity) throws Exception {
        return fornecedorBusiness.post(entity);
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade fornecida
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Fornecedor put(@Validated @RequestBody final Fornecedor entity) throws Exception {
        return fornecedorBusiness.put(entity);
    }

    /**
     * Serviço exposto para salvar alterações de acordo com a entidade e id fornecidos
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Fornecedor put(@PathVariable("id") final java.lang.String id, @Validated @RequestBody final Fornecedor entity) throws Exception {
        return fornecedorBusiness.put(entity);
    }


    /**
     * Serviço exposto para remover a entidade de acordo com o id fornecido
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") java.lang.String id) throws Exception {
        fornecedorBusiness.delete(id);
    }


  /**
   * NamedQuery list
   * @generated
   */
  @RequestMapping(method = RequestMethod.GET
  )    
  public  HttpEntity<PagedResources<Fornecedor>> listParams (Pageable pageable, PagedResourcesAssembler assembler){
      return new ResponseEntity<>(assembler.toResource(fornecedorBusiness.list(pageable   )), HttpStatus.OK);    
  }

  /**
   * OneToMany Relationship GET
   * @generated
   */
  @RequestMapping(method = RequestMethod.GET
  , value="/{instanceId}/Estoque")    
  public HttpEntity<PagedResources<Estoque>> findEstoque(@PathVariable("instanceId") java.lang.String instanceId, Pageable pageable, PagedResourcesAssembler assembler) {
    return new ResponseEntity<>(assembler.toResource(fornecedorBusiness.findEstoque(instanceId,  pageable )), HttpStatus.OK);
  }

  /**
   * OneToMany Relationship DELETE 
   * @generated
   */  
  @RequestMapping(method = RequestMethod.DELETE
  , value="/{instanceId}/Estoque/{relationId}")    
  public void deleteEstoque(@PathVariable("relationId") java.lang.String relationId) throws Exception {
    this.estoqueBusiness.delete(relationId);
  }
  
  /**
   * OneToMany Relationship PUT
   * @generated
   */  
  @RequestMapping(method = RequestMethod.PUT
  , value="/{instanceId}/Estoque/{relationId}")
  public Estoque putEstoque(@Validated @RequestBody final Estoque entity, @PathVariable("relationId") java.lang.String relationId) throws Exception {
	return this.estoqueBusiness.put(entity);
  }  
  
  /**
   * OneToMany Relationship POST
   * @generated
   */  
  @RequestMapping(method = RequestMethod.POST
  , value="/{instanceId}/Estoque")
  public Estoque postEstoque(@Validated @RequestBody final Estoque entity, @PathVariable("instanceId") java.lang.String instanceId) throws Exception {
	Fornecedor fornecedor = this.fornecedorBusiness.get(instanceId);
	entity.setFornecedor(fornecedor);
	return this.estoqueBusiness.post(entity);
  }   


  /**
   * ManyToMany Relationship GET
   * @generated
   */
  @RequestMapping(method = RequestMethod.GET
  ,value="/{instanceId}/Produto")
  public HttpEntity<PagedResources<Produto>> listProduto(@PathVariable("instanceId") java.lang.String instanceId,  Pageable pageable, PagedResourcesAssembler assembler ) {
    return new ResponseEntity<>(assembler.toResource(fornecedorBusiness.listProduto(instanceId,  pageable )), HttpStatus.OK); 
  }

  /**
   * ManyToMany Relationship POST
   * @generated
   */  
  @RequestMapping(method = RequestMethod.POST
  ,value="/{instanceId}/Produto")
  public Fornecedor postProduto(@Validated @RequestBody final Produto entity, @PathVariable("instanceId") java.lang.String instanceId) throws Exception {
      Estoque newEstoque = new Estoque();

      Fornecedor instance = this.fornecedorBusiness.get(instanceId);

      newEstoque.setProduto(entity);
      newEstoque.setFornecedor(instance);
      
      this.estoqueBusiness.post(newEstoque);

      return newEstoque.getFornecedor();
  }   

  /**
   * ManyToMany Relationship DELETE
   * @generated
   */  
  @RequestMapping(method = RequestMethod.DELETE
  ,value="/{instanceId}/Produto/{relationId}")
  public void deleteProduto(@PathVariable("instanceId") java.lang.String instanceId, @PathVariable("relationId") java.lang.String relationId) {
	  this.fornecedorBusiness.deleteProduto(instanceId, relationId);
  }  



    /**
     * Serviço exposto para recuperar a entidade de acordo com o id fornecido
     * 
     * @generated
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Fornecedor get(@PathVariable("id") java.lang.String id) throws Exception {
        return fornecedorBusiness.get(id);
    }
}
