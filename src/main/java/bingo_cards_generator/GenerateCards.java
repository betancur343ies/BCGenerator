package bingo_cards_generator;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.log.SystemOutCounter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Spliterator.OfInt;
import java.util.stream.Stream;

/**
 * 
 * @author Betancur
 *
 */
public class GenerateCards {
	public static final String DEST = "results/bingo_cards.pdf";
	public static final String DEST1 = "results/bingo_conditions.pdf";

	public static final String CARDIMAGE = "./src/main/resources/img/Bingo Poderoso (Tabla).png";
	public static final String WPLAYIMAGE = "./src/main/resources/img/wplay_logo.png";
	public static final String CONDITIONIMAGE = "./src/main/resources/img/Bingo Condiciones.png";

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new GenerateCards().manipulateCardsPdf(DEST);
		
//		File file1 = new File(DEST1);
//		file1.getParentFile().mkdirs();
//		new GenerateCards().manipulateConditionsPdf(DEST1);
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

		Image cardImage = new Image(ImageDataFactory.create(CARDIMAGE));      
        cardImage.setAutoScale(true);
		Image wplayImage = new Image(ImageDataFactory.create(WPLAYIMAGE));	    
		wplayImage.setAutoScale(true);
//		Image conditionsImage = new Image(ImageDataFactory.create(CONDITIONIMAGE));    
//		conditionsImage.setAutoScale(true);
		
		Table ballsTable = getBallsTable(wplayImage);
		Paragraph tableNum = new Paragraph("12521");
		tableNum.setFontSize(20);
		
		int cardsTotal = 10;
		for (int p = 0; p < cardsTotal/2; p++) {
	        pdfDoc.addNewPage(new PageSize(pageSize));	        
	    
	        for (int i = 0; i < 2; i++) {
	        	
//	    		if (p % 2 == 0) {
	    			cardImage.setFixedPosition(p + 1, 0, 450);    		
		    		ballsTable.setFixedPosition(p + 1, 179, 542, 216);
		    	    doc.add(cardImage);
		    	    doc.add(ballsTable);
		    	    
		    	    cardImage.setFixedPosition(p + 1, 0, 50);    		
		    		ballsTable.setFixedPosition(p + 1, 179, 142, 216);
		    	    doc.add(cardImage);
		    	    doc.add(ballsTable);
		    	    
		    	    tableNum.setRotationAngle(1.57);
		    	    tableNum.setFixedPosition(p + 1, 35, 536, 200);
		    	    doc.add(tableNum);
		    	    
		    	    tableNum.setFixedPosition(p + 1, 35, 136, 200);
		    	    doc.add(tableNum);
//	    		} else {
//	    			conditionsImage.setFixedPosition(p + 1, 0, 450);    		
//		    	    doc.add(conditionsImage);
//		    	    
//		    	    conditionsImage.setFixedPosition(p + 1, 0, 50);    		
//		    	    doc.add(conditionsImage);
//	    		}
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

		Image conditionsImage = new Image(ImageDataFactory.create(CONDITIONIMAGE));    
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
	
	static int[] joinArray(int[]... arrays) {
        int length = 0;
        for (int[] array : arrays) {
            length += array.length;
        }

        final int[] result = new int[length];

        int offset = 0;
        for (int[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }
	
	public String[] organizar(int[] lista) {
		System.out.println(Arrays.toString(lista));
		int[] columna1 = Arrays.copyOfRange(lista, 0, 5);
		int[] columna2 = Arrays.copyOfRange(lista, 5, 10);
		int[] columna3 = Arrays.copyOfRange(lista, 10, 14);
		int[] columna4 = Arrays.copyOfRange(lista, 14, 19);
		int[] columna5 = Arrays.copyOfRange(lista, 19, 24);
		
		System.out.println(Arrays.toString(columna1));
		System.out.println(Arrays.toString(columna2));
		System.out.println(Arrays.toString(columna3));
		System.out.println(Arrays.toString(columna4));
		System.out.println(Arrays.toString(columna5));
		
		int[] concatAll = joinArray(columna5,columna4,columna3, columna2, columna1);
		System.out.println(Arrays.toString(concatAll));
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
	private Table getBallsTable(Image centerImg) throws Exception{
		PdfFont font = PdfFontFactory.createFont(FontProgramFactory.createFont(FontConstants.HELVETICA_BOLD));
        Paragraph para;
        int[] balls = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23}; 
        
        String[] organizar = organizar(balls);
        System.out.println("organnizar:"+Arrays.toString(organizar));
		Table ballsTable = new Table(5);
		int r=0;
		int c=0;
		for (int i = 0; i < 25; i++) {
//			System.out.println("*** " + balls[i]);
//			if (i%5 == 0) {
//				r=1;
//				c++;
//			}else {
//				r++;
//			}
//			
//			Cell cell = new Cell(r, c);
			
			Cell cell = new Cell();
	        cell.setBorder(Border.NO_BORDER);
	        cell.setHeight(50);
	        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	        
			if (i < 12) {
				para = new Paragraph(organizar[i]).setFont(font);
				para.setFontSize(15);
		        para.setFixedLeading(0);
		        para.setMultipliedLeading(1);
		        cell.add(para);
			}
			
			if (i > 12) {
				para = new Paragraph(organizar[i-1]).setFont(font);
				para.setFontSize(15);
		        para.setFixedLeading(0);
		        para.setMultipliedLeading(1);
		        cell.add(para);
			}
			
			if (i == 12) {	    	
				cell.add(centerImg);
			}			
			
			cell.setRotationAngle(1.57);
			ballsTable.addCell(cell);
		}
		
		return ballsTable;
	}

}