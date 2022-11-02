# infovita

URL BASE: 
```
https://infovita.herokuapp.com
```
## Obter estabelecimento: `GET /estabelecimento/{id}`
Endpoint que retorna um estabelecimento de acordo com o ID fornecido, junto com uma coleção de equipamentos associados a esta unidade:

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
  "equipamentos": []
}
```

## Obter estabelecimentos: `GET /estabelecimento`
Endpoint que solicita todos os estabelecimentos cadastrados no banco de dados, junto as suas respectivas coleções de equipamentos;

## Obter estabelecimentos: `GET /estabelecimento/on-page?page={nº da pag}`
Endpoint que solicita todos os estabelecimentos de forma paginada, junto as suas respectivas coleções de equipamentos;

<strong>OBS:</strong> A primeira página começa no índice 0, sendo que cada uma contém 25 elementos;

## Cadastrar estabelecimento: `POST /estabelecimento`
Este endpoint deve receber um JSON contendo os dados do estabelecimento a ser cadastrado;

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

`201: CREATED` Contendo um JSON com os dados cadastrados e o novo ID do estabelecimento;

## Atualizar estabelecimento: `PUT /estabelecimento`
Este Endpoint deve receber um JSON contendo os novos dados do estabelecimento a ser atualizado, incluindo o seu ID;

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
- `404: NOT FOUND` O estabelecimento não foi encontrado.

## Excluir estabelecimento: `DELETE /estabelecimento/{id}`
Este endpoint solicita uma exclusão de um equipamento, de acordo com o ID especificado na URL.

Como resposta, é esperado: 
- `204: NO CONTENT` Indicando que a exclusão foi efetuada;
- `404: NOT FOUND` O estabelecimento não foi encontrado.

## Obter equipamento: `GET /equipamento/{id}`
Endpoint que retorna um equipamento de acordo com o ID fornecido na URL. Como resposta, é esperado: 

`200: OK` Com um corpo seguindo este formato de exemplo: 
```json
{
  "id": 1,
  "nome": "Gama câmara",
}
```
`404: NOT FOUND` O estabelecimento não foi encontrado.

## Cadastrar equipamento: `POST /equipamento`
Este endpoint deve receber um JSON contendo os dados do equipamento a ser cadastrado;

<strong>OBS: </strong>Diferente do cadastro do estabelecimento, o ID do novo equipamento deve ser fornecido no JSON;

Exemplar:

```json
{
  "id": 1,
  "nome": "Gama câmara",
}
```
Respostas esperadas: 
- `201: CREATED` Contendo um JSON com os dados cadastrados e o novo ID do estabelecimento;
- `409: CONFLICT` Caso o ID fornecido já esteja associado a um equipamento.

## Atualizar equipamento: `PUT /equipamento`

Este Endpoint deve receber um JSON contendo o nome do equipamento a ser atualizado, incluindo o seu ID;

Exemplar:

```json
{
  "id": 1,
  "nome": "Câmara gama",
}
```
Resposta esperada:
- `204: NO CONTENT` Indicando que a atualização foi efetuada;
- `404: NOT FOUND` O equipamento não foi encontrado.

## Excluir equipamento: `DELETE /equipamento/{id}`
Este endpoint solicita uma exclusão de um equipamento, de acordo com o ID especificado na URL.

Como resposta, é esperado: 
- `204: NO CONTENT` Indicando que a exclusão foi efetuada;
- `404: NOT FOUND` O equipamento não foi encontrado.

## Vincular estabelecimento/equipamento: `POST /merge`
Basicamente, este endpoint adiciona um equipamento na coleção de equipamentos de um determinado estabelecimento.

Para tal objetivo, é necessário enviar que um JSON contendo os ID's do estabelecimento e do equipamento, junto com algumas informações adicionais: 

Exemplar: 
```json
{
  "estabelecimento": 1,
  "equipamento": 1,
  "existentes": 5,
  "funcionais": 3
}
```

<strong>OBS:</strong> Os campos "existentes" e "funcionais" não fazem parte da tabela de equipamentos. 
Eles são de uma tabela intermediária, fruto do relacionamento muitos-para-muitos, e fazem referencia apenas para um determinado estabelecimento.

Como resposta, é esperado: 
- `204: NO CONTENT` Indicando sucesso na operação;
- `404: NOT FOUND` Equipamento e/ou estabelecimento não encontrado.

## Desvincular estabelecimento/equipamento: `DELETE /unmerge`
Ao contrário da ação anterior, este endpoint remove um equipamento da coleção de equipamentos de um estabelecimento.

Para tal objetivo, é necessário enviar que um JSON contendo os ID's do estabelecimento e do equipamento: 

Exemplar: 
```json
{
  "estabelecimento": 1,
  "equipamento": 1
}
```
Como resposta, é esperado: 
- `204: NO CONTENT` Indicando sucesso na operação;
- `404: NOT FOUND` Equipamento e/ou estabelecimento não encontrado.
