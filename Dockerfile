FROM openjdk:11 
VOLUME /tmp        
EXPOSE 8080       
ADD ./build/libs/key-manager-rest-api-0.1-all.jar nome.jar    
ENTRYPOINT ["java","-jar","/nome.jar"]   
