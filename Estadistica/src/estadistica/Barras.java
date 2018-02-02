

package estadistica;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;



public class Barras extends Grafica
{
    public Barras() {
        tipo = "Barras";
        }

    @Override
    public void crearGrafico() {
        graf = ChartFactory.createBarChart3D(titulo, "Categorias","Frecuencia Absoluta", datos, PlotOrientation.VERTICAL, true, true, true);

    }

}
