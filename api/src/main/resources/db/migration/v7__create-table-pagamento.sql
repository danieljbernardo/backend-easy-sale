create table pagamento(
    id bigserial primary key unique not null,
    venda_id bigint unique not null,
    tipo_pagamento text not null,

    constraint fk_pagamento_venda foreign key(venda_id) references venda(id)
);