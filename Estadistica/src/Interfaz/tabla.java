/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaz;


import java.awt.*;
import java.awt.event.*;
import com.sun.java.swing.*;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;


import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

// El Modelo de la Tabla es el que controla todos los
// datos que se colocan en ella
class tabla extends AbstractTableModel {

    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
//  Object datos[][] = {
//    {"uno","dos","tres","cuatro"},
//    {"cinco","seis","siete","ocho"},
//    {"nueve","diez","once","doce"},
//  };
//
//  // Esta clase imprime los datos en la consola cada vez
//  // que se produce un cambio en cualquiera de las
//  // casillas de la tabla
//  class TablaListener implements TableModelListener {
//    public void tableChanged( TableModelEvent evt ) {
//      for( int i=0; i < datos.length; i++ ) {
//        for( int j=0; j < datos[0].length; j++ )
//          System.out.print( datos[i][j] + " " );
//        System.out.println();
//      }
//    }
//  }
//  // Constructor
//  ModeloDatos() {
//    addTableModelListener( new TablaListener() );
//  }
//  // Devuelve el número de columnas de la tabla
//  public int getColumnCount() {
//    return( datos[0].length );
//  }
//  // Devuelve el número de filas de la tabla
//  public int getRowCount() {
//    return( datos.length );
//  }
//  // Devuelve el valor de una determinada casilla de la tabla
//  // identificada mediante fila y columna
//  public Object getValueAt( int fila,int col ) {
//    return( datos[fila][col] );
//  }
//  // Cambia el valor que contiene una determinada casilla de
//  // la tabla
//  public void setValueAt( Object valor,int fila,int col ) {
//    datos[fila][col] = valor;
//    // Indica que se ha cambiado
//    fireTableDataChanged();
//  }
//  // Indica si la casilla identificada por fila y columna es
//  // editable
//  public boolean isCellEditable( int fila,int col ) {
//    return( true );
//  }
//}
//
//
//public class java1416 extends JPanel {
//  public java1416() {
//    setLayout( new BorderLayout() );
//    JTable tabla = new JTable( new ModeloDatos() );
//    // La tabla se añade a un ScrollPane para que sea éste el
//    // que controle automáticamente en tamaño de la tabla,
//    // presentando una barra de desplazamiento cuando sea
//    // necesario
//    JScrollPane panel = new JScrollPane( tabla );
//    add( panel,BorderLayout.CENTER );
//  }
//
//  public static void main(String args[]) {
//    JFrame frame = new JFrame( "Tutorial de Java, Swing" );
//    frame.addWindowListener( new WindowAdapter() {
//      public void windowClosing( WindowEvent evt ) {
//        System.exit( 0 );
//      }
//    } );
//    frame.getContentPane().add( new java1416(),BorderLayout.CENTER );
//    frame.setSize( 200,200 );
//    frame.setVisible( true );
//  }

}
