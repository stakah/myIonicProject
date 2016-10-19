package controleEstoque.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;


 /**
 * Classe que representa a tabela VENDAITEM
 * @modified
 */
@Entity
@Table(name = "\"VENDA\"")

@XmlRootElement
public class Venda implements Serializable {

	/**
	 * UID da classe, necessário na serialização 
	 * @generated
	 */
	private static final long serialVersionUID = 82542637l;

	/**
	 * @generated
	 */
	@Id

	@Column(name = "id", insertable = true, updatable = true)
	private java.lang.String id = UUID.randomUUID().toString().toUpperCase();

	/**
	 * @generated
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "datavenda", nullable = false, unique = false, insertable = true, updatable = true)
	private java.util.Date dataVenda;

	/**
	 * @generated
	 */
	@ManyToOne
	@JoinColumn(name = "fk_cliente", referencedColumnName = "id", insertable = true, updatable = true)
	private Cliente cliente;


  /**
   * @modified
   */
  @OneToMany
  @JoinColumn(name="fk_venda") 
  private List<VendaItem> vendaItens;
  
  /**
   * @modified
   */
  @Transient
  private Estoque tempEstoque;
  
  /**
   * @modified
   */
  @Transient
  private int tempQtd;
  
  public List<VendaItem> getVendaItens() {
    return this.vendaItens;
  }
  
  public void setVendaItens(List<VendaItem> itens) {
    this.vendaItens = itens;
  }
     
	/**
	 * Construtor
	 * @generated
	 */
	public Venda() {
	}

	/**
	 * Obtém id
	 * @param id id
	 * return id
	 * @generated
	 */
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * Define id
	 * @param id id
	 * @generated
	 */
	public Venda setId(java.lang.String id) {
		this.id = id;
		return this;
	}

	/**
	 * Obtém dataVenda
	 * @param dataVenda dataVenda
	 * return dataVenda
	 * @generated
	 */
	public java.util.Date getDataVenda() {
		return this.dataVenda;
	}

	/**
	 * Define dataVenda
	 * @param dataVenda dataVenda
	 * @generated
	 */
	public Venda setDataVenda(java.util.Date dataVenda) {
		this.dataVenda = dataVenda;
		return this;
	}

	/**
	 * Obtém cliente
	 * @param cliente cliente
	 * return cliente
	 * @generated
	 */
	public Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * Define cliente
	 * @param cliente cliente
	 * @generated
	 */
	public Venda setCliente(Cliente cliente) {
		this.cliente = cliente;
		return this;
	}

	/**
	 * @generated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}

	/**
	 * @generated
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Venda))
			return false;

		Venda other = (Venda) obj;

		if (this.id == null && other.id != null)
			return false;
		else if (!this.id.equals(other.id))
			return false;

		return true;

	}
}
