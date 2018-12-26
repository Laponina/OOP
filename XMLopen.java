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
	 * ���������� ������������� ������
	 */
	private static final long serialVersionUID = 284893047113821873L;
	
	static String fileNameOpen;
	/**
	 * 
	 * @param str - �������� ����
	 * @param tableModel - ������ ������� � ������� ����� �������� ������
	 * @param table - ���� �������
	 * @throws Exception - � ������ ������. ��������
	 */
	XMLopen(String str, DefaultTableModel tableModel,JTable table) throws Exception {

		/** ����� �� ����� ���� ��� ������� ������ � ����� �����*/
		FileDialog openXML = new FileDialog(this,str,FileDialog.LOAD);
		openXML.setFile("*.xml");// ��������� ���������� ��������
		openXML.setVisible(true);
		fileNameOpen = openXML.getDirectory() + openXML.getFile();

		
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		
		try {
			Document doc = getDocument();
			//showDocument(doc, textArea);
			doc.getDocumentElement().normalize();
			// ��������� ������ ��������� book
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
		 *@exception SAXException - ������ ������ �� *.xml �����
		 *@exception IOException - ������ �����/������
		 */
		}catch(SAXException e){
			e.printStackTrace();

		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private static Document getDocument() throws Exception {
		try {
			// �������� ������, ����������� ������ �������� XML - ���������� 
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();// ����� ���������
			// ������� ������ ��������
			DocumentBuilder builder = f.newDocumentBuilder();
			// ��������� ( �������� ) ������ �� ����
			/**
			 * @return ��������� ������ builder ������ DocumentBuilder � ����� ������
			 */
			return builder.parse(new File(fileNameOpen));
		/**
		 * @exception Exception
		 */
		} catch (Exception exception) {
			/**
			 * @trows - ��������� ������ - "XML parsing error!"
			 */
			throw new Exception("XML parsing error!");
		}
	}
}