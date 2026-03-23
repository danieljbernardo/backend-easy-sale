create table venda(
    id bigserial primary key unique not null,
    cliente_id bigint not null,
    valor_total decimal not null,
    data_venda date not null,
    constraint fk_venda_cliente foreign key (cliente_id) references cliente (id)
);