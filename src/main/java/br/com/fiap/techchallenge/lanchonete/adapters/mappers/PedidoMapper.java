package br.com.fiap.techchallenge.lanchonete.adapters.mappers;

import br.com.fiap.techchallenge.lanchonete.dtos.inbound.PedidoDTO;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.WebhookDTO;
import br.com.fiap.techchallenge.lanchonete.dtos.outbound.PedidoResponse;
import br.com.fiap.techchallenge.lanchonete.entities.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.entities.Pedido;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    @Mapping(target = "numero", ignore = true)
    @Mapping(target = "etapaPedido", ignore = true)
    @Mapping(target = "statusPagamento", ignore = true)
    Pedido toEntity(PedidoDTO pedidoRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    PedidoEntity toDbEntity(Pedido pedido);

    @Mapping(source = "id", target = "numero")
    Pedido fromDbEntityToEntity(PedidoEntity pedidoEntity);

    @Mapping(source = "numero", target = "codigo")
    @Mapping(source = "statusPagamento", target = "statusPagamento")
    @Mapping(source = "etapaPedido", target = "etapaPedido")
    PedidoResponse toResponse(Pedido pedido);

    @Mapping(source = "numeroPedido", target = "numero")
    @Mapping(target = "identificacaoCliente", ignore = true)
    @Mapping(target = "produtoId", ignore = true)
    @Mapping(target = "etapaPedido", ignore = true)
    Pedido fromWebhookToPedido(WebhookDTO webhookDTO);

    @Mapping(target = "statusPagamento", expression = "java(statusPagamento.toString())")
    @Mapping(target = "etapaPedido", expression = "java(etapaPedido.toString())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "identificacaoCliente", ignore = true)
    @Mapping(target = "produtoId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    PedidoEntity toStatusPagoAndEtapaPedidoEmPreparacao(StatusPagamento statusPagamento, EtapaPedido etapaPedido);
}
