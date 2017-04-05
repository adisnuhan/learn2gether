FBM DB-Unit
===============

Defines DB Unit-Wrapper for all FBM Systems

HOW TO release a new version
--------------------------

#### 1. Changes dokumentieren  ####

    src/changes/changes.xml
    
#### 2. Änderungen einchecken  ####
    svn commit -m 'meaningful message'

#### 3. Release vorbereiten  ####
    
    mvn:release:prepare
    
#### 4. Release durchführen  #### 
    
    mvn release:perform
    
#### 5. Property / Dependency in referencing projects anpassen  ####

    ...
    <properties>
        ...
        <xerces.version>2.11.0</xerces.version>
        <fbm-dbunit.version>1.0.9</fbm-dbunit.version>
        <fbm-build-tools-version>0.0.5</fbm-build-tools-version>
        ...
    </properties>
    ...
    <dependency>
        <groupId>com.bmw.cs.test</groupId>
        <artifactId>dbunit</artifactId>
        <scope>test</scope>
        <version>${fbm-dbunit.version}</version>
    </dependency>
    