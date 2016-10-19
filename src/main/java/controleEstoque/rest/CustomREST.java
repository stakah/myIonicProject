package controleEstoque.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controleEstoque.business.CustomBusiness;
import controleEstoque.entity.DashboardInfoDTO;
import controleEstoque.entity.FaturamentoInfoDTO;
import controleEstoque.entity.LabelValueDTO;

@RestController
@RequestMapping(value = "/api/rest/controleEstoque/Custom")
public class CustomREST {

	@Autowired
	@Qualifier("CustomBusiness")
	private CustomBusiness customBusiness;
	
	@PersistenceContext(unitName = "controleEstoque")
  private EntityManager entityManager;


  //Obtem informações do dashboard - primeira parte
	@RequestMapping(method = RequestMethod.GET, value = "/DashboardInfo")
	public DashboardInfoDTO getDashboardInfo() throws Exception {

		DashboardInfoDTO info = new DashboardInfoDTO();
		info.totalVenda = customBusiness.getTotalVenda().intValue();
		info.totalFornecedor = customBusiness.getTotalFornecedor().intValue();
		info.totalCliente = customBusiness.getTotalCliente().intValue();
		info.totalProdutoSemEstoque = customBusiness.getTotalEstoqueZerado().intValue();
		return info;
	}
	
	//Obtem  info do grafico Faturamento do mês 
	@RequestMapping(method = RequestMethod.GET, value = "/Faturamento")
	public List<FaturamentoInfoDTO> getFaturamentoMes() throws Exception {
	  
    Date dateStart = getFirstDayOfMonth();
    Date dateEnd = getLastDayOfMonth();
	 
	  Query query = entityManager
	  .createQuery("select SUM(vi.quantidade * vi.estoque.valorVenda), vi.venda.dataVenda, SUM(vi.quantidade * vi.estoque.valorCompra) "+
	               "from VendaItem vi "+
	               "where vi.venda.dataVenda >= :dateStart and vi.venda.dataVenda <= :dateEnd "+
	               "group by vi.venda.dataVenda " +
	               "order by vi.venda.dataVenda")
	               .setParameter("dateStart", dateStart)
	               .setParameter("dateEnd", dateEnd);
	  Vector<Object> results = (Vector<Object>)query.getResultList();
	  List<FaturamentoInfoDTO> listFat = new ArrayList<FaturamentoInfoDTO>();
	  for (Object object : results) {
	    FaturamentoInfoDTO f = new FaturamentoInfoDTO();
	    f.valorVendaDia = (Double)((Object[])object)[0];
	    f.dataVenda = (Date)((Object[])object)[1];
	    f.valorCompraDia = (Double)((Object[])object)[2];
	    f.dataVendaFormat = getDateFormated(f.dataVenda);
	    listFat.add(f);
    }
	  return listFat;
	}
	
	//Obtem info do grafico quantidade de produtos
	@RequestMapping(method = RequestMethod.GET, value = "/ProdutoEstoque")
	public List<LabelValueDTO> getProdutoEstoque() throws Exception {
	  
	 
	  Query query = entityManager
	  .createQuery("select CONCAT(e.produto.marca, ' - ',e.produto.descricao), e.quantidade "+
	               "from Estoque e ");
	  Vector<Object> results = (Vector<Object>)query.getResultList();
	  List<LabelValueDTO> list = new ArrayList<LabelValueDTO>();
	  for (Object object : results) {
	    LabelValueDTO l = new LabelValueDTO();
	    l.label = (String)((Object[])object)[0];
	    l.value = String.valueOf((Long)((Object[])object)[1]);
	    list.add(l);
    }
	  return list;
	}
	
	private Date getLastDayOfMonth() {
	  Date currentDate = new Date();
	  Date dt = new Date(currentDate.getYear(), currentDate.getMonth(), 1);
	  Calendar c = Calendar.getInstance(); 
    c.setTime(dt); 
    c.add(Calendar.MONTH, 1);
    c.add(Calendar.DAY_OF_MONTH, -1);
    return new Date(currentDate.getYear(), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
  }
  
  private Date getFirstDayOfMonth() {
    Date currentDate = new Date();
    return new Date(currentDate.getYear(), currentDate.getMonth(), 1);
  }

  private String getDateFormated(Date date) {
    //Locale local = new Locale("pt","BR");
    //DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy",local);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    return dateFormat.format(date);
  }
}
