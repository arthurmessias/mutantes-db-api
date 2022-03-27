# mutantes-db-api

Microservice responsable por registrar cadenas de ADN en la base de datos

## Dependencias
[mutantes-registry](https://github.com/arthurmessias/mutantes-registry) - NetFlix Eureka Server

## Para clonar este repositorio
```bash
git clone https://github.com/arthurmessias/mutantes-db-api.git
cd mutantes-db-api
```

## Unit tests
```bash
mvn test
```
## Start Microservice
```bash
mvn spring-boot:run
```

## Microservice endpoint
* Local endpoint: http://localhost:8080
* Heroku endpoint: https://mutantes-db-api.herokuapp.com
* Swagger UI `/mutantes-db-api/swagger-ui.html`

### POST /statistic
```
http://localhost:8080/mutantes-db-api/statistic?version=1
```

**Parameters**

`version=1`

**Body**

`application/json`
```
{
    "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"],
	"isMutant": true;
}
```

**Response**
`application/json` `201-CREATED`
```
{
    "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"],
	"isMutant": true;
}
```

### GET /stats
```
http://localhost:8080/mutantes-db-api/stats?version=1
```

**Parameters**
`version=1`


**Response**
`application/json` `200-OK`
```
{
	"count_mutant_dna":40, 
	"count_human_dna":100: 
	"ratio":0.4
}
```

## MongoDB Atlas

### Namespace
* mutant_db

### Collection
* statistics

### Document example
```
{"_id":"ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG","isMutant":true,"_class":"com.mutantes.mutantesapi.model.Statistic"}
```
