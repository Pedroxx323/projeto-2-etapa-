# useFinanceiro

Serviço Spring Boot para cadastrar e listar contas a pagar. A aplicação roda
na porta 8082 e consulta o `useService` por HTTP antes de salvar uma conta.
A listagem consulta diretamente o banco financeiro.

## Estrutura do código

- `api/controller` recebe as chamadas HTTP.
- `api/dto` define e valida os dados enviados nas chamadas.
- `api/model` contém as entidades JPA.
- `api/repository` usa `JpaRepository` para acessar o MySQL.
- `api/service` concentra as regras e a consulta ao serviço de cadastros.

## Executar

1. Inicie o `useService` na porta 8081 ou configure `CADASTROS_URL` com a
   URL dele.
2. Crie um esquema MySQL dedicado, por exemplo `use_financeiro`, e configure
   um usuário com acesso a esse esquema. O Spring cria as tabelas que estiverem faltando.
   O Hibernate valida a estrutura ao iniciar.
3. Defina `FINANCEIRO_DB_USER` e `FINANCEIRO_DB_PASSWORD` no ambiente.
   Se necessário, ajuste `FINANCEIRO_DB_URL`, cujo padrão é
   `jdbc:mysql://127.0.0.1:3306/use_financeiro`.
4. No diretório deste módulo, execute `mvn spring-boot:run` com Java 17.

## Demonstrar o fluxo

Use um ID existente retornado pelo `GET /centros` do `useService`.

```http
POST /contas
Content-Type: application/json

{
  "centroId": "centro-1",
  "descricao": "Conta de energia de novembro",
  "categoria": "ENERGIA",
  "valor": 120.50,
  "vencimento": "2026-11-20"
}
```

O cadastro retorna `201 Created`, com um ID próprio e status `PENDENTE`.
Repita o cadastro com o vencimento de dezembro e liste os lançamentos com
`GET /contas?centroId=centro-1`. A lista é ordenada por vencimento
e pode conter várias contas da mesma categoria.

| Situação no cadastro | Resposta |
| --- | --- |
| Campos inválidos | `400 Bad Request` |
| Centro inexistente | `404 Not Found` |
| Serviço de cadastros indisponível | `503 Service Unavailable` |

## Registrar a baixa manual

Use o ID retornado pelo cadastro. Informe a data em que o pagamento foi
realizado e a forma usada, por exemplo `PIX` ou `BOLETO`.

```http
POST /contas/{contaId}/pagamento
Content-Type: application/json

{
  "dataPagamento": "2026-11-17",
  "formaPagamento": "PIX"
}
```

A resposta `201 Created` contém o ID da conta, a data, a forma de pagamento
e o valor integral da conta. A listagem passa a exibir o status `PAGA`.
Datas futuras ou campos inválidos retornam `400`, uma conta inexistente
retorna `404` e uma conta já paga retorna `409`.

## Registrar e consultar doações

```http
POST /doacoes
Content-Type: application/json

{
  "centroId": "centro-1",
  "origem": "Campanha de arrecadação",
  "valor": 250.00,
  "dataRecebimento": "2026-11-17"
}
```

O cadastro retorna `201 Created` com o ID da doação. O campo `origem` é
opcional para doações anônimas. Campos inválidos ou datas futuras retornam
`400`. Centro inexistente retorna `404`, e indisponibilidade do serviço de
cadastros retorna `503`.

Consulte `GET /doacoes?centroId=centro-1` para listar as doações daquele centro,
da data de recebimento mais recente para a mais antiga.
