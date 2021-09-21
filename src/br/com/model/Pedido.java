package br.com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

//anotação para informa a raiz do xml
@XmlRootElement
//anotação para acessar os atributos
@XmlAccessorType(XmlAccessType.FIELD)
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private Calendar dataPedido;
	private Calendar dataPagamento;
	private BigDecimal preco;
	
	
	@XmlElementWrapper(name = "itens")
	//anotação para informar o nome da lista
	@XmlElement(name="item")
	//anotação para definir os filhos da lista como item
	private Set<Item> itens = new LinkedHashSet<Item>();

	public Pedido() {
	}
	
	public Pedido(Integer codigo, Calendar dataPedido, Calendar dataPagamento, BigDecimal preco) {
		super();
		this.codigo = codigo;
		this.dataPedido = dataPedido;
		this.dataPagamento = dataPagamento;
		this.preco = preco;
	}

	public void adicionarItens(Item item) {
		this.itens.add(item);
	}
	public Set<Item> getItens() {
		return itens;
	}
	
	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Calendar getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Calendar dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Calendar getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", dataPedido=" + dataPedido.toString() + ", dataPagamento=" + dataPagamento.toString()
				+ ", preco=" + preco + ", itens=" + itens.toString() + "]";
	}

}
