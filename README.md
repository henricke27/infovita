# infovita

URL BASE: 
```
https://infovita.herokuapp.com
```
## Obter establishment: `GET /establishment/{id}`
Endpoint que retorna um establishment de acordo com o ID fornecido, junto com uma coleção de equipment associados a esta unidade:

`200: OK` Com um corpo seguindo este formato de exemplo:  

```json
{
  "id": 2,
  "nome": "CBD",
  "nomeEmpresarial": "CENTRO BIONUCLEAR DE DIAGNOSTICO LTDA",
  "cnes": "2323311",
  "cnpj": "74045626000150",
  "sus": true,
  "endereco": {
    "placeId": "id para a api",
    "bairro": "CENTRO",
    "complemento": "SUL",
    "municipio": "TERESINA",
    "numero": "489",
    "logradouro": "RUA DES PIRES DE CASTRO"
 },
  "equipment": []
}
```

## Obter establishments: `GET /establishment`
Endpoint que solicita todos os establishments cadastrados no banco de dados, junto as suas respectivas coleções de equipment;

## Obter establishments: `GET /establishment/on-page?page={nº da pag}`
Endpoint que solicita todos os establishments de forma paginada, junto as suas respectivas coleções de equipment;

<strong>OBS:</strong> A primeira página começa no índice 0, sendo que cada uma contém 25 elementos;

## Cadastrar establishment: `POST /establishment`
Este endpoint deve receber um JSON contendo os dados do establishment a ser cadastrado;

Exemplar:
```json
{
  "nome": "CBD",
  "nomeEmpresarial": "CENTRO BIONUCLEAR DE DIAGNOSTICO LTDA",
  "cnes": "2323311",
  "cnpj": "74045626000150",
  "sus": true,
  "endereco": {
    "placeId": "id para a api",
    "bairro": "CENTRO",
    "complemento": "SUL",
    "municipio": "TERESINA",
    "numero": "489",
    "logradouro": "RUA DES PIRES DE CASTRO"
}
```
Resposta esperada:

`201: CREATED` Contendo um JSON com os dados cadastrados e o novo ID do establishment;

## Atualizar establishment: `PUT /establishment`
Este Endpoint deve receber um JSON contendo os novos dados do establishment a ser atualizado, incluindo o seu ID;

Exemplar:
```json
{
  "id": 2,
  "nome": "CBD",
  "nomeEmpresarial": "CENTRO BIONUCLEAR DE DIAGNOSTICO LTDA",
  "cnes": "2323311",
  "cnpj": "74045626000150",
  "sus": true,
  "endereco": {
    "placeId": "id para a api",
    "bairro": "CENTRO",
    "complemento": "SUL",
    "municipio": "TERESINA",
    "numero": "489",
    "logradouro": "RUA DES PIRES DE CASTRO"
}
```
Resposta esperada:
- `204: NO CONTENT` Indicando que a atualização foi efetuada;
- `404: NOT FOUND` O establishment não foi encontrado.

## Excluir establishment: `DELETE /establishment/{id}`
Este endpoint solicita uma exclusão de um equipment, de acordo com o ID especificado na URL.

Como resposta, é esperado: 
- `204: NO CONTENT` Indicando que a exclusão foi efetuada;
- `404: NOT FOUND` O establishment não foi encontrado.

## Obter equipment: `GET /equipment/{id}`
Endpoint que retorna um equipment de acordo com o ID fornecido na URL. Como resposta, é esperado: 

`200: OK` Com um corpo seguindo este formato de exemplo: 
```json
{
  "id": 1,
  "nome": "Gama câmara",
}
```
`404: NOT FOUND` O establishment não foi encontrado.

## Cadastrar equipment: `POST /equipment`
Este endpoint deve receber um JSON contendo os dados do equipment a ser cadastrado;

<strong>OBS: </strong>Diferente do cadastro do establishment, o ID do novo equipment deve ser fornecido no JSON;

Exemplar:

```json
{
  "id": 1,
  "nome": "Gama câmara",
}
```
Respostas esperadas: 
- `201: CREATED` Contendo um JSON com os dados cadastrados e o novo ID do establishment;
- `409: CONFLICT` Caso o ID fornecido já esteja associado a um equipment.

## Atualizar equipment: `PUT /equipment`

Este Endpoint deve receber um JSON contendo o nome do equipment a ser atualizado, incluindo o seu ID;

Exemplar:

```json
{
  "id": 1,
  "nome": "Câmara gama",
}
```
Resposta esperada:
- `204: NO CONTENT` Indicando que a atualização foi efetuada;
- `404: NOT FOUND` O equipment não foi encontrado.

## Excluir equipment: `DELETE /equipment/{id}`
Este endpoint solicita uma exclusão de um equipment, de acordo com o ID especificado na URL.

Como resposta, é esperado: 
- `204: NO CONTENT` Indicando que a exclusão foi efetuada;
- `404: NOT FOUND` O equipment não foi encontrado.

## Vincular establishment/equipment: `POST /merge`
Basicamente, este endpoint adiciona um equipment na coleção de equipment de um determinado establishment.

Para tal objetivo, é necessário enviar que um JSON contendo os ID's do establishment e do equipment, junto com algumas informações adicionais: 

Exemplar: 
```json
{
  "establishment": 1,
  "equipment": 1,
  "existentes": 5,
  "funcionais": 3
}
```

<strong>OBS:</strong> Os campos "existentes" e "funcionais" não fazem parte da tabela de equipment. 
Eles são de uma tabela intermediária, fruto do relacionamento muitos-para-muitos, e fazem referencia apenas para um determinado establishment.

Como resposta, é esperado: 
- `204: NO CONTENT` Indicando sucesso na operação;
- `404: NOT FOUND` Equipamento e/ou establishment não encontrado.

## Desvincular establishment/equipment: `DELETE /unmerge`
Ao contrário da ação anterior, este endpoint remove um equipment da coleção de equipment de um establishment.

Para tal objetivo, é necessário enviar que um JSON contendo os ID's do establishment e do equipment: 

Exemplar: 
```json
{
  "establishment": 1,
  "equipment": 1
}
```
Como resposta, é esperado: 
- `204: NO CONTENT` Indicando sucesso na operação;
- `404: NOT FOUND` Equipamento e/ou establishment não encontrado.
