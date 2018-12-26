package lab2;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



import javax.swing.table.DefaultTableModel;
/**
 * @author pidr
 * @version 1.0
 * ������ ����� ��������� ������ �� ������� � *.html �����
 *
 */
public class htmlSave {
		
	/**
	 * @param tableModel - ������ ������� � ������� ����� �������� ������
	 */
	htmlSave(DefaultTableModel tableModel){
		
		/** ������ ������ pw ������ PrintWriter - ������ ���� ��� ������ �� ����. ����� � ����*/
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("./6.html"));
			/**
			 * IOException - ������ �����/������
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
		/** ��������� � pw ������� � �����������*/
		pw.println("<TABLE BORDER><TR><TH>�����<TH>�����<TH>��� �������<TH>�������</TR>");
		/** ������ ��� ������ �� ������� tableModel � ������� pw*/
		for(int i = 0; i < tableModel.getRowCount(); i++) {

			pw.println("<TR><TD>" + (String) tableModel.getValueAt(i,0)
					+ "<TD>" + (String) tableModel.getValueAt(i,1)
					+ "<TD>" + (String) tableModel.getValueAt(i,2)
					+ "<TD>" + (String) tableModel.getValueAt(i,3));
		}
		pw.println("</TABLE>");
		/** ��������� pw*/
		pw.close();
	}
}


