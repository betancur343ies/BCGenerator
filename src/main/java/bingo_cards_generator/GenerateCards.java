package bingo_cards_generator;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;

import java.io.File;

public class GenerateCards {
	public static final String DEST = "results/bingo_cards.pdf";
	public static final String CARD_IMAGE = "./src/main/resources/img/Bingo Poderoso (Tabla).png";
	public static final String CONDITION_IMAGE = "./src/main/resources/img/Bingo Poderoso (Tabla).png";

	
	public static void main(String[] args) throws Exception {
	    File file = new File(DEST);
	    file.getParentFile().mkdirs();
	    new GenerateCards().manipulatePdf(DEST);
	}
	
	protected void manipulatePdf(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
	    PageSize pageSize = new PageSize(PageSize.A4.decreaseHeight((float) 1.76));

	    Document doc = new Document(pdfDoc, pageSize);
	    doc.setMargins(0, 0, 0, 0);	 
	    
	    Image image = new Image(ImageDataFactory.create(CARD_IMAGE));	    
	    Table table = new Table(1);    
	      
	    for (int i = 0; i < 2; i++) {
	        table.addCell(image.setAutoScale(true));
	    }
	    
	    doc.add(table);
	    doc.close();
	}
}