package vttp.project.keefe.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Pwned {
    public List<String> name;
    public List<String> desc;
    public List<String> date;
    public List<String> logo;

    public List<String> getName() {
        return name;
    }
    public void setName(List<String> name) {
        this.name = name;
    }
    public List<String> getDesc() {
        return desc;
    }
    public void setDesc(List<String> desc) {
        this.desc = desc;
    }
    public List<String> getDate() {
        return date;
    }
    public void setDate(List<String> date) {
        this.date = date;
    }
    public List<String> getLogo() {
        return logo;
    }
    public void setLogo(List<String> logo) {
        this.logo = logo;
    }

    public static Pwned create(String json) {
        Pwned pwn = new Pwned();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader reader = Json.createReader(is);
            JsonArray returnNames = reader.readArray();
  
            List<String> names = new LinkedList<>();
            List<String> desc = new LinkedList<>();
            List<String> logo = new LinkedList<>();
            List<String> date = new LinkedList<>();

            for(int i=0; i<returnNames.size(); i++)
            {
                JsonObject o = returnNames.getJsonObject(i);
                names.add(o.getString("Name"));
                desc.add(o.getString("Description"));
                logo.add(o.getString("LogoPath"));
                date.add(o.getString("BreachDate"));
          
            }                   
                pwn.setName(names);
                pwn.setDesc(desc);
                pwn.setLogo(logo);
                pwn.setDate(date);   

        } catch(IOException ex){
            ex.printStackTrace();

       }

        return pwn;
    }
   
}
