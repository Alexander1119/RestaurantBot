package com.restaurant.bot.bl;
//funcion principal para calcular distancia entre puntos

public class Ubicacion {

    public static double distance(double latp, double lonp, double lon, double latg){
        final int R = 6371; //radio total de la tierra;

        Double latDistance = Math.toRadians(latp - latg);
        Double lonDistance = Math.toRadians(lonp - lon);
        Double a = Math.sin(latDistance/2)*Math.sin(latDistance/2)
                +Math.cos(Math.toRadians(latg))*Math.cos(Math.toRadians(latp))*Math.sin(lonDistance/2)*Math.sin(lonDistance/2);
        Double c= 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double distance = R*c*1000; //Conversion a metros

        //double height = elg-elp;

        //distance=Math.pow(distance, 2); //+ Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
