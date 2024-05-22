package br.com.fiap.techchallenge.lanchonete.adapters.driven;

import br.com.fiap.techchallenge.lanchonete.core.domain.repositories.IMercadoPago;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MercadoPago implements IMercadoPago {

    @Override
    public Boolean efetuarPagamento(Integer numeroPedido) {
        return true;
    }
}
