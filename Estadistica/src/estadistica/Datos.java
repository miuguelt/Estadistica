
package estadistica;

public class Datos {

    String[] categoria;
    float[] valores;
    int numDatos;

    public Datos(String[] categoria, float[] valores, int bloques) {
        this.categoria = categoria;
        this.valores = valores;
        numDatos = bloques;
    }

    public String[] getCategoria() {
        return categoria;
    }

    public void setCategoria(String[] categoria) {
        this.categoria = categoria;
    }

    public int getNumDatos() {
        return numDatos;
    }

    public void setNumDatos(int numDatos) {
        this.numDatos = numDatos;
    }

    public float[] getValores() {
        return valores;
    }

    public void setValores(float[] valores) {
        this.valores = valores;
    }
}
