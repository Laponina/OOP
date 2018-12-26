/**
 * 
 */
package lab2;


//Добавить другие действия (других слушателей) кроме нажатия на кнопку + удаление + добавление

//Подключение графических библиотек
import java.io.*;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterConfiguration;
import net.sf.jasperreports.engine.export.JRHtmlExporterContext;
import net.sf.jasperreports.engine.export.JRHtmlReportConfiguration;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.HtmlExporterOutput;
import net.sf.jasperreports.engine.export.HtmlExporter;

import java.io.IOException;
import java.util.HashMap;


public class BookList {
	
//Объявления графических компонентов	
	private JFrame bookList;
	private DefaultTableModel model;
	private JButton save;
	private JButton open;
	private JButton add;
	private JButton change;
	private JButton delete;
	private JButton print;
	private JButton xml_open;
	private JButton xml_save;	
	private JToolBar toolBar;
	private JScrollPane scroll;
	private JTable books;
	private JComboBox author;
	private JTextField bookName;
	private JButton filter;
	//private JButton readers;

	
		public void show() {
		// Создание окна
		bookList = new JFrame("Список книг");
		bookList.setSize(500, 300);
		bookList.setLocation(100, 100);
		bookList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Создание кнопок и прикрепление иконок
		save = new JButton("Save");
		open = new JButton("Open");
		add = new JButton("Add");
		change = new JButton("Change");
		delete = new JButton("Delete");
		print = new JButton("Print");
		xml_open = new JButton("XML open");
		xml_save = new JButton("XML save");
		
		// Настройка подсказок для кнопок
		save.setToolTipText("Сохранить список книг");
		xml_save.setToolTipText("Сохранить список книг в xml");
		open.setToolTipText("Открыть список книг");
		xml_open.setToolTipText("Открыть список книг в xml");
		add.setToolTipText("Добавить новую книгу в список");
		change.setToolTipText("Изменить информацию о книге");
		delete.setToolTipText("Удалить книгу");
		print.setToolTipText("Распечатать список книг");
		
		// Добавление кнопок на панель инструментов
		toolBar = new JToolBar("Панель инструментов");

		toolBar.add(save);
		toolBar.add(xml_save);
		toolBar.add(open);
		toolBar.add(xml_open);
		toolBar.add(add);
		toolBar.add(change);
		toolBar.add(delete);
		toolBar.add(print);
		
			
		// Размещение панели инструментов
		bookList.setLayout(new BorderLayout());
		bookList.add(toolBar, BorderLayout.NORTH);
		
		// Создание таблицы с данными
		String [] columns = {"Author", "Book", "Code", "Existence"};
		String [][] data = { };
		model=  new DefaultTableModel(data, columns);
		books = new JTable(model);
		scroll = new JScrollPane(books);
		
		// Размещение таблицы с данными
		bookList.add(scroll, BorderLayout.CENTER);
		
		// Подготовка компонентов поиска
		author = new JComboBox(new String[]{"Author"});
		bookName = new JTextField("Название книги");
		filter = new JButton("Research");
		//readers = new JButton("Readers");
		
		
		// Добавление компонентов на панель
		JPanel filterPanel = new JPanel();
		filterPanel.add(author);
		filterPanel.add(bookName);
		filterPanel.add(filter);
		//filterPanel.add(readers);
		
				
		// Размещение  панели поиска внизу окна
		bookList.add(filterPanel, BorderLayout.SOUTH);
		
		//Действия слушателей
		xml_save.addActionListener(new ActionListener ()
			{
			public void actionPerformed (ActionEvent event) 
			{
				try {
					XMLsave xmlSave = new XMLsave("xml save", model);
					JOptionPane.showMessageDialog (bookList, "Файл успешно сохранен!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		xml_open.addActionListener(new ActionListener ()
		{
			public void actionPerformed (ActionEvent event) 
			{	
				try {
					XMLopen xmlOpen = new XMLopen("xml open", model, books);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		open.addActionListener(new ActionListener()
			{
				public void actionPerformed (ActionEvent event) 
				{
					try {
						JFileChooser filechooser = new JFileChooser();
					
						filechooser.showOpenDialog(bookList);
						File file = filechooser.getSelectedFile();
						String fileName = new String();
						fileName = file.getName();
					
						BufferedReader reader = new BufferedReader(new FileReader(fileName));
						int rows = model.getRowCount();
						for (int i = 0; i < rows; i++) model.removeRow(0); // Очистка таблицы
						String author1;
						do {
							author1 = reader.readLine();
							if(author1 != null) 
								{ String title = reader.readLine();
								String code = reader.readLine();
								String have = reader.readLine();
								author.addItem(author1);
								model.addRow(new String[]{author1, title, code, have}); // Запись строки в таблицу
								
								}
						} while(author1 != null);
					reader.close();
					} catch (FileNotFoundException e) {e.printStackTrace();}
				  catch (IOException e) {e.printStackTrace();}
				}
			});
		
		save.addActionListener (new ActionListener()
		{
			public void actionPerformed (ActionEvent event) 
			{
				 try {
						BufferedWriter writer = new BufferedWriter (new FileWriter("file.txt"));
						for (int i = 0; i < model.getRowCount(); i++)  							
							for (int j = 0; j < model.getColumnCount(); j++)  // Для всех столбцов
							{writer.write ((String) model.getValueAt(i, j)); // Записать значение из ячейки
							writer.write("\r\n"); // Записать символ перевода каретки
							}						
						writer.close();
						if (model.getRowCount() != 0)
							JOptionPane.showMessageDialog (bookList, "Файл успешно сохранен!");  //надо бы сделать ограничение на пустой файл
					      }
						catch(IOException e)    // Ошибка записи в файл
							{ e.printStackTrace();  }
						
			}
		});

		add.addMouseListener (new MouseListener()
		{
			public void mouseClicked (MouseEvent event) 
			{
				JOptionPane.showMessageDialog (bookList, "Проверка нажатия на кнопку");				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		change.addActionListener (new ActionListener()
		{
			public void actionPerformed (ActionEvent event) 
			{
				JOptionPane.showMessageDialog (bookList, "Проверка нажатия на кнопку");				
			}
		});
		
		delete.addActionListener (new ActionListener()
		{
			public void actionPerformed (ActionEvent event) 
			{
				JOptionPane.showMessageDialog (bookList, "Проверка нажатия на кнопку");				
			}
		});
		
		print.addActionListener (new ActionListener()
		{
			
			public void actionPerformed (ActionEvent event) 
			{
				JOptionPane.showMessageDialog (bookList, "Проверка нажатия на кнопку");	
				
				htmlSave html_save = new htmlSave(model);
				
				/*
				 * 	datasource – имя XML-файла с данными;
					xpath – «XPath» (тип источника данных – XML-файл);
					template – имя jrxml-файла шаблона;
					resultpath – имя файла отчета.

				 */
				/*
				String datasource, xpath, template, resultpath;
				datasource = new String ("6.xml");
				datasource = new String (
				*/
				//printer("6.xml", "XPath", "report1.jrxml", "otchet.pdf");
			}
			
		

		});
		
		filter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	checkName(bookName);
				}

				catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(bookList, ex.toString());
				}
				catch(MyException myEx) {
					JOptionPane.showMessageDialog(null, myEx.getMessage());	
				}}});

		
		bookList.setVisible(true);
	}

	//обработка исключений
	private class MyException extends Exception {
		public MyException() {
			super ("Вы не ввели название книги для поиска");
			}}
	
	
	
	
	
	private void checkName (JTextField bookName) throws MyException,NullPointerException
	{
		String sName = bookName.getText();
		if (sName.contains("Название книги")) 	throw new MyException();
		if (sName.length() == 0) throw new NullPointerException();
	}  


	public static void main(String[] args) {
		
		new BookList().show();
	}
	
}




