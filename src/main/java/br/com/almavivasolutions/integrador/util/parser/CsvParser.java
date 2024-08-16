package br.com.almavivasolutions.integrador.util.parser;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.almavivasolutions.integrador.model.entity.certificado.CertificadoFactory;

public class CsvParser {
	private static final String separator = ",";
	
    public static String columnsToLine(List<String> cells) {
        StringBuilder line = new StringBuilder();
        for (String cell : cells) {
            line.append(cell).append(separator);
        }

        String lineToString = line.toString();
        return lineToString.substring(0, lineToString.length() - 1) + '\n';
    }

    public static List<String> lineToColumns(String line){
        return List.of(line.split(separator));
    }
    
    public static Object objectFromTableLine(String line1, String type) {
        List<String> line = lineToColumns(line1);
        
    	Object object = CertificadoFactory.getCertificado(type);
        Class<?> objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();

        // Verifique se a lista de linhas tem o mesmo número de elementos que o número de campos
//        if (fields.length != line.size()) {
//        	System.out.println("O número de elementos na linha não corresponde ao número de campos no objeto.");
//            throw new IllegalArgumentException("O número de elementos na linha não corresponde ao número de campos no objeto.");
//        }

        // Formatter para o formato de data dd/MM/yyyy
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            try {
                // Obtenha o tipo do campo
                Class<?> fieldType = field.getType();
                String value = line.get(i);
                
                // Converta o valor da string para o tipo correto e defina o valor no campo
                if (fieldType == String.class) {
                    field.set(object, value);
                } else if (fieldType == int.class || fieldType == Integer.class) {
                    field.set(object, Integer.parseInt(value));
                } else if (fieldType == long.class || fieldType == Long.class) {
                    field.set(object, Long.parseLong(value));
                } else if (fieldType == double.class || fieldType == Double.class) {
                    field.set(object, Double.parseDouble(value));
                } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                    field.set(object, Boolean.parseBoolean(value));
                } else if (fieldType == LocalDate.class) {
                    field.set(object, LocalDate.parse(value, dateFormatter));
                }else {
                	System.out.println("nao deu pra parsear");
                	continue;
                }
            } catch (IllegalAccessException e) {
            	System.out.println("nao sei o que é isso");
            	continue;
            } catch(ArrayIndexOutOfBoundsException e) {
            	System.out.println("range nao encontrado");
            	continue;
            }
        }

        return object;
    }

    public static String objectToPattern(Object object){
        StringBuilder line = new StringBuilder();
        Class<?> objClass = object.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                line.append(value.toString()).append(separator);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        String lineToString = line.toString();
        return lineToString.substring(0, lineToString.length() - 1) + '\n';
    }
    
    public static List<String> getColumn(String columnName, List<List<String>> table){
        List<String> c = null;
        for(List<String> column : table){
            if(column.getFirst().equals(columnName)){
                c = column;
            }
        }

        return c;
    }

    public static List<List<String>> tableFromUpload(InputStream upload){
        List<List<String>> table = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        try(Scanner scanner = new Scanner(upload)){
            String line;
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                lines.add(line);
            }
        }

        for(String line : lines){
            List<String> cells = lineToColumns(line);
            table.add(cells);
        }

        return table;
    }
}