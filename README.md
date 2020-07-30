# Anote2daemon
@Note Server

This project is part of the http://anote-project.org/ developed by Silicolife.

## Installation

In the latest release its possible to use the @Note Server using the docker command:

```bash
docker run -t bisbii/anote2daemon
```

## Usage

By default, docker image contains H2 database that is stored in /app/databases path.

However, it's possible to use other hibernate compatible databases changing the application settings.

The application settings are at /app/ directory inside docker image.

To change the database settings, please modify the file /app/database/hibernate.cfg.xml

To change the spring settings, please modify the files in folder /app/spring

To change other application settings, please modify the file /app/application.properties


## Build from the source

To build the WAR package for tomcat:

```bash
git clone https://github.com/silicolife/anote2daemon
cd anote2daemon
mvn clear package
```
WAR file will be available at target folder.

To build the docker image:

```bash
git clone https://github.com/silicolife/anote2daemon
cd anote2daemon
docker build -t anote2daemon:latest .
```

## License
[LGPLv3](https://choosealicense.com/licenses/lgpl-3.0/)
