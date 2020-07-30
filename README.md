# Anote2daemon
[![@Note Server](https://img.shields.io/badge/url-%40Note%20Project-brightgreen)](http://anote-project.org/)
[![LGPLv3](https://img.shields.io/github/license/silicolife/anote2daemon)](https://choosealicense.com/licenses/lgpl-3.0/)

A server version of @note2 that enables the daemon connection for multiple users to a single database. 

This software is part of the [@note2](http://anote-project.org/) developed by [Silicolife](http://www.silicolife.com/).

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

```bash
docker run -v PATH/MODIFIEDHIBERNATE.cfg.xml:/app/database/hibernate.cfg.xml -t bisbii/anote2daemon
```

To change the spring settings, please modify the files in folder /app/spring

```bash
docker run -v PATH/MODIFIEDSPRING:/app/database/spring -t bisbii/anote2daemon
```

To change other application settings, please modify the file /app/application.properties

```bash
docker run -v PATH/MODIFIEDAPPLICATION.properties:/app/application.properties -t bisbii/anote2daemon
```

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
