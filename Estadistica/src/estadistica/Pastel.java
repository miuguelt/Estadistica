

package estadistica;

import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;


public class Pastel extends Grafica
{
    DefaultPieDataset datp;

    public Pastel() {
        this.datp = new  DefaultPieDataset();
        }


    @Override
    public void cargarDatos(Datos dat, String titulo)
    {
        datp = new DefaultPieDataset();
        String[] nombre = dat.getCategoria();
        float[] valores = dat.getValores();
         for(int i = 0; i<dat.getNumDatos(); i++)
        {
            datp.setValue(nombre[i], valores[i]);
        }
         crearGrafico();
              

    }

    public void crearGrafico() {
        graf = ChartFactory.createPieChart3D( "Frecuencia Relativa", datp, true, true, true);
    }





}
