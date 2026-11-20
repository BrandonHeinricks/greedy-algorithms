package uni;

import java.util.ArrayList;

public class mocks {

    /*
    Problema 2 (5 puntos). Eres un inversor financiero con un presupuesto máximo de
    inversión de 𝑃 euros. Se te presenta un conjunto de 𝑁 proyectos de inversión. Cada
    proyecto i requiere una inversión de 𝑐𝑖 euros y genera un retorno de 𝑟𝑖 euros (siendo
    este retorno mayor que la inversión). Se permite invertir cualquier cantidad 𝑥𝑖 en un
    proyecto, donde 0 ≤ 𝑥" ≤ 𝑐", siendo el retorno obtenido proporcional a la cantidad
    invertida (Ejemplo: para un proyecto que tenga asociado un coste de 200€ y un
    retorno de 400€, si se invierten 100€ el retorno será de 200€). El objetivo es
    maximizar el retorno total de la inversión sin exceder el presupuesto 𝑷. Ejemplo:


            +-----------+-----+-----+-----+-----+-----+-----+
            | Proyecto  |  A  |  B  |  C  |  D  |  E  |  F  |
            +-----------+-----+-----+-----+-----+-----+-----+
            | Coste     | 200 | 500 | 100 | 400 | 300 | 250 |
            +-----------+-----+-----+-----+-----+-----+-----+
            | Retorno   | 300 | 700 | 200 | 500 | 600 | 400 |
            +-----------+-----+-----+-----+-----+-----+-----+

    Implementar en Java, siguiendo el esquema voraz, el método
    ArrayList<Inversion> invertir(ArrayList<Proyecto> proyectos, float P)
    que devuelva la lista de proyectos con la cantidad invertida en cada uno de ellos.
     */

    public ArrayList<Inversion> invertir(ArrayList<Proyecto> proyectos, float P) {
        ArrayList<Inversion> solucion = new ArrayList<Inversion>();
        Proyecto candidato = null;
        while ((P > 0) && !(proyectos.isEmpty())) {
            candidato = seleccionarCandidato(proyectos);
            proyectos.remove(candidato);
            float invertido = candidato.getCoste();
            if (P < invertido) {
                invertido = P;
            }
            solucion.add(new Inversion(candidato.getNombre(), invertido));
            P = P - invertido;
        }
        return solucion;
    }


        private Proyecto seleccionarCandidato(ArrayList<Proyecto> proyectos){
        Proyecto mejor = null;
        for(Proyecto p : proyectos){
            if(mejor == null){
                mejor = p;
            }
            else{
                float ratio = p.getRetorno() / p.getCoste();
                float mejorRatio = mejor.getRetorno() / mejor.getCoste();
                if(ratio > mejorRatio){
                    mejor = p;
                }else if(ratio == mejorRatio){
                    if(p.getCoste() < mejor.getCoste()){
                        mejor = p;
                    }
                }
            }
        }
        return mejor;
    }

    /*
    2. El error del ojeador (Tu criterio de selección):
    Has usado la resta: p.getRetorno() - p.getCoste() (el beneficio neto).
    En los problemas fraccionarios, el criterio siempre es la división (el ratio): p.getRetorno() / p.getCoste().Piénsalo así:
    Un proyecto cuesta 1000€ y te devuelve 1100€ (Beneficio = 100€). Otro cuesta 10€ y te devuelve 110€ (Beneficio = 100€).
    Con tu resta, ambos empatan. Con la división, el segundo gana por goleada porque por cada euro que metes, te da muchísima más rentabilidad.
     */


    /*
    El avión en el que viajabas ha sufrido un terrible accidente y se ha estrellado
    en las aguas cercanas a una isla desierta. Tú y un reducido grupo de supervivientes
    habéis conseguido superar la peor parte, pero ahora estáis preocupados por sobrevivir al
    hambre. Por suerte el avión transportaba alimentos para los pasajeros y tripulantes en
    varios contenedores, por lo que necesitáis recuperar todo lo posible antes de que el
    avión se hunda. El valor de cada contenedor puede calcularse en función de los días que
    permite subsistir (debido a la cantidad de comida que almacena) y el tiempo que se tarda
    en extraerlo del avión (por su tamaño o lugar de almacenaje). Como único experto en
    algorítmica del grupo de supervivientes, has sido elegido para gestionar el rescate de
    contenedores.

    SE PIDE: Implementar un algoritmo basado en el esquema Voraz que maximice el
    tiempo que podréis sobrevivir en la isla gracias a los días de subsistencia que os darán
    los contenedores de alimentos que seáis capaces de rescatar antes de que se hunda el
    avión. El algoritmo deberá tener la siguiente cabecera:
    ArrayList<Contenedor> rescate(ArrayList<Contenedor> contenedores, int hundir)
     */

    private Contenedor seleccionarContenedor(ArrayList<Contenedor> contenedores){
        Contenedor mejor = null;
        for(Contenedor c : contenedores) {
            if(mejor == null){
                mejor = c;
            }
            float beneficio = c.getBeneficio();
            if(beneficio > mejor.getBeneficio()){
                mejor = c;
            }
            else if (mejor.getBeneficio() == beneficio){
                if(c.getTiempo() < mejor.getTiempo()){
                    mejor = c;
                }
            }
        }
        return mejor;
    }


    public ArrayList<Contenedor> rescate(ArrayList<Contenedor> contenedores, int hundir){
        ArrayList<Contenedor> solucion = new ArrayList<>();
        while(!contenedores.isEmpty() && hundir > 0){
            Contenedor mejor = seleccionarContenedor(contenedores);
            contenedores.remove(mejor);
            if(hundir - mejor.getTiempo() > 0){
                solucion.add(mejor);
                hundir -= mejor.getTiempo();
            }
        }
        if(solucion.size() > 0){
            return solucion;
        }else{
            return null;
        }
    }



    /*
    Problema (2.5 puntos). Dado un array de números enteros superiores a cero, se quiere
    obtener el número más grande construido a partir de la concatenación de todos los
    números del array.
    SE PIDE: Implementar un algoritmo basado en el esquema Voraz que maximice el número resultado de concatenar todos los números del array. Usa la siguiente cabecera:
    int numeroGrande(ArrayList<Numero> nums)
    donde nums es una lista de números tipo Numero.

     */

    public int numeroGrande(ArrayList<Numero> nums){
        Numero solucion = new Numero(0);
        while(!nums.isEmpty()){
            Numero n = seleccionarNumero(nums);
            nums.remove(n);
            solucion.concatRight(n);
        }
        return solucion.getNumInt();
    }

    private Numero seleccionarNumero(ArrayList<Numero> nums){
        Numero mejor = null;
        for(Numero n : nums){
            if(mejor(n,mejor)){
                mejor = n;
            }
        }
        return mejor;
    }
    //tira true si es mejor A
    private boolean mejor(Numero A, Numero B){
        Numero p1= new Numero(A), p2= new Numero(B);
        p1.concatLeft(B);
        p2.concatLeft(A);
        return p1.getNumInt() < p2.getNumInt();
    }



    /*

    Problema (2.5 puntos). Has sido contratado como el publicista web de una casa de
    subastas y una de tus tareas es elegir qué productos mostrar en primer plano del portal
    web. Para cada sesión de subasta deseas exponer los productos más caros para atraer a
    la clientela. Se te proporciona una lista de productos, donde de cada artículo se dispone
    de su valor de salida (en euros) y la sesión en la que se subastará. Las sesiones se
    identifican con un numero en el rango [0.. (numSesiones-1)]. Sólo se pueden mostrar
    maxArtSesion artículos de una misma sesión, mientras que se quiere mostrar
    exactamente numDeseados artículos en total (independientemente de la sesión).

    SE PIDE: Implementar un algoritmo basado en el esquema Voraz que maximice el valor
    total de los artículos mostrados, devolviendo el valor total alcanzado. El método tendrá
    la siguiente cabecera:
    int maxValor(ArrayList<ProductoSubasta> productos,
    int maxArtSesion, int numDeseados, int numSesiones)
    donde productos es la lista de productos, maxArtSesion es el número de artículos
    máximos de cada sesión, numDeseados es el número de artículos que se desea exponer
    y numSesiones es el número de sesiones que se celebrarán.
     */


    public int maxValor(ArrayList<ProductoSubasta> productos, int maxArtSesion, int numDeseados, int numSesiones){
        int[] sesionesUsadas = new int[numSesiones];
        int solucion = 0;
        while(!productos.isEmpty() && numDeseados > 0){
            ProductoSubasta prod = seleccionarProducto(productos);
            productos.remove(prod);
            if(maxArtSesion - 1 >= 0){
                int sesion = prod.getSesion();
                if(sesionesUsadas[sesion] < maxArtSesion){
                    solucion += prod.getValor();
                    sesionesUsadas[sesion]++;
                    numDeseados--;
                }
            }
        }
        return solucion;

    }
    private ProductoSubasta seleccionarProducto(ArrayList<ProductoSubasta> productos){
        ProductoSubasta mejor = null;
        for(ProductoSubasta p : productos){
            if(mejor == null){
                mejor = p;
            }
            if(mejor.getValor() < p.getValor()){
                mejor = p;
            }
        }
        return mejor;
    }
}
