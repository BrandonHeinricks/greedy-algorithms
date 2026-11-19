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

}
