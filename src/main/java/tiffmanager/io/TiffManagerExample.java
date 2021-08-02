package tiffmanager.io;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TiffManagerExample {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please give your input file");
        GitaGeoTiffReader tiffReader = new GitaGeoTiffReader(scanner.nextLine());
        System.out.println("please give location anf name of file that you want tiff write there");
        File file = new File(scanner.nextLine());
        GitaGeoTiffWriter gitaGeoTiffWriter = new GitaGeoTiffWriter(file, tiffReader.getBufferedImage(),
                tiffReader.getaVList());
        gitaGeoTiffWriter.write();
        System.out.println("Every Thing is done");
    }
}
