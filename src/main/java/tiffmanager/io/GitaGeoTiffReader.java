package tiffmanager.io;


import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.formats.tiff.GeotiffReader;
import gov.nasa.worldwind.geom.Sector;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GitaGeoTiffReader {
    private GeotiffReader geotiffReader;
    private BufferedImage bufferedImage;
    private AVList aVList;
    Sector sector;

    public GitaGeoTiffReader(String tiffSourceString) throws IOException {
        this(new File(tiffSourceString));

    }

    public GitaGeoTiffReader(File tiffSource) throws IOException {
        this.geotiffReader = new GeotiffReader(tiffSource);
        AVList tempList = new AVListImpl();
        geotiffReader.copyMetadataTo(tempList);
        sector = (Sector) tempList.getValue(AVKey.SECTOR);

        aVList = new AVListImpl();
        if (sector != null) {
            aVList.setValue(AVKey.SECTOR, sector);
        } else throw new IOException("The Tiff doesn't have sector");

        aVList.setValue(AVKey.COORDINATE_SYSTEM, AVKey.COORDINATE_SYSTEM_GEOGRAPHIC);
        aVList.setValue(AVKey.PIXEL_FORMAT, AVKey.IMAGE);
        aVList.setValue(AVKey.BYTE_ORDER, AVKey.BIG_ENDIAN);
        aVList.getValues().forEach(System.out::println);

        bufferedImage = geotiffReader.read();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public AVList getaVList() {
        return aVList;
    }

    public Sector getSector() {
        return sector;
    }

    public void setaVList(AVList aVList) {
        this.aVList = aVList;
    }

    public void close() {
        geotiffReader.dispose();
        geotiffReader.close();
    }

    public GeotiffReader getGeotiffReader() {
        return geotiffReader;
    }

}
