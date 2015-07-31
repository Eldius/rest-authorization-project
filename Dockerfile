FROM maven:3.3.3-jdk-8-onbuild

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

ONBUILD ADD . /usr/src/app

ONBUILD RUN mvn clean install javadoc:jar site site:jar

CMD mvn clean install
