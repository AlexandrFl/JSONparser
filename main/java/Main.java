import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String filePath = "D:\\Java Project\\JSONparser\\src\\new_data.json";

    public static void main(String[] args) {
        String json = readString(filePath);
        List<Employee> employees = jsonToList(json);
        System.out.println(employees);

    }

    public static String readString(String filePath) {
        String jsonStr = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String s;
            while ((s = br.readLine()) != null) {
                jsonStr = s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    public static List<Employee> jsonToList(String jsonStr) {
        List<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            JSONArray lang = (JSONArray) parser.parse(jsonStr);
            for (Object o : lang) {
                JSONObject obj = (JSONObject) o;
                String json = String.valueOf(obj);
                Employee employee = gson.fromJson(json, Employee.class);
                list.add(employee);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
