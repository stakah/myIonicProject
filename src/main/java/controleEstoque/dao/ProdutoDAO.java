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
@Repository("ProdutoDAO")
@Transactional(transactionManager="controleEstoque-TransactionManager")
public interface ProdutoDAO extends JpaRepository<Produto, java.lang.String> {

  /**
   * Obtém a instância de Produto utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM Produto entity WHERE entity.id = :id")
  public Produto findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de Produto utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM Produto entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);

  /**
   * Lista com paginação de acordo com a NamedQuery
   * 
   * @generated
   */
  @Query("select p from Produto p")
  public Page<Produto> list ( Pageable pageable );
  

  /**
   * OneToMany Relation
   * @generated
   */
  @Query("SELECT entity FROM Estoque entity WHERE entity.produto.id = :id")
  public Page<Estoque> findEstoque(@Param(value="id") java.lang.String id,  Pageable pageable );



  /**
   * ManyToOne Relation
   * @generated
   */
  @Query("SELECT entity.fornecedor FROM Estoque entity WHERE entity.produto.id = :id")
  public Page<Fornecedor> listFornecedor(@Param(value="id") java.lang.String id,  Pageable pageable);

    /**
     * ManyToOne Relation Delete
     * @generated
     */
    @Modifying
    @Query("DELETE FROM Estoque entity WHERE entity.produto.id = :instanceId AND entity.fornecedor.id = :relationId")
    public int deleteFornecedor(@Param(value="instanceId") java.lang.String instanceId, @Param(value="relationId") java.lang.String relationId);




}