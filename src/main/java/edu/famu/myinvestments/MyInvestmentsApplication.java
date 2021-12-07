package edu.famu.myinvestments;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
//AUTOMATICALLY CONNECTS TO FIREBASE DATABASE
public class MyInvestmentsApplication {

public static void main(String[] args) throws IOException {

            //This line may be different based on what your project is named. Use the appropriate class name appears above
            //Load another class inside of another class without an object
            //Loader can load anything out of the resource folder
            ClassLoader loader = MyInvestmentsApplication.class.getClassLoader();

            //opens the file stored in resources
            //File opens the file
            File file = new File(loader.getResource("serviceAccountKey.json").getFile());
            //reads the data from the file
            //FileInputStream reads the file
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());


            //connect to Firebase
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

            SpringApplication.run(MyInvestmentsApplication.class, args);
        }

}

