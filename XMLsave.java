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
	 * @param str - �������� ����
	 * @param tableModel - ������ ������� � ������� ����� �������� ������
	 * @throws Exception - � ������ ������. ��������
	 */
	XMLsave(String str, DefaultTableModel tableModel) throws Exception{
		
		/** ����� �� ����� ���� ��� ������� ������ � ����� �����*/
		//if(tableModel.getRowCount().==0) return;
		FileDialog savaXML = new FileDialog(this,str,FileDialog.SAVE);
		savaXML.setFile("*.xml");
		// ��������� ���������� ��������
		savaXML.setVisible(true);
		
		//���������� ��� �������� ��� �����
		String fileNameSave = savaXML.getDirectory() + savaXML.getFile();
		
		Document doc = getDocument();
		// ������ �������� ������� booklist � ��������� ��� � ��������
		Node booklist1 = doc.createElement("booklist");// ������� �������
		doc.appendChild(booklist1);// ��������� ������
		for(int i = 0; i < tableModel.getRowCount(); i++){
			Element book = doc.createElement("book");
			booklist1.appendChild(book);
			book.setAttribute("Author", (String) tableModel.getValueAt(i, 0));
			book.setAttribute("Book", (String) tableModel.getValueAt(i, 1));
			book.setAttribute("Code", (String) tableModel.getValueAt(i, 2));
			book.setAttribute("Existence", (String) tableModel.getValueAt(i, 3));
		}
		try{
			// �������� � �������������� ���������
			Transformer trans=TransformerFactory.newInstance().newTransformer();
			trans.setOutputProperty(OutputKeys.METHOD, "xml");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(fileNameSave)));
			
		/**
		 *@exception TransformerConfigurationException - ������ �������� *.xml ���������������
		 *@exception TransformerException - ������ ������ *.xml ���������������
		 *@exception IOException - ������ �����/������
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
			// �������� ������, ����������� ������ �������� XML - ���������� 
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();// ����� ���������
			// ������� ������ ��������
			DocumentBuilder builder = f.newDocumentBuilder();
			
			/**
			 * @return ��������� ������ builder ������ DocumentBuilder
			 */
			return builder.newDocument();
		/**
		 * @exception Exception
		 */
		} catch (Exception exception) {
			/**
			 * @throws - ��������� ������ - "XML parsing error!"
			 */
			throw new Exception("XML parsing error!");
		}
	}
}

