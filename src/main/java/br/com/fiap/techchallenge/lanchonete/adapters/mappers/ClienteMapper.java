package br.com.fiap.techchallenge.lanchonete.adapters.mappers;

import br.com.fiap.techchallenge.lanchonete.dtos.inbound.ClienteDTO;
import br.com.fiap.techchallenge.lanchonete.entities.Cliente;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toEntity(ClienteDTO clienteRequest);

    @Mapping(target = "id", ignore = true)
    ClienteEntity toDbEntity(Cliente cliente);

    Cliente fromDbEntityToEntity(ClienteEntity clienteEntity);
}
