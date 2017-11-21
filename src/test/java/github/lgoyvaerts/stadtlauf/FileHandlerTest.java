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
        Truth.assertThat(startListe).isEqualTo("1\t1\tBruppacher 1\n" +
                "4\t1\tLutz 2\n" +
                "7\t1\tAchermann\n" +
                "2\t1\tBruppacher 2\n" +
                "5\t1\tHaas\n" +
                "8\t1\tPfammatter\n" +
                "3\t1\tLutz 1\n" +
                "6\t1\tVogt\n" +
                "11\t1\tGähwiler");
    }
}
