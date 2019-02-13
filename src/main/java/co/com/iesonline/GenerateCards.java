package co.com.iesonline;

import java.io.File;
import java.util.Arrays;

import com.itextpdf.io.font.PdfEncodings;
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
import com.itextpdf.layout.property.TextAlignment;

/**
 * 
 * @author B3tancur343
 *
 */
public class GenerateCards {
	
	public static final String FONT = "./src/main/resources/fonts/a_AlternaOtl.ttf";
	
	public static final String CARDS_PDF_LOCATION = "results/bingo_cards.pdf";
	public static final String CONDITIONS_PDF_LOCATION = "results/bingo_conditions.pdf";

	public static final String CARD_IMAGE_LOCATION = "./src/main/resources/img/Bingo Poderoso (Tabla).png";
	public static final String ICON_LOCATION = "./src/main/resources/img/wplay_logo.png";
	public static final String CONDITIONS_IMAGE_LOCATION = "./src/main/resources/img/Bingo Condiciones.png";

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		File file = new File(CARDS_PDF_LOCATION);
		file.getParentFile().mkdirs();
		new GenerateCards().manipulateCardsPdf(CARDS_PDF_LOCATION);
		
		File file1 = new File(CONDITIONS_PDF_LOCATION);
		file1.getParentFile().mkdirs();
		new GenerateCards().manipulateConditionsPdf(CONDITIONS_PDF_LOCATION);
	}

	/**
	 * 
	 * @param dest
	 * @throws Exception
	 */
	protected void manipulateCardsPdf(String dest) throws Exception {
		
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		PageSize pageSize = new PageSize(PageSize.A4);

		Document doc = new Document(pdfDoc, pageSize);
		doc.setMargins(0, 0, 0, 0);

		Image cardImage = new Image(ImageDataFactory.create(CARD_IMAGE_LOCATION));      
        cardImage.setAutoScale(true);
		Image wplayImage = new Image(ImageDataFactory.create(ICON_LOCATION));	    
		wplayImage.setAutoScale(true);
		
		Table ballsTable = getBallsTable(wplayImage);
		Paragraph tableNum = new Paragraph("12521");
		tableNum.setFontSize(20);
		
		//TODO Set total cards.
		int totalCards = 10;
		
		//TODO Set width and coordinates of balls table before generate pdf's.
		int tableWidth = 205;
		int xCard1 = 188;
		int yCard1 = 548;
		int xCard2 = xCard1;
		int yCard2 = 148;
		
		for (int p = 0; p < totalCards/2; p++) {
	        
			pdfDoc.addNewPage(new PageSize(pageSize));	        
	    
	        for (int i = 0; i < 2; i++) {    		

	    	    //card1	    	    
    			cardImage.setFixedPosition(p + 1, 0, 450);				
				ballsTable.setFixedPosition(p + 1, xCard1, yCard1, tableWidth);
				ballsTable.setHeight(100);
	    	    doc.add(cardImage);
	    	    doc.add(ballsTable);

	    	    tableNum.setRotationAngle(1.57);
	    	    tableNum.setFixedPosition(p + 1, 35, 536, 200);
	    	    doc.add(tableNum);
	    	    
	    	    //card2	    	    
	    	    cardImage.setFixedPosition(p + 1, 0, 50);    		
	    		ballsTable.setFixedPosition(p + 1, xCard2, yCard2, tableWidth);
	    	    doc.add(cardImage);
	    	    doc.add(ballsTable);
	    	    
//	    	    tableNum.setRotationAngle(1.57);
	    	    tableNum.setFixedPosition(p + 1, 35, 136, 200);
	    	    doc.add(tableNum);
		    }
		}
		
		doc.close();		
	}
	
	/**
	 * 
	 * @param dest
	 * @throws Exception
	 */
	protected void manipulateConditionsPdf(String dest) throws Exception {
		
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		PageSize pageSize = new PageSize(PageSize.A4);

		Document doc = new Document(pdfDoc, pageSize);
		doc.setMargins(0, 0, 0, 0);

		Image conditionsImage = new Image(ImageDataFactory.create(CONDITIONS_IMAGE_LOCATION));    
		conditionsImage.setAutoScale(true);
		
		int cardsTotal = 10;
		for (int p = 0; p < cardsTotal/2; p++) {
	        pdfDoc.addNewPage(new PageSize(pageSize));
	        
			conditionsImage.setFixedPosition(p + 1, 0, 450);    		
    	    doc.add(conditionsImage);
    	    
    	    conditionsImage.setFixedPosition(p + 1, 0, 50);    		
    	    doc.add(conditionsImage);
		}
		
		doc.close();		
	}

	/**
	 * 
	 * @param arrays
	 * @return
	 */
	public static int[] joinArray(int[]... arrays) {
        
		int length = 0;
        int offset = 0;
        

        for (int[] array : arrays) {
            length += array.length;
        }

        final int[] result = new int[length];
        
        for (int[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }
	
	/**
	 * 
	 * @param lista
	 * @return
	 */
	public String[] realignTableIds(int[] lista) {
				
		int[] columna1 = Arrays.copyOfRange(lista, 0, 5);
		int[] columna2 = Arrays.copyOfRange(lista, 5, 10);
		int[] columna3 = Arrays.copyOfRange(lista, 10, 14);
		int[] columna4 = Arrays.copyOfRange(lista, 14, 19);
		int[] columna5 = Arrays.copyOfRange(lista, 19, 24);
		
//		System.out.println(Arrays.toString(columna1));
//		System.out.println(Arrays.toString(columna2));
//		System.out.println(Arrays.toString(columna3));
//		System.out.println(Arrays.toString(columna4));
//		System.out.println(Arrays.toString(columna5));
		
		int[] concatAll = joinArray(columna5,columna4,columna3, columna2, columna1);
//		System.out.println(Arrays.toString(concatAll));
		
		String[] result = new String[24];
		
		for (int i = 0; i < concatAll.length; i++) {
			String value = String.valueOf(concatAll[i]);
			result[i] = value;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param centerImg
	 * @param cardImg
	 * @return
	 */
	private Table getBallsTable(Image centerImg) throws Exception {
		
		PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.WINANSI, true);		
        
        int[] balls = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23}; 
        
        String[] organizar = realignTableIds(balls);
        System.out.println("organizar:" + Arrays.toString(organizar));
        
		Table ballsTable = new Table(5);
        Paragraph para = null;
        Cell cell = null;
		
		for (int i = 0; i < 25; i++) {
			
			cell = new Cell();			
	        cell.setBorder(Border.NO_BORDER);
			cell.setHeight(30);
			cell.setWidth(45);
//	        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);

			cell.setRotationAngle(1.57);
	        
			if (i < 12) {
				para = new Paragraph(organizar[i]).setFont(font);
				para.setTextAlignment(TextAlignment.CENTER);
				para.setFontSize(20);
//		        para.setFixedLeading(0);
//		        para.setMultipliedLeading(1);
		        para.setBold();
		        
		        cell.add(para);
			}
			
			if (i > 12) {
				para = new Paragraph(organizar[i-1]).setFont(font);	
				para.setTextAlignment(TextAlignment.CENTER);
				para.setFontSize(20);
//		        para.setFixedLeading(0);
//		        para.setMultipliedLeading(1);
		        para.setBold();
				cell.add(para);	   
			}
			
			if (i == 12) {	    	
//				cell.add(centerImg);
			}	
			
			
			ballsTable.addCell(cell);
		}
		
		return ballsTable;
	}
	
}