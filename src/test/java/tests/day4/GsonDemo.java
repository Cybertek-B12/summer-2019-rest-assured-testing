package tests.day4;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import pojos.Job;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonDemo {

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

    @Test
    public void serializeThis() throws IOException {
        // the converter
        Gson gson = new Gson();

        // java object that we want to serialize
        Job job = new Job("TE", "Teacher", 10, 100);

        // class that writes file
        FileWriter output = new FileWriter("src/test/resources/te_job.json");

        // toJson --> serialization is happening here.
        gson.toJson(job, output);

        // write into the file
        output.flush();
        output.close();
    }
}
