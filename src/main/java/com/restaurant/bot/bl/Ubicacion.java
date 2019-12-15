package com.restaurant.bot.bl;
//funcion principal para calcular distancia entre puntos

public class Ubicacion {

    public static boolean distance(double latp, double lonp, double latg, double lon){
        final int R = 6371; //radio total de la tierra en kilometros;

        Double latDistance = Math.toRadians(latg - latp);
        Double lonDistance = Math.toRadians(lon - lonp);
        double sindLat = Math.sin(latDistance / 2);
        double sindLng = Math.sin(lonDistance / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latp)) * Math.cos(Math.toRadians(latg));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distance = R * va2;
        /*Double a = Math.sin(latDistance/2)*Math.sin(latDistance/2)
                +Math.cos(Math.toRadians(latg))*Math.cos(Math.toRadians(latp))*Math.sin(lonDistance/2)*Math.sin(lonDistance/2);
        Double c= 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double distance = R*c*1000; //Conversion a metros

        //double height = elg-elp;

        //distance=Math.pow(distance, 2); //+ Math.pow(height, 2);
*/      boolean status = false;
        double validator=compare(distance);
        if (validator>0){
            status=true;
        }else{
            status=false;
        }
        return status;
    }

    private static double compare(double distance) {
        double km = distance;
        if(km<3){
            return km;
        }
        else{
            return 0;
        }
    }

}