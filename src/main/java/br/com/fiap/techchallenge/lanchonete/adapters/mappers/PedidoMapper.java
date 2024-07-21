package br.com.fiap.techchallenge.lanchonete.adapters.mappers;

import br.com.fiap.techchallenge.lanchonete.dtos.inbound.PedidoDTO;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.WebhookDTO;
import br.com.fiap.techchallenge.lanchonete.dtos.outbound.PedidoResponse;
import br.com.fiap.techchallenge.lanchonete.entities.Pedido;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    Pedido toEntity(PedidoDTO pedidoRequest);

    PedidoEntity toDbEntity(Pedido pedido);

    @Mapping(source = "id", target = "numero")
    Pedido fromDbEntityToEntity(PedidoEntity pedidoEntity);

    @Mapping(source = "numero", target = "codigo")
    @Mapping(source = "statusPagamento", target = "statusPagamento")
    @Mapping(source = "etapaPedido", target = "etapaPedido")
    PedidoResponse toResponse(Pedido pedido);

    @Mapping(source = "numeroPedido", target = "numero")
    Pedido fromWebhookToPedido(WebhookDTO webhookDTO);
}
