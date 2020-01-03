package com.bolsadeideas.springboot.app.view.pdf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bolsadeideas.springboot.app.models.entity.Biblioteca;
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.Item_acervo;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/Pdfs/AcervoBiblioteca")
public class AcervoView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Font fuente = new Font();
		fuente.setSize(15f);
		fuente.setStyle(Font.BOLD);
		// inicio cabecera

		Image imagen = Image.getInstance("iconosS/seg-principal-zapata.png");
		imagen.setAlignment(Image.ALIGN_CENTER);
		int indentation = 0;
		float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()
				- indentation) / imagen.getWidth()) * 85;
		imagen.scalePercent(scaler);
		document.add(imagen);
		Paragraph l1 = new Paragraph("DIRECCION DE FOMENTO EDUCATIVO", fuente);
		l1.setAlignment(Paragraph.ALIGN_CENTER);

		Paragraph legendSecond = new Paragraph("SECRETARIA DE EDUCACION GUERRERO", fuente);
		legendSecond.setAlignment(Paragraph.ALIGN_CENTER);
		document.addTitle("Reporte de escuelas por region");

		document.add(legendSecond);
		document.add(l1);
		document.add(Chunk.NEWLINE);

		PdfPCell celdaRenger = null;

		List<Item_acervo> listaAcervo = (ArrayList<Item_acervo>) model.get("acervo");
		// Datos de la escuela y bibliteoca
		Item_acervo item = listaAcervo.get(0);
		Biblioteca biblioteca = item.getBiblioteca();
		Escuela escuela = biblioteca.getEscuela();
		PdfPTable tablaDatosEscuela = new PdfPTable(2);
		celdaRenger = new PdfPCell(new Phrase("Datos de la escuela"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		celdaRenger.setColspan(2);
		tablaDatosEscuela.addCell(celdaRenger);
		tablaDatosEscuela.addCell("Escuela: ");
		tablaDatosEscuela.addCell(escuela.getNombre_escuela().toString().toLowerCase());
		tablaDatosEscuela.addCell("Direccion");
		tablaDatosEscuela.addCell(escuela.getDireccion().toString().toLowerCase());

		tablaDatosEscuela.addCell("Turno");
		tablaDatosEscuela.addCell(escuela.getTurno());
		tablaDatosEscuela.addCell("Tipo");
		tablaDatosEscuela.addCell(escuela.getTipo());
		tablaDatosEscuela.addCell("Telefono");
		tablaDatosEscuela.addCell(escuela.getTelefono());
		celdaRenger = new PdfPCell(new Phrase("Datos de la biblioteca"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		celdaRenger.setColspan(2);
		tablaDatosEscuela.addCell(celdaRenger);
		tablaDatosEscuela.addCell("Biblioteca");
		tablaDatosEscuela.addCell(biblioteca.getNombre_bibioteca());
		tablaDatosEscuela.addCell("Fecha de registro");
		tablaDatosEscuela.addCell(biblioteca.getFecha_registro().toString());
		tablaDatosEscuela.setSpacingAfter(18f);
		
		tablaDatosEscuela.setWidths(new float[] { 1,2f });
		document.add(tablaDatosEscuela);
		// fin Datos de la escuela y bibliteoca

		PdfPTable tabla = new PdfPTable(3);

		celdaRenger = new PdfPCell(new Phrase("ASIGNATURA"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		tabla.addCell(celdaRenger);
		celdaRenger = new PdfPCell(new Phrase("CANTIDAD"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		tabla.addCell(celdaRenger);
		celdaRenger = new PdfPCell(new Phrase("FECHA DE REGISTRO"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		tabla.addCell(celdaRenger);

		int cantidadAcervo = 0;
		for (Item_acervo item_acervo : listaAcervo) {
			tabla.addCell(new Phrase(item_acervo.getAsignatura().getAsignatura()));
			tabla.addCell(new Phrase(item_acervo.getCantidad() + ""));
			tabla.addCell(new Phrase(item_acervo.getFechaRegistro().toString()));
			cantidadAcervo++;
		}
		celdaRenger = new PdfPCell(new Phrase("Total: " + cantidadAcervo));
		celdaRenger.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celdaRenger.setColspan(3);
		celdaRenger.setPadding(8f);

		tabla.addCell(celdaRenger);
		tabla.setWidths(new float[] { 2f, 1, 2f });
		document.add(tabla);

	}

}
