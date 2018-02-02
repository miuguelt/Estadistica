

package estadistica;

public class Select_Graf
{
    Grafica diagrama;
    Datos datos;
    Pareto pareto;


    public void select(int opcion, Datos datos)
    {

        if(opcion == 0)
        {
            diagrama = new Linea();
            diagrama.cargarDatos(datos, "Linea");
            diagrama.pintar();
        }
        if(opcion == 1)
        {
            diagrama = new Barras();
            diagrama.cargarDatos(datos, "Barras");
            diagrama.pintar();
        }
        if (opcion == 2)
        {
            System.out.println("pastel listo");
            diagrama = new Pastel();
            diagrama.cargarDatos(datos, "Pastel");
            diagrama.pintar();
        }

    }

    public void pareto(float[] frecuencia,String[] categoria, int bloques)
    {
           pareto = new Pareto(frecuencia, categoria, bloques);
           
    }
}
