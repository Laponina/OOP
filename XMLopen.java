package lab2;

import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLopen extends JFrame{
	/**
	 * уникальный идентификатор класса
	 */
	private static final long serialVersionUID = 284893047113821873L;
	
	static String fileNameOpen;
	/**
	 * 
	 * @param str - название окна
	 * @param tableModel - модель таблицы в которую будем заносить данные
	 * @param table - сама таблица
	 * @throws Exception - в случае исключ. ситуаций
	 */
	XMLopen(String str, DefaultTableModel tableModel,JTable table) throws Exception {

		/** Вывод на экран окна для задания адреса и имени файла*/
		FileDialog openXML = new FileDialog(this,str,FileDialog.LOAD);
		openXML.setFile("*.xml");// Установка начального каталога
		openXML.setVisible(true);
		fileNameOpen = openXML.getDirectory() + openXML.getFile();

		
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		
		try {
			Document doc = getDocument();
			//showDocument(doc, textArea);
			doc.getDocumentElement().normalize();
			// Получение списка элементов book
			NodeList nlBooks = doc.getElementsByTagName("book");
			for ( int temp = 0; temp < nlBooks.getLength(); temp ++){
				Node elem = nlBooks.item(temp);
				NamedNodeMap attrs = elem.getAttributes();
				String author = attrs.getNamedItem("Author").getNodeValue();
				String titlse = attrs.getNamedItem("Book").getNodeValue();
				String have = attrs.getNamedItem("Code").getNodeValue();
				String xz = attrs.getNamedItem("Existence").getNodeValue();
				tableModel.addRow(new String[] {author, titlse, have, xz });
			}
		/**
		 *@exception SAXException - ошибка чтения из *.xml файла
		 *@exception IOException - ошибка ввода/вывода
		 */
		}catch(SAXException e){
			e.printStackTrace();

		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private static Document getDocument() throws Exception {
		try {
			// Получаем парсер, порождающий дерево объектов XML - документов 
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();// Новый экземпляр
			// Создает пустой документ
			DocumentBuilder builder = f.newDocumentBuilder();
			// Разбирает ( получает ) данные по пути
			/**
			 * @return возращает объект builder класса DocumentBuilder с новым файлом
			 */
			return builder.parse(new File(fileNameOpen));
		/**
		 * @exception Exception
		 */
		} catch (Exception exception) {
			/**
			 * @trows - возращает строку - "XML parsing error!"
			 */
			throw new Exception("XML parsing error!");
		}
	}
}