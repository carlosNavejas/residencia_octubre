package com.bolsadeideas.springboot.app.view.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bolsadeideas.springboot.app.models.entity.Socio;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/Socios/listasocios")
public class SociosPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Socio> listaSocios = (List<Socio>) model.get("lista");
		PdfPTable tabla = new PdfPTable(1);
		for (Socio socio : listaSocios) {
			tabla.addCell(socio.getNombre() + " " + socio.getApellido_p());
			tabla.addCell(socio.getGrado());
			tabla.addCell(socio.getGrupo());
			tabla.addCell(socio.getTitular());
		}
		document.addTitle("Lista de socios activos");
		document.addAuthor("Carlos Dircio Nava");
		document.add(tabla);

	}

}
