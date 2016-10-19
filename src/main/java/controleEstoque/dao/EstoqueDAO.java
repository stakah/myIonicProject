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
@Repository("EstoqueDAO")
@Transactional(transactionManager="controleEstoque-TransactionManager")
public interface EstoqueDAO extends JpaRepository<Estoque, java.lang.String> {

  /**
   * Obtém a instância de Estoque utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM Estoque entity WHERE entity.id = :id")
  public Estoque findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de Estoque utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM Estoque entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);

  /**
   * Lista com paginação de acordo com a NamedQuery
   * 
   * @generated
   */
  @Query("select c from Estoque c")
  public Page<Estoque> list ( Pageable pageable );
  

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM VendaItem entity WHERE entity.estoque.id = :id")
  public Page<VendaItem> findVendaItem(@Param(value="id") java.lang.String id,  Pageable pageable );



  /**
   * ManyToOne Relation
   * @generated
   */
  @Query("SELECT entity.venda FROM VendaItem entity WHERE entity.estoque.id = :id")
  public Page<Venda> listVenda(@Param(value="id") java.lang.String id,  Pageable pageable);

    /**
     * ManyToOne Relation Delete
     * @generated
     */
    @Modifying
    @Query("DELETE FROM VendaItem entity WHERE entity.estoque.id = :instanceId AND entity.venda.id = :relationId")
    public int deleteVenda(@Param(value="instanceId") java.lang.String instanceId, @Param(value="relationId") java.lang.String relationId);




}