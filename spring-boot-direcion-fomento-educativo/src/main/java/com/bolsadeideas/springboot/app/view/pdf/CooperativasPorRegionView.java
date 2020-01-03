package com.bolsadeideas.springboot.app.view.pdf;

import java.awt.Color;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.bolsadeideas.springboot.app.models.entity.RegionesEscuelas;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/Pdfs/CooperativasRegiones")
public class CooperativasPorRegionView  extends AbstractPdfView{

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
		Paragraph l1 = new Paragraph("DIRECCION DE FOMENTO EDUCATIVO",fuente);
		l1.setAlignment(Paragraph.ALIGN_CENTER);

		Paragraph legendSecond = new Paragraph("SECRETARIA DE EDUCACION GUERRERO",fuente);
		legendSecond.setAlignment(Paragraph.ALIGN_CENTER);
		document.addTitle("Reporte de cooperativas por region");

		document.add(legendSecond);
		document.add(l1);
		document.add(Chunk.NEWLINE);

		// Cabecera
		PdfPCell celdaRenger = null;

		List<RegionesEscuelas> listaRegionesEscuelas = (List<RegionesEscuelas>) model.get("escuelas");
		PdfPTable tabla = new PdfPTable(2);

		celdaRenger = new PdfPCell(new Phrase("REGION"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		tabla.addCell(celdaRenger);
		celdaRenger = new PdfPCell(new Phrase("NUMERO DE COOPERATIVAS"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);

		tabla.addCell(celdaRenger);

		int totalEscuelas = 0;

		for (RegionesEscuelas regionesEscuelas : listaRegionesEscuelas) {
			tabla.addCell(regionesEscuelas.getMunicipio().toString().toUpperCase());

			celdaRenger = new PdfPCell(new Phrase(regionesEscuelas.getCantidadEscuelas() + ""));
			celdaRenger.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			tabla.addCell(celdaRenger);
			totalEscuelas += regionesEscuelas.getCantidadEscuelas();
		}

		tabla.addCell("Total de cooperativas: ");
		celdaRenger = new PdfPCell(new Phrase(totalEscuelas + ""));
		celdaRenger.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla.addCell(celdaRenger);
		document.addTitle("Lista De Cooperativas");
		
		tabla.setWidths(new float[] { 1, 1.5f });

		tabla.setSpacingAfter(20);

		document.add(tabla);

		
	}

}
