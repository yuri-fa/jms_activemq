package br.com.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.model.Item;
import br.com.model.Pedido;

public class PedidoFactory {
	
	public static Pedido gerarPedidoComValores() {
		Pedido pedido = new Pedido(12,gerarData("08/09/2021"),gerarData("08/09/2021"),new BigDecimal(12345L));
		pedido.adicionarItens(gerarItem(1,"drogas"));
		pedido.adicionarItens(gerarItem(2, "cachaca"));
		pedido.adicionarItens(gerarItem(3, "rock roll"));
		return pedido;
	}
	
	private static Item gerarItem(Integer codigo, String nome) {
		return new Item(codigo,nome);
	}
	
	private static Calendar gerarData(String data) {
		Calendar calendar = Calendar.getInstance();
		try {
			Date date = new SimpleDateFormat("dd/MM/yyy").parse(data);
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
	}

}
