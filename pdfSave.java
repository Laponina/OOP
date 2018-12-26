package lab2;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.*;

/**
 * Данный класс сохраняет данные из таблицы в *.pdf файла
 * @author pi
 * @version 1.0
 * 
 */
public class pdfSave {
	
	/**
	 * @param tableModel - модель таблицы в которую будем заносить данные
	 */
	pdfSave(DefaultTableModel tableModel) {
 
		/** Создаём объект document класса Document создаём документ А4*/
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		/** Создаём объект t класса PdfPTable с 4мя колонками*/
    	PdfPTable t = new PdfPTable(4);

    	try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("./Data/Library/PdfDataLibraryf.pdf"));
		/**
		 * @exception FileNotFoundException
		 * @exception DocumentException
		 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

    	/** Создаём объект  bfComic класса BaseFont который задаёт новый шрифт*/
    	BaseFont bfComic = null;
		try {
			bfComic = BaseFont.createFont("/Windows/Fonts/Arial.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		/**
		 * @exception DocumentException
		 * @exception IOException
		 */
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		/** Создаём объект  font1 класса Font который задаёт новый шрифт и размер*/
		Font font1 = new Font(bfComic, 12);

		/** Вносим заголовки в таблицу t*/
		t.addCell(new PdfPCell(new Phrase("Автор",font1)));
		t.addCell(new PdfPCell(new Phrase("Книга",font1)));
		t.addCell(new PdfPCell(new Phrase("Год издания",font1)));
		t.addCell(new PdfPCell(new Phrase("Наличие",font1)));

		/** Вносим все данные из таблицы tableModel в таблицу t*/
		for(int i = 0; i < tableModel.getRowCount(); i++){
			t.addCell(new Phrase((String) tableModel.getValueAt(i,0),font1));
			t.addCell(new Phrase((String) tableModel.getValueAt(i,1),font1));
			t.addCell(new Phrase((String) tableModel.getValueAt(i,2),font1));
			t.addCell(new Phrase((String) tableModel.getValueAt(i,3),font1));
		}
		/** Открываем документ*/
		document.open();

		try {
			/** Заполняем документ *.pdf из таблицы t*/
			document.add(t);
		/**
		* @exception DocumentException
		*/
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		/** Закрываем документ*/
		document.close();
    }
}
