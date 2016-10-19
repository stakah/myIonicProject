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
@Repository("FornecedorDAO")
@Transactional(transactionManager="controleEstoque-TransactionManager")
public interface FornecedorDAO extends JpaRepository<Fornecedor, java.lang.String> {

  /**
   * Obtém a instância de Fornecedor utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM Fornecedor entity WHERE entity.id = :id")
  public Fornecedor findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de Fornecedor utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM Fornecedor entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);

  /**
   * Lista com paginação de acordo com a NamedQuery
   * 
   * @generated
   */
  @Query("select f from Fornecedor f")
  public Page<Fornecedor> list ( Pageable pageable );
  

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Estoque entity WHERE entity.fornecedor.id = :id")
  public Page<Estoque> findEstoque(@Param(value="id") java.lang.String id,  Pageable pageable );



  /**
   * ManyToOne Relation
   * @generated
   */
  @Query("SELECT entity.produto FROM Estoque entity WHERE entity.fornecedor.id = :id")
  public Page<Produto> listProduto(@Param(value="id") java.lang.String id,  Pageable pageable);

    /**
     * ManyToOne Relation Delete
     * @generated
     */
    @Modifying
    @Query("DELETE FROM Estoque entity WHERE entity.fornecedor.id = :instanceId AND entity.produto.id = :relationId")
    public int deleteProduto(@Param(value="instanceId") java.lang.String instanceId, @Param(value="relationId") java.lang.String relationId);




}