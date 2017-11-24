package github.lgoyvaerts.stadtlauf;

import com.google.common.truth.Truth;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileHandlerTest {

    @Test
    public void file_should_open() throws IOException{
        String file = "startliste.txt";
        MyFileHandler handler = new MyFileHandler();
        String startListe = handler.readFile(file);
        Truth.assertThat(startListe).isNotNull();
    }
}
