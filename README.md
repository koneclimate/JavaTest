# JavaTest
### INSTALL
In the project directory,registermember,security (you can set DB in src/main/resource/application.property) :

```bash
cd registermember
mvn clean package
```

```bash
cd security
mvn clean package
```

for build jar file, jar file will in folder registermember/target/ and security/target/

### START
In the project directory, you can run:

```bash
java -jar registermember-0.0.1-SNAPSHOT  

java -jar security-0.0.1-SNAPSHOT  
```

## DB
you can use command in file DB.sql

### Step 
1.you can test in my application by use postman to import file .File > Import 
2.first api GetJwt use for get jwt massage
3.second api in change header Authorization from GetJwt



