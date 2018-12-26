package lab2;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



import javax.swing.table.DefaultTableModel;
/**
 * @author pidr
 * @version 1.0
 * Данный класс сохраняет данные из таблицы в *.html файла
 *
 */
public class htmlSave {
		
	/**
	 * @param tableModel - модель таблицы в которую будем заносить данные
	 */
	htmlSave(DefaultTableModel tableModel){
		
		/** Создаём объект pw класса PrintWriter - создаём файл бля записи по указ. имени и пути*/
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("./6.html"));
			/**
			 * IOException - ошибка ввода/вывода
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
		/** Добавляем в pw таблицу с заголовками*/
		pw.println("<TABLE BORDER><TR><TH>Автор<TH>Книга<TH>Год издания<TH>Наличие</TR>");
		/** Вносим все данные из таблицы tableModel в таблицу pw*/
		for(int i = 0; i < tableModel.getRowCount(); i++) {

			pw.println("<TR><TD>" + (String) tableModel.getValueAt(i,0)
					+ "<TD>" + (String) tableModel.getValueAt(i,1)
					+ "<TD>" + (String) tableModel.getValueAt(i,2)
					+ "<TD>" + (String) tableModel.getValueAt(i,3));
		}
		pw.println("</TABLE>");
		/** Закрываем pw*/
		pw.close();
	}
}


