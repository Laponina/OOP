package lab2;

import java.awt.FileDialog;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class XMLsave extends JFrame{
	/**
	 * 
	 * @param str - название окна
	 * @param tableModel - модель таблицы в которую будем заносить данные
	 * @throws Exception - в случае исключ. ситуаций
	 */
	XMLsave(String str, DefaultTableModel tableModel) throws Exception{
		
		/** Вывод на экран окна для задания адреса и имени файла*/
		//if(tableModel.getRowCount().==0) return;
		FileDialog savaXML = new FileDialog(this,str,FileDialog.SAVE);
		savaXML.setFile("*.xml");
		// Установка начального каталога
		savaXML.setVisible(true);
		
		//Определяем имя каталога или файла
		String fileNameSave = savaXML.getDirectory() + savaXML.getFile();
		
		Document doc = getDocument();
		// Создаём корневой элемент booklist и добавляем его в документ
		Node booklist1 = doc.createElement("booklist");// создать элемент
		doc.appendChild(booklist1);// добавляем ребёнка
		for(int i = 0; i < tableModel.getRowCount(); i++){
			Element book = doc.createElement("book");
			booklist1.appendChild(book);
			book.setAttribute("Author", (String) tableModel.getValueAt(i, 0));
			book.setAttribute("Book", (String) tableModel.getValueAt(i, 1));
			book.setAttribute("Code", (String) tableModel.getValueAt(i, 2));
			book.setAttribute("Existence", (String) tableModel.getValueAt(i, 3));
		}
		try{
			// Создание и преобразование документа
			Transformer trans=TransformerFactory.newInstance().newTransformer();
			trans.setOutputProperty(OutputKeys.METHOD, "xml");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(fileNameSave)));
			
		/**
		 *@exception TransformerConfigurationException - ошибка создания *.xml преобразователя
		 *@exception TransformerException - ошибка работы *.xml преобразователя
		 *@exception IOException - ошибка ввода/вывода
		 */
		}catch(TransformerConfigurationException e){
			e.printStackTrace();
		}catch(TransformerException e){
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
			
			/**
			 * @return возращает объект builder класса DocumentBuilder
			 */
			return builder.newDocument();
		/**
		 * @exception Exception
		 */
		} catch (Exception exception) {
			/**
			 * @throws - возращает строку - "XML parsing error!"
			 */
			throw new Exception("XML parsing error!");
		}
	}
}

