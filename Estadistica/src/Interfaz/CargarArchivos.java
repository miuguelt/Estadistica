

package Interfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class CargarArchivos {

    public void CargarArchivo(String direccion, String a[]){
    int i = 0;
    File archiv=null;
                 FileReader fr=null;
                 BufferedReader br=null;
                 try{
                 archiv=new File(direccion);
                 fr=new FileReader(archiv);
                 br= new BufferedReader(fr);
                 while((a[i]=br.readLine())!=null) i++;
                 }catch(Exception e){
                 System.out.println(e);
                 }


    }

}
