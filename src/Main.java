import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        loadHomePage("www.google.com");
    }

    public static void loadHomePage(String url) {

        String operatingSystem = System.getProperty("os.name").toLowerCase();
        Runtime runtime = Runtime.getRuntime();

        try {
            if (operatingSystem.indexOf("win") >= 0) {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            }
            else if(operatingSystem.indexOf("mac") >= 0) {
                runtime.exec("open " + url);
            }
            else if(operatingSystem.indexOf("nix") >=0 || operatingSystem.indexOf("nux") >=0) {
                String[] browsers = { "epiphany", "firefox", "mozilla", "konqueror", "netscape", "opera", "links", "lynx" };
                StringBuffer cmd = new StringBuffer();
                for (int i=0; i < browsers.length - 1; i++)
                    cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");

                runtime.exec(new String[]{"sh", "-c", cmd.toString()});
            }
            else {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        //Solution 1 - Not supported on all platforms

        try {
            Desktop desktop = Desktop.getDesktop();
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                URI uri = new URI(url);
                desktop.browse(uri);
            }
        } catch (IOException | URISyntaxException ex) {
        }*/
    }
}
