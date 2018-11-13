package bingo_cards_generator;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
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
    
		int totalCards = 2;
        for (int i = 0; i < totalCards; i++) {
        	
    		cardImage.setFixedPosition(i + 1, 0, 450);
    	    doc.add(cardImage);
    	    
    	    cardImage.setFixedPosition(i + 1, 0, 50);
    	    doc.add(cardImage);
	    }
		
		doc.close();
	}

}