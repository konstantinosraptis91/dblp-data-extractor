This project try to provide a solution on how extract specific data from a "big" XML file with Java. More specifically, the main goal is to extract the number of publications per year from the XML file that is available on the repository below: 

repo: https://dblp.org/xml/release/

The file used to test the library is "dblp-2020-10-01.xml.gz". Download the file from the repo and add it in the default system downloads folder. Extract the compressed file before run the application. Go to builder module and edit the XMLParserTest class in order to specify the file the application is using to test the XMLParser.

How to run:

./gradlew :builder:test

Java version: 11
