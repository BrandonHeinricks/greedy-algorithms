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



    /*
    El juego de la rana (en España), juego del sapo (en Perú,
    Uruguay, Ecuador, Bolivia y Argentina), o simplemente rana (en
    Colombia); es un juego de lanzamiento de precisión múltiple donde
    se intenta introducir un determinado número de fichas, argollas o
    discos de metal en los múltiples agujeros que existen en la mesa del
    sapo o rana.
    Si bien las partidas habitualmente consisten en 10 tiradas, donde
    el jugador que consigue la máxima puntuación gana, en este caso
    se ha decidido modificar el juego, y se trata de conseguir una
    puntuación concreta, usando el menor número de discos posibles.

    Dada la mesa de juego que se muestra en la imagen (que permite
    conseguir: {5,10,15,25,50} puntos por disco), se pide implementar
    un algoritmo que muestre como resultado a qué secciones de la
    mesa (agujeros) deben tirarse los discos para conseguir obtener la
    puntuación deseada, teniendo en cuenta que en el mismo agujero de
    la mesa pueden tirarse tantos discos como sea necesario.
    Implementar un algoritmo en Java (numDiscosGreedy), basado en un esquema voraz, que,
    dada una puntuación concreta y una mesa de juego con valores desordenados, devuelva
    cuántos discos deben tirarse y a qué agujero, teniendo en cuenta que el número de discos a
    tirar debe ser el mínimo posible
     */
    public ArrayList<AgujeroRana> numdiscosGreedy(ArrayList<Integer> valoresRana, int puntuacion){
        ArrayList<AgujeroRana> solucion = new ArrayList<>();
        while(!valoresRana.isEmpty() && puntuacion > 0){
            int mejor = seleccionarRana(valoresRana);
            valoresRana.remove(Integer.valueOf(mejor));
            int cantidad = puntuacion/mejor;
            if(cantidad > 0){
                AgujeroRana rana = new AgujeroRana(mejor, cantidad);
                solucion.add(rana);
                puntuacion -= (mejor*cantidad);
            }
        }
        if(puntuacion == 0){
            return solucion;
        }
        else return null;
    }
    private int seleccionarRana(ArrayList<Integer> valoresRana ){
        int mejor = Integer.MIN_VALUE;
        for(int v : valoresRana){
            if(v > mejor){
                mejor = v;
            }
        }
        return mejor;
    }



    /*
    Se quiere formar una torre de objetos con los cinco sólidos platónicos(1)
    (tetraedros, hexaedros (cubos), octaedros, dodecaedros e icosaedros) que en total
    sumarán N caras. Por ejemplo, una torre formada por tres solidos: [1 icosaedro, 1
    dodecaedro, 1 tetraedro] sumaría un total de 36 caras. Para formar una torre válida, la
    base y los elementos intermedios deben poder sostenerse sobre una cara superior y otra
    inferior, es decir un sólido no puede apoyarse sobre una arista. Es por ello que en una
    torre válida el tetraedro solo podrá estar en la cima y sólo podrá haber 1 como máximo.

    Aclaración: La torre no tiene por qué ser estable desde un punto de vista físico.

    SE PIDE: Implementar un algoritmo basado en el esquema Voraz que dado un numero
    v de caras, devuelva cuántos objetos de cada tipo han sido necesarios. En caso de que
    el problema no tenga solución se devolverá null.
     */


    public int[] greedyTower(int v){
        //no hace falta unmetodo de seleccion de candidatos si ya tenemos una lista ordenada por el mejor primero ,
        // en este caso el mejor es el de mayor caras
        int[] solucion = new int[5];
        //llenar la solucion con cero en todas sus casillas;
        for(int i = 0; i< solucion.length; i++){
            solucion[i] = 0;
        }
        int[] candidatos = {20,12,8,6};
        int k = 0;
        while(k < candidatos.length && v > 0){
            int cantidad = v/candidatos[k];
            v -= (cantidad*candidatos[k]);
            solucion[solucion.length - k -1] = cantidad;
            k++;
        }
        if(v == 4){
           solucion[4] = 1;
           v -= 4;
        }
        if(v == 0){
            return solucion;
        }else return null;
    }


    /*
    Esquema voraz. La filmoteca ha organizado un maratón de cine de
    terror. Durante 24 horas se proyectarán películas (todas diferentes) en las N salas
    disponibles. El Joker, gran aficionado a este género de películas, ha conseguido la
    programación completa donde aparecen todas las películas que se van a proyectar
    durante el maratón: junto con el título, nombre del director, duración de la película y
    otros datos de interés, se indica la sala de proyección y la hora de comienzo.
    El objetivo del Joker es ver el máximo número posible de películas. Ayúdale a
    planificar su maratón de cine, implementando el método ArrayList<Pelicula>
    obtenerListado(ArrayList<Pelicula> candidatos) en Java, basado en un esquema voraz,
    que devuelva la lista de películas seleccionadas.
     */
    public ArrayList<Pelicula> obtenerListado(ArrayList<Pelicula> candidatos){
        ArrayList<Pelicula> solucion = new ArrayList<>();
        while(!candidatos.isEmpty()){
            Pelicula seleccionada = seleccionPeliculas(candidatos);
            candidatos.remove(seleccionada);

            if(solucion.size() == 0){
                solucion.add(seleccionada);
            }
            else if(solucion.get(solucion.size() -1).getFin() <= seleccionada.getInicio() ){
                solucion.add(seleccionada);
            }
        }
        if(solucion.size() > 0){
            return solucion;
        }else return null;
    }
    private Pelicula seleccionPeliculas(ArrayList<Pelicula> candidatos){
        Pelicula mejor = null;
        for(Pelicula c : candidatos){
            if(mejor == null){
                mejor = c;
            }
            else if(c.getFin() < mejor.getFin()){
                mejor = c;
            }
        }
        return mejor;
    }
    /*

   Nos han encargado el seguimiento de un evento deportivo. Nos piden
    reconstruir dicho evento al completo a partir de varios clips con grabaciones de video. Cada clip
    cubre un periodo concreto del evento desde un instante de inicio -start- hasta otro de finalización -
    end-, tal que [start, end). Considera que el evento empezó en el instante 0 y concluyó exactamente
    tras h horas.
    SE PIDE: Implementar un algoritmo basado en el esquema Voraz que minimice el número de clips
    necesarios para reconstruir el evento completo. Si no fuera posible cubrir el evento al completo
    con los clips disponibles, el algoritmo debe devolver null. el método implementado deberá tener la
    siguiente cabecera:
     */

    public ArrayList<Clip> minimoClip(int h, ArrayList<Clip> candidatos){
        ArrayList<Clip> solucion = new ArrayList<>();
        boolean flagImposible = false;
        int s = 0;
        while( !candidatos.isEmpty() && s < h && flagImposible == false){
            Clip seleccionado = seleccionarClip(candidatos, s);
            if(seleccionado == null){
                flagImposible = true;
            }
            else {
                candidatos.remove(seleccionado);
                solucion.add(seleccionado);
                s = seleccionado.getEnd();
            }
        }
        if(s >= h){
            return solucion;
        }else return null;
    }


    private Clip seleccionarClip(ArrayList<Clip> candidatos, int s){
        Clip mejor = null;
        for(Clip c : candidatos){
            if(c.getStart() <= s && s < c.getEnd()){
                if(mejor == null || c.getEnd() > mejor.getEnd()){
                    mejor = c;
                }
            }
        }
        return mejor;
    }





    /*
    Nos dan un vector de N números enteros, y un número K. Podemos
    transformar el vector multiplicando por -1 uno de sus elementos. Deseamos
    transformar el vector K veces exactas de manera que la suma de sus elementos
    sea máxima.

    Ejemplo:
        K: 4
        vector:   [ -2 |  0 | 5 | -1 | 2 ]

    La solución es:
                  [  2 |  0 | 5 |  1 | 2 ]

    Detalle de la solución:
        Inicial:  [ -2 |  0 | 5 | -1 | 2 ]
        Paso 1:   [ +2 |  0 | 5 | -1 | 2 ]
        Paso 2:   [  2 |  0 | 5 | +1 | 2 ]
        Paso 3:   [  2 | -0 | 5 |  1 | 2 ]
        Paso 4:   [  2 | +0 | 5 |  1 | 2 ]  <-- Solución final


    Se pide implementar en Java un algoritmo basado en un esquema voraz que
    resuelva1 el problema (esto es que encuentre el vector de suma máxima tras K
    transformaciones) con complejidad en tiempo2 O(N·K) donde N es el tamaño del
    vector y K el número de transformaciones pedidas:
*/


    public int[] vectorTransformado(int[] vector, int k){
        while(k > 0){
            int indiceSeleccionado = seleccionarIndice(vector);
            vector[indiceSeleccionado] = vector[indiceSeleccionado] * -1;
            k--;
        }
        return vector;
    }
    private int seleccionarIndice(int[] lista) {
        int indiceMejor = 0; // Empezamos asumiendo que el primero es el mejor
        for (int i = 1; i < lista.length; i++) {
            if (lista[i] < lista[indiceMejor]) {
                indiceMejor = i;
            }
        }
        return indiceMejor;
    }
}
