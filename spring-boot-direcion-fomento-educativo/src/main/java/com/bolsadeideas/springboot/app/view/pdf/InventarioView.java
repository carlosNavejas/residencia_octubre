package com.bolsadeideas.springboot.app.view.pdf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bolsadeideas.springboot.app.models.entity.Item_inventario;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Component("/Pdfs/InventarioCooperativa")
public class InventarioView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Item_inventario> listaAcervo = (ArrayList<Item_inventario>) model.get("iventario");
	
		
		Paragraph p = new Paragraph("Hola mundo  "+listaAcervo.size());
		
		
		document.add(p);

	}

}
