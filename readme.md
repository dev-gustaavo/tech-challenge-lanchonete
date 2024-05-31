# Tech Challenge - API Lanchonete

API resopnsável por gerenciar clientes, produtos e pedidos de uma lanchonete.

# Pré-requisitos
1. Docker
   1. Para instalação [clique aqui](https://www.docker.com/get-started/)
2. Docker compose
   1. Para instalação [clique aqui](https://docs.docker.com/compose/install/)

# Para executar o projeto:
1. Acesse via terminal a pasta do projeto, o local onde estará o arquivo ```docker-compose.yaml```
2. Execute o comando abaixo: 
```bash
docker-compose up --build
```

# Passo a passo funcional da API

### Para um bom funcionamento da API, siga o passo a passo abaixo: 
1. Primeiro, cadastre um produto, utilizando a rota ```POST``` /produto
2. Após, você já está apto para cadastrar um pedido. Capture o id do produto que foi cadastrado e para cadastrar um 
pedido utilize a rota ```POST``` /pedido
3. Para realizar o pagamento do pedido, utilize a rota ```POST``` /pedido/pagar/{numeroPedido}

### Além disso, você também poderá:
1. Editar, deletar ou listar por categoria um produto
2. Listar todos os pedidos com status pago
3. Cadastrar, listar, editar, deletar ou listar por CPF um cliente

# Documentações

Link da documentação com o desenho do DDD: [Clique aqui para acessar o Miro](https://miro.com/app/board/uXjVKHPTdLg=/?share_link_id=544608334788)
<br>
Após subir a aplicação, para acessar o Swagger [Clique aqui](http://localhost:8080/swagger-ui/index.html)
