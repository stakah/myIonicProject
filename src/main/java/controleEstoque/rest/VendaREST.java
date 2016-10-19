package controleEstoque.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controleEstoque.business.EstoqueBusiness;
import controleEstoque.business.VendaBusiness;
import controleEstoque.business.VendaItemBusiness;
import controleEstoque.entity.Estoque;
import controleEstoque.entity.Venda;
import controleEstoque.entity.VendaItem;

/**
 * Controller para expor serviços REST de Venda
 * 
 * @author Usu�rio de Teste
 * @version 1.0
 * @generated
 **/
@RestController
@RequestMapping(value = "/api/rest/controleEstoque/Venda")
public class VendaREST {

	/**
	 * Classe de negócio para manipulação de dados
	 * 
	 * @generated
	 */
	@Autowired
	@Qualifier("VendaBusiness")
	private VendaBusiness vendaBusiness;

	/**
	 * @generated
	 */
	@Autowired
	@Qualifier("EstoqueBusiness")
	private EstoqueBusiness estoqueBusiness;
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
	public Venda post(@Validated @RequestBody final Venda entity) throws Exception {
		for (VendaItem v : entity.getVendaItens()) {
			Estoque est = estoqueBusiness.get(v.getEstoque().getId());
			est.setQuantidade(est.getQuantidade() - v.getQuantidade());
			estoqueBusiness.put(est);
		}
		return vendaBusiness.post(entity);
	}

	/**
	 * Serviço exposto para salvar alterações de acordo com a entidade fornecida
	 * 
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Venda put(@Validated @RequestBody final Venda entity) throws Exception {
		return vendaBusiness.put(entity);
	}

	/**
	 * Serviço exposto para salvar alterações de acordo com a entidade e id fornecidos
	 * 
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public Venda put(@PathVariable("id") final java.lang.String id, @Validated @RequestBody final Venda entity)
			throws Exception {
		return vendaBusiness.put(entity);
	}

	/**
	 * Serviço exposto para remover a entidade de acordo com o id fornecido
	 * 
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable("id") java.lang.String id) throws Exception {
		vendaItemBusiness.deleteVendaItemFromVendaId(id);
		vendaBusiness.delete(id);
	}

	/**
	 * NamedQuery list
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<PagedResources<Venda>> listParams(Pageable pageable, PagedResourcesAssembler assembler) {
		return new ResponseEntity<>(assembler.toResource(vendaBusiness.list(pageable)), HttpStatus.OK);
	}

	/**
	 * NamedQuery getTotal
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getTotal")
	public HttpEntity<PagedResources<Venda>> getTotalParams(Pageable pageable, PagedResourcesAssembler assembler) {
		return new ResponseEntity<>(assembler.toResource(vendaBusiness.getTotal(pageable)), HttpStatus.OK);
	}

	/**
	 * OneToMany Relationship GET
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{instanceId}/VendaItem")
	public HttpEntity<PagedResources<VendaItem>> findVendaItem(@PathVariable("instanceId") java.lang.String instanceId,
			Pageable pageable, PagedResourcesAssembler assembler) {
		return new ResponseEntity<>(assembler.toResource(vendaBusiness.findVendaItem(instanceId, pageable)),
				HttpStatus.OK);
	}

	/**
	 * OneToMany Relationship DELETE 
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{instanceId}/VendaItem/{relationId}")
	public void deleteVendaItem(@PathVariable("relationId") java.lang.String relationId) throws Exception {
		this.vendaItemBusiness.delete(relationId);
	}

	/**
	 * OneToMany Relationship PUT
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{instanceId}/VendaItem/{relationId}")
	public VendaItem putVendaItem(@Validated @RequestBody final VendaItem entity,
			@PathVariable("relationId") java.lang.String relationId) throws Exception {
		return this.vendaItemBusiness.put(entity);
	}

	/**
	 * OneToMany Relationship POST
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/{instanceId}/VendaItem")
	public VendaItem postVendaItem(@Validated @RequestBody final VendaItem entity,
			@PathVariable("instanceId") java.lang.String instanceId) throws Exception {
		Venda venda = this.vendaBusiness.get(instanceId);
		entity.setVenda(venda);
		return this.vendaItemBusiness.post(entity);
	}

	/**
	 * ManyToMany Relationship GET
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{instanceId}/Estoque")
	public HttpEntity<PagedResources<Estoque>> listEstoque(@PathVariable("instanceId") java.lang.String instanceId,
			Pageable pageable, PagedResourcesAssembler assembler) {
		return new ResponseEntity<>(assembler.toResource(vendaBusiness.listEstoque(instanceId, pageable)),
				HttpStatus.OK);
	}

	/**
	 * ManyToMany Relationship POST
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/{instanceId}/Estoque")
	public Venda postEstoque(@Validated @RequestBody final Estoque entity,
			@PathVariable("instanceId") java.lang.String instanceId) throws Exception {
		VendaItem newVendaItem = new VendaItem();

		Venda instance = this.vendaBusiness.get(instanceId);

		newVendaItem.setEstoque(entity);
		newVendaItem.setVenda(instance);

		this.vendaItemBusiness.post(newVendaItem);

		return newVendaItem.getVenda();
	}

	/**
	 * ManyToMany Relationship DELETE
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{instanceId}/Estoque/{relationId}")
	public void deleteEstoque(@PathVariable("instanceId") java.lang.String instanceId,
			@PathVariable("relationId") java.lang.String relationId) {
		this.vendaBusiness.deleteEstoque(instanceId, relationId);
	}

	/**
	 * Serviço exposto para recuperar a entidade de acordo com o id fornecido
	 * 
	 * @generated
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Venda get(@PathVariable("id") java.lang.String id) throws Exception {
		return vendaBusiness.get(id);
	}
}
