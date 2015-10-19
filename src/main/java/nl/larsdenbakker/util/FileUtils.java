package nl.larsdenbakker.util;

import static com.google.common.base.Preconditions.checkNotNull;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public class FileUtils {

   /**
    * Create a file and test if we can read and write it.
    *
    * @param file The file.
    * @throws IOException If any problems during creating, reading or writing
    * occurred.
    */
   public static void createAndTestReadWrite(File file) throws IOException {
      checkNotNull(file);
      if (!file.exists()) {
         file.getAbsoluteFile().getParentFile().mkdirs();
         file.createNewFile();
      }
      if (!file.canRead()) {
         throw new IOException("File " + file + " cannot be read.");
      } else if (!file.canWrite()) {
         throw new IOException("File " + file + " cannot be written to.");
      }
   }

}
