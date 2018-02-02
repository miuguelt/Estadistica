package Interfaz;

import estadistica.Analisis;
import estadistica.Datos;
import estadistica.Grafica;
import estadistica.LeerExcel;
import estadistica.Pareto;
import estadistica.Select_Graf;
import java.awt.Choice;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


/**
 *
 * @author usuario
 */
public class Ventana extends javax.swing.JFrame {

    /** Creates new form Ventana */
    public Ventana() {
        initComponents();
    }
    DecimalFormat formato = new DecimalFormat("0,00");
    float [] valores = new float[127];
    float [] frecuencia= new float[127];
    float [] frecuenciAbsAcu= new float[127];
    float [] frecuenciaRel= new float[127];
    String [] frecuenciaRelString = new String[127];
    float [] frecuenciaRelAcu= new float[127];
    double probabilidad = 0;
    double resultado = 0;

//Funciones de probabilidad
  DecimalFormat decimal = new DecimalFormat("0.0000");
    public int factorial(int n)
    {
        if(n == 0||n==1)
        { return 1;
        }
        else return  n*factorial(n-1);
    }

    public int combinatoria(int n, int k)
    { return factorial(n)/(factorial(n-k)*factorial(k));
    }




    public void bernoulli()

    {

        probabilidad = Double.parseDouble( jTextField1.getText());
        int x = Integer.parseInt(jTextField2.getText());
        resultado = Math.pow(probabilidad, x)* Math.pow(1-probabilidad,1-x);
    }


    public void binomial()

    {

        probabilidad = Double.parseDouble( jTextField1.getText());
        int k = Integer.parseInt(jTextField2.getText());
        int n = Integer.parseInt(jTextField3.getText());
        resultado = combinatoria(n,k)*Math.pow(probabilidad, k)*Math.pow(1-probabilidad, n-k);
    }


    public void geometrica()

    { probabilidad = Double.parseDouble( jTextField1.getText());
      int x = Integer.parseInt(jTextField2.getText());
      resultado = Math.pow(1-probabilidad, x-1)*probabilidad;
    }

    public void hipergeometrica()

    { int N = Integer.parseInt(jTextField1.getText());
      int n = Integer.parseInt(jTextField2.getText());
      int d = Integer.parseInt(jTextField3.getText());
      int x = Integer.parseInt(jTextField4.getText());
      resultado =  Double.valueOf(combinatoria(d,x)*combinatoria(N-d,n-x))/Double.valueOf(combinatoria(N,n));

    }

    public void poisson()

    { probabilidad = Double.parseDouble(jTextField1.getText());
      int n = Integer.parseInt(jTextField2.getText());
      int k = Integer.parseInt(jTextField3.getText());
      resultado = (Math.exp(-n*probabilidad)*Math.pow(probabilidad*n, k))/factorial(k);
    }


    public void binomial_neg()

    { probabilidad = Double.parseDouble(jTextField1.getText());
      int x = Integer.parseInt(jTextField2.getText());
      int r = Integer.parseInt(jTextField3.getText());
      resultado = combinatoria(x-1,r-1)*Math.pow(1-probabilidad,x-r)*Math.pow(probabilidad,r);
    }


    public void uniforme()
    { double n = Double.parseDouble(jTextField1.getText());
      resultado = 1/n;
    }
//Fin Funciones de probabilidad


    String [] categoria = new String[127];
    int bloques;
    int cant = 0;
    Analisis medidas = new Analisis();
    Pareto diagrama;
    @SuppressWarnings("empty-statement")
    public void leer(String archivo)
    {
        float[] datos,temp;
     Cell cell;
     int tam=0;
    {
        //Se abre el fichero Excel
        Workbook workbook;
        try {
            workbook = Workbook.getWorkbook(new File(archivo));
            //Se obtiene la primera hoja
            Sheet sheet = workbook.getSheet(0);
            temp = new float[500];
            try{
                do{
                    //Se obtiene la celda i-esima
                     cell = sheet.getCell(0,tam);
                     temp[tam] = (float) ((NumberCell)cell).getValue();
                     System.out.println("tam "+ (tam)+"   num:  "+temp[tam]);

                     tam++;
                     }while(true);
                }
                catch (Exception ex)
                {
                    System.out.println("Error!   "+ex);
                }

                System.out.println("total "+ tam);


            } catch (BiffException ex) {
                Logger.getLogger(Grafica.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ek){};

    }

    //Se leen las primeras 5 celdas
    datos = new float[tam];

    valores = datos;

    }


    public void organizar()
    {
        float menor = valores[0];
        float mayor = valores[0];
        float temp = 0;
        for(int i=0; i<valores.length-1;i++) //Organizar de menor a mayor
        {
            for (int j = i; j < valores.length; j++)
            {
                if(valores[i] > valores[j])
                {
                    temp = valores[i];
                    valores[i] = valores[j];
                    valores[j] = temp;
                }
            }
        }
        menor = valores [0];
        mayor = valores [valores.length-1];
        System.out.println("Mayor"+mayor);
        float distancia =  ((mayor - menor) / bloques);
        
        float temp2 = 0;
        String tem= "";
        String tem1 = "";
        int posic1 = 0;
        int posic2 = 0;
        
        for (int i = 0; i < bloques; i++) //Crea el String de categorias
        {
            temp2 = (menor + distancia);
            if(bloques-1 == i){temp2 = mayor;}
            tem = ""+temp2;
            tem1 = ""+menor;
            posic1 = tem.indexOf(".");
            posic2 = tem1.indexOf(".");
          
            categoria[i]=""+tem1.substring(0, posic2+2)+" - "+tem.substring(0, posic1+2);
//            System.out.println("temp   "+ formato.format(temp2));
            menor = temp2;
        }
        for (int i = 0; i < cant+3; i++) frecuencia[i]=0;


        cant = 0;
        float lim=valores[0]+distancia;
        for (int i = 0; i < valores.length; i++) //Calculo de la frecuencia Absoluta
        {
//            limite = ""+lim;
//
//            coma = limite.indexOf(".");
//            valor_antes = limite.substring(0, coma);
//            valor_desp = limite.substring(coma+1, coma+2);
//
//            val_ant = Integer.parseInt(valor_antes);
//            val_des = Integer.parseInt(valor_desp);
//
//            if(val_des == 9)
//            {
//             lim = val_ant +1;
//            }
//            System.out.println("limite"+ lim);

            if(valores[i] < lim)
                frecuencia[cant] +=1;
            else
            {
                System.out.println("frecuencia "+frecuencia[cant]+"  valor "+valores[i]);
                if(cant!=bloques-1)
                {
                    cant +=1;
                    lim +=distancia;
                }
                System.out.println("lim +" +lim);
                frecuencia[cant] +=1;
            }
        }
        for (int i = 0; i < bloques; i++)//frecuencia absoluta acumulada
        {
            if(i!=0)
               frecuenciAbsAcu[i] = frecuencia[i]+frecuenciAbsAcu[i-1];
            else
               frecuenciAbsAcu[i] = frecuencia[i];
        }
        float total=frecuenciAbsAcu[bloques-1];
        for (int i = 0; i < bloques; i++)//Frecuencia Relativa
        {
            frecuenciaRel[i]=frecuencia[i]/total;
//            frecuenciaRelString[i]= formato.format(frecuenciaRel[i]);

            
        }


        for (int i = 0; i < bloques; i++)
        {
            if(i != 0)
               frecuenciaRelAcu[i] = frecuenciaRel[i] + frecuenciaRelAcu[i-1];
            else
            {
                frecuenciaRelAcu[i] = frecuenciaRel[i];
            }
        }

        

        
       tfrecuencia.setModel(new javax.swing.table.DefaultTableModel(
       new Object [cant+1][],
       new String [] {
        "Intervalo", "Frec rela", "Frec abs", "Rel acum", "Abs acum"
       }
       ));
        for (int i = 0; i < cant+1; i++)
        {
            tfrecuencia.setValueAt(categoria[i], i, 0);
            tfrecuencia.setValueAt(frecuencia[i], i, 1);
            tfrecuencia.setValueAt(frecuenciAbsAcu[i], i, 2);
            tfrecuencia.setValueAt(frecuenciaRel[i], i, 3);
            tfrecuencia.setValueAt(frecuenciaRelAcu[i], i, 4);
            System.out.println("frec  "+frecuencia[i]);
        }


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jFileChooser1 = new javax.swing.JFileChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        percen = new javax.swing.JLabel();
        percentil = new javax.swing.JSpinner();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        recorte = new javax.swing.JSpinner();
        jSpinner1 = new javax.swing.JSpinner();
        media = new javax.swing.JLabel();
        numero = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        jToggleButton3 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfrecuencia = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        distrib = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        resu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Calcular = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jToggleButton4 = new javax.swing.JToggleButton();
        autor = new javax.swing.JLabel();
        miguel = new javax.swing.JLabel();
        oscar = new javax.swing.JLabel();
        jesus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        percen.setText("Percentil");

        percentil.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jToggleButton2.setText("Graficar");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Generar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        recorte.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(5), null, null, Integer.valueOf(1)));

        media.setText("Media recortada");

        numero.setText("Numero de Categorias");

        descripcion.setColumns(20);
        descripcion.setRows(5);
        descripcion.setText("Por favor primero abre un archivo \n*.xls “Office 2003”, después puede\ncalcular lo que deseo con estos \ndatos.\n\nEL PROGRAMA ÚNICAMENTE LEE LOS \nDATOS QUE ESTAN EN LA PRIMERA\nCOLUMNA DE EXCEL, HASTA \nQUE ENCUETRE UNA CELDA VACÍA.");
        jScrollPane2.setViewportView(descripcion);

        jToggleButton3.setText("Abrir");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(numero, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jToggleButton3)
                                .addGap(27, 27, 27)
                                .addComponent(jToggleButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(jToggleButton2))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(recorte, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(media)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(percentil, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(percen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(numero)
                .addGap(18, 18, 18)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton3)
                    .addComponent(jToggleButton2)
                    .addComponent(jToggleButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(media)
                        .addGap(18, 18, 18)
                        .addComponent(recorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(percen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(percentil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos", jPanel1);

        tfrecuencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Intervalo", "Frec rela", "Frec abs", "Rel acum", "Abs acum"
            }
        ));
        jScrollPane1.setViewportView(tfrecuencia);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Frecuencias", jPanel2);

        distrib.setModel(new javax.swing.DefaultComboBoxModel(new String[] {" ", "Bernoulli", "Binomial", "Geometrica", "Hipergeometrica","Poisson","Binomial Negativa","Uniforme Discreta" }));
        distrib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distribActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        resu.setEditable(false);

        jLabel1.setText("Resultado");

        Calcular.setText("Calcular");
        Calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Calcular)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(distrib, 0, 103, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(83, 83, 83))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(distrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(Calcular)
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(resu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116))
        );

        jTabbedPane1.addTab("Distribuciones", jPanel4);

        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/UIS - copia.jpg"))); // NOI18N

        autor.setText("Autores:");

        miguel.setText("Miguel Ángel Tejedor Mendoza");

        oscar.setText("Oscar Julian Medrano Garcia");

        jesus.setText("Jesus Alberto Ortiz");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(miguel)
                            .addComponent(oscar))
                        .addGap(110, 110, 110)
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(autor)
                    .addComponent(jesus))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(autor)
                        .addGap(18, 18, 18)
                        .addComponent(miguel)
                        .addGap(18, 18, 18)
                        .addComponent(oscar)))
                .addGap(18, 18, 18)
                .addComponent(jesus)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Autores", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        bloques = Integer.parseInt(jSpinner1.getValue().toString());
        System.out.println("bloques"+bloques);
        organizar();

        Analisis resul = new Analisis();
        resul.media(valores); resul.media_recortada(valores, Integer.parseInt(recorte.getValue().toString()));
        resul.mediana(valores); resul.moda(valores);


        float desvia = (float) Math.sqrt(resul.varianza(valores));
        descripcion.setText(resul.toString(valores)+"Desviacion:   "+desvia+
                "\nPercentil:   "+ resul.percentil(Integer.parseInt(percentil.getValue().toString()), valores));
        
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        Select_Graf grafico = new Select_Graf(); //Nos sirbe para seleccionar el tipo de grafico       
        Choice select = new Choice();
        select.addItem("Linea");
        select.addItem("Barras");
        select.addItem("Pastel");
        select.addItem("Pareto");
        JOptionPane.showMessageDialog(null, select);
        Datos datos = null;
        //Se pasa el vector de frecuencia correspondiente
        if(select.getSelectedIndex()==0) datos = new Datos(categoria, frecuenciAbsAcu, bloques);
        if(select.getSelectedIndex()==1) datos = new Datos(categoria, frecuencia, bloques);
        if(select.getSelectedIndex()==2) datos = new Datos(categoria, frecuenciaRel, bloques);
        if(select.getSelectedIndex()==3) datos = new Datos(categoria, frecuencia, bloques);



        if(select.getSelectedIndex()!=3) grafico.select(select.getSelectedIndex(), datos);
        else
        {
           grafico.pareto(frecuencia, categoria, bloques);
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // TODO add your handling code here:
       FileNameExtensionFilter filter = new FileNameExtensionFilter("xls", "xls");
       jFileChooser1 = new JFileChooser("Archivos");
       jFileChooser1.setFileFilter(filter);
       if(jFileChooser1.showOpenDialog(null)==0)
       {
           String archivo = jFileChooser1.getSelectedFile().toString();
           LeerExcel lectura = new LeerExcel();
           valores = lectura.leerExcel(archivo);
           
       }

    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void distribActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distribActionPerformed


        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        resu.setText("");

        jTextField1.setVisible(true);

        switch(distrib.getSelectedIndex()) {
            case 0 : break;
            case 2 : jTextField2.setVisible(true); jTextField3.setVisible(true);  jTextField4.setVisible(false);
            jLabel2.setText("Probabilidad"); jLabel3.setText("Aciertos (k)"); jLabel4.setText("Ensayos (n)");
            jLabel5.setText("");break;
            case 1 : jTextField2.setVisible(true); jTextField3.setVisible(false); jTextField4.setVisible(false);
            jLabel2.setText("Probabilidad"); jLabel3.setText("Solucion {0,1}"); jLabel4.setText("");
            jLabel5.setText("");break;
            case 3 : jTextField2.setVisible(true); jTextField3.setVisible(false); jTextField4.setVisible(false);
            jLabel2.setText("Probabilidad"); jLabel3.setText("Ensayos (x)"); jLabel4.setText("");
            jLabel5.setText("");break;
            case 4 : jTextField2.setVisible(true); jTextField3.setVisible(true);  jTextField4.setVisible(true);
            jLabel2.setText("T. Poblacion (N)"); jLabel3.setText("T. Muestra (n)"); jLabel4.setText("E. con Caract. (d)");
            jLabel5.setText("E. de Categ. (x)");break;
            case 5 : jTextField2.setVisible(true); jTextField3.setVisible(true); jTextField4.setVisible(false);
            jLabel2.setText("Probabilidad"); jLabel3.setText("Ensayos (n)"); jLabel4.setText("Valor (k)");
            jLabel5.setText("");break;
            case 6 : jTextField2.setVisible(true); jTextField3.setVisible(true);  jTextField4.setVisible(false);
            jLabel2.setText("Probabilidad"); jLabel3.setText("Fracasos (x)"); jLabel4.setText("Exitos (r)");
            jLabel5.setText("");break;
            case 7 : jTextField2.setVisible(false); jTextField3.setVisible(false); jTextField4.setVisible(false);
            jLabel2.setText("C. de Valores (n)"); jLabel3.setText(""); jLabel4.setText("");
            jLabel5.setText("");break;
        }


        // TODO add your handling code here:
}//GEN-LAST:event_distribActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextField4ActionPerformed

    private void CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalcularActionPerformed



        switch(distrib.getSelectedIndex())

        {

            case 0 : break;
            case 1 : bernoulli();break;
            case 2 : binomial();break;
            case 3 : geometrica();break;
            case 4 : hipergeometrica();break;
            case 5 : poisson();break;
            case 6 : binomial_neg();break;
            case 7 : uniforme();break;

        }


        resu.setText(decimal.format(resultado));


        // TODO add your handling code here:
}//GEN-LAST:event_CalcularActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });

    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Calcular;
    private javax.swing.JLabel autor;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JComboBox distrib;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JLabel jesus;
    private javax.swing.JLabel media;
    private javax.swing.JLabel miguel;
    private javax.swing.JLabel numero;
    private javax.swing.JLabel oscar;
    private javax.swing.JLabel percen;
    private javax.swing.JSpinner percentil;
    private javax.swing.JSpinner recorte;
    private javax.swing.JTextField resu;
    private javax.swing.JTable tfrecuencia;
    // End of variables declaration//GEN-END:variables

}
