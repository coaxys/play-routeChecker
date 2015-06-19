Route Checker (for [Play! Framework 1.x](https://github.com/playframework/play1))
=======================================

![Coaxys logo](http://www.coaxys.com/public/images/coaxys-logo.svg)

Copyright (c) 2015 Coaxys <contact@coaxys.com>.
Licensed under the [Apache 2 licence](http://www.apache.org/licenses/LICENSE-2.0).

[Télécharger/Download](https://github.com/coaxys/play-routeChecker/tree/dev/target/RouteChecker-1.2.one-jar.jar)

Description
-----------

Simple parser to check that all routes contained in your .html files are
present in your route file. This way you can avoid 404 errors.

The parser only check your files. It does not modify anything.
For now, Route Checker is not able to check if your route file contain
useless routes. Futhermore, it is not able to check the methods used
by routes.

---

Un parseur assez simple qui vérifie que toutes les routes contenues dans
vos fichiers .html sont présentes dans votre fichier route. De cette
manière, vous pouvez éviter des erreurs 404.

Le parseur ne fais que vérifier vos fichiers, il ne modifie rien.
Pour le moment, Route Checker n'est pas capable de déterminer si votre
fichier de route contient des routes inutiles. Il ne vérifie  pas
non plus les méthodes utilisées par les routes.

Utilisation
-----------

* Open command line and go to the directory where RouteChecker-VERSION.jar is.
* Use : java -jar RouteChecker-VERSION.jar path_to_route_file path_of_views_directory
* Analyze the shown results.

---

* Ouvrez un terminal et déplacez vous à l'endroit où vous avez mis RouteChecker-VERSION.jar.
* Lancez : java -jar RouteChecker-VERSION.jar chemin_vers_fichier_routes chemin_vers_repertoire_views
* Analysez les résultats.

### License
This software is licensed under the Apache 2 license, quoted below.

Copyright 2015 Coaxys (http://www.coaxys.com).

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
