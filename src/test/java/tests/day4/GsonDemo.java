package tests.day4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import pojos.Job;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonDemo {

    // READ A FILE
    @Test
    public void deserializeThis() throws FileNotFoundException {
        // converts the input to java object, objects to output
        Gson gson =  new Gson();
        // file that want to read
        FileReader jsonFIle = new FileReader("src/test/resources/it_job.json");
        // fromJson --> takes json input and converts to object
        Job job = gson.fromJson(jsonFIle, Job.class);
        System.out.println(job);
    }

    // WRITE A FILE
    @Test
    public void serializeThis() throws IOException {
        // the converter
//        Gson gson = new Gson();

        // use this to format the json file
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // java object that we want to serialize
        Job job = new Job("CO", "Cook", 10, 100);

        // class that writes file
        FileWriter output = new FileWriter("src/test/resources/te_job.json");

        // toJson --> serialization is happening here.
        gson.toJson(job, output);

        // write into the file
        output.flush();
        output.close();

        // 10 to 15 mins
    }
}
