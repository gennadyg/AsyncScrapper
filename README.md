# AsyncScrapper

## General explanation

1. Program is trying to read HTML from http://challenge.algopix.com.s3-website-us-east-1.amazonaws.com and 
to read four first products metadata - name/price.
2. Main class is in 'com.algopix.main.MainApplication'.

##To run program from command line win/lnux:
```
git clone https://github.com/gennadyg/AsyncScrapper
//windows
gradlew.bat createExecutableJar runFinalJar
//linux 
./gradlew createExecutableJar runFinalJar
```

Java 8 is required !
