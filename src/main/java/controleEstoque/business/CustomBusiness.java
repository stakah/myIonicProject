package controleEstoque.business;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import controleEstoque.dao.*;
import controleEstoque.entity.*;
import java.util.*;

@Service("CustomBusiness")
public class CustomBusiness {

	@Autowired
	@Qualifier("CustomDAO")
	protected CustomDAO repository;

	public Long getTotalVenda() {
		return repository.getTotalVenda();
	}
	
	public Long getTotalEstoqueZerado() {
		return repository.getTotalEstoqueZerado();
	}
	
	public Long getTotalCliente() {
	  return repository.getTotalCliente();
	}
	
	public Long getTotalFornecedor() {
	  return repository.getTotalFornecedor();
	}
	
/*	public List<FaturamentoInfoDTO> getFaturamentoMes() {
	  return repository.getFaturamentoMes();
	}*/

}
