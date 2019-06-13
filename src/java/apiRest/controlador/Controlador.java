/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiRest.controlador;

import apiRest.Modelo.Response;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author aldis
 */
public class Controlador {

    public boolean isFecha(String dato1) {//Se Verifica si el dato enviado se puede convetir LocalTime.
        try {
            LocalTime localTime = LocalTime.parse(dato1,
                    DateTimeFormatter.ofPattern("HH:mm:ss"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }

    }

    public boolean isNumber(String dato2) {//Se verifica que el segundo dato sea un numero.
        try {
            Integer.parseInt(dato2);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Response calcularRespuesta(String dato1, String dato2) {

        Response r = null;

        if (isFecha(dato1) && isNumber(dato2)) {//Se verica los datos para poder proceder.

            LocalTime localTime = LocalTime.parse(dato1,
                    DateTimeFormatter.ofPattern("HH:mm:ss"));//se convierte de String a localtime el dato1 
            ZoneOffset offset = ZoneOffset.ofHours(Integer.parseInt(dato2));//Configuramos la zona a la cual vamos a convertir la fecha
            LocalTime utc = OffsetTime.of(localTime, offset).withOffsetSameInstant(ZoneOffset.UTC).toLocalTime();//convertimos la fecha al formato UTC con la timezone enviada.
            String time = utc.toString();
            String timezone = "utc";
            r = new Response(time, timezone);//creamos la respuesta con los datos calculados.
        }

        return r;

    }

    public String convertirJson(Response response) {//convertimos la clase Response a JSON
        Gson gson = new Gson();
        return gson.toJson(response);
    }

}
