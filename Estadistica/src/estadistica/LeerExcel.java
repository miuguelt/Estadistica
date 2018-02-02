

package estadistica;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LeerExcel
{
    float[] datos,temp;
    public  Cell cell;
    public  int tam=0;
    @SuppressWarnings("empty-statement")
    public  float [] leerExcel(String archivo)
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
    //Se leen las primeras 5 celdas
    datos = new float[tam];
    for (int p = 0; p <tam; p++)
    {
        datos[p] = temp[p];
        System.out.println("es lo qu  "+"i "+p+"   "+ datos[p]);
    }
    return datos;



    }

}
