package bingo_cards_generator;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;

/**
 * 
 * @author Betancur
 *
 */
public class GenerateCards {
	public static final String DEST = "results/bingo_cards.pdf";
	public static final String CARDIMAGE = "./src/main/resources/img/Bingo Poderoso (Tabla).png";
	public static final String WPLAYIMAGE = "./src/main/resources/img/wplay_logo.png";
	public static final String CONDITIONIMAGE = "./src/main/resources/img/Bingo Poderoso (Tabla).png";

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new GenerateCards().manipulatePdf(DEST);
	}

	/**
	 * 
	 * @param dest
	 * @throws Exception
	 */
	protected void manipulatePdf(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		PageSize pageSize = new PageSize(PageSize.A4);

		Document doc = new Document(pdfDoc, pageSize);
		doc.setMargins(0, 0, 0, 0);

		Image cardImage = new Image(ImageDataFactory.create(CARDIMAGE));      
        cardImage.setAutoScale(true);
		Image wplayImage = new Image(ImageDataFactory.create(WPLAYIMAGE));	    
		wplayImage.setAutoScale(true);
    
		Table ballsTable = getBallsTable(wplayImage);
		Paragraph tableNum = new Paragraph("12521");
		tableNum.setFontSize(20);
		
		int totalCards = 1;
        for (int i = 0; i < totalCards; i++) {
        	
    		cardImage.setFixedPosition(i + 1, 0, 450);    		
    		ballsTable.setFixedPosition(179, 542, 216);
    	    doc.add(cardImage);
    	    doc.add(ballsTable);
    	    
    	    cardImage.setFixedPosition(i + 1, 0, 50);    		
    		ballsTable.setFixedPosition(179, 142, 216);
    	    doc.add(cardImage);
    	    doc.add(ballsTable);
    	    
    	    tableNum.setRotationAngle(1.57);
    	    tableNum.setFixedPosition(35, 536, 200);
    	    doc.add(tableNum);
    	    
    	    tableNum.setFixedPosition(35, 136, 200);
    	    doc.add(tableNum);
	    }
		
		doc.close();
		
	}
	
	/**
	 * 
	 * @param centerImg
	 * @param cardImg
	 * @return
	 */
	private Table getBallsTable(Image centerImg) throws Exception{
		PdfFont font = PdfFontFactory.createFont(FontProgramFactory.createFont(FontConstants.HELVETICA_BOLD));
        Paragraph para;
		
		Table ballsTable = new Table(5);
		
		for (int i = 0; i < 25; i++) {
			Cell cell = new Cell();
	        cell.setBorder(Border.NO_BORDER);
			if (i != 12) {
				para = new Paragraph(String.valueOf(i)).setFont(font);
				para.setFontSize(15);
		        para.setFixedLeading(0);
		        para.setMultipliedLeading(1);
		        cell.setHeight(50);
		        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
		        cell.add(para);
			}
			else {	    	
				cell.add(centerImg);
			}			
			
			cell.setRotationAngle(1.57);
			ballsTable.addCell(cell);
		}
		
		return ballsTable;
	}

}