# scss-compiler

A Clojure library to compile SCSS files to CSS. Uses jRuby and supports Compass.

This project is at proof of concept stage at this point. For instance, performance is quite poor.

[![Clojars Project](http://clojars.org/lupapiste/scss-compiler/latest-version.svg)](http://clojars.org/lupapiste/scss-compiler)

## sass and compass gems with their dependencies

Yuo'll need to download sass and compass manually into your project.
Put the required gems under resources/gems. One known working combination:

* chunky_png-1.3.3
* compass-1.0.3
* compass-core-1.0.3
* compass-import-once-1.0.5
* ffi-1.9.6-java
* multi_json-1.10.1
* rb-fsevent-0.9.4
* rb-inotify-0.9.5
* sass-3.4.12

Directory structure:

    resources
        \ gems
          +---cache
          |   \ (contains *.gem files)
          +---gems
          |   +---chunky_png-1.3.3
          |   +---compass-1.0.3
          |   +---compass-core-1.0.3
          |   +---compass-import-once-1.0.5
          |   +---ffi-1.9.6-java
          |   +---multi_json-1.10.1
          |   +---rb-fsevent-0.9.4
          |   +---rb-inotify-0.9.5
          |   \---sass-3.4.12
          \---specifications
              \ (contains *.gemspec files)


## License

Copyright © 2015 Solita Oy

Distributed under the Eclipse Public License, the same as Clojure.
