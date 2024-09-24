FROM openjdk
EXPOSE  8082
ADD target/*.jar product.jar
ENTRYPOINT ["java", "-jar", "product.jar" ]