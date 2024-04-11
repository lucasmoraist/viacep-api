# API-ViaCep

## Documentação
A API ViaCep-UserCadastro é uma aplicação desenvolvida em Java que utiliza o framework Spring Boot. Ela oferece capacidades de recuperação de informações de endereço utilizando o serviço ViaCep e permite o gerenciamento de cadastro de usuários.

**Uso:** Esta API pode ser utilizada por desenvolvedores que necessitam de uma solução para consultar detalhes de endereço utilizando um CEP ou para gerenciar o cadastro de usuários em seus sistemas.

## Endpoints

### Cadastrar usuário
`POST` /user

| campo | tipo | obriatório | descrição
| --- | :---: | :---: | ---
| id | Long | Sim | id do usuário
| nome | String | Sim | nome do usuário
| email | String | Sim | email do usuário, deverá ser único
| password | String | Sim | senha do usuário
| dtNasc | Date | Sim | data de nascimento do usuário, deverá ser maior de 18
| address | List | Sim | endereço do usuário

**Códigos de Respostas**
| código | descrição
| --- | ---
| 201 | pessoa cadastrada
| 404 | url da requisição não encontrada
| 500 | existe dado faltando no preechimento || email duplicado

### Lista usuários
`GET` /user
**Antes da req POST do endereço**
```
[
	{
		"id": 1,
		"nome": "Lucas",
		"email": "lucas@lucas.com",
		"password": "1234",
		"dtNasc": "11/01/2004",
		"address": []
	}
]
```
**Depois da req POST do endereço**
```
[
	{
		"id": 1,
		"nome": "Lucas",
		"email": "lucas@lucas.com",
		"password": "1234",
		"dtNasc": "11/01/2004",
		"address": [
			{
				"cep": "04707-900",
				"logradouro": "Av. Roque Petroni Júnior",
				"complemento": "",
				"bairro": "Jardim das Acacias",
				"localidade": "São Paulo",
				"uf": "SP",
				"numero": null
			}
    	]
	}
]
```

**Códigos de Respostas**
| código | descrição
| --- | ---
| 200 | dados retornados no corpo da resposta
| 404 | url inexistente

### Cadastrar endereço
`POST` /{idUser}/{cep}

| campo | tipo | obriatório | descrição
| --- | :---: | :---: | ---
| cep | String | Sim | irá receber o cep através da url 
| logradouro | String | Sim | receberá o nome do logradourdo
| complemento | String | Não | receberá o complemento vazio, porém, é possível passar no corpo da requisição
| bairro | String | Sim | receberá o nome do bairro
| localidade | String | Sim | receberá a localidade
| uf | String | Sim | receberá o uf
| numero | String | Não | número do logradouro, passado no corpo da requisição

**Códigos de Respostas**
| código | descrição
| --- | ---
| 201 | endereço cadastrada
| 400 | erro na validação de dados da requisição
| 500 | usuário inexistente || cep inválido
