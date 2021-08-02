package tiffmanager.io;


import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.data.BufferedImageRaster;
import gov.nasa.worldwind.formats.tiff.GeotiffWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GitaGeoTiffWriter {
    private File outputFile;
    private BufferedImage bufferedImage;
    private GeotiffWriter geotiffWriter;
    private AVList aVList;

    public GitaGeoTiffWriter(File outputFile, BufferedImage bufferedImage, AVList aVList) throws IOException {
        this.outputFile = outputFile;
        this.bufferedImage = bufferedImage;
        this.aVList = aVList;
        geotiffWriter = new GeotiffWriter(this.outputFile);
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) throws IOException {
        this.outputFile = outputFile;
        geotiffWriter = new GeotiffWriter(this.outputFile);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public AVList getaVList() {
        return aVList;
    }

    public void setaVList(AVList aVList) {
        this.aVList = aVList;
    }

    public void write() throws IOException {
        try {
            geotiffWriter.write(BufferedImageRaster.wrapAsGeoreferencedRaster(bufferedImage, aVList));
        } finally {
            geotiffWriter.close();
        }
    }

    public GeotiffWriter getGeotiffWriter() {
        return geotiffWriter;
    }

    @Deprecated
    public void close() {
        geotiffWriter.close();
    }
}

