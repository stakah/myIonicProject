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
@Repository("VendaItemDAO")
@Transactional(transactionManager="controleEstoque-TransactionManager")
public interface VendaItemDAO extends JpaRepository<VendaItem, java.lang.String> {

  /**
   * Obtém a instância de VendaItem utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM VendaItem entity WHERE entity.id = :id")
  public VendaItem findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de VendaItem utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM VendaItem entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);

  /**
   * Lista com paginação de acordo com a NamedQuery
   * 
   * @generated
   */
  @Query("select v from VendaItem v")
  public Page<VendaItem> list ( Pageable pageable );
  
  @Modifying
  @Query("DELETE FROM VendaItem entity WHERE entity.venda.id = :id")
  public void deleteVendaItemFromVendaId(@Param(value="id") java.lang.String id);






}