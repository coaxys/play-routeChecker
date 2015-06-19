import org.fusesource.jansi.Ansi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class RouteChecker {

    private static final String VERSION = "1.2";

    private List<Route> routes = new ArrayList<>();
    private boolean isOk = true;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage : java -jar RouteChecker-" + VERSION + ".jar chemin_vers_fichier_routes chemin_vers_repertoire_views");
            System.out.println("Exemple : java -jar RouteChecker-" + VERSION + ".jar /home/pierre/Documents/coaxys/workspace/feazy-web/conf/routes /home/pierre/Documents/coaxys/workspace/feazy-web/app/views");

            System.out.println("\nEssai avec conf/routes et app/views\n");
            new RouteChecker().checkRoute("conf/routes", "app/views");
        } else {
            new RouteChecker().checkRoute(args[0], args[1]);
        }
    }

    private void checkRoute(String inputRouteFile, String inputViewsDirectory) {
        File routeFile = new File(inputRouteFile);
        readRoutes(routeFile);

        File viewsDirectory = new File(inputViewsDirectory);
        if (viewsDirectory.exists() && viewsDirectory.isDirectory()) {
            browseDirectory(viewsDirectory);

            if (isOk) {
                System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("\nLes routes présentes dans vos fichiers *.html sont bien présentes dans le fichier route").reset());
            } else {
                System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("\nErreur(s) de routage détectée(s) !").reset());
            }
        } else {
            System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Dossier views incorrect").reset());
        }
    }


    private void browseDirectory(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] list = file.listFiles();
                if (list != null) {
                    for (File aList : list) {
                        browseDirectory(aList);
                    }
                } else {
                    System.out.println(file + " : Erreur de lecture.");
                }
            } else {
                if (file.getName().endsWith(".html")) {
                    System.out.println("Vérification de " + file.getPath());
                    checkFileForRoutes(file);
                }
            }
        } else {
            System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Dossier views incorrect").reset());
        }
    }

    private void checkFileForRoutes(File htmlFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(htmlFile), "utf-8"))) {
            String line;
            int lineIndex = 0;
            Pattern pattern = Pattern.compile("@\\{(controllers.)?(.*?)\\(.*?\\)\\}");
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    lineIndex++;
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        final String action = matcher.group(2);

                        boolean found = routes.stream().anyMatch(
                                route -> route.getAction().equals(action)
                        );
                        if (!found) {
                            System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Erreur de routage :\nFichier : " + htmlFile.getName() + " (Ligne " + lineIndex + ")\nRoute erronnée : " + action + "\n").reset());
                            isOk = false;
                        }
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readRoutes(File routeFile) {
        if (routeFile.exists() && routeFile.isFile()) {
            try {
                Stream<String> lines = Files.lines(Paths.get(routeFile.getAbsolutePath()));
                lines.forEach(line -> {
                    if (!line.isEmpty()) {
                        String splittedLine[] = line.split("#");
                        if (splittedLine.length > 1) {
                            line = splittedLine[0];
                        }
                        splittedLine = line.split("(\\s|/\\t)+");
                        if (splittedLine.length > 2) {
                            Route route = new Route(EMethods.getByName(splittedLine[0]), splittedLine[1], splittedLine[2]);
                            routes.add(route);
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("Fichier routes incorrect").reset());
        }
    }
}
