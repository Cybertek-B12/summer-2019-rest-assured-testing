package tests.day4;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import pojos.Job;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class GsonDemo {

    @Test
    public void deserializeThis() throws FileNotFoundException {
        // converts the input to java object, objects to output
        Gson gson =  new Gson();

        // file that want to read
        FileReader reader = new FileReader("src/test/resources/it_job.json");

        // fromJson --> takes json input and converts to object
        Job job = gson.fromJson(reader, Job.class);

        System.out.println(job);

    }
}
