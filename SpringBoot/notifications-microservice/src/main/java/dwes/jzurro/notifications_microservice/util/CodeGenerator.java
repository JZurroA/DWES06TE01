/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dwes.jzurro.notifications_microservice.util;

import java.time.Year;
import java.util.Random;

/**
 * Clase para generar el código alfanumérico para el justificante
 * @author Jabier Zurro Aduriz
 */
public class CodeGenerator {
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 7;
    
    /**
     * Método que genera el código alfanumérico
     * @return año actual concatenado con el código alfanumérico
     * generado aleatoriamente
     */
    public static String generateCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        
        for(int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        
        return Year.now().getValue() + "-" + code.toString();
    }
}
