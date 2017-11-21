package github.lgoyvaerts.stadtlauf;

import java.io.*;

public class MyFileHandler {
    public String readFile(String file) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/"+file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder temp = new StringBuilder();
        String bufferdRead =  bufferedReader.readLine();
        while(bufferdRead != null){
            temp.append(bufferdRead);
            bufferdRead = bufferedReader.readLine();
            if (bufferdRead!=null){
                temp.append("\n");
            }
        }
        return temp.toString();
    }
}
