package oficiali.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oficiali.models.TrajectoryModel;

public class FeedDataBase {
    private static final File RESOURCES = new File("resources");
    
    public static void main(String[] args) {
        ArrayList<File> scriptFiles = new ArrayList<>();
        blowFileStructure(RESOURCES.listFiles(), scriptFiles);  
        final ArrayList<TrajectoryModel> trajectoryModels = new ArrayList<>();
        scriptFiles.forEach(o -> {
            
            try {
                trajectoryModels.addAll(listTrajectoryModels(o));
            } catch (IOException ex) {
                Logger.getLogger(FeedDataBase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(FeedDataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        System.out.println("trajectoryModels.stream().count(): " + trajectoryModels.stream().count());
        
    }
    
    private static ArrayList<TrajectoryModel> listTrajectoryModels(File f) throws FileNotFoundException, IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        ArrayList<TrajectoryModel> result = new ArrayList<>();
        String line = "";
        while( ( line = br.readLine()) != null) {
            result.add(TrajectoryModel.parser(line));
        }
        return result;
    }
    
    private static void blowFileStructure(File[] files, ArrayList<File> scriptFiles) {
        for(File file : files) {
            if(file.isDirectory())
                blowFileStructure(file.listFiles(), scriptFiles);
            else 
                scriptFiles.add(file);
        }
    }
    
}
