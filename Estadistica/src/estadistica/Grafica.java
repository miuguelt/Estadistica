

package estadistica;


import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;


public abstract class Grafica
{
    DefaultCategoryDataset datos;
    JFreeChart graf;
    ChartFrame frame;
    String titulo;
    String tipo;

    public Grafica() {
        datos = new  DefaultCategoryDataset();
        }


    public void cargarDatos(Datos dat, String titulo)//Se agregan los datos y se da un titulo al diagrama
    {
        String[] categorias = dat.getCategoria(); //se obtienen los valaores
        float[] valores = dat.getValores();


            for(int i = 0; i<dat.getNumDatos(); i++)
            {
                System.out.println(dat.getNumDatos()
                        );
                datos.addValue(valores[i],"",categorias[i]);
            }
            crearGrafico();
        

    }

    public void pintar()
    {
        frame = new ChartFrame(tipo, graf);
        frame.pack();
        frame.setVisible(true);
    }

    public abstract void crearGrafico();

}
