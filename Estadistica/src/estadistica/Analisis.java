/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estadistica;

/**
 *
 * @author usuario
 */
public class Analisis
{   private float var;
    private float media;
    private float mediana;
    private float moda;
    private float media_recortada;

    public float media(float[] valores){

        for (int i = 0; i < valores.length; i++) {
          media = media + valores[i];

        }

        media = media/valores.length;

        return media;
        }

    public float mediana(float[] valores){

    if (valores.length%2 == 0 )
    {
        int mitad = valores.length/2;
        float segundo =valores[mitad-1];
        float primero =valores[mitad];

        mediana = (primero + segundo)/2;

    }

    else
    {   int mitad = (valores.length  + 1)/2;

        mediana = valores[mitad];

    }
    return mediana;
    }

    public float moda(float[] valores)
    {

        float valor[]= new float[valores.length];
        int contador[] = new int[valores.length];
        int con = 0;
        for(int i = 0; i<valores.length-1; i++)
        {

            if(valores[i]== valores[i+1])
            {

               contador[con] += 1;


            }

            else
            {
                valor[con] = valores[i];
                contador[con] += 1;
                con +=1;


            }
      }
        int posicion =0;
        int mayor = contador[0];
        for (int i = 0; i < con; i++)
        {
            if(mayor < contador[i])
            {
                mayor = contador[i];
                posicion = i;

            }



        }

         moda = valor[posicion];
            return moda;

        }


    public float percentil(int porcentaje, float[] valores)
    {
       int pos =  (int)(valores.length*porcentaje)/100;
        System.out.println("Vo "+valores[pos]);
       return valores[pos];

    }


    public float varianza(float[] valores)

    {
        for (int i = 0; i < valores.length; i++) {
             var += (valores[i]-media)*(valores[i]-media);
             System.out.println("valor "+ valores[i]+" - "+ media +" = " + var);
        }

      return var/(valores.length);

    }

    public float media_recortada(float[] valores, int porcentaje)
    { float suma = 0;
      int recorte = (int) ((valores.length * porcentaje) / 100);
        System.out.println("recorte"+ recorte);
        for (int i = recorte; i < valores.length-recorte; i++)
        {
            System.out.println("valor "+i+ " = " +valores[i]);
            suma += valores[i];

        }

        media_recortada = suma/(valores.length- 2*recorte);
        return media_recortada;
    }

    public String toString(float[] valores)
    {
        return "Media:   "+media+"\nMedia recortada:   "+media_recortada+"\nMediana:   "+mediana+"\nModa:   "+moda
                +"\nCuartil 1:   "+percentil(25, valores)+"\nCuartil 2:   "+percentil(50, valores)+"\nCuartil 3:   "+percentil(75, valores)+
                "\n";
    }



}




