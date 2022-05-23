package vttp.project.keefe.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Pwned {
    public List<String> name;
    public String desc;
    public Date date;
    public String logo;
    public List<String> dataTypes;

    public List<String> getName() {
        return name;
    }
    public void setName(List<String> name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public List<String> getDataTypes() {
        return dataTypes;
    }
    public void setDataTypes(List<String> dataTypes) {
        this.dataTypes = dataTypes;
    }

    public static Pwned create(String json) {
        Pwned pwn = new Pwned();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader reader = Json.createReader(is);
            //JsonObject o = reader.readObject();
            JsonArray returnNames = reader.readArray();
  
            List<String> names = new LinkedList<>();
            for(int i=0; i<returnNames.size(); i++)
            {
                JsonObject o = returnNames.getJsonObject(i);
                names.add(o.getString("Name"));
            }                   
                pwn.setName(names);
            

        } catch(IOException ex){
            ex.printStackTrace();

       }

        return pwn;
    }
   
}
