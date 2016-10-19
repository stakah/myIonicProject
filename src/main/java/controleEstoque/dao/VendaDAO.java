package controleEstoque.dao;

import controleEstoque.entity.*;
import java.util.List;
import org.springframework.stereotype.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*;



/**
 * Realiza operação de Create, Read, Update e Delete no banco de dados.
 * Os métodos de create, edit, delete e outros estão abstraídos no JpaRepository
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * 
 * @generated
 */
@Repository("VendaDAO")
@Transactional(transactionManager="controleEstoque-TransactionManager")
public interface VendaDAO extends JpaRepository<Venda, java.lang.String> {

  /**
   * Obtém a instância de Venda utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM Venda entity WHERE entity.id = :id")
  public Venda findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de Venda utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM Venda entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);
  
  

  /**
   * Lista com paginação de acordo com a NamedQuery
   * 
   * @generated
   */
  @Query("select v from Venda v")
  public Page<Venda> list ( Pageable pageable );
  
  /**
   * Lista com paginação de acordo com a NamedQuery
   * 
   * @generated
   */
  @Query("select count(v.id) from Venda v")
  public Page<Venda> getTotal ( Pageable pageable );
  

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM VendaItem entity WHERE entity.venda.id = :id")
  public Page<VendaItem> findVendaItem(@Param(value="id") java.lang.String id,  Pageable pageable );



  /**
   * ManyToOne Relation
   * @generated
   */
  @Query("SELECT entity.estoque FROM VendaItem entity WHERE entity.venda.id = :id")
  public Page<Estoque> listEstoque(@Param(value="id") java.lang.String id,  Pageable pageable);

    /**
     * ManyToOne Relation Delete
     * @generated
     */
    @Modifying
    @Query("DELETE FROM VendaItem entity WHERE entity.venda.id = :instanceId AND entity.estoque.id = :relationId")
    public int deleteEstoque(@Param(value="instanceId") java.lang.String instanceId, @Param(value="relationId") java.lang.String relationId);




}