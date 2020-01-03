package com.bolsadeideas.springboot.app.view.pdf;

import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Phaser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bolsadeideas.springboot.app.models.entity.Cooperativa;
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.RegionesEscuelas;
import com.bolsadeideas.springboot.app.models.entity.Socio;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/Pdfs/sociosPorCooperativa")
public class SociosPdfView extends AbstractPdfView {

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

		// Cabecera
		PdfPCell celdaRenger = null;

		ArrayList<Socio> listaSocios = (ArrayList<Socio>) model.get("socios");
		// datos de la escuela
		Socio so = listaSocios.get(0);
		Cooperativa co = so.getCooperativa();
		Escuela escuela = co.getEscuela();
		PdfPTable tablaDatosEscuela = new PdfPTable(2);
		celdaRenger = new PdfPCell(new Phrase("Datos de la escuela"));
		celdaRenger.setColspan(2);
		celdaRenger.setPadding(4f);
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
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

		celdaRenger = new PdfPCell(new Phrase("Datos de la cooperativa"));
		celdaRenger.setColspan(2);
		celdaRenger.setPadding(4f);
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		tablaDatosEscuela.addCell(celdaRenger);
		tablaDatosEscuela.addCell("Cooperativa");
		tablaDatosEscuela.addCell(co.getNombre_cooperativa());
		tablaDatosEscuela.addCell("Tipo");
		tablaDatosEscuela.addCell(co.getTipo());
		tablaDatosEscuela.addCell("Fecha de registro");
		tablaDatosEscuela.addCell(co.getFecha_registro().toString());
		tablaDatosEscuela.setWidths(new float[] { 1, 2.5f });
		tablaDatosEscuela.setSpacingAfter(15f);
		document.add(tablaDatosEscuela);
		// fin datos de la escuela y cooperativa
		PdfPTable tabla = new PdfPTable(5);

		celdaRenger = new PdfPCell(new Phrase("Nombre"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		tabla.addCell(celdaRenger);
		celdaRenger = new PdfPCell(new Phrase("Grado"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		tabla.addCell(celdaRenger);
		celdaRenger = new PdfPCell(new Phrase("Grupo"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		tabla.addCell(celdaRenger);
		celdaRenger = new PdfPCell(new Phrase("Titular"));

		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		tabla.addCell(celdaRenger);
		celdaRenger = new PdfPCell(new Phrase("Fecha de registro"));
		celdaRenger.setBackgroundColor(new Color(3, 252, 211));
		celdaRenger.setPadding(8f);
		tabla.addCell(celdaRenger);
		int totalEscuelas = 0;

		Font fuentes = new Font();

		fuentes.setSize(40f);

		Phrase phrase = null;

		for (Socio socio : listaSocios) {

			phrase = new Phrase(socio.getNombre() + " " + socio.getApellido_p() + " " + socio.getApellido_m());
			tabla.addCell(phrase);
			phrase = new Phrase(socio.getGrado());

			tabla.addCell(phrase);

			phrase = new Phrase(socio.getGrupo());

			tabla.addCell(phrase);

			phrase = new Phrase(socio.getTitular());

			tabla.addCell(phrase);

			phrase = new Phrase(socio.getFecha_registro().toString());

			tabla.addCell(phrase);
			totalEscuelas++;
		}
		celdaRenger = new PdfPCell(new Phrase("Total de socios: " + totalEscuelas));
		celdaRenger.setPadding(5f);
		celdaRenger.setColspan(5);
		celdaRenger.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla.addCell(celdaRenger);

		tabla.setWidths(new float[] { 2f, 1, 1, 2f, 2f });
		document.add(tabla);

	}

}
