package com.ampcarsoft.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampcarsoft.entity.Coche;
import com.ampcarsoft.entity.User;
import com.ampcarsoft.repository.CocheRepository;
import com.ampcarsoft.repository.UserRepository;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class InformeService {

	private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
	private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

	private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	
	@Autowired
	private CocheRepository dao;
	
	@Autowired
	private UserRepository daoUser;

	public byte[] generarInforme() throws Exception {
		return showHelp();
	}
	
	public byte[] showHelp() throws Exception {

	    Document document = new Document();
	    ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    PdfWriter.getInstance(document, stream);
	    document.open();
	    
        // Primera página 
        Chunk chunk = new Chunk("AMPCARSOFT", chapterFont);
        chunk.setBackground(BaseColor.GRAY);
        Chapter chapter = new Chapter(new Paragraph(chunk), 1);
        chapter.setNumberDepth(0);
        chapter.add(new Paragraph("Informe anual", paragraphFont));       
        document.add(chapter);

        // LISTADO DE VEHICULOS
        Anchor anchor = new Anchor("Listado de vehículos", categoryFont);
        anchor.setName("Table export to PDF (Exportamos la tabla a PDF)");            
        Chapter chapTitle = new Chapter(new Paragraph(anchor), 1);
        Integer numColumns = 5;

        PdfPTable table = new PdfPTable(numColumns); 
        table.addCell(new PdfPCell(new Phrase("Matricula")));
        table.addCell(new PdfPCell(new Phrase("Marca")));
        table.addCell(new PdfPCell(new Phrase("Modelo")));
        table.addCell(new PdfPCell(new Phrase("Potencia")));
        table.addCell(new PdfPCell(new Phrase("Cliente")));

        table.setHeaderRows(1);
        
        List<Coche> coches = (List<Coche>) dao.findAll();
        for (int row = 0; row < coches.size(); row++) {
            table.addCell(coches.get(row).getMatricula());
            table.addCell(coches.get(row).getMarca());
            table.addCell(coches.get(row).getModelo());
            table.addCell(String.valueOf(coches.get(row).getPotencia()));
            table.addCell(coches.get(row).getCliente().getUsername());
        }
        chapTitle.add(table);
        document.add(chapTitle);
	    
        // LISTADO DE USUARIOS
        Anchor anchor2 = new Anchor("Listado de usuarios", categoryFont);
        Chapter chapTitle2 = new Chapter(new Paragraph(anchor2), 2);
        Integer numColumns2 = 4;

        PdfPTable table2 = new PdfPTable(numColumns2); 
        table2.addCell(new PdfPCell(new Phrase("Nombre")));
        table2.addCell(new PdfPCell(new Phrase("Apellidos")));
        table2.addCell(new PdfPCell(new Phrase("Edad")));
        table2.addCell(new PdfPCell(new Phrase("Username")));

        table2.setHeaderRows(1);
        
        List<User> usuarios = (List<User>) daoUser.findAll();
        for (int row = 0; row < usuarios.size(); row++) {
        	table2.addCell(usuarios.get(row).getNombre());
        	table2.addCell(usuarios.get(row).getApellidos());
        	table2.addCell(String.valueOf(usuarios.get(row).getEdad()));
        	table2.addCell(usuarios.get(row).getUsername());
        }
        chapTitle2.add(table2);
        document.add(chapTitle2);
	    
	   
	    document.close();

	    return stream.toByteArray();   
	}

}
