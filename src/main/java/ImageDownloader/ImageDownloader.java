package ImageDownloader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageDownloader {
	
	private String destinationPath, imageUrl;
	
	public ImageDownloader() {
	}
	
	public String downloadImage(String dp, String imgurl) {
		destinationPath = dp;
		imageUrl = imgurl;
		
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            
            String imageType = "jpg"; // Default image type
            
            // Check the image type
            if (image != null) {
                ImageIO.write(image, "jpg", new File(destinationPath));
                System.out.println("Image downloaded successfully.");
                return "exito";
            } else {
                System.out.println("Error: Unable to download the image.");
                return "fallo";
            } 
        } catch (Exception e) {

            e.printStackTrace();
            return "fallo";
        }
	}

	public static void main(String[] args) {
		ImageDownloader id = new ImageDownloader();
		id.downloadImage("C:\\Users\\vodka\\Desktop\\Prueba1\\imagen1.jpg", "https://scontent.cdninstagram.com/v/t51.29350-15/445602009_820951142762782_6464100833568814554_n.jpg?stp=dst-jpg_e35_p480x480&efg=eyJ2ZW5jb2RlX3RhZyI6ImltYWdlX3VybGdlbi4xMTc5eDIwOTYuc2RyLmYyOTM1MCJ9&_nc_ht=scontent.cdninstagram.com&_nc_cat=110&_nc_ohc=3paPGtDxcpkQ7kNvgGbaZ1M&edm=APs17CUBAAAA&ccb=7-5&ig_cache_key=MzM3MjQzMDY3OTg1MTMwMTY2MQ%3D%3D.2-ccb7-5&oh=00_AYDfVKY0FuasoMY5nfElf_o_DxiwS7nHSQTQOibOTiMFmg&oe=664E7400&_nc_s");
		
	}
}
