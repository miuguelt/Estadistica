
package estadistica;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;

public class Linea extends Grafica
{
    public Linea()
    {
        tipo = "Linea";
    }

    @Override
    public void crearGrafico() {
        graf = ChartFactory.createLineChart3D(titulo,"Categorias","Frecuencia abs Acumulada", datos, PlotOrientation.VERTICAL, true, true, true);
    }

}
