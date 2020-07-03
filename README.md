# Batman-Movies
## Overview
**Batman Movies** is a simple application which shows all movies which superhero, batman, staring in them. user can also see details of movies by choosing each one.

## Technical Overview
The app is developed upon Clean + MVVM architecture. it has two data sources : 
1. Remote data source which is based on [OMDB API](https://www.omdbapi.com). api is called with GET method with two query parameters, one is api key and second one depends on the needs. we add "batman" as query parameter to search all batman movies and if we want to get details of a movie we add it's imdb id as query parameter instead.
2. Cache data source which store movies in a sqlite database to use when user is offline.

worth metioning that both data sources are **unit tested**
## Design
Icons are from AndroidStudio built-in Material Icon pack. The illustration icons are from [iconfinder.com](https://iconfinder.com)

## Further Developments
Further developments can include unit test repositories, models and view models, write integration test and UI tests.
