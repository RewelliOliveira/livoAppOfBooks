# Guia de Integração - API de Prateleiras (Shelfs)

Este documento descreve os endpoints disponíveis para gerenciamento de Prateleiras no microserviço Library Service.

## Informações Gerais

- **Base URL**: `/library/shelfs`
- **Autenticação**: Todos os endpoints requerem autenticação via JWT Bearer Token no header `Authorization`
- **Formato do Token**: `Bearer <token>`
- **Porta Padrão**: 8085

## Autenticação

Todos os endpoints requerem o header de autenticação:

```
Authorization: Bearer <seu_token_jwt>
```

O token JWT deve conter o ID do usuário (`userId`), que será extraído automaticamente pelo sistema.

---

## Endpoints

### 1. Criar Prateleira

Cria uma nova prateleira para o usuário autenticado.

- **Método HTTP**: `POST`
- **URL**: `/library/shelfs`
- **Status de Sucesso**: `201 CREATED`

#### Request Body

```json
{
  "name": "Minha Prateleira",
  "description": "Descrição da prateleira (opcional)",
  "books": [
    {
      "id": 123,
      "bookId": "abc123",
      "status": "QUERO_LER"
    }
  ]
}
```

#### Campos do Request

- `name` (obrigatório): Nome da prateleira (string, não pode estar vazio)
- `description` (opcional): Descrição da prateleira (string)
- `books` (opcional): Lista de livros para adicionar na criação da prateleira
  - `id` (obrigatório): ID do livro na biblioteca do usuário (Long)
  - `bookId` (obrigatório): ID do livro no sistema (String)
  - `status` (obrigatório): Status do livro na prateleira (enum: `LENDO`, `LIDO`, `QUERO_LER`, `ABANDONADO`)

#### Response Body

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Minha Prateleira",
  "quantity": 1,
  "bookShelfDto": [
    {
      "id": "660e8400-e29b-41d4-a716-446655440000",
      "bookId": 123,
      "status": "QUERO_LER",
      "addedAt": "2024-01-15T10:30:00",
      "rating": null
    }
  ]
}
```

#### Regras de Negócio

- O nome da prateleira deve ser único para o usuário (não pode haver duas prateleiras com o mesmo nome)
- Todos os livros informados no array `books` devem existir no sistema (validação via Book Service)
- Se `books` não for informado ou estiver vazio, a prateleira será criada sem livros

#### Possíveis Erros

- **400 BAD REQUEST**: Validação de campos falhou
  ```json
  {
    "field": "name",
    "message": "O nome da pratelheira deve ser informado.",
    "statusCode": 400,
    "error": "Validation Error"
  }
  ```

- **409 CONFLICT**: Nome da prateleira já existe para o usuário
  ```json
  {
    "field": "name",
    "message": "Já existe uma pratilheira com o nome 'Minha Prateleira' para este usuário.",
    "statusCode": 409,
    "error": "ShelfNameAlreadyExistsException"
  }
  ```

- **404 NOT FOUND**: Livro não encontrado no sistema
  ```json
  {
    "field": null,
    "message": "Book not found with ID: 123",
    "statusCode": 404,
    "error": "BookNotFoundException"
  }
  ```

- **401 UNAUTHORIZED**: Token JWT ausente ou inválido
  ```
  Missing or invalid Authorization header
  ```

---

### 2. Listar Todas as Prateleiras

Retorna todas as prateleiras do usuário autenticado.

- **Método HTTP**: `GET`
- **URL**: `/library/shelfs/all`
- **Status de Sucesso**: `200 OK`

#### Request

Não requer parâmetros ou body.

#### Response Body

```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Minha Prateleira",
    "quantity": 2,
    "bookShelfDto": [
      {
        "id": "660e8400-e29b-41d4-a716-446655440000",
        "bookId": 123,
        "status": "QUERO_LER",
        "addedAt": "2024-01-15T10:30:00",
        "rating": 4.5
      },
      {
        "id": "770e8400-e29b-41d4-a716-446655440000",
        "bookId": 456,
        "status": "LENDO",
        "addedAt": "2024-01-16T14:20:00",
        "rating": null
      }
    ]
  },
  {
    "id": "880e8400-e29b-41d4-a716-446655440000",
    "name": "Favoritos",
    "quantity": 0,
    "bookShelfDto": []
  }
]
```

#### Regras de Negócio

- Retorna apenas prateleiras pertencentes ao usuário autenticado
- Se o usuário não possuir prateleiras, retorna uma lista vazia `[]`
- O campo `quantity` representa a quantidade de livros na prateleira

#### Possíveis Erros

- **401 UNAUTHORIZED**: Token JWT ausente ou inválido

---

### 3. Buscar Prateleira por ID

Retorna os detalhes de uma prateleira específica.

- **Método HTTP**: `GET`
- **URL**: `/library/shelfs/{id}`
- **Status de Sucesso**: `200 OK`

#### Parâmetros de URL

- `id` (obrigatório): UUID da prateleira

#### Exemplo de Request

```
GET /library/shelfs/550e8400-e29b-41d4-a716-446655440000
```

#### Response Body

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Minha Prateleira",
  "quantity": 1,
  "bookShelfDto": [
    {
      "id": "660e8400-e29b-41d4-a716-446655440000",
      "bookId": 123,
      "status": "QUERO_LER",
      "addedAt": "2024-01-15T10:30:00",
      "rating": null
    }
  ]
}
```

#### Regras de Negócio

- Apenas o dono da prateleira pode acessá-la
- O usuário autenticado é verificado automaticamente através do token JWT

#### Possíveis Erros

- **404 NOT FOUND**: Prateleira não encontrada
  ```json
  {
    "field": null,
    "message": "Pratileira com o ID 550e8400-e29b-41d4-a716-446655440000 não não encontrada.",
    "statusCode": 404,
    "error": "ResourceNotFoundException"
  }
  ```

- **403 FORBIDDEN**: Usuário não tem permissão para acessar esta prateleira
  ```json
  {
    "field": null,
    "message": "Você não tem permissão para acessar essa prateleira.",
    "statusCode": 403,
    "error": "DeniedAccessException"
  }
  ```

- **400 BAD REQUEST**: Formato de UUID inválido
  ```json
  {
    "field": "id",
    "message": "Valor 'abc123' é inválido para o campo 'id'. O tipo esperado é UUID.",
    "statusCode": 400,
    "error": "PathVariable Type Mismatch"
  }
  ```

- **401 UNAUTHORIZED**: Token JWT ausente ou inválido

---

### 4. Listar Livros de uma Prateleira por Status

Retorna os livros de uma prateleira filtrados por status.

- **Método HTTP**: `GET`
- **URL**: `/library/shelfs/{id}/books`
- **Status de Sucesso**: `200 OK`

#### Parâmetros de URL

- `id` (obrigatório): UUID da prateleira

#### Query Parameters

- `status` (obrigatório): Status do livro (enum: `LENDO`, `LIDO`, `QUERO_LER`, `ABANDONADO`)

#### Exemplo de Request

```
GET /library/shelfs/550e8400-e29b-41d4-a716-446655440000/books?status=LENDO
```

#### Response Body

```json
[
  {
    "id": "660e8400-e29b-41d4-a716-446655440000",
    "bookId": 123,
    "status": "LENDO",
    "addedAt": "2024-01-15T10:30:00",
    "rating": 4.5
  },
  {
    "id": "770e8400-e29b-41d4-a716-446655440000",
    "bookId": 456,
    "status": "LENDO",
    "addedAt": "2024-01-16T14:20:00",
    "rating": null
  }
]
```

#### Valores Possíveis para Status

- `LENDO`: Livro está sendo lido atualmente
- `LIDO`: Livro já foi lido
- `QUERO_LER`: Livro está na lista de desejos
- `ABANDONADO`: Leitura foi abandonada

#### Regras de Negócio

- Apenas o dono da prateleira pode acessar seus livros
- Se não houver livros com o status especificado, retorna uma lista vazia `[]`
- O filtro é case-sensitive (deve usar exatamente os valores do enum)

#### Possíveis Erros

- **404 NOT FOUND**: Prateleira não encontrada
  ```json
  {
    "field": null,
    "message": "Pratileira com o ID 550e8400-e29b-41d4-a716-446655440000 não não encontrada.",
    "statusCode": 404,
    "error": "ResourceNotFoundException"
  }
  ```

- **403 FORBIDDEN**: Usuário não tem permissão para acessar esta prateleira
  ```json
  {
    "field": null,
    "message": "Você não tem permissão para acessar essa prateleira.",
    "statusCode": 403,
    "error": "DeniedAccessException"
  }
  ```

- **400 BAD REQUEST**: Status inválido ou formato de UUID inválido
  ```json
  {
    "field": "status",
    "message": "Valor 'INVALIDO' é inválido para o campo 'status'. O tipo esperado é BookStatus.",
    "statusCode": 400,
    "error": "PathVariable Type Mismatch"
  }
  ```

- **401 UNAUTHORIZED**: Token JWT ausente ou inválido

---

### 5. Atualizar Prateleira

Atualiza os dados de uma prateleira existente.

- **Método HTTP**: `PUT`
- **URL**: `/library/shelfs/{id}`
- **Status de Sucesso**: `200 OK`

#### Parâmetros de URL

- `id` (obrigatório): UUID da prateleira

#### Request Body

```json
{
  "name": "Prateleira Atualizada",
  "description": "Nova descrição",
  "books": [
    {
      "id": 123,
      "bookId": "abc123",
      "status": "LIDO"
    },
    {
      "id": 789,
      "bookId": "xyz789",
      "status": "QUERO_LER"
    }
  ]
}
```

**Nota**: Todos os campos são opcionais no PUT, mas se `books` for informado, a lista completa será substituída (não adicionada).

#### Response Body

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Prateleira Atualizada",
  "quantity": 2,
  "bookShelfDto": [
    {
      "id": "660e8400-e29b-41d4-a716-446655440000",
      "bookId": 123,
      "status": "LIDO",
      "addedAt": "2024-01-15T10:30:00",
      "rating": null
    },
    {
      "id": "880e8400-e29b-41d4-a716-446655440000",
      "bookId": 789,
      "status": "QUERO_LER",
      "addedAt": "2024-01-17T09:15:00",
      "rating": null
    }
  ]
}
```

#### Regras de Negócio

- Apenas o dono da prateleira pode atualizá-la
- Se `name` for informado e já existir outra prateleira com o mesmo nome (exceto a atual), retornará erro 409
- Se `books` for informado, a lista completa de livros será substituída (comportamento de PUT)
- Todos os livros informados devem existir no sistema
- Campos não informados no request não serão alterados (exceto `books`, que substitui completamente)

#### Possíveis Erros

- **404 NOT FOUND**: Prateleira não encontrada
  ```json
  {
    "field": null,
    "message": "Pratileira com o ID 550e8400-e29b-41d4-a716-446655440000 não não encontrada.",
    "statusCode": 404,
    "error": "ResourceNotFoundException"
  }
  ```

- **403 FORBIDDEN**: Usuário não tem permissão para atualizar esta prateleira
  ```json
  {
    "field": null,
    "message": "Você não tem permissão para acessar essa prateleira.",
    "statusCode": 403,
    "error": "DeniedAccessException"
  }
  ```

- **409 CONFLICT**: Nome da prateleira já existe para outro usuário
  ```json
  {
    "field": "name",
    "message": "Já existe uma pratilheira com o nome 'Prateleira Atualizada' para este usuário.",
    "statusCode": 409,
    "error": "ShelfNameAlreadyExistsException"
  }
  ```

- **404 NOT FOUND**: Livro não encontrado no sistema
  ```json
  {
    "field": null,
    "message": "Book not found with ID: 123",
    "statusCode": 404,
    "error": "BookNotFoundException"
  }
  ```

- **401 UNAUTHORIZED**: Token JWT ausente ou inválido

---

### 6. Deletar Prateleira

Remove uma prateleira do sistema.

- **Método HTTP**: `DELETE`
- **URL**: `/library/shelfs/{id}`
- **Status de Sucesso**: `204 NO CONTENT`

#### Parâmetros de URL

- `id` (obrigatório): UUID da prateleira

#### Exemplo de Request

```
DELETE /library/shelfs/550e8400-e29b-41d4-a716-446655440000
```

#### Response Body

Sem conteúdo (204 No Content)

#### Regras de Negócio

- Apenas o dono da prateleira pode deletá-la
- Ao deletar uma prateleira, todos os livros associados a ela também são removidos (cascade delete)
- A operação é irreversível

#### Possíveis Erros

- **404 NOT FOUND**: Prateleira não encontrada
  ```json
  {
    "field": null,
    "message": "Pratileira com o ID 550e8400-e29b-41d4-a716-446655440000 não não encontrada.",
    "statusCode": 404,
    "error": "ResourceNotFoundException"
  }
  ```

- **403 FORBIDDEN**: Usuário não tem permissão para deletar esta prateleira
  ```json
  {
    "field": null,
    "message": "Você não tem permissão para acessar essa prateleira.",
    "statusCode": 403,
    "error": "DeniedAccessException"
  }
  ```

- **401 UNAUTHORIZED**: Token JWT ausente ou inválido

---

### 7. Adicionar Livro à Prateleira

Adiciona um livro a uma prateleira existente.

- **Método HTTP**: `POST`
- **URL**: `/library/shelfs/{id}/books`
- **Status de Sucesso**: `201 CREATED`

#### Parâmetros de URL

- `id` (obrigatório): UUID da prateleira

#### Request Body

```json
{
  "id": 123,
  "bookId": "abc123",
  "status": "QUERO_LER"
}
```

#### Campos do Request

- `id` (obrigatório): ID do livro na biblioteca do usuário (Long)
- `bookId` (obrigatório): ID do livro no sistema (String, não pode estar vazio)
- `status` (obrigatório): Status do livro na prateleira (enum: `LENDO`, `LIDO`, `QUERO_LER`, `ABANDONADO`)

#### Response Body

```json
{
  "id": "660e8400-e29b-41d4-a716-446655440000",
  "bookId": 123,
  "status": "QUERO_LER",
  "addedAt": "2024-01-17T10:30:00",
  "rating": null
}
```

#### Regras de Negócio

- Apenas o dono da prateleira pode adicionar livros
- O livro não pode estar duplicado na mesma prateleira
- O livro deve existir no sistema (validação via Book Service)
- O campo `addedAt` é preenchido automaticamente com a data/hora atual
- O campo `rating` é inicializado como `null`

#### Possíveis Erros

- **404 NOT FOUND**: Prateleira não encontrada
  ```json
  {
    "field": null,
    "message": "Pratileira com o ID 550e8400-e29b-41d4-a716-446655440000 não não encontrada.",
    "statusCode": 404,
    "error": "ResourceNotFoundException"
  }
  ```

- **403 FORBIDDEN**: Usuário não tem permissão para acessar esta prateleira
  ```json
  {
    "field": null,
    "message": "Você não tem permissão para acessar essa prateleira.",
    "statusCode": 403,
    "error": "DeniedAccessException"
  }
  ```

- **404 NOT FOUND**: Livro não encontrado no sistema
  ```json
  {
    "field": null,
    "message": "Livro como o ID: 123 não encontrado",
    "statusCode": 404,
    "error": "BookNotFoundException"
  }
  ```

- **409 CONFLICT**: Livro já existe na prateleira
  ```json
  {
    "field": "Usuário e prateleira",
    "message": "Usuário já possui esse livro na prateleira pessoal",
    "statusCode": 409,
    "error": "ExistingAssociationException"
  }
  ```

- **400 BAD REQUEST**: Validação de campos falhou
  ```json
  {
    "field": "bookId",
    "message": "Book ID is required",
    "statusCode": 400,
    "error": "Validation Error"
  }
  ```

- **401 UNAUTHORIZED**: Token JWT ausente ou inválido

---

### 8. Remover Livro da Prateleira

Remove um livro de uma prateleira.

- **Método HTTP**: `DELETE`
- **URL**: `/library/shelfs/{id}/books/{bookId}`
- **Status de Sucesso**: `204 NO CONTENT`

#### Parâmetros de URL

- `id` (obrigatório): UUID da prateleira
- `bookId` (obrigatório): ID do livro na biblioteca do usuário (Long)

#### Exemplo de Request

```
DELETE /library/shelfs/550e8400-e29b-41d4-a716-446655440000/books/123
```

#### Response Body

Sem conteúdo (204 No Content)

#### Regras de Negócio

- Apenas o dono da prateleira pode remover livros
- O livro deve existir na prateleira especificada
- A operação remove apenas a associação entre o livro e a prateleira (não deleta o livro da biblioteca do usuário)

#### Possíveis Erros

- **404 NOT FOUND**: Prateleira não encontrada
  ```json
  {
    "field": null,
    "message": "Pratileira com o ID 550e8400-e29b-41d4-a716-446655440000 não não encontrada.",
    "statusCode": 404,
    "error": "ResourceNotFoundException"
  }
  ```

- **403 FORBIDDEN**: Usuário não tem permissão para acessar esta prateleira
  ```json
  {
    "field": null,
    "message": "Você não tem permissão para acessar essa prateleira.",
    "statusCode": 403,
    "error": "DeniedAccessException"
  }
  ```

- **404 NOT FOUND**: Livro não encontrado na prateleira
  ```
  Book not found in this shelf
  ```

- **400 BAD REQUEST**: Formato de UUID ou Long inválido
  ```json
  {
    "field": "bookId",
    "message": "Valor 'abc' é inválido para o campo 'bookId'. O tipo esperado é Long.",
    "statusCode": 400,
    "error": "PathVariable Type Mismatch"
  }
  ```

- **401 UNAUTHORIZED**: Token JWT ausente ou inválido

---

### 9. Buscar Livros na Prateleira

Busca livros em uma prateleira específica por termo de pesquisa.

- **Método HTTP**: `GET`
- **URL**: `/library/shelfs/{shelfId}/search/{term}`
- **Status de Sucesso**: `200 OK`

#### Parâmetros de URL

- `shelfId` (obrigatório): UUID da prateleira
- `term` (obrigatório): Termo de busca (string)

#### Exemplo de Request

```
GET /library/shelfs/550e8400-e29b-41d4-a716-446655440000/search/Harry%20Potter
```

#### Response Body

```json
[
  {
    "id": 123,
    "bookId": "abc123",
    "bookStatus": "QUERO_LER",
    "thumbnail": "https://example.com/thumbnail.jpg",
    "title": "Harry Potter e a Pedra Filosofal",
    "readingProgress": 0.0,
    "personalRatting": null
  },
  {
    "id": 456,
    "bookId": "def456",
    "bookStatus": "LENDO",
    "thumbnail": "https://example.com/thumbnail2.jpg",
    "title": "Harry Potter e a Câmara Secreta",
    "readingProgress": 0.5,
    "personalRatting": 4
  }
]
```

#### Campos do Response

- `id`: ID do livro na biblioteca do usuário (Long)
- `bookId`: ID do livro no sistema (String)
- `bookStatus`: Status do livro (enum: `LENDO`, `LIDO`, `QUERO_LER`, `ABANDONADO`)
- `thumbnail`: URL da imagem de capa do livro (String)
- `title`: Título do livro (String)
- `readingProgress`: Progresso de leitura (BigDecimal, de 0.0 a 1.0)
- `personalRatting`: Avaliação pessoal do usuário (Integer, pode ser null)

#### Regras de Negócio

- Apenas o dono da prateleira pode buscar livros nela
- A busca é realizada pelo título do livro (estratégia `TITLE_SHELVES`)
- O termo de busca é case-insensitive
- Se nenhum livro for encontrado, retorna uma lista vazia `[]`
- A busca é feita apenas nos livros da prateleira especificada

#### Possíveis Erros

- **404 NOT FOUND**: Prateleira não encontrada
  ```json
  {
    "field": null,
    "message": "Pratileira com o ID 550e8400-e29b-41d4-a716-446655440000 não não encontrada.",
    "statusCode": 404,
    "error": "ResourceNotFoundException"
  }
  ```

- **403 FORBIDDEN**: Usuário não tem permissão para acessar esta prateleira
  ```json
  {
    "field": null,
    "message": "Você não tem permissão para acessar essa prateleira.",
    "statusCode": 403,
    "error": "DeniedAccessException"
  }
  ```

- **400 BAD REQUEST**: Formato de UUID inválido
  ```json
  {
    "field": "shelfId",
    "message": "Valor 'abc123' é inválido para o campo 'shelfId'. O tipo esperado é UUID.",
    "statusCode": 400,
    "error": "PathVariable Type Mismatch"
  }
  ```

- **401 UNAUTHORIZED**: Token JWT ausente ou inválido

---

## Estrutura de Dados

### BookStatus (Enum)

Valores possíveis:
- `LENDO`: Livro está sendo lido atualmente
- `LIDO`: Livro já foi lido
- `QUERO_LER`: Livro está na lista de desejos
- `ABANDONADO`: Leitura foi abandonada

### ShelfDto

```json
{
  "id": "UUID",
  "name": "String",
  "quantity": "Integer",
  "bookShelfDto": "List<BookShelfDto>"
}
```

### BookShelfDto

```json
{
  "id": "UUID",
  "bookId": "Long",
  "status": "BookStatus",
  "addedAt": "LocalDateTime (ISO 8601)",
  "rating": "Float (nullable)"
}
```

### ShelfPostDto

```json
{
  "name": "String (obrigatório)",
  "description": "String (opcional)",
  "books": "List<BookShelfPostDto> (opcional)"
}
```

### BookShelfPostDto

```json
{
  "id": "Long (obrigatório)",
  "bookId": "String (obrigatório, não vazio)",
  "status": "BookStatus (obrigatório)"
}
```

### AssociationResponseDTO (Busca)

```json
{
  "id": "Long",
  "bookId": "String",
  "bookStatus": "BookStatus",
  "thumbnail": "String",
  "title": "String",
  "readingProgress": "BigDecimal",
  "personalRatting": "Integer (nullable)"
}
```

---

## Regras Importantes de Negócio

### Autenticação e Autorização

1. **Todos os endpoints requerem autenticação JWT**: O token deve ser enviado no header `Authorization` com o formato `Bearer <token>`
2. **Isolamento de dados**: Usuários só podem acessar suas próprias prateleiras
3. **Validação de propriedade**: Todas as operações verificam se a prateleira pertence ao usuário autenticado

### Validações de Dados

1. **Nome único**: Cada usuário não pode ter duas prateleiras com o mesmo nome
2. **Validação de livros**: Todos os livros referenciados devem existir no sistema (validação via Book Service)
3. **Prevenção de duplicatas**: Um livro não pode ser adicionado duas vezes na mesma prateleira

### Operações de Atualização

1. **PUT substitui completamente**: Ao atualizar uma prateleira com `books`, a lista completa é substituída (não adicionada)
2. **Campos opcionais**: No PUT, todos os campos são opcionais, mas se informados, são validados

### Operações de Exclusão

1. **Cascade delete**: Ao deletar uma prateleira, todos os livros associados são removidos automaticamente
2. **Remoção de livro**: Remover um livro da prateleira não remove o livro da biblioteca do usuário

### Busca

1. **Escopo limitado**: A busca é realizada apenas nos livros da prateleira especificada
2. **Busca por título**: A busca é feita pelo título do livro (case-insensitive)

---

## Códigos de Status HTTP

- **200 OK**: Operação realizada com sucesso (GET, PUT)
- **201 CREATED**: Recurso criado com sucesso (POST)
- **204 NO CONTENT**: Operação realizada com sucesso sem retorno de conteúdo (DELETE)
- **400 BAD REQUEST**: Erro de validação ou formato inválido
- **401 UNAUTHORIZED**: Token JWT ausente ou inválido
- **403 FORBIDDEN**: Usuário não tem permissão para acessar o recurso
- **404 NOT FOUND**: Recurso não encontrado
- **409 CONFLICT**: Conflito de dados (ex: nome duplicado, livro já existe)

---

## Estrutura de Erro Padrão

Todos os erros retornam no formato `ErrorResponseDTO`:

```json
{
  "field": "String (nome do campo com erro, pode ser null)",
  "message": "String (mensagem descritiva do erro)",
  "statusCode": "Integer (código HTTP)",
  "error": "String (tipo do erro)"
}
```

**Exceção**: Erros de autenticação JWT retornam apenas uma string com a mensagem de erro.

---

## Observações para Integração Android

1. **Gerenciamento de Token**: Sempre inclua o token JWT no header `Authorization` de todas as requisições
2. **Tratamento de Erros**: Implemente tratamento específico para cada código de status HTTP
3. **Validação Local**: Valide os dados antes de enviar para melhorar a experiência do usuário
4. **UUIDs**: Use UUIDs válidos nos parâmetros de URL (formato: `550e8400-e29b-41d4-a716-446655440000`)
5. **Enums**: Use exatamente os valores do enum `BookStatus` (case-sensitive)
6. **Datas**: As datas são retornadas no formato ISO 8601 (`2024-01-15T10:30:00`)
7. **Listas Vazias**: Endpoints que retornam listas podem retornar arrays vazios `[]` quando não há dados
8. **Null Safety**: Alguns campos podem ser `null` (ex: `rating`, `description`, `personalRatting`)

---

## Exemplos de Fluxo Completo

### Criar Prateleira e Adicionar Livros

1. **POST** `/library/shelfs` - Criar prateleira
2. **POST** `/library/shelfs/{id}/books` - Adicionar livros individualmente (ou incluir no passo 1)

### Buscar e Filtrar Livros

1. **GET** `/library/shelfs/all` - Listar todas as prateleiras
2. **GET** `/library/shelfs/{id}` - Ver detalhes de uma prateleira
3. **GET** `/library/shelfs/{id}/books?status=LENDO` - Filtrar por status
4. **GET** `/library/shelfs/{id}/search/{term}` - Buscar por título

### Atualizar Prateleira

1. **GET** `/library/shelfs/{id}` - Obter dados atuais
2. **PUT** `/library/shelfs/{id}` - Atualizar com novos dados

---

**Última atualização**: Dezembro de 2025
