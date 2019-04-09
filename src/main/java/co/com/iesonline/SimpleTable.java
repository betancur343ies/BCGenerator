package co.com.iesonline;

import com.itextpdf.io.image.ImageDataFactory;

/*

This file is part of the iText (R) project.
Copyright (c) 1998-2016 iText Group NV

*/

//package com.itextpdf.samples.sandbox.tables;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
//import com.itextpdf.samples.GenericTest;
//import com.itextpdf.test.annotations.type.SampleTest;
//import org.junit.experimental.categories.Category;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;

//@Category(SampleTest.class)
public class SimpleTable {// extends GenericTest {
	public static final String DEST = "results/simple_table.pdf";
	public static final String WPLAYIMAGE = "./src/main/resources/img/wplay_logo.png";

	public static void main(String[] args) throws Exception {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new SimpleTable().manipulatePdf(DEST);
	}

	// @Override
	protected void manipulatePdf(String dest) throws Exception {
		Image wplayImage = new Image(ImageDataFactory.create(WPLAYIMAGE));
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Table ballsTable = new Table(5);
		ballsTable.setWidthPercent(50);
		ballsTable.setFixedPosition(200, 550, 200);

		/*for (int i = 0; i < 25; i++) {
			if (i != 12) {
				ballsTable.addCell(String.valueOf(i));
			} else {
				wplayImage.setAutoScale(true);
				ballsTable.addCell(wplayImage);
			}
		}*/
		
		Cell cell;
		Paragraph para;
		for (int i = 0; i < 25; i++) {

			cell = new Cell();
			cell.setBorder(Border.NO_BORDER);
			
			// setting balls
			if (i < 12) {
				para = new Paragraph(String.valueOf(i));
				para.setTextAlignment(TextAlignment.CENTER);
				para.setBold();

				cell.add(para);
			}

			if (i > 12) {
				para = new Paragraph(String.valueOf(i - 1));
				para.setTextAlignment(TextAlignment.CENTER);
				para.setBold();
				
				cell.add(para);
			}

			ballsTable.addCell(cell);
		}

		doc.add(ballsTable);
		doc.close();
	}
}