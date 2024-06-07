package br.com.dw.relatorios.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.entidades.Defeito;
import br.com.dw.entidades.Responsavel;
import br.com.dw.entidades.Tipo;
import br.com.dw.relatorios.entidades.Defeito_componente;
import br.com.dw.relatorios.entidades.Defeito_produto;
import br.com.dw.relatorios.entidades.Defeito_qtde;
import br.com.dw.relatorios.entidades.Defeito_responsavel;
import br.com.dw.relatorios.servico.ServicoDefeito_componente;
import br.com.dw.relatorios.servico.ServicoDefeito_produto;
import br.com.dw.relatorios.servico.ServicoDefeito_qtde;
import br.com.dw.relatorios.servico.ServicoDefeito_responsavel;
import br.com.dw.servico.ServicoDefeito;
import br.com.dw.servico.ServicoResponsavel;
import br.com.dw.servico.ServicoTipo;
import io.quickchart.QuickChart;


@Named
@ViewScoped
public class BeanRelatorio01 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ServicoDefeito_qtde servico_defeito_qtde;
	private List<Defeito_qtde> lista_defeito_qtde = new ArrayList<Defeito_qtde>();
	
	@Inject
	private ServicoDefeito sdefeito;
	private List<Defeito> defeitos = new ArrayList<Defeito>();
	private Defeito defeito = new Defeito();
	
	@Inject
	private ServicoResponsavel sresponsavel;
	private List<Responsavel> responsaveis = new ArrayList<Responsavel>();
	private Responsavel responsavel = new Responsavel();
	
	@Inject
	private ServicoTipo stipo;
	private List<Tipo> tipos = new ArrayList<Tipo>();
	private Tipo tipo = new Tipo();
	
	@Inject
	private ServicoDefeito_componente servico_defeito_componente;
	private List<Defeito_componente> lista_defeito_componente = new ArrayList<Defeito_componente>();
	
	@Inject
	private ServicoDefeito_responsavel servico_defeito_responsavel;
	private List<Defeito_responsavel> lista_defeito_responsavel = new ArrayList<Defeito_responsavel>();
	
	@Inject
	private ServicoDefeito_produto servico_defeito_produto;
	private List<Defeito_produto> lista_defeito_produto = new ArrayList<Defeito_produto>();
	
	
	private Date data_grafico = new Date();
	private Date data_grafico2 = new Date();
	private int f_defeito,f_responsavel,f_tipo = -1;
	
	private String urlimg, urlimg2, urlimg3,urlimg4,urlimg5;
	
	@PostConstruct
	public void init() {
		defeitos = sdefeito.consultar();
		responsaveis = sresponsavel.consultar();
		tipos = stipo.consultar();
		
	}
	
	public void filtrar(){
		if(defeito == null) {
			f_defeito = -1;
		}else {
			f_defeito = defeito.getId();
		}
		
		if(responsavel == null) {
			f_responsavel = -1;
		}else {
			f_responsavel = responsavel.getId();
		}
		
		if(tipo == null) {
			f_tipo = -1;
		}else {
			f_tipo = tipo.getId();
		}
		
		lista_defeito_qtde = servico_defeito_qtde.defeito_qtde(f_defeito,data_grafico, data_grafico2, f_responsavel, f_tipo);
		lista_defeito_componente = servico_defeito_componente.defeito_componente(f_defeito, data_grafico, data_grafico2, f_responsavel, f_tipo);
		lista_defeito_responsavel = servico_defeito_responsavel.defeito_responsavel(f_defeito, data_grafico, data_grafico2, f_responsavel, f_tipo);
		lista_defeito_produto = servico_defeito_produto.defeito_produto(f_defeito, data_grafico, data_grafico2, f_responsavel, f_tipo);
	
		gerarimg();
		gerarimg2();
		gerarimg3();
		gerarimg4();
		gerarimg5();
				
	}
	
	private void gerarimg5() {
		urlimg5 = "";
		if(lista_defeito_produto.size()>0) {
			String[] defeito =  new String[lista_defeito_produto.size()];
			BigInteger[] qtde = new BigInteger[lista_defeito_produto.size()];
			
			for (int i = 0; i < lista_defeito_produto.size(); i++) {
				defeito[i]= lista_defeito_produto.get(i).getNome2();
				qtde[i]= lista_defeito_produto.get(i).getTotal();
			}
		
		QuickChart chart = new QuickChart("http","177.72.156.109",8854);
        chart.setWidth(2000);
        chart.setHeight(800);
        chart.setBackgroundColor("#ffffff");
        chart.setVersion("2");
        chart.setConfig("{ "
        		+ "  type: 'horizontalBar', "
        		+ "  data: { "
        		+ "    labels: "+Arrays.toString(defeito)+", "
        		+ "    datasets: [ "
        		+ "      { "
        		+ "        data: "+Arrays.toString(qtde)+", "
        		+ "        backgroundColor: getGradientFillHelper('vertical', ['red', '#FF7256','#1E90FF','#00BFFF']), "
        		+ "      }, "
        		+ "    ], "
        		+ "  }, "
        		+ "  options: { "
        		+ "    legend: { display: false }, "
        		+ "    responsive: true, "
        		+ "    plugins: { "
        		+ "      datalabels: { "
        		+ "		   font: { weight: 'bold', size: 12, },	"
        		+ "        anchor: 'end', "
        		+ "        align: 'center', "
        		+ "        color: '#fff', "
        		+ "        backgroundColor: 'rgb(34, 139, 34, 0.6)', "
        		+ "        formatter: (value) => { "
        		+ "          return value + ''; "
        		+ "        }, "
        		+ "      }, "
        		+ "    }, "
        		+ "  }, "
        		+ "}");

       
        //System.out.println(chart.getUrl());
        
        urlimg5 = chart.getUrl();
		
		}
	}
	
	private void gerarimg4() {
		urlimg4 = "";
		if(lista_defeito_responsavel.size()>0) {
			String[] defeito =  new String[lista_defeito_responsavel.size()];
			BigDecimal[] qtde = new BigDecimal[lista_defeito_responsavel.size()];
			
			for (int i = 0; i < lista_defeito_responsavel.size(); i++) {
				defeito[i]= lista_defeito_responsavel.get(i).getNome2();
				qtde[i]= lista_defeito_responsavel.get(i).getPercentual();
			}
		
		QuickChart chart = new QuickChart("http","177.72.156.109",8854);
        chart.setWidth(1000);
        chart.setHeight(500);
        chart.setBackgroundColor("#ffffff");
        chart.setVersion("2");
        chart.setConfig("{ "
        		+ "  type: 'horizontalBar', "
        		+ "  data: { "
        		+ "    labels: "+Arrays.toString(defeito)+", "
        		+ "    datasets: [ "
        		+ "      { "
        		+ "        data: "+Arrays.toString(qtde)+", "
        		+ "        backgroundColor: getGradientFillHelper('vertical', ['red', '#FF7256','#1E90FF','#00BFFF']), "
        		+ "      }, "
        		+ "    ], "
        		+ "  }, "
        		+ "  options: { "
        		+ "    legend: { display: false }, "
        		+ "    responsive: true, "
        		+ "    plugins: { "
        		+ "      datalabels: { "
        		+ "		   font: { weight: 'bold', size: 12, },	"
        		+ "        anchor: 'end', "
        		+ "        align: 'center', "
        		+ "        color: '#fff', "
        		+ "        backgroundColor: 'rgb(34, 139, 34, 0.6)', "
        		+ "        formatter: (value) => { "
        		+ "          return value + '%'; "
        		+ "        }, "
        		+ "      }, "
        		+ "    }, "
        		+ "  }, "
        		+ "}");

       
        //System.out.println(chart.getUrl());
        
        urlimg4 = chart.getUrl();
		
		}
	}	
	
	private void gerarimg3() {
		urlimg3 = "";
		if(lista_defeito_componente.size()>0) {
			String[] defeito =  new String[lista_defeito_componente.size()];
			BigInteger[] qtde = new BigInteger[lista_defeito_componente.size()];
			
			for (int i = 0; i < lista_defeito_componente.size(); i++) {
				defeito[i]= lista_defeito_componente.get(i).getNome2();
				qtde[i]= lista_defeito_componente.get(i).getTotal();
			}
		
		QuickChart chart = new QuickChart("http","177.72.156.109",8854);
        chart.setWidth(1000);
        chart.setHeight(500);
        chart.setBackgroundColor("#ffffff");
        chart.setVersion("2");
        chart.setConfig("{ "
        		+ "  type: 'bar', "
        		+ "  data: { "
        		+ "    labels: "+Arrays.toString(defeito)+", "
        		+ "    datasets: [ "
        		+ "      { "
        		+ "        data: "+Arrays.toString(qtde)+", "
        		+ "        backgroundColor: getGradientFillHelper('horizontal', ['red', '#FF7256','#1E90FF','#00BFFF']), "
        		+ "      }, "
        		+ "    ], "
        		+ "  }, "
        		+ "  options: { "
        		+ "     scales: { "
        		+ "      xAxes: [{ "
        		+ "        ticks: { "
        		+ "          minRotation: 90 "
        		+ "        } "
        		+ "      }] "
        		+ "    },"
        		+ "    layout: { padding: {top: 35 } }, "
        		+ "    legend: { display: false }, "
        		+ "    responsive: true, "
        		+ "    plugins: { "
        		+ "      datalabels: { "
        		+ "        anchor: 'end', "
        		+ "        align: 'top', "
        		+ "        color: '#fff', "
        		+ "        backgroundColor: 'rgba(34, 139, 34, 0.6)', "
        		+ "        borderColor: 'rgba(34, 139, 34, 1.0)', "
        		+ "        borderWidth: 1, "
        		+ "        borderRadius: 5, "
        		+ "        font: { weight: 'bold', size: 12, }, "
        		+ "      }, "
        		+ "    }, "
        		+ "  }, "
        		+ "}");

       
        //System.out.println(chart.getUrl());
        
        urlimg3 = chart.getUrl();
		
		}
	}
	
	private void gerarimg2() {
		urlimg2 = "";
		if(lista_defeito_componente.size()>0) {
			String[] defeito =  new String[lista_defeito_componente.size()];
			BigDecimal[] qtde = new BigDecimal[lista_defeito_componente.size()];
			
			for (int i = 0; i < lista_defeito_componente.size(); i++) {
				defeito[i]= lista_defeito_componente.get(i).getNome2();
				qtde[i]= lista_defeito_componente.get(i).getPercentual();
			}
		
		QuickChart chart = new QuickChart("http","177.72.156.109",8854);
        chart.setWidth(1000);
        chart.setHeight(500);
        chart.setBackgroundColor("#ffffff");
        chart.setVersion("2");
        chart.setConfig("{ "
        		+ "  type: 'horizontalBar', "
        		+ "  data: { "
        		+ "    labels: "+Arrays.toString(defeito)+", "
        		+ "    datasets: [ "
        		+ "      { "
        		+ "        data: "+Arrays.toString(qtde)+", "
        		+ "        backgroundColor: getGradientFillHelper('vertical', ['red', '#FF7256','#1E90FF','#00BFFF']), "
        		+ "      }, "
        		+ "    ], "
        		+ "  }, "
        		+ "  options: { "
        		+ "    legend: { display: false }, "
        		+ "    responsive: true, "
        		+ "    plugins: { "
        		+ "      datalabels: { "
        		+ "		   font: { weight: 'bold', size: 12, },	"
        		+ "        anchor: 'end', "
        		+ "        align: 'center', "
        		+ "        color: '#fff', "
        		+ "        backgroundColor: 'rgb(34, 139, 34, 0.6)', "
        		+ "        formatter: (value) => { "
        		+ "          return value + '%'; "
        		+ "        }, "
        		+ "      }, "
        		+ "    }, "
        		+ "  }, "
        		+ "}");

       
        //System.out.println(chart.getUrl());
        
        urlimg2 = chart.getUrl();
		
		}
	}
	
	private void gerarimg() {
		urlimg = "";
		if(lista_defeito_qtde.size()>0) {
			String[] defeito =  new String[lista_defeito_qtde.size()];
			BigDecimal[] qtde = new BigDecimal[lista_defeito_qtde.size()];
			
			for (int i = 0; i < lista_defeito_qtde.size(); i++) {
				defeito[i]= lista_defeito_qtde.get(i).getNome2();
				qtde[i]= lista_defeito_qtde.get(i).getPercentual();
			}
		
		QuickChart chart = new QuickChart("http","177.72.156.109",8854);
        chart.setWidth(1000);
        chart.setHeight(500);
        chart.setVersion("2");
        chart.setConfig("{ "
        		+ "  type: 'horizontalBar', "
        		+ "  data: { "
        		+ "    labels: "+Arrays.toString(defeito)+", "
        		+ "    datasets: [ "
        		+ "      { "
        		+ "        data: "+Arrays.toString(qtde)+", "
        		+ "       backgroundColor: getGradientFillHelper('vertical', ['red', '#FF7256','#1E90FF','#00BFFF']), "
        		+ "      }, "
        		+ "    ], "
        		+ "  }, "
        		+ "  options: { "
        		+ "    legend: { display: false }, "
        		+ "    responsive: true, "
        		+ "    plugins: { "
        		+ "      datalabels: { "
        		+ "		   font: { weight: 'bold', size: 12, },	"
        		+ "        anchor: 'end', "
        		+ "        align: 'center', "
        		+ "        color: '#fff', "
        		+ "        backgroundColor: 'rgb(34, 139, 34, 0.6)', "
        		+ "        formatter: (value) => { "
        		+ "          return value + '%'; "
        		+ "        }, "
        		+ "      }, "
        		+ "    }, "
        		+ "  }, "
        		+ "}");

       
        //System.out.println(chart.getUrl());
        
        urlimg = chart.getUrl();
		
		}
	}

	public List<Defeito_produto> getLista_defeito_produto() {
		return lista_defeito_produto;
	}

	public void setLista_defeito_produto(List<Defeito_produto> lista_defeito_produto) {
		this.lista_defeito_produto = lista_defeito_produto;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getUrlimg5() {
		return urlimg5;
	}

	public void setUrlimg5(String urlimg5) {
		this.urlimg5 = urlimg5;
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Defeito_responsavel> getLista_defeito_responsavel() {
		return lista_defeito_responsavel;
	}

	public void setLista_defeito_responsavel(List<Defeito_responsavel> lista_defeito_responsavel) {
		this.lista_defeito_responsavel = lista_defeito_responsavel;
	}

	public String getUrlimg4() {
		return urlimg4;
	}

	public void setUrlimg4(String urlimg4) {
		this.urlimg4 = urlimg4;
	}

	public String getUrlimg3() {
		return urlimg3;
	}

	public void setUrlimg3(String urlimg3) {
		this.urlimg3 = urlimg3;
	}

	public List<Defeito_componente> getLista_defeito_componente() {
		return lista_defeito_componente;
	}

	public void setLista_defeito_componente(List<Defeito_componente> lista_defeito_componente) {
		this.lista_defeito_componente = lista_defeito_componente;
	}

	public String getUrlimg2() {
		return urlimg2;
	}

	public void setUrlimg2(String urlimg2) {
		this.urlimg2 = urlimg2;
	}

	public List<Defeito> getDefeitos() {
		return defeitos;
	}

	public void setDefeitos(List<Defeito> defeitos) {
		this.defeitos = defeitos;
	}

	public Defeito getDefeito() {
		return defeito;
	}

	public void setDefeito(Defeito defeito) {
		this.defeito = defeito;
	}

	public List<Defeito_qtde> getLista_defeito_qtde() {
		return lista_defeito_qtde;
	}

	public void setLista_defeito_qtde(List<Defeito_qtde> lista_defeito_qtde) {
		this.lista_defeito_qtde = lista_defeito_qtde;
	}

	public Date getData_grafico() {
		return data_grafico;
	}

	public void setData_grafico(Date data_grafico) {
		this.data_grafico = data_grafico;
	}

	public Date getData_grafico2() {
		return data_grafico2;
	}

	public void setData_grafico2(Date data_grafico2) {
		this.data_grafico2 = data_grafico2;
	}

	public String getUrlimg() {
		return urlimg;
	}

	public void setUrlimg(String urlimg) {
		this.urlimg = urlimg;
	}
	
	

}
