# Tech Challenge - API Lanchonete

API resopnsável por gerenciar clientes, produtos e pedidos de uma lanchonete.

# Pré-requisitos
1. Docker
   1. Para instalação [clique aqui](https://www.docker.com/get-started/)
2. Docker compose
   1. Para instalação [clique aqui](https://docs.docker.com/compose/install/)
3. Docker Desktop
   1. Para instalação [clique aqui](https://www.docker.com/products/docker-desktop/)
4. Habilitar o Kubernetes através do menu de configuração do Docker Desktop
   1. Após abrir o Docker Desktop, clique na engrnagem no canto superior direito;
   2. Vá em "Kubernetes"
   3. Habilite o Kubernetes selecionando o check box "Enable Kubernetes"

# Para executar o projeto:
1. Acesse via terminal a pasta do projeto
2. Execute em ordem os comandos abaixo: 
```bash
kubectl apply -f kubernetes/  # você vai configurar o configmap, o hpa e as métricas do cluster
cd kubernetes                 # você vai acessar a pasta com os demais arquivos do cluster kubernetes
kubectl apply -f banco_dados/ # você vai aplicar os arquivos necessários para subir o banco de dados 
kubectl apply -f aplicacao/   # você vai aplicar os arquivos necessários para subir a aplicação
```

#### Após os passos acima, a API estará funcionando e será possível realizar as operações, conforme descrito abaixo.

# Passo a passo funcional da API

### Para um bom funcionamento da API, siga o passo a passo abaixo: 
1. Primeiro, cadastre um produto, utilizando a rota ```POST``` /produto
2. Após, você já está apto para cadastrar um pedido. Capture o id do produto que foi cadastrado e para cadastrar um 
pedido utilize a rota ```POST``` /pedido
3. Para gerar o QR Code para o pagamento do pedido, utilize a rota ```POST``` /pedido/gerar/qr_code/{numeroPedido}, 
informando o número do pedido gerado anteriormente. Vale lembrar que o QR Code gerado é apenas um mock
4. Após, para simular o webhook de notificação do pagamento, utilize a rota `POST` /pedido/webhook/notificacao/pagamento, 
informando o `statusPagamento: PAGO`
5. Para consultar o andamento do pedido, você pode utilizar a rota `GET` /pedido/consultar/status/pagamento/{numeroPedido}, 
informando o número do pedido gerado anteriormente
6. Para listar todos os pedidos, utilize a rota `GET` /pedido/listar

### Além disso, você também poderá:
1. Editar, deletar ou listar por categoria um produto
2. Listar todos os pedidos com status pago
3. Cadastrar, listar, editar, deletar ou listar por CPF um cliente

# Documentações

Link da documentação com o desenho do DDD: [Clique aqui para acessar o Miro](https://miro.com/app/board/uXjVKHPTdLg=/?share_link_id=544608334788)
<br>
Após subir a aplicação, para acessar o Swagger [Clique aqui](http://localhost:8080/swagger-ui/index.html)
