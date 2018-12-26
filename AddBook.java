/**
 * 
 */
package lab2;

import java.awt.BorderLayout;
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author ������� �����
 *
 */
public class AddBook {

	/**
	 * @param args
	 */
	
	private JFrame addBook;
	
	private JTextField bookName;
	private JTextField bookCode;
	private JTextField bookAuthor;
	
	public void show()
	{
		// �������� ����
		addBook = new JFrame("���������� ����� �����");
		addBook.setSize(500, 300);
		addBook.setLocation(100, 100);
		addBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ���������� ����������� 
		bookAuthor = new JTextField("Author", 15);
		bookName = new JTextField("Book", 15);
		bookCode = new JTextField("Code", 15);
		
		//GridLayout - �����������
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
	
		panel.add(bookAuthor);
		panel.add(bookName);
		panel.add(bookCode);
		
		//JPanel npanel = new JPanel();
		panel.add(new JButton("Add"));
		
		//addBook.add(npanel, BorderLayout.SOUTH);
		addBook.add(panel);

		
		// ������������ �������� �����
		addBook.setVisible(true);
	
	}
	
	public static void main(String[] args) 
	{
		new AddBook().show();

	}
	
	
	

}
