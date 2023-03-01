FROM openjdk:11
WORKDIR /app
COPY . /app/
RUN ./mvnw dependency:resolve
EXPOSE 9320
CMD ["./mvnw", "spring-boot:run"]